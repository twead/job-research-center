package com.szakdolgozat.security.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.szakdolgozat.entity.Role;
import com.szakdolgozat.entity.User;

public class UserPrincipal implements UserDetails {

	private String email;
	private String password;
	private boolean isEnabled;
	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(String email, String password, Collection<? extends GrantedAuthority> authorities,
			boolean isEnabled) {
		super();
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.isEnabled = isEnabled;
	}

	public static UserPrincipal build(User user) {
		List<Role> roleList = new ArrayList<>();
		roleList.add(user.getRole());

		List<GrantedAuthority> authorities = roleList.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());
		return new UserPrincipal(user.getEmail(), user.getPassword(), authorities, user.isEnabled());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
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
		return isEnabled;
	}

}
