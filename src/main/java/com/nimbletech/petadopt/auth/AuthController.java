package com.nimbletech.petadopt.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.nimbletech.petadopt.user.Role;
import com.nimbletech.petadopt.user.User;
import com.nimbletech.petadopt.user.UserApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static com.nimbletech.petadopt.auth.JwtUtil.REFRESH_SECRET_KEY;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserApi userApi;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginData) {
        try {
            String email = loginData.get("email");
            String password = loginData.get("password");

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));

            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            Long userId = userDetails.getId();

            String accessToken = jwtUtil.generateAccessToken(userDetails.getUsername(), userId, userDetails.getAuthorities());
            String refreshToken = jwtUtil.generateRefreshToken(userDetails.getUsername());

            return ResponseEntity.ok(Map.of(
                    "accessToken", accessToken,
                    "refreshToken", refreshToken
            ));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid email or password"));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(@RequestBody Map<String, String> request) {
        try {
            String refreshToken = request.get("refreshToken");
            String username = jwtUtil.extractUsername(refreshToken, REFRESH_SECRET_KEY);

            boolean isValid = jwtUtil.validateRefreshToken(refreshToken, username);
            if (!isValid) {
                return ResponseEntity.status(401).body(Map.of("error", "Invalid refresh token"));
            }

            User user = userApi.findByEmail(username).orElseThrow();
            String newAccessToken = jwtUtil.generateAccessToken(username, user.getId(),
                    userDetailsService.loadUserByUsername(username).getAuthorities());

            return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "Refresh failed"));
        }
    }

    @PostMapping("/google-login")
    public ResponseEntity<Map<String, String>> googleLogin(@RequestBody Map<String, String> body) {
        String idTokenString = body.get("token");

        try {
            GoogleIdToken idToken = verifyGoogleToken(idTokenString);

            if (idToken == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Invalid Google ID token"));
            }

            GoogleIdToken.Payload payload = idToken.getPayload();
            String googleId = payload.getSubject();
            String email = payload.getEmail();
            String name = (String) payload.get("name");

            // Find user by googleId or email
            User user = userApi.findByGoogleId(googleId);
            if (user == null) {
                user = userApi.findByEmail(email).orElse(null);
            }

            if (user == null) {
                // New user - register
                user = new User();
                user.setGoogleId(googleId);
                user.setEmail(email);
                user.setName(name);
                user.setRoles(Set.of(Role.ROLE_USER)); // default role
                userApi.save(user);
            } else if (user.getGoogleId() == null) {
                // Link googleId to existing user by email
                user.setGoogleId(googleId);
                userApi.save(user);
            }

            // Generate BOTH tokens using updated JwtUtil methods
            String accessToken = jwtUtil.generateAccessToken(
                    user.getEmail(),
                    user.getId(),
                    user.getAuthorities()
            );
            String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());

            log.info("Generated tokens for Google userId {}", user.getId());
            return ResponseEntity.ok(Map.of(
                    "accessToken", accessToken,
                    "refreshToken", refreshToken
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to authenticate with Google"));
        }
    }

    private GoogleIdToken verifyGoogleToken(String idTokenString) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList("995686742971-0nb6jhrirbu5a2hofrpfgah39h2gv8rt.apps.googleusercontent.com"))
                .build();

        return verifier.verify(idTokenString);
    }

}
