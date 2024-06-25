package com.example.controller;

import com.example.common.Result;
import com.example.entity.Course;
import com.example.entity.Institution;
import com.example.service.ListService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
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

    @GetMapping("/institution/{id}/report/tree")
    public Result getInstitutionById(@PathVariable Integer id) {
        Institution institution = listService.findById(id);
        if (institution != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("name", institution.getName());
            response.put("full_name", institution.getFull_name());
            response.put("treeData", listService.getTreeDataByInstitutionId(id));
            return Result.success(response);
        } else {
            return Result.error("Institution not found");
        }
}


}
