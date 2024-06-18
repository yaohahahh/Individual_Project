package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Account;
import com.example.entity.Student;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
import org.springframework.stereotype.Service;
import com.example.entity.Admin;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AdminService {
    @Resource
    private AdminMapper adminMapper;

     //login
    public Account login(Account account){
        Account dbAdmin=adminMapper.selectByUsername(account.getUsername());
        if(dbAdmin==null){ //Username doesn't exist.
            throw new CustomException("Wrong Username!");
        }
        if(!dbAdmin.getPassword().equals(account.getPassword())){
            throw new CustomException("Wrong Password!");
        }
        //login success
        return dbAdmin;
    }

    public void register(Account account) {
        Admin admin=new Admin();
        admin.setUsername(account.getUsername());
        admin.setPassword(account.getPassword());
        admin.setRole(account.getRole());

        this.add(admin);
    }

    private void add(Admin admin) {
        Admin dbAdmin=adminMapper.selectByUsername(admin.getUsername());
        if(dbAdmin!=null){
            throw new CustomException("Username Already Exist!");
        }
        //没填名字
        if (ObjectUtil.isEmpty(admin.getName())){
            admin.setName(admin.getUsername());
        }

        adminMapper.insert(admin);
    }

}
