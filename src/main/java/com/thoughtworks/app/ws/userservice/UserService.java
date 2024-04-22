package com.thoughtworks.app.ws.userservice;

import com.thoughtworks.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.thoughtworks.app.ws.ui.model.request.UserDetailsRequestModel;
import com.thoughtworks.app.ws.ui.model.response.UserRest;

public interface UserService {
    UserRest createUser(UserDetailsRequestModel userDetails);
    UserRest updateUser(String userId,UpdateUserDetailsRequestModel userDetails);
    void deleteUser(String userId);
}
