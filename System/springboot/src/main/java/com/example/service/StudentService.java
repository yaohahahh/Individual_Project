package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Account;
import com.example.entity.Student;
import com.example.exception.CustomException;
import com.example.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentService {
    @Resource
    private StudentMapper studentMapper;

    //login
    public Account login(Account account){
        Account dbStudent =studentMapper.selectByUsername(account.getUsername());
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
        Student student = new Student();
        student.setUsername(account.getUsername());
        student.setPassword(account.getPassword());
        student.setRole(account.getRole());

        this.add(student);
    }

    private void add(Student student) {
        Student dbStudent = studentMapper.selectByUsername(student.getUsername());
        if(dbStudent !=null){
            throw new CustomException("Username Already Exist!");
        }
        //没填名字
        if (ObjectUtil.isEmpty(student.getName())){
            student.setName(student.getUsername());
        }

        studentMapper.insert(student);
    }
}
