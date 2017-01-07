package com.easywakee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RequestMapping;
import com.easywakee.entities.User;

@RepositoryRestResource(collectionResourceRel="users", path="users")
public interface UserRepository extends MongoRepository<User, String> {
    public List<User> findByFirstName(@Param("fn")String firstName);
    public List<User> findByLastName(@Param("ln") String lastName);
    public List<User> findByFirstNameAndLastName(@Param("fn") String firstName,@Param("ln") String lastName);
    public User findByEmail(@Param("id") String email);
    public long deleteUserByEmail(@Param("id") String email);
}