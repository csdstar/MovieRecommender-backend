package org.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;
import org.example.backend.entity.UserEntity;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    @Select("SELECT * FROM user_table WHERE username = #{username}")
    Optional<UserEntity> findByUsername(String username);

//    @Insert("INSERT INTO user_table (username, password) VALUES(#{username}, #{password})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    int insert(UserEntity user);

    @Select("SELECT EXISTS(SELECT 1 FROM user_table WHERE username = #{username})")
    boolean existsByUsername(String username);
}
