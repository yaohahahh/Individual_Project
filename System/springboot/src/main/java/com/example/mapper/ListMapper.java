package com.example.mapper;

import com.example.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ListMapper {
    @Select("select * from Sustainability_Data.Institution where name like concat('%', #{name} ,'%') order by id desc")
    List<Institution> selectAll(Institution institution);

    @Select("select * from Sustainability_Data.Institution where id = #{id}")
    Institution selectById(@Param("id") Integer id);

    @Select("SELECT * FROM Sustainability_Data.Institution WHERE LOWER(name) LIKE CONCAT('%', LOWER(#{query}), '%')")
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
            @Result(column = "total_point", property = "total_point"),
            @Result(column = "id", property = "credits",
                    many = @Many(select = "selectCreditsByImpactAreaId"))
    })
    List<Impact_Area> selectImpactAreasByCategoryId(Integer categoryId);

    @Select("SELECT * FROM Sustainability_Data.Credit WHERE impact_area_id = #{impactAreaId}")
    List<Credit> selectCreditsByImpactAreaId(Integer impactAreaId);

    @Insert("insert into Sustainability_Data.Institution (name,full_name,score,rating) values (#{name},#{full_name},#{score},#{rating})")
    void insert(Institution institution);

    @Update("update Sustainability_Data.Institution set name=#{name}, full_name= #{full_name}, score=#{score},rating=#{rating} where id=#{id}")
    void updateById(Institution institution);

    //先删除impact再删category,最后删institution
    @Delete("delete from Sustainability_Data.Institution where id = #{id}")
    void deleteById(Integer id);

}
