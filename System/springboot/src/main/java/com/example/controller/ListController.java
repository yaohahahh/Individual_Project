package com.example.controller;

import com.example.common.Result;
import com.example.entity.Course;
import com.example.entity.Institution;
import com.example.service.ListService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
        return institution != null ? Result.success(institution) : Result.error("Institution not found");
    }

    @GetMapping("/institution/{id}/version/{version}")
    public Result getInstitutionByIdAndVersion(@PathVariable Integer id, @PathVariable Float version) {
        Institution institution = listService.findByIdAndVersion(id, version);
        return institution != null ? Result.success(institution) : Result.error("Institution version not found");
    }


}
