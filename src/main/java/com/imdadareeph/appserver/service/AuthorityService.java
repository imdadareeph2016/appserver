package com.imdadareeph.appserver.service;

import java.util.List;

import com.imdadareeph.appserver.model.Authority;

/**
 * @author imdadareeph
 * @Created on 2018-MAY-02
 */
public interface AuthorityService {
  List<Authority> findById(Long id);

  List<Authority> findByname(String name);

}
