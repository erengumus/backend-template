package com.template.backendtemplate.core.auth;

import com.template.backendtemplate.core.auth.model.LoginRequestDto;
import com.template.backendtemplate.core.auth.model.UserInfoDto;
import com.template.backendtemplate.core.auth.model.UserRegisterDto;
import com.template.backendtemplate.core.messages.BusinessRuleMessages;
import com.template.backendtemplate.core.service.MessageService;
import com.template.backendtemplate.core.service.SessionUserService;
import com.template.backendtemplate.core.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.Locale;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final MessageService messageService;
    private final SessionUserService sessionUserService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDto userRegisterDto) {
        log.debug("Register endpoint called with email: {}", userRegisterDto.getEmail());
        String token = userService.register(userRegisterDto);
        log.info("User registered successfully: {}", userRegisterDto.getEmail());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
        log.debug("Login endpoint called for email: {}", loginRequestDto.getEmail());
        String token = userService.login(loginRequestDto);
        log.info("Login successful for email: {}", loginRequestDto.getEmail());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/user")
    public ResponseEntity<UserInfoDto> getUserInfo(@RequestHeader("Authorization") String authorizationHeader) throws AuthenticationException {
        log.debug("Get user info endpoint called. Authorization header: {}", authorizationHeader);
        String token = authorizationHeader.replace("Bearer ", "");
        String email = jwtUtil.getEmailFromToken(token);
        log.debug("Extracted email from token: {}", email);
        UserInfoDto userInfo = userService.getUserInfo(email);
        log.info("User info retrieved successfully for email: {}", email);
        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/welcome")
    public String getWelcomeMessage() {
        log.debug("Welcome endpoint called.");
        String name = sessionUserService.getUserInfo().getName();
        String message = messageService.getMessage(BusinessRuleMessages.WSH_0001, name);
        log.info("Welcome message retrieved for user: {}", name);
        return message;
    }

    @GetMapping("/checkLocale")
    public String checkLocale(@RequestHeader("Accept-Language") String lang) {
        log.debug("Check locale endpoint called with Accept-Language: {}", lang);
        Locale locale = LocaleContextHolder.getLocale();
        log.debug("Locale from LocaleContextHolder: {}", locale.getLanguage());
        return "Locale: " + locale.getLanguage();
    }
}