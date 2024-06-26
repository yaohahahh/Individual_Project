package com.example.service;

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



}
