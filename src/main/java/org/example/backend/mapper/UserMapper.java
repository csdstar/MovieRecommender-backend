package org.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import java.util.Optional;
import org.example.backend.entity.UserEntity;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    @Select("SELECT * FROM user_table WHERE name = #{username}")
    Optional<UserEntity> findByUsername(String username);

    @Insert("INSERT INTO user_table(name, password, id) " +
            "VALUES(#{username}, #{password}, #{id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(UserEntity user);

    @Select("SELECT EXISTS(SELECT 1 FROM user_table WHERE name = #{username})")
    boolean existsByUsername(String username);
}
