package com.icebergsocialnetwork.services.InterfaceService;

import com.icebergsocialnetwork.model.user.User;
import com.icebergsocialnetwork.services.IGenericService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGenericService<User>, UserDetailsService {
    User findByUsername(String username);

    boolean checkLogin(User user);

    boolean isRegister(User user);

    boolean isCorrectConfirmPassword(User user);

    User findUserByIdAndInfomodifierIsTrue(Long id);

    int lockUser(String username);

    int unlockUser(String username);

}
