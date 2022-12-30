package com.lms.AM.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lms.AM.FeignClient.AuthClient;
import com.lms.AM.Pojo.LoginCredentails;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    AuthClient authClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        log.debug("Start: loadUserByUsername Method.");
        LoginCredentails loginCredentails = authClient.login(username);
        if(loginCredentails == null) {
            log.debug("End: loadUserByUsername Method.");
            throw new UsernameNotFoundException("Username is not found: " + username);
        }else {
            log.debug("End: loadUserByUsername Method.");
            return new User(loginCredentails.getUname(),loginCredentails.getPassword(),new ArrayList<>());
        }
    }
    
}
