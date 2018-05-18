package com.imdadareeph.appserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imdadareeph.appserver.model.Authority;

/**
 * @author imdadareeph
 * @Created on 2018-MAY-02
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
  Authority findByName(String name);
}
