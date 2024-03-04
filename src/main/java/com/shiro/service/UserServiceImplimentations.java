package com.shiro.service;

import com.shiro.Config.JwtProvider;
import com.shiro.model.User;
import com.shiro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplimentations implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email =jwtProvider.getEmailFromJwtToken(jwt);
        User user=findUserByEmail(email);

        return user;

    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user=userRepository.findByEmail(email);

        if(user == null){
            throw new Exception("User not found");
        }

        return user;
    }
}
