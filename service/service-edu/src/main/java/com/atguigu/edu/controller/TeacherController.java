package com.atguigu.edu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.edu.entity.Teacher;
import com.atguigu.edu.entity.vo.TeacherQuery;
import com.atguigu.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.hamcrest.core.IsNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-01-05
 */
@RestController
@RequestMapping(value = "/edu/teacher")
public class TeacherController {

    @Autowired
    public TeacherService teacherService;

    //查询所有讲师
    @GetMapping("/findAll")
    public R findAll() {
        List<Teacher> list = teacherService.list(null);
        return R.ok().data("teachers", list);
    }

    //逻辑删除讲师
    @DeleteMapping("{id}")
    public R removeById(@PathVariable String id) {
        boolean b = teacherService.removeById(id);
        return b?R.ok():R.error();
    }

    //分页查询讲师
    @GetMapping("pageTeacher/{current}/{size}")
    public R pageTeacher(@PathVariable Long current,
                         @PathVariable Long size){
        Page<Teacher> teacherPage = new Page<>(current,size);
        teacherService.page(teacherPage, null);
        long total = teacherPage.getTotal();
        List<Teacher> teachers = teacherPage.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("teachers",teachers);
        return R.ok().data(map);
    }

    //多条件组合分页查询
    @PostMapping("pageTeacherCondition")
    public R pageTeacherCondition(@RequestBody(required = false) TeacherQuery teacherQuery){
        long current = teacherQuery.getCurrent();
        long limit = teacherQuery.getLimit();
        Page<Teacher> teacherPage = new Page<>(current,limit);

        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        Date begin = teacherQuery.getBegin();
        Date end = teacherQuery.getEnd();

        if(!StringUtils.isEmpty(name)){
            wrapper.like("name", name);
        }

        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level", level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create", begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create", end);
        }

        teacherService.page(teacherPage, wrapper);
        long total = teacherPage.getTotal();
        List<Teacher> teachers = teacherPage.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("teachers",teachers);
        return R.ok().data(map);


    }
}

