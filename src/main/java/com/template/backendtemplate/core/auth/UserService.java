package com.template.backendtemplate.core.auth;


import com.template.backendtemplate.core.auth.model.LoginRequestDto;
import com.template.backendtemplate.core.auth.model.UserInfoDto;
import com.template.backendtemplate.core.auth.model.UserRegisterDto;

import javax.naming.AuthenticationException;

public interface UserService {
    String register(UserRegisterDto userRegisterDto);
    String login(LoginRequestDto loginRequestDto);
    UserInfoDto getUserInfo(String email) throws AuthenticationException;
}
