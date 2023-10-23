package com.example.demo.repositories;

import com.example.demo.exceptions.EtAuthException;
import com.example.demo.model.User;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{


    @Override
    public Integer create(String firstName, String lasName, String email, String password) throws EtAuthException {
        return null;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws EtAuthException {
        return null;
    }

    @Override
    public Integer getCountByEmail(String email) {
        return null;
    }

    @Override
    public User findById(Integer userId) {
        return null;
    }
}
