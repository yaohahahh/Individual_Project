package com.example.mapper;

import com.example.entity.Admin;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;

public interface AdminMapper {
    @Select("select * from admin where username = #{username}")
    Admin selectByUsername(String username);

    @Insert("Insert Into Admin (username,password,name,role) values (#{username},#{password},#{name},#{role} )")
    void insert(Admin admin);
}
