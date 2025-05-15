package org.example.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.backend.entity.TestEntity;
import java.util.List;

public interface ITestService extends IService<TestEntity> {
    /** 服务接口，定义了服务层需要实现的方法。
     * 这个接口继承自IService，提供了CRUD等基本操作的默认实现。
     * @return List of TestEntity
     */
    public List<String> getTestEntityList();
}
