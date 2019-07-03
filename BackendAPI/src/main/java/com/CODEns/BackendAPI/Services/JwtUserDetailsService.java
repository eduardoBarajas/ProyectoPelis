package com.CODEns.BackendAPI.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
	private UsersService users_service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.CODEns.BackendAPI.Entities.User current = users_service.findByUsername(username);
		if (current != null) {
			return new User(current.getUsername(), current.getPassword(), AuthorityUtils.createAuthorityList(String.valueOf(current.getAuthority())));
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
    }
}