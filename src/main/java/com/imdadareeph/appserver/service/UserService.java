package com.imdadareeph.appserver.service;

import java.util.List;

import com.imdadareeph.appserver.model.User;
import com.imdadareeph.appserver.model.UserRequest;

/**
 * @author imdadareeph
 * @Created on 2018-MAY-02
 */
public interface UserService {
  void resetCredentials();

  User findById(Long id);

  User findByUsername(String username);

  List<User> findAll();

  User save(UserRequest user);
}
