package org.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.backend.entity.CommentEntity;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<CommentEntity> {

    //查询用户对电影的评论
    @Select("SELECT comment FROM comment_table WHERE user_id = #{userId} AND movie_id = #{movieId}")
    List<String> CommentsByUserIdAndMovieId(Integer userId, Integer movieId);

}
