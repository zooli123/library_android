package com.example.szaboz.sonrisalibrary.factory;

import com.example.szaboz.sonrisalibrary.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by szaboz on 2017.07.05..
 */

public class UserFactory {

    private List<User> users = new ArrayList<>();

    public List<User> getAllUsers(){
        users.add(new User("szaboz@sonrisa.hu", "password"));
        users.add(new User("ebenedek@sonrisa.hu", "password2"));
        users.add(new User("andersen@sonrisa.hu", "password3"));

        return users;
    }
}
