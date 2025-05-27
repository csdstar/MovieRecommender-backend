package org.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.backend.entity.BrowseEntity;

@Mapper
public interface BrowseMapper extends BaseMapper<BrowseEntity> {
    //查询用户是否看过电影
    @Select("SELECT COUNT(*) > 0 FROM browse WHERE user_id = #{userId} AND movie_id = #{movieId}")
    boolean existByUserIdAndMovieId(Integer userId, Integer movieId);
}
