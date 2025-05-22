package org.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
// @Data 是 Lombok 库的一个注解，用于自动生成类的 getter、setter、toString、equals 和 hashCode 方法。
// 这样可以减少编写这些标准方法的代码量，使代码更加简洁。

@TableName("browse_table")
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

public class BrowseEntity {

    @TableField("user_id")
    private Integer userId;

    @TableField("movie_id")
    private Integer movieId;

    @TableField("time")
    private LocalDateTime time;
}
