package com.example.mapper;

import com.example.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface StudentMapper {
    @Select("select * from student where username = #{username}")
    Student selectByUsername(String username);

    @Insert("Insert Into student (username,password,name,role) values (#{username},#{password},#{name},#{role} )")
    void insert(Student student);
}
