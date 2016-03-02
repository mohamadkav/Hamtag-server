package net.hamtag.server.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.datatypes.device.DeviceMgr;
import net.hamtag.server.datatypes.user.User;
import net.hamtag.server.datatypes.user.UserMgr;
import net.hamtag.server.datatypes.user.UserRole;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(final String username) 
		throws UsernameNotFoundException {
	
		User user=UserMgr.getByUsername(username);
		Device device=DeviceMgr.getDeviceByPhoneNumber(username);
		if(user==null&&device==null)
			throw new UsernameNotFoundException("Username not found in DB");
		if(user!=null){
			List<GrantedAuthority> authorities = 
						buildUserAuthority(user.getUserRoles());

		return buildUserForAuthentication(user, authorities);
		}
		else {
			List<GrantedAuthority> setAuths = new ArrayList<GrantedAuthority>();
			setAuths.add(new SimpleGrantedAuthority("ROLE_DEVICE"));
			return new org.springframework.security.core.userdetails.User(device.getPhoneNumber(),device.getPassword(), 
					true, true, true, true, setAuths);
		}
	}

	// Converts User to
	// org.springframework.security.core.userdetails.User
	private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, 
		List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), 
			user.getEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

}