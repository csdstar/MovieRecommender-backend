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
    // id
    @TableField("id")
    private Integer id;

    // 电影名
    @TableField("name")
    private String name;

    // 电影封面链接
    @TableField("picture_link")
    private String pictureLink;

    // 电影评分
    @TableField("score")
    private Float score;

    @TableField("evaluators")
    private Integer evaluators;

    // 电影标签
    @TableField("tag")
    private String tag;

    // 电影排名
    @TableField("ranking")
    private Integer ranking;

    // 上映年份
    @TableField("year")
    private Integer year;

    // 导演
    @TableField("director")
    private String director;

    // 编剧
    @TableField("scriptwriter")
    private String scriptwriter;

    // 主演
    @TableField("actor")
    private String actor;

    // 电影类型
    @TableField("type")
    private String type;

    // 电影地区
    @TableField("area")
    private String area;

    // 语种/语言
    @TableField("language")
    private String language;

    // 上映日期
    @TableField("date")
    private String date;

    // 片长
    @TableField("length")
    private String length;

    // 电影在IMDb的编号
    @TableField("IMDb")
    private String imdb;

    // 标签1
    @TableField("tag1")
    private String tag1;

    // 剧情简介
    @TableField("synopsis")
    private String synopsis;

    // 看过的人数/播放量?
    @TableField("viewed")
    private Integer viewed;

    // 想看人数
    @TableField("wanted")
    private Integer wanted;

    // 去哪看/播放源
    @TableField("WhereToSee")
    private String whereToSee;

    // 豆瓣页面地址
    @TableField("URL")
    private String url;
}
