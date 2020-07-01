package com.deliver.bank.bank.auth.service;

import com.deliver.bank.bank.auth.security.UserSecurity;
import com.deliver.bank.bank.user.entities.User;
import com.deliver.bank.bank.user.entities.enums.UserProfile;
import com.deliver.bank.bank.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private Set<UserProfile> userProfiles = new HashSet<>();

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new UsernameNotFoundException(email);
        userProfiles.add(user.getUserProfile());
        return new UserSecurity(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                userProfiles
        );
    }
}
