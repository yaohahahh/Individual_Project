package com.example.controller;

import com.example.common.Result;
import com.example.entity.Category;
import com.example.entity.Institution;
import com.example.service.ListService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/list")
public class ListController {
    @Resource
    private ListService listService;

    @GetMapping("/search")
    public List<Institution> searchInstitutions(@RequestParam String query) {
        return listService.searchInstitutions(query);
    }

    @GetMapping("/selectAll")
    public List<Institution> selectAll(Institution institution) {
        return listService.selectAll(institution);
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

    //Send request
    @PostMapping("/add")
    //Accepting json data with RequestBody
    public Result add(@RequestBody Institution institution) {
        listService.add(institution);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Institution institution) {
        //Determine whether to update based on id
        listService.updateById(institution);
        return Result.success();
    }

    @PutMapping("/updatePoint")
    public Result updatePoint(@RequestBody Institution institution) {
        listService.updatePoint(institution);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    //Delete by passing the id parameter
    public Result delete(@PathVariable Integer id) {
        listService.deleteById(id);
        return Result.success();
    }
}