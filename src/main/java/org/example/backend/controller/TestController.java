package org.example.backend.controller;

import org.example.backend.exception.ServiceException;
import org.example.backend.result.Result;
import org.example.backend.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 表示此类是控制器类，且会以Json格式返回数据
@RequestMapping("/test") // 调用此类的api路径
public class TestController {
    // service实例，用于数据库操作。通过构造器注入的方式注入，确保依赖的不可变性
    private final TestServiceImpl testService;

    @Autowired
    public TestController(TestServiceImpl testService) {
        this.testService = testService;
    }

    /**
     * 实现了异常处理的controller函数，返回值为标准的response格式
     *
     * @return Result: 格式是code,data,message
     */
    @GetMapping("/service/all")
    public Result<List<String>> getAllNameByService() {
        try {
            List<String> names = testService.getTestEntityList();
            return Result.success(names);
        } catch (ServiceException e) {
            // 处理异常
            return Result.error(Result.ERROR_CODE, e.getMessage());
        }
    }
}
