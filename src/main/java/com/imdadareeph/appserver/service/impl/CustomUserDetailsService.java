package com.imdadareeph.appserver.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.imdadareeph.appserver.model.User;
import com.imdadareeph.appserver.repository.UserRepository;

/**
 * @author imdadareeph
 * @Created on 2018-MAY-02
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return user;
        }
    }

    public void changePassword(String oldPassword, String newPassword) {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();

        if (authenticationManager != null) {
            LOGGER.debug("Re-authenticating user '"+ username + "' for password change request.");

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
        } else {
            LOGGER.debug("No authentication manager set. can't change Password!");

            return;
        }

        LOGGER.debug("Changing password for user '"+ username + "'");

        User user = (User) loadUserByUsername(username);
        
        /*Added by Imdad */
        if(null!=user && user.getAuthorities()!=null && !user.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("ROLE_ADMIN"))){
   		 LOGGER.info("Is User role ROLE_ADMIN? == "+user.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("ROLE_ADMIN"))+"for User Name == "+user.getUsername());
   		 LOGGER.info(user.getUsername()+" has ADMIN role. Currently user with admin role password cange is disabled");
   		return;
        }else{
        	 user.setPassword(passwordEncoder.encode(newPassword));
             userRepository.save(user);
        }
        /*Added by Imdad end*/
        
        /*
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);*/

    }

}
