package com.baobang.piospa.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.baobang.piospa.entities.Staff;
import com.baobang.piospa.repositories.StaffRepository;
/**
  * @author BaoBang
  * @Created Jul 11, 2018
  * 
  */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 

	@Autowired
	StaffRepository mStaffRepository;
 
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Staff appUser = mStaffRepository.findByUsername(userName);
 
        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
 
        System.out.println("Found User: " + appUser);
 
        // [ROLE_USER, ROLE_ADMIN,..]
        List<Integer> roleNames = new ArrayList<>();
        roleNames.add(new Integer(appUser.getIsAdmin()));
 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (int role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role == 1 ? "ROLE_ADMIN" : "ROLE_USER");
                grantList.add(authority);
            }
        }
 
        UserDetails userDetails = (UserDetails) new User(appUser.getAccount(), //
                appUser.getPassword(), grantList);
 
        return userDetails;
    }
 
}