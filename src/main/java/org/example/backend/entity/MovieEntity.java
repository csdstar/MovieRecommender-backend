package org.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
// @Data 是 Lombok 库的一个注解，用于自动生成类的 getter、setter、toString、equals 和 hashCode 方法。
// 这样可以减少编写这些标准方法的代码量，使代码更加简洁。

@TableName("movie_table")
// @TableName 是 MyBatis-Plus 框架的一个注解，用于指定当前类对应的数据库表名。
// 在这个例子中，它指定了当前类对应的数据库表名为 "test_table"。

@Accessors(chain = true)
// @Accessors 是 Lombok 库的一个注解，用于配置生成 getter 和 setter 方法的方式。
// chain = true 表示生成的 setter 方法会返回当前对象，以便进行链式调用。
// 例如：user.setName("Alice").setAge(30);

@JsonSerialize(using= ToStringSerializer.class)
// using= ToStringSerializer.class 表示使用 ToStringSerializer 来序列化当前类的实例。
// ToStringSerializer 会调用对象的 toString() 方法来获取序列化的字符串。
// 这通常用于确保对象在转换为 JSON 时，以特定的字符串形式表示，例如使用对象的 ID 而不是整个对象结构。

public class MovieEntity {
    @TableField("id")
    // @TableField 是 MyBatis-Plus 框架的一个注解，用于指定当前字段对应的数据库字段
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("picture_link")
    private String pictureLink;

    @TableField("score")
    private Float score;

    @TableField("evaluators")
    private Integer evaluators;

    @TableField("tag")
    private String tag;

    @TableField("ranking")
    private Integer ranking;

    @TableField("year")
    private Integer year;

    @TableField("director")
    private String director;

    @TableField("scriptwriter")
    private String scriptwriter;

    @TableField("actor")
    private String actor;

    @TableField("type")
    private String type;

    @TableField("area")
    private String area;

    @TableField("language")
    private String language;

    @TableField("date")
    private String date;

    @TableField("length")
    private String length;

    @TableField("IMDb")
    private String imdb;

    @TableField("tag1")
    private String tag1;

    @TableField("synopsis")
    private String synopsis;

    @TableField("viewed")
    private Integer viewed;

    @TableField("wanted")
    private Integer wanted;

    @TableField("WhereToSee")
    private String whereToSee;

    @TableField("URL")
    private String url;
}
