package org.example.backend.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.dto.movie_details.CastDTO;
import org.example.backend.dto.movie_details.MovieDTO;
import org.example.backend.dto.movie_details.RatingDTO;
import org.example.backend.dto.movie_details.UserStatusDTO;
import org.example.backend.entity.MovieEntity;
import org.example.backend.mapper.BrowseMapper;
import org.example.backend.mapper.CommentMapper;
import org.example.backend.mapper.FavoriteMapper;
import org.example.backend.mapper.MovieMapper;
import org.example.backend.service.MovieDetails_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


@Service
@Slf4j
public class MovieDetails_ServiceImpl extends ServiceImpl<MovieMapper, MovieEntity> implements MovieDetails_Service {

    private final MovieMapper movieMapper;
    private final FavoriteMapper favoriteMapper;
    private final BrowseMapper browseMapper;
    private final CommentMapper commentMapper;

    @Autowired
    public MovieDetails_ServiceImpl(MovieMapper movieMapper, FavoriteMapper favoriteMapper, BrowseMapper browseMapper, CommentMapper commentMapper) {
        this.movieMapper = movieMapper;
        this.favoriteMapper = favoriteMapper;
        this.browseMapper = browseMapper;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<MovieDTO> getMovieDetails(Integer userId) {
        //从数据库中获取所有电影实体
        List<MovieEntity> movieEntities = movieMapper.selectList(null);
        //创建一个新的MovieDTO列表
        List<MovieDTO> movieDTOs = new ArrayList<>();
        //遍历电影实体列表，将每个实体转换为DTO
        for(MovieEntity movieEntity : movieEntities) {
            MovieDTO movieDTO = new MovieDTO();
            //电影id
            movieDTO.setId(movieEntity.getId());
            //电影标题
            movieDTO.setTitle(movieEntity.getName());
            //片源url
            movieDTO.setMovie(movieEntity.getUrl());
            //年份
            movieDTO.setYear(movieEntity.getYear());
            //播放量
            movieDTO.setRuntime(movieEntity.getViewed());
            //rating类
            RatingDTO ratingDTO = new RatingDTO();
                //评分
            ratingDTO.setScore(movieEntity.getScore());
                //收藏量
                // 计算favorite中的该电影id的数量
            ratingDTO.setVotes(favoriteMapper.CountNumByMovieId(movieEntity.getId()));
            movieDTO.setRating(ratingDTO);
            //海报链接
            movieDTO.setPoster(movieEntity.getPictureLink());
            //风格标签
            List<String> genres = new ArrayList<>();
            if(movieEntity.getType() != null){
                genres = Arrays.asList(movieEntity.getType().split("/"));
            }
            movieDTO.setGenres(genres);
            //导演
            List<String> directors = new ArrayList<>();
            if(movieEntity.getDirector() != null){
                directors = Arrays.asList(movieEntity.getDirector().split("/"));
            }
            movieDTO.setDirectors(directors);
            //编剧
            List<String> writers = new ArrayList<>();
            if(movieEntity.getScriptwriter() != null){
                writers = Arrays.asList(movieEntity.getScriptwriter().split("/"));
            }
            movieDTO.setWriters(writers);
            //演员
            List<CastDTO> casts = new ArrayList<>();
            if(movieEntity.getActor() != null){
                String[] actorArray = movieEntity.getActor().split("/");
                for(String actor : actorArray){
                    CastDTO castDTO = new CastDTO();
                    castDTO.setName(actor);
                    casts.add(castDTO);
                }
            }
            movieDTO.setCasts(casts);
            //国家
            List<String> countries = new ArrayList<>();
            if (movieEntity.getArea() != null) {
                countries = Arrays.asList(movieEntity.getArea().split("/"));
            }
            movieDTO.setCountries(countries);
            //语言
            List<String> languages = new ArrayList<>();
            if (movieEntity.getLanguage() != null) {
                languages = Arrays.asList(movieEntity.getLanguage().split("/"));
            }
            movieDTO.setLanguages(languages);
            //发行时间
            movieDTO.setReleaseDate(movieEntity.getDate());
            //简介
            movieDTO.setSummary(movieEntity.getSynopsis());

            //用户相关
            UserStatusDTO userStatusDTO = new UserStatusDTO();
            //是否看过
            userStatusDTO.setWatched(browseMapper.existByUserIdAndMovieId(userId, movieEntity.getId()));
            //是否收藏
            userStatusDTO.setWishlisted(favoriteMapper.existsByUserIdAndMovieId(userId,movieEntity.getId()));
            //用户评分
            float rating = (float) (9 + new Random().nextInt(10) / 10.0);
            userStatusDTO.setRating(rating);
            //用户评论
            userStatusDTO.setComment(commentMapper.CommentsByUserIdAndMovieId(userId,movieEntity.getId()));
            movieDTO.setUserStatus(userStatusDTO);


            movieDTOs.add(movieDTO);
        }

        return movieDTOs;
    }
    @Override
    public List<MovieDTO> searchMovies(String keyword) {
        String kw = "%" + keyword.trim() + "%";
        // 构造多列 OR 的模糊查询
        QueryWrapper<MovieEntity> qw = new QueryWrapper<>();
        qw.like("name", kw)
                .or().like("director", kw)
                .or().like("scriptwriter", kw)
                .or().like("actor", kw)
                .or().like("type", kw)
                .or().like("area", kw);

        // 拿到实体
        List<MovieEntity> es = movieMapper.selectList(qw);

        // 复用你 getMovieDetails 里的那套手工 DTO 映射（省略 UserStatus 部分）
        List<MovieDTO> dtos = new ArrayList<>();
        for (MovieEntity e : es) {
            MovieDTO dto = new MovieDTO();
            dto.setId(e.getId());
            dto.setTitle(e.getName());
            dto.setMovie(e.getUrl());
            dto.setYear(e.getYear());
            dto.setRuntime(e.getViewed());
            RatingDTO rt = new RatingDTO();
            rt.setScore(e.getScore());
            rt.setVotes(favoriteMapper.CountNumByMovieId(e.getId()));
            dto.setRating(rt);
            dto.setPoster(e.getPictureLink());
            dto.setGenres(e.getType() == null ? List.of() : List.of(e.getType().split("/")));
            dto.setDirectors(e.getDirector() == null ? List.of() : List.of(e.getDirector().split("/")));
            dto.setWriters(e.getScriptwriter() == null ? List.of() : List.of(e.getScriptwriter().split("/")));
            List<CastDTO> casts = new ArrayList<>();
            if (e.getActor() != null) for (String a : e.getActor().split("/")) {
                CastDTO c = new CastDTO(); c.setName(a); casts.add(c);
            }
            dto.setCasts(casts);
            dto.setCountries(e.getArea() == null ? List.of() : List.of(e.getArea().split("/")));
            dto.setLanguages(e.getLanguage() == null ? List.of() : List.of(e.getLanguage().split("/")));
            dto.setReleaseDate(e.getDate());
            dto.setSummary(e.getSynopsis());
            dtos.add(dto);
        }
        return dtos;
    }
}
