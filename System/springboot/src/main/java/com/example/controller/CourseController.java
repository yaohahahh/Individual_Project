package com.example.controller;

import com.example.common.Result;
import com.example.entity.Course;
import com.example.service.CourseService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    private CourseService courseService;

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "6") Integer pageSize,
                             Course course ) {
        PageInfo<Course> pageInfo = courseService.selectPage(pageNum, pageSize,course);
        return Result.success(pageInfo);
    }

    //发送请求
    @PostMapping("/add")
    //用RequestBody接受json数据
    public Result add(@RequestBody Course course) {
        courseService.add(course);
        return Result.success();
    }

    @PutMapping("/update")
    //用RequestBody接受json数据
    public Result update(@RequestBody Course course) {
        //根据id判断是否更新
        courseService.updateById(course);
        return Result.success();
    }


    @DeleteMapping("/delete/{id}")
    //通过传id这个参数来删除
    public Result delete(@PathVariable Integer id) {
        courseService.deleteById(id);
        return Result.success();
    }

}
