package com.example.demo.repositories;

import com.example.demo.exceptions.EtAuthException;
import com.example.demo.model.User;

public interface UserRepository {
    Integer create(String firstName,String lasName, String email, String password) throws EtAuthException;

    User findByEmailAndPassword(String email, String password) throws  EtAuthException;

    Integer getCountByEmail(String email);

    User findById(Integer userId);
}
