package org.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("test_table")
@Accessors(chain = true)
@JsonSerialize(using= ToStringSerializer.class)
public class TestEntity {
    private Integer id;
    private String name;
}
