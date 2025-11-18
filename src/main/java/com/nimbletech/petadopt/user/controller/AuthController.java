package com.nimbletech.petadopt.user.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.nimbletech.petadopt.config.CustomUserDetails;
import com.nimbletech.petadopt.jwt.JwtUtil;
import com.nimbletech.petadopt.user.model.Role;
import com.nimbletech.petadopt.user.model.User;
import com.nimbletech.petadopt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        try {
            String email = loginData.get("email");
            String password = loginData.get("password");

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );


            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            Long userId = userDetails.getId();

            String token = jwtUtil.generateToken(userDetails.getUsername(), userId, userDetails.getAuthorities());
            log.info("Generated token {} for userId {}", token, userId);

            return ResponseEntity.ok(Map.of("token", token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid email or password"));
        }
    }

    @PostMapping("/google-login")
    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> body) {
        String idTokenString = body.get("token");
        GoogleIdToken.Payload payload;

        try {
            GoogleIdToken idToken = verifyGoogleToken(idTokenString);

            if (idToken == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid Google ID token"));
            }

            payload = idToken.getPayload();

            String googleId = payload.getSubject(); // Google user ID
            String email = payload.getEmail();
            String name = (String) payload.get("name");

            // Find user by googleId or email
            User user = userRepository.findByGoogleId(googleId);
            if (user == null) {
                user = userRepository.findByEmail(email).orElse(null);
            }

            if (user == null) {
                // New user - register
                user = new User();
                user.setGoogleId(googleId);
                user.setEmail(email);
                user.setName(name);
                user.setRoles(Set.of(Role.ROLE_USER)); // default role
                userRepository.save(user);
            } else if (user.getGoogleId() == null) {
                // Link googleId to existing user by email
                user.setGoogleId(googleId);
                userRepository.save(user);
            }

            // Generate JWT using user info and roles
            String token = jwtUtil.generateToken(user.getEmail(), user.getId(), user.getAuthorities());
            log.info("Generated token {} for userId {}", token, user.getId());
            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Failed to authenticate with Google"));
        }
    }

    private GoogleIdToken verifyGoogleToken(String idTokenString) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList("995686742971-0nb6jhrirbu5a2hofrpfgah39h2gv8rt.apps.googleusercontent.com"))
                .build();

        return verifier.verify(idTokenString);
    }

}
