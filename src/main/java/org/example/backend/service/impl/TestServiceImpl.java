package org.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.entity.TestEntity;
import org.example.backend.exception.ServiceException;
import org.example.backend.mapper.TestMapper;
import org.example.backend.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 服务实体类，实现上层接口ITestService。
 * 这个类负责实现具体的业务逻辑。
 */
@Service // 标注这是一个服务层组件，由Spring容器管理
@Slf4j // 引入日志log，可以用来记录日志
public class TestServiceImpl extends ServiceImpl<TestMapper, TestEntity> implements ITestService {
    // Mapper实例，用于数据库操作。通过构造器注入的方式注入，确保依赖的不可变性
    private final TestMapper testMapper;

    /**
     * 构造器注入TestMapper。
     * 通过这种方式，我们确保了TestServiceImpl在创建时必须传入TestMapper实例，
     * 这样可以避免字段注入可能带来的问题，如循环依赖和依赖不可见性。
     *
     * @param testMapper MyBatis的Mapper接口，用于数据库访问
     */
    @Autowired
    public TestServiceImpl(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    /** 实现了服务接口和异常检测，返回数据库表格中所有行的name值。也可以在这里对其进行一些数据处理
     * @return List of TestEntity
     */
    @Override
    public List<String> getTestEntityList() {
        List<TestEntity> list = testMapper.selectList(null);
        if (list != null && !list.isEmpty()) {
            List<String> nameList = new ArrayList<>();
            for (TestEntity testEntity : list)
                nameList.add(testEntity.getName());
            return nameList;
        } else {
            // 抛出自定义异常
            throw new ServiceException("No TestEntity found");
        }
    }
}
