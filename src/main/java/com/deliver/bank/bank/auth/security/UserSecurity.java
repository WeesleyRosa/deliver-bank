package com.deliver.bank.bank.auth.security;

import com.deliver.bank.bank.person.entities.enums.PersonProfile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
public class UserSecurity implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Getter
    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSecurity(Long id, String email, String password, Set<PersonProfile> profiles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = profiles
                .stream()
                .map(profile -> new SimpleGrantedAuthority(profile.getProfileDescription()))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(PersonProfile profile) {
        return this.getAuthorities().contains(new SimpleGrantedAuthority(profile.getProfileDescription()));
    }
}
