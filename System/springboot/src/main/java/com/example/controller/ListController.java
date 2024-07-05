package com.example.controller;

import com.example.common.Result;
import com.example.entity.Category;
import com.example.entity.Course;
import com.example.entity.Impact_Area;
import com.example.entity.Institution;
import com.example.service.ListService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/list")
public class ListController {
    @Resource
    private ListService listService;

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "6") Integer pageSize,
                             Institution institution) {
        PageInfo<Institution> pageInfo = listService.selectPage(pageNum, pageSize,institution);
        return Result.success(pageInfo);
    }

    @GetMapping("/search")
    public List<Institution> searchInstitutions(@RequestParam String query) {
        return listService.searchInstitutions(query);
    }

    @GetMapping("/choose/{id}")
    public Institution chooseById(@PathVariable Integer id) {
        return listService.findById(id);
    }


    @GetMapping("/institution/{id}")
    public Result getInstitutionById(@PathVariable Integer id) {
        Institution institution = listService.findById(id);
        if (institution == null) {
            return Result.error("Institution not found");
        }

        List<Category> categories = listService.findCategoriesByInstitutionId(institution.getId());
        institution.setCategories(categories);

        return Result.success(institution);

    }

    //发送请求
    @PostMapping("/add")
    //用RequestBody接受json数据
    public Result add(@RequestBody Institution institution) {
        listService.add(institution);
        return Result.success();
    }

    @PutMapping("/update")
    //用RequestBody接受json数据
    public Result update(@RequestBody Institution institution) {
        //根据id判断是否更新
        listService.updateById(institution);
        return Result.success();
    }

    @PutMapping("/update/{id}")
    //用RequestBody接受json数据
    public Result update_report(@RequestBody Institution institution) {
        //根据id判断是否更新
        listService.updateById(institution);
        return Result.success();
    }


    @DeleteMapping("/delete/{id}")
    //通过传id这个参数来删除
    public Result delete(@PathVariable Integer id) {


        listService.deleteById(id);
        return Result.success();
    }
}

