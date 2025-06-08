package org.example.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.backend.dto.movie_details.MovieDTO;
import org.example.backend.entity.MovieEntity;
import java.util.List;

public interface MovieDetails_Service extends IService<MovieEntity> {

    // 获取电影详情
    List<MovieDTO> getMovieDetails(Integer userId);

    List<MovieDTO> searchMovies(String keyword);
}
