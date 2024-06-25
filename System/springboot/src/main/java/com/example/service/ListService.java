package com.example.service;

import com.example.entity.Category;
import com.example.entity.Credit;
import com.example.entity.Impact_Area;
import com.example.entity.Institution;
import com.example.mapper.ListMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ListService {
    @Resource
    private ListMapper listMapper;

    //total是查询的总数,list是数据列表
    //pageNum是当前页码,pageSize是每页个数
    public PageInfo<Institution> selectPage(int pageNum, int pageSize, Institution institution){
        PageHelper.startPage(pageNum,pageSize);
        List<Institution> institutionList = listMapper.selectAll(institution);
        return PageInfo.of(institutionList);
    }
    public Institution findById(Integer id) {
        return listMapper.selectById(id);
    }

    public List<Category> getTreeDataByInstitutionId(Integer institutionId) {
        List<Category> categories = listMapper.selectCategoriesByInstitutionId(institutionId);
        for (Category category : categories) {
            List<Impact_Area> impact_areas = listMapper.selectImpact_AreaByCategoryId(category.getId());
            for (Impact_Area impact_area : impact_areas) {
                List<Credit> credits = listMapper.selectCreditsByImpact_AreaId(impact_area.getId());

                impact_area.setCredits(credits);
            }
            category.setImpact_areas(impact_areas);
        }
        return categories;
    }


}
