package com.example.mapper;

import com.example.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ListMapper {
    @Select("select * from Sustainability_Data.Institution where name like concat('%', #{name} ,'%') order by id desc")
    List<Institution> selectAll(Institution institution);

    @Select("select * from Sustainability_Data.Institution where id = #{id}")
    Institution selectById(@Param("id") Integer id);

    @Select("SELECT * FROM Sustainability_Data.Institution WHERE LOWER(name) LIKE CONCAT('%', LOWER(#{query}), '%') OR LOWER(fullName) LIKE CONCAT('%', LOWER(#{query}), '%')")
    List<Institution> findByNameContainingIgnoreCase(@Param("query") String query);

    @Select("SELECT * FROM Sustainability_Data.Category WHERE institution_id = #{institutionId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "id", property = "impact_areas",
                    many = @Many(select = "selectImpactAreasByCategoryId"))
    })
    List<Category> selectCategoriesByInstitutionId(Integer institutionId);

    @Select("SELECT * FROM Sustainability_Data.Impact_Area WHERE category_id = #{categoryId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "point", property = "point"),
            @Result(column = "total_point", property = "total_point")
    })
    List<Impact_Area> selectImpactAreasByCategoryId(Integer categoryId);


    @Insert("insert into Sustainability_Data.Institution (name,fullName,score,rating,link) values (#{name},#{fullName},#{score},#{rating},#{link})")
    void insert(Institution institution);

    @Update("update Sustainability_Data.Institution set name=#{name}, fullName= #{fullName}, score=#{score},rating=#{rating}, link=#{link} where id=#{id}")
    void updateById(Institution institution);

    @Update("update Sustainability_Data.Impact_Area set point=#{point} where id=#{id}")
    void updateImpactAreaPoint(@Param("id") Integer id, @Param("point") Float point);

    //First delete impact then delete category, and finally delete institution.
    @Delete("delete from Sustainability_Data.Impact_Area where category_id = #{id}")
    void deleteImpactArea(Integer id);
    @Delete("delete from Sustainability_Data.Category where institution_id = #{id}")
    void deleteCategory(Integer id);
    @Delete("delete from Sustainability_Data.Institution where id = #{id}")
    void deleteById(Integer id);

}
