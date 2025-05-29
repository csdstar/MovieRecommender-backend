package org.example.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.LoginReq;
import org.example.backend.entity.UserEntity;
import org.example.backend.dto.RegisterReq;
import org.example.backend.exception.ServiceException;
import org.example.backend.mapper.UserMapper;
import org.example.backend.service.IUserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserMapper userMapper;

    @Override
    public Integer register(RegisterReq req) {
        // 检查用户名是否存在
        if (userMapper.existsByUsername(req.getUsername())) {
            throw new ServiceException("用户名已存在");
        }

        // 创建用户对象
        UserEntity user = new UserEntity();
        user.setUsername(req.getUsername());
        user.setPassword(req.getPassword());


        // 插入数据库
        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public String login(LoginReq req) {
        UserEntity user = userMapper.findByUsername(req.getUsername())
                .orElseThrow(() -> new ServiceException("用户不存在"));

        if (!user.getPassword().equals(req.getPassword())) {
            throw new ServiceException("密码错误");
        }

        return "登录成功";
    }
}
