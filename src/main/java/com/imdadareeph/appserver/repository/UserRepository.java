package com.imdadareeph.appserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imdadareeph.appserver.model.User;

/**
 * @author imdadareeph
 * @Created on 2018-MAY-02
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername( String username );
}

