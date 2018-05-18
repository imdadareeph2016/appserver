package com.imdadareeph.appserver.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imdadareeph.appserver.model.Authority;
import com.imdadareeph.appserver.repository.AuthorityRepository;
import com.imdadareeph.appserver.service.AuthorityService;

/**
 * @author imdadareeph
 * @Created on 2018-MAY-02
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

  @Autowired
  private AuthorityRepository authorityRepository;

  @Override
  public List<Authority> findById(Long id) {
    // TODO Auto-generated method stub

    Authority auth = this.authorityRepository.findOne(id);
    List<Authority> auths = new ArrayList<>();
    auths.add(auth);
    return auths;
  }

  @Override
  public List<Authority> findByname(String name) {
    // TODO Auto-generated method stub
    Authority auth = this.authorityRepository.findByName(name);
    List<Authority> auths = new ArrayList<>();
    auths.add(auth);
    return auths;
  }

}
