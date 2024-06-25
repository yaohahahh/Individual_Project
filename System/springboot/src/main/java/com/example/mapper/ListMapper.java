package com.example.mapper;

import com.example.entity.Category;
import com.example.entity.Credit;
import com.example.entity.Impact_Area;
import com.example.entity.Institution;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ListMapper {
    @Select("select * from Sustainability_Data.Institution where name like concat('%', #{name} ,'%') order by id desc")
    List<Institution> selectAll(Institution institution);

    @Select("select * from Sustainability_Data.Institution where id = #{id}")
    Institution selectById(Integer id);

    @Select("select * from Sustainability_Data.Category where institution_id = #{institutionId}")
    List<Category> selectCategoriesByInstitutionId(Integer institutionId);

    @Select("select * from Sustainability_Data.Impact_Area where category_id = #{categoryId}")
    List<Impact_Area> selectImpact_AreaByCategoryId(Integer categoryId);

    @Select("select * from Sustainability_Data.Credit where impact_area_id = #{impact_areaId}")
    List<Credit> selectCreditsByImpact_AreaId(Integer impact_areaId);

}
