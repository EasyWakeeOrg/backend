package com.easywakee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.easywakee.entities.User;

@RepositoryRestResource(collectionResourceRel="users", path="users")
public interface UserRepository extends MongoRepository<User, String> {
    public User findByEmail(@Param("id") String email);
    public long deleteUserByEmail(@Param("id") String email);
}