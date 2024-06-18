package com.example.service;

import com.example.entity.Course;
import com.example.mapper.CourseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseService {
    @Resource
    private CourseMapper courseMapper;

    //total是查询的总数,list是数据列表
    //pageNum是当前页码,pageSize是每页个数
    public PageInfo<Course> selectPage(int pageNum, int pageSize, Course course){
        PageHelper.startPage(pageNum,pageSize);
        List<Course> courseList = courseMapper.selectAll(course);
        return PageInfo.of(courseList);
    }

    //新增数据
    public void add(Course course) {
        courseMapper.insert(course);
    }

    public void updateById(Course course) {
        courseMapper.updateById(course);
    }

    public void deleteById(Integer id) {
        courseMapper.deleteById(id);
    }
}
