package com.mdp.safe.client.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class CommonUserDetails implements UserDetails {
	 
	private static final long serialVersionUID = 1L;
	String username;
	String password;
	boolean isAccountNonExpired=true;
	boolean isAccountNonLocked=true;
	boolean isEnabled=true;
	boolean isCredentialsNonExpired=true;
	Collection<? extends GrantedAuthority> authorities;
	
	Date createTime;
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	User user;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		
		return isEnabled;
	}
	

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}
	
	public static CommonUserDetails fromUser(User user){
		CommonUserDetails userDetails=new CommonUserDetails();
		userDetails.setUsername(user.getUserid());
		userDetails.setPassword(user.getPassword());
		userDetails.setUser(user);
		userDetails.setCreateTime(new Date());
		return userDetails;
		 
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
