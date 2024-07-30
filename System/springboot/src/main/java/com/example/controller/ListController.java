package com.example.controller;

import com.example.common.Result;
import com.example.entity.Category;
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

    @GetMapping("/selectAll")
    public List<Institution> selectAll(Institution institution) {
        return listService.selectAll(institution);
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

    @PutMapping("/updatePoint")
    public Result updatePoint(@RequestBody Institution institution) {
        listService.updatePoint(institution);
        return Result.success();
    }


    @DeleteMapping("/delete/{id}")
    //通过传id这个参数来删除
    public Result delete(@PathVariable Integer id) {
        listService.deleteById(id);
        return Result.success();
    }
}

