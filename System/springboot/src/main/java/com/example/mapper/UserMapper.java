package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from student where username = #{username}")
    User selectByUsername(String username);

    @Insert("Insert Into student (username,password,name,role) values (#{username},#{password},#{name},#{role} )")
    void insert(User user);
}
