package com.thoughtworks.app.ws.userservice;

import com.thoughtworks.app.ws.ui.model.request.UserDetailsRequestModel;
import com.thoughtworks.app.ws.ui.model.response.UserRest;

public interface UserService {
    UserRest createUser(UserDetailsRequestModel userDetails);
}
