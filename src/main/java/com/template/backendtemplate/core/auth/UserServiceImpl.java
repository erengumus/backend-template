package com.template.backendtemplate.core.auth;

import com.template.backendtemplate.core.auth.entity.*;
import com.template.backendtemplate.core.auth.mapper.UserMapper;
import com.template.backendtemplate.core.auth.model.LoginRequestDto;
import com.template.backendtemplate.core.auth.model.enums.RoleType;
import com.template.backendtemplate.core.auth.model.UserInfoDto;
import com.template.backendtemplate.core.auth.model.UserRegisterDto;
import com.template.backendtemplate.core.auth.repository.RoleRepository;
import com.template.backendtemplate.core.auth.repository.UserRepository;
import com.template.backendtemplate.core.auth.repository.UserRolesRepository;
import com.template.backendtemplate.core.messages.BusinessRuleMessages;
import com.template.backendtemplate.core.service.MessageService;
import com.template.backendtemplate.core.service.SessionUserService;
import com.template.backendtemplate.core.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final MessageService messageService;
    private final SessionUserService sessionUserService;
    private final RoleRepository roleRepository;
    private final UserRolesRepository userRolesRepository;
    private final UserMapper userMapper;

    @Override
    public String register(UserRegisterDto userRegisterDto) {
        log.debug("Register method called with email: {}", userRegisterDto.getEmail());
        Optional<UserEntity> existingUser = userRepository.findByEmail(userRegisterDto.getEmail());
        if (existingUser.isPresent()) {
            log.warn("Attempt to register an already existing email: {}", userRegisterDto.getEmail());
            throw new IllegalArgumentException(messageService.getMessage(BusinessRuleMessages.ERR_0002));
        }
        UserEntity user =userMapper.toEntity(userRegisterDto);
        String encodedPassword =  passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        log.info("User successfully registered with email: {}", user.getEmail());

        assignRoleToUser(user, RoleType.USER);
        log.debug("Default role 'USER' assigned to email: {}", user.getEmail());

        return messageService.getMessage(BusinessRuleMessages.WSH_0002);
    }

    public void assignRoleToUser(UserEntity user, RoleType roleType) {
        log.debug("Assigning role '{}' to user with email: {}", roleType, user.getEmail());
        String roleName = roleType.name();
        RoleEntity role = roleRepository.findByName(roleName)
                .orElseThrow(() -> {
                    log.error("Role not found: {}", roleName);
                    return new IllegalArgumentException(messageService.getMessage(BusinessRuleMessages.ERR_0004));
                });
        UserRolesEntity userRole = new UserRolesEntity();
        userRole.setUser(user);
        userRole.setRole(role);

        userRolesRepository.save(userRole);
        log.info("Role '{}' successfully assigned to user with email: {}", roleType, user.getEmail());
    }

    @Override
    public String login(LoginRequestDto loginRequestDto) {
        log.debug("Login method called for email: {}", loginRequestDto.getEmail());
        UserEntity user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> {
                    log.warn("Login attempt with invalid email: {}", loginRequestDto.getEmail());
                    return new IllegalArgumentException(messageService.getMessage(BusinessRuleMessages.ERR_0002));
                });
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            log.warn("Invalid password attempt for email: {}", loginRequestDto.getEmail());
            throw new IllegalArgumentException(messageService.getMessage(BusinessRuleMessages.ERR_0002));
        }
        List<String> roles = userRolesRepository.findAllByUserId(user.getId())
                .stream()
                .map(userRole -> userRole.getRole().getName())
                .toList();
        log.debug("Roles fetched for email {}: {}", loginRequestDto.getEmail(), roles);


        String token = jwtUtil.generateAccessToken(loginRequestDto.getEmail(), roles);
        log.info("Login successful for email: {}", loginRequestDto.getEmail());

        return token;
    }

    @Override
    public UserInfoDto getUserInfo(String email) throws AuthenticationException {
        log.debug("Fetching user info for email: {}", email);
        UserInfoDto userInfoDto = sessionUserService.getUserInfo();

        if (userInfoDto != null) {
            log.debug("User info found for email: {}", email);
            return userInfoDto;
        }

        log.error("Authentication failed for email: {}", email);
        throw new AuthenticationException(messageService.getMessage(BusinessRuleMessages.ERR_0003));
    }
}
