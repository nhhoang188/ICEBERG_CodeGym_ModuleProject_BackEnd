package com.icebergsocialnetwork.services.user.Impl;

import com.icebergsocialnetwork.model.user.Role;
import com.icebergsocialnetwork.model.user.User;
import com.icebergsocialnetwork.model.user.UserPrinciple;
import com.icebergsocialnetwork.repository.user.UserRepository;
import com.icebergsocialnetwork.services.friend.IFriendReques;
import com.icebergsocialnetwork.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.List;
import java.util.Set;

@Component
@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleService roleService;
    @Autowired
    IFriendReques friendReques;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getAvatar() == null) {
            user.setAvatar("https://firebasestorage.googleapis.com/v0/b/fir-upload-file-337b5.appspot.com/o/RoomsImages%2F1616726926965?alt=media&token=a763f7d3-43ec-4858-a292-eadec094e477");
        } else {
            user.setAvatar(user.getAvatar());
        }
        if (user.getFullname() == null){
            user.setFullname(user.getUsername());
        } else {
            user.setFullname(user.getFullname());
        }
        if (user.getImgcover() == null) {
            user.setImgcover("https://firebasestorage.googleapis.com/v0/b/fir-upload-file-337b5.appspot.com/o/RoomsImages%2F1616726914439?alt=media&token=dafe0595-f166-436d-857e-1389cb755b3c");
        } else {
            user.setImgcover(user.getImgcover());
        }
        if (user.getRoles() == null) {
            Role role = roleService.findByName("ROLE_USER");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            return UserPrinciple.build(user.get());
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            return userRepository.findByUsername(username).get();
        }
        return null;
    }

    @Override
    public boolean checkLogin(User user) {
        return false;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public List<User> getFriends(String username) {
        return userRepository.getFriends(username);
    }

    @Override
    public boolean isRegister(User user) {
        boolean isRegister = false;
        Iterable<User> users = this.findAll();
        for (User currentUser : users) {
            if (user.getUsername().equals(currentUser.getUsername()) || user.getEmail().equals(currentUser.getEmail())) {
                isRegister = true;
                break;
            }
        }
        return isRegister;
    }

    @Override
    public boolean isCorrectConfirmPassword(User user) {
        return false;
    }

    @Override
    public User findUserByIdAndInfomodifierIsTrue(Long id) {
        User user = userRepository.findUserById(id);
        User user1 = new User();
        user1.setAvatar(user.getAvatar());
        user1.setFullname(user.getFullname());
        user1.setImgcover(user.getImgcover());
        user1.setDescription(user.getDescription());
        return user1;
    }

    @Override
    public int lockUser(String username) {
        return userRepository.lockUser(username);
    }

    @Override
    public int unlockUser(String username) {
        return userRepository.unlockUser(username);
    }

    @Override
    public List<User> userYouMayKnow(Long id) {
        User user = findById(id);
        Iterable<User> userList = findAll();
        List<User> userList1 = new ArrayList<>();
        for (User u : userList) {
            boolean checkfr = friendReques.checkFriendNative2(user.getId(), u.getId()) == null;
            if (checkfr && !(user.getId().equals(u.getId()))){
                userList1.add(u);
            }
        }
        return userList1;
    }

    @Override
    public List<User> findAllByFullnameContaining(String fullname){
        return userRepository.findAllByFullnameContaining(fullname);
    }

}
