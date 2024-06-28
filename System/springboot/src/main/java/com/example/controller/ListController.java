package com.example.controller;

import com.example.common.Result;
import com.example.entity.Category;
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
}

