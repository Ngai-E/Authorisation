package com.ngai.auth.security;

import com.ngai.auth.Utils.Parameters;
import com.ngai.auth.model.entity.TUsers;
import com.ngai.auth.model.repository.TUsersRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    TUsersRespository respository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<TUsers> optUser = respository.findByUsername(username);

        if (optUser.isEmpty()) return null;

        User user = new User(username, optUser.get(0).getPassword(), new ArrayList<>());
        user.setEnabled(Parameters.USER_STATUS.active.name().equals(optUser.get(0).getStatus()));
        user.setName(optUser.get(0).getName());
        user.setTel(optUser.get(0).getPhone());
        return user;
    }
}
