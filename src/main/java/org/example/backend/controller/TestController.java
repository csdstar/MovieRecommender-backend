package org.example.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.backend.entity.TestEntity;
import org.example.backend.exception.ServiceException;
import org.example.backend.mapper.TestMapper;
import org.example.backend.result.Result;
import org.example.backend.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 表示此类是控制器类，且会以Json格式返回数据
@RequestMapping("/test") // 调用此类的api路径
public class TestController {
    // Mapper实例，用于数据库操作。通过构造器注入的方式注入，确保依赖的不可变性
    private final TestMapper testMapper;
    private final TestServiceImpl testService;

    @Autowired
    public TestController(TestMapper testMapper, TestServiceImpl testService) {
        this.testMapper = testMapper;
        this.testService = testService;
    }

    @GetMapping("/all") // 调用此接口的路径，GET请求调用
    public List<TestEntity> getAll() {
        //由于mapper继承自BaseMapper类，它包含许多mybatis做好的功能。
        // 简单的增删改查都不需要我们自己写SQL语句。
        List<TestEntity> list = testMapper.selectList(null);

        // 在终端打印调试信息
        for (TestEntity t : list)
            System.out.println("这一行id是" + t.getId() + " name是" + t.getName());
        // 成功的话在浏览器访问对应网址应当也能看到数据库信息
        // localhost:8080/test/all
        return list;
    }

    @GetMapping("/id_name")  // 另一个接口，从路径中获取id和name并进行查询
    public List<TestEntity> getByIdAndName(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name
    ) {
        // 同样的，完全不用自己写SQL，直接用mybatis实现的功能即可完成简单查询

        // 用id查询一行
        TestEntity entityById = testMapper.selectById(id);

        // 再用name查询一行
        // 由于现在不是用主键（id)进行查询，mybatis没有直接可用的函数了
        // 我们需要手动构建一个查询条件，创建一个查询条件变量q
        QueryWrapper<TestEntity> q = new QueryWrapper<>();

        // 使用 QueryWrapper 的 lambda 表达式方式来指定查询条件。
        // 我们查询时的每一行都会自动被转为实体类对象
        // 这里指定查询条件为：查询到的行被转为实体类后，其name字段与我们传入的参数name相等。
        q.lambda().eq(TestEntity::getName, name);

        // 通过上面构建的查询条件q进行查询
        TestEntity entityByName = testMapper.selectOne(q);

        // 打印调试信息并返回查到的两行
        List<TestEntity> list = List.of(entityById, entityByName);
        for (TestEntity t : list)
            System.out.println("这一行id是" + t.getId() + " name是" + t.getName());

        // 成功的话在浏览器访问对应网址应当也能看到数据库信息
        // 示例链接：http://localhost:8080/test/id_name?id=1&name=%E4%BA%8C%E5%B8%85%E5%93%A5
        // 注意这里是在本地数据库查，你的数据库跟我不一样，所以照抄我的name查不出来的
        return list;
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
