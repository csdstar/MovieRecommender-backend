package org.example.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.LoginReq;
import org.example.backend.entity.UserEntity;
import org.example.backend.dto.RegisterReq;
import org.example.backend.exception.ServiceException;
import org.example.backend.mapper.UserMapper;
import org.example.backend.service.IUserService;
import org.example.backend.utils.jwt.JwtUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;

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
        // 根据用户名查找用户
        UserEntity user = userMapper.findByUsername(req.getUsername())
                .orElseThrow(() -> new ServiceException("用户不存在"));

        // 再验证用户密码
        if (!user.getPassword().equals(req.getPassword())) {
            throw new ServiceException("密码错误");
        }

        //用该用户id新建token并返回
        return jwtUtils.generateToken(user.getId());
    }
}
