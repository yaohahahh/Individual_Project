package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Account;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    //login
    public Account login(Account account){
        Account dbStudent = userMapper.selectByUsername(account.getUsername());
        if(dbStudent ==null){ //Username doesn't exist.
            throw new CustomException("Wrong Username!");
        }
        if(!dbStudent.getPassword().equals(account.getPassword())){
            throw new CustomException("Wrong Password!");
        }
        //login success
        return dbStudent;
    }

    public void register(Account account) {
        User user = new User();
        user.setUsername(account.getUsername());
        user.setPassword(account.getPassword());
        user.setRole(account.getRole());

        this.add(user);
    }

    private void add(User user) {
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if(dbUser !=null){
            throw new CustomException("Username Already Exist!");
        }
        //没填名字
        if (ObjectUtil.isEmpty(user.getName())){
            user.setName(user.getUsername());
        }

        userMapper.insert(user);
    }
}
