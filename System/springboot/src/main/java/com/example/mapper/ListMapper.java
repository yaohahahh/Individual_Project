package com.example.mapper;

import com.example.entity.Institution;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ListMapper {
    @Select("select * from Sustainability_Data.Institution where name like concat('%', #{name} ,'%') order by id desc")
    List<Institution> selectAll(Institution institution);

    @Select("select * from Sustainability_Data.Institution where id = #{id}")
    Institution selectById(Integer id);

    @Select("select * from Sustainability_Data.Institution where id = #{id} and version = #{version}")
    Institution selectByIdAndVersion(Integer id, Float version);
}
