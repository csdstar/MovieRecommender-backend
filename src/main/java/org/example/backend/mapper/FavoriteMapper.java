package org.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.backend.entity.FavoriteEntity;


//mapper充当java代码与数据库之间的桥梁，负责定义数据库操作的方法。
@Mapper
public interface FavoriteMapper extends BaseMapper<FavoriteEntity> {
    //定义一个方法来统计某个电影的收藏数量
    @Select("SELECT COUNT(*) FROM favorite_table WHERE movie_id = #{movieId}")
    Integer CountNumByMovieId(Integer movieId);
    //两种实现函数的方式，一种是使用xml文件来隔离sql语句和代码，另一种是用@Select注解来实现函数。


    //查询用户是否收藏过这部电影
    @Select("SELECT COUNT(*) > 0 FROM favorite_table WHERE user_id = #{userId} AND movie_id = #{movieId}")
    boolean existsByUserIdAndMovieId(Integer userId, Integer movieId);
}
