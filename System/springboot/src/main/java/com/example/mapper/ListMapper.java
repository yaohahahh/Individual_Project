package com.example.mapper;

import com.example.entity.Category;
import com.example.entity.Credit;
import com.example.entity.Impact_Area;
import com.example.entity.Institution;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ListMapper {
    @Select("select * from Sustainability_Data.Institution where name like concat('%', #{name} ,'%') order by id desc")
    List<Institution> selectAll(Institution institution);

    @Select("select * from Sustainability_Data.Institution where id = #{id}")
    Institution selectById(Integer id);

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
            @Result(column = "id", property = "credits",
                    many = @Many(select = "selectCreditsByImpactAreaId"))
    })
    List<Impact_Area> selectImpactAreasByCategoryId(Integer categoryId);

    @Select("SELECT * FROM Sustainability_Data.Credit WHERE impact_area_id = #{impactAreaId}")
    List<Credit> selectCreditsByImpactAreaId(Integer impactAreaId);

}
