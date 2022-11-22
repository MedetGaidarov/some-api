package com.example.backendjavaapijob.ui.controller.auth;



import com.example.backendjavaapijob.domain.user.model.User;
import com.example.backendjavaapijob.infrastructure.utils.JwtTokenUtil;
import com.example.backendjavaapijob.ui.dto.auth.AuthRequestDto;
import com.example.backendjavaapijob.ui.dto.auth.AuthResponseDto;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;



    @PostMapping("login")
    public ResponseEntity<Object> authenticate(@RequestHeader("x-profile-type") String type,
                                               @RequestBody AuthRequestDto authRequestDto) {
        try {
            logger.info("Profile type {}, email {}, password {}",
                    type, authRequestDto.getUsername(), authRequestDto.getPassword());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            type,
                            new User(authRequestDto.getUsername(), authRequestDto.getPassword())
                    )
            );

            String principal = (String) authentication.getPrincipal();
            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtTokenUtil.generateAccessToken(principal, authentication.getAuthorities())
                    )
                    .body(new AuthResponseDto(jwtTokenUtil.generateRefreshToken(principal)));
        } catch (AuthenticationException ex) {
            logger.error("Authentication exception: " + ex.getMessage());
            JSONObject response = new JSONObject();
            response.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (Exception e) {
            logger.error("Error in LOGIN: {}", e.getMessage());
            JSONObject response = new JSONObject();
            response.put("message", "Server corrupted by Chaos! We call an Inquisition to handle problems!.");
            return ResponseEntity.internalServerError().body(response);
        }
    }

}
