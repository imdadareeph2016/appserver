package com.imdadareeph.appserver.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.imdadareeph.appserver.model.Authority;
import com.imdadareeph.appserver.model.User;
import com.imdadareeph.appserver.model.UserRequest;
import com.imdadareeph.appserver.repository.UserRepository;
import com.imdadareeph.appserver.service.AuthorityService;
import com.imdadareeph.appserver.service.UserService;

/**
 * @author imdadareeph
 * @Created on 2018-MAY-02
 */
@Service
public class UserServiceImpl implements UserService {
  protected final Log LOGGER = LogFactory.getLog(getClass());

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthorityService authService;

  public void resetCredentials() {
    List<User> users = userRepository.findAll();
    for (User user : users) {
    	if(null!=user && user.getAuthorities()!=null && !user.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("ROLE_ADMIN"))){
    		 LOGGER.info("Is User role ROLE_ADMIN? == "+user.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("ROLE_ADMIN"))+"for User Name == "+user.getUsername());
    		 //LOGGER.info("User Name == "+user.getUsername());
    		// LOGGER.info("User Name == "+user.getUsername());
    		 user.setPassword(passwordEncoder.encode("123"));
    	     userRepository.save(user);
    	}
    	
    	/*
    	 if(null!=user && user.getAuthorities()!=null && !user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
    		 LOGGER.info("User role == "+user.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("ROLE_ADMIN")));
    		 LOGGER.info("User role == "+user.getUsername());
    		 user.setPassword(passwordEncoder.encode("123"));
    	     userRepository.save(user);
    	} 
    	*/
     
    }
  }

  @Override
  // @PreAuthorize("hasRole('USER')")
  public User findByUsername(String username) throws UsernameNotFoundException {
    User u = userRepository.findByUsername(username);
    return u;
  }

  @PreAuthorize("hasRole('ADMIN')")
  public User findById(Long id) throws AccessDeniedException {
    User u = userRepository.findOne(id);
    return u;
  }

  @PreAuthorize("hasRole('ADMIN')")
  public List<User> findAll() throws AccessDeniedException {
    List<User> result = userRepository.findAll();
    return result;
  }

  @Override
  public User save(UserRequest userRequest) {
    User user = new User();
    user.setUsername(userRequest.getUsername());
    user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
    user.setFirstname(userRequest.getFirstname());
    user.setLastname(userRequest.getLastname());
    List<Authority> auth = authService.findByname("ROLE_USER");
    user.setAuthorities(auth);
    this.userRepository.save(user);
    return user;
  }

}
