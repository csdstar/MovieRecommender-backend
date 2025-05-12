package org.example.backend.controller;

import org.example.backend.entity.TestEntity;
import org.example.backend.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestMapper testMapper;

    @GetMapping("/all")
    public List<TestEntity> getAll() {
        List<TestEntity> list = testMapper.selectList(null);
        list.forEach(System.out::println); // 打印调试
        return list;
    }
}
