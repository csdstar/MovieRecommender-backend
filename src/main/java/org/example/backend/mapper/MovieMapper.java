package org.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.example.backend.dto.movie_details.MovieDTO;
import org.example.backend.dto.movie_details.RatingDTO;
import org.example.backend.entity.MovieEntity;

import java.util.List;

@Mapper
public interface MovieMapper extends BaseMapper<MovieEntity> {
    @Select({
            "SELECT",
            "  m.id,",
            "  m.name        AS title,",
            "  m.picture_link         AS movie,",
            "  m.year,",
            "  m.viewed      AS runtime,",
            "  m.score,",
            "  (SELECT COUNT(*) FROM favorite f WHERE f.movie_id = m.id) AS votes,",
            "  m.picture_link AS poster,",
            "  m.release_date,",
            "  m.synopsis     AS summary",
            "FROM movies m",
            "WHERE m.name LIKE #{kw}"
    })
    @Results(id = "MovieResultMap", value = {
            // 基本字段
            @Result(column = "id",        property = "id",       id = true),
            @Result(column = "title",     property = "title"),
            @Result(column = "movie",     property = "movie"),
            @Result(column = "year",      property = "year"),
            @Result(column = "runtime",   property = "runtime"),
            @Result(column = "poster",    property = "poster"),
            @Result(column = "release_date", property = "releaseDate"),
            @Result(column = "summary",   property = "summary"),
            @Result(property = "genres",   javaType = List.class,
                    column = "id",
                    many = @Many(select = "org.example.backend.mapper.GenreMapper.findByMovieId")),
            @Result(property = "directors",javaType = List.class,
                    column = "id",
                    many = @Many(select = "org.example.backend.mapper.DirectorMapper.findByMovieId")),
            @Result(property = "writers",  javaType = List.class,
                    column = "id",
                    many = @Many(select = "org.example.backend.mapper.WriterMapper.findByMovieId")),
            @Result(property = "casts",    javaType = List.class,
                    column = "id",
                    many = @Many(select = "org.example.backend.mapper.CastMapper.findByMovieId")),
            @Result(property = "countries",javaType = List.class,
                    column = "id",
                    many = @Many(select = "org.example.backend.mapper.CountryMapper.findByMovieId")),
            @Result(property = "languages",javaType = List.class,
                    column = "id",
                    many = @Many(select = "org.example.backend.mapper.LanguageMapper.findByMovieId"))
    })
    List<MovieDTO> searchByKeyword(@Param("kw") String kw);
}
