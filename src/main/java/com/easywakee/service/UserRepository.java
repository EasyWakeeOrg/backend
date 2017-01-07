package com.easywakee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RequestMapping;


@RepositoryRestResource(collectionResourceRel="users", path="users")
public interface UserRepository extends MongoRepository<String, String> {
    public List<String> findByFirstName(@Param("fn")String firstName);
    public List<String> findByLastName(@Param("ln") String lastName);
    public List<String> findByFirstNameAndLastName(@Param("fn") String firstName,@Param("ln") String lastName);
    public String findByEmail(@Param("id") String email);
    public long deleteUserByEmail(@Param("id") String email);
}