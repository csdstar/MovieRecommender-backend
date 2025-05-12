# 软件工程基础课程
## 电影推荐项目
### 后端部分
​		启动测试前请先在本地设置好MySQL数据库。由于配置文件中设置的数据库url是：
`jdbc:mysql://127.0.0.1:3306/movie`

​		请务必保证数据库是MySQL数据库，设置在3306端口，并在本地数据库创建架构movie。对应的sql指令如下：

```sql
CREATE DATABASE movie;
```

​		在创建数据库架构movie之后，请进入数据库movie，并在movie数据库中建立与本人测试格式相同的表格结构，以防实体类(entity包下的`TestEntity`)识别出错。sql指令如下：

```sql
create table if not exists test_table
(
    id   int auto_increment
        primary key,
    name varchar(20) not null
)
    comment '这是一个用来测试数据库的表';
```

​		在创建了相同格式的表后，可以自由在表中增添行以进行测试。

​		若有intellij IDEA专业版，应当可以打开数据库插件并看到如下样式：

![image-20250512215426902](assets/image-20250512215426902.png)

​		初始化的代码中仅实现了两个个简易的controller查询，用来打印该表中的所有数据。如果你前面操作都成功了，在浏览器打开对应网址应该能看到数据库信息。

```http
http://localhost:8080/test/all

http://localhost:8080/test/id_name?id=1&name=%E4%BA%8C%E5%B8%85%E5%93%A5
```

