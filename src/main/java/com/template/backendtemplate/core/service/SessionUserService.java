package com.template.backendtemplate.core.service;

import com.template.backendtemplate.core.auth.entity.UserEntity;
import com.template.backendtemplate.core.auth.model.UserInfoDto;
import com.template.backendtemplate.core.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionUserService {

    private final UserRepository userRepository;

    /**
     * Retrieves the email from the session to authenticate the user.
     * @return The user's email address.
     */
    public String getEmailFromSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getPrincipal().toString();
        }
        return null;
    }

    /**
     * Retrieves userId from the session by using authenticated email
     * @return userId
     */
    public Long getUserIdFromSession() {
        String email = getEmailFromSession();
        if (email != null) {
            return findUserIdByEmail(email);
        }
        return null;
    }

    /**
     * Returns both the username and email information.
     * @return A UserInfoDto object containing the user's information.
     */
    public UserInfoDto getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Optional<UserEntity> optionalUser = userRepository.findByEmail(authentication.getPrincipal().toString());
            if (optionalUser.isPresent()){
                UserEntity user = optionalUser.get();
                UserInfoDto userInfoDto = new UserInfoDto();
                userInfoDto.setEmail(user.getEmail());
                userInfoDto.setName(user.getName());
                userInfoDto.setSurname(user.getSurname());
                userInfoDto.setUsername(user.getUsername());
                return userInfoDto;
            }

        }
        return null;
    }

    /**
     * Finds the user ID by email.
     * @param email The email address of the user.
     * @return The user ID, or null if no user is found.
     */
    private Long findUserIdByEmail(String email) {
         Optional<UserEntity> user = userRepository.findByEmail(email);
         return user.map(UserEntity::getId).orElse(null);
    }
}
