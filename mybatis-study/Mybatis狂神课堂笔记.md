# Mybatis

环境：

- JDK1.8

- Mysql5.7
- maven3.6.1
- IDEA

回顾：

- JDBC

- Mysql
- Java基础
- Maven
- junit



SSM框架：都是有配置文件的， 学习的最好方式是看官网文档。

# 1.简介

## 1.1获取Mybatis

1.Maven仓库：

```xml
<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.6</version>
</dependency>

```

2.Github: 

3.Mybatis中文文档：https://mybatis.org/mybatis-3/zh/index.html

##  1.2持久层

数据持久化

- 持久化就是将程序的 数据在持久状态和瞬时状态转化的过程

- 内存：**断电即失**

- 数据库（JDBC）、IO文件持久化

为什么要持久化

- 有一些对象不能让他丢掉
- 内存太贵

## 1.3持久层

Dao层、Service层、Controller层

- 完成持久化工作的代码块
- 层界限十分明显

## 1.4为什么需要Mybatis

- 帮助程序员将数据存入数据库中

- 方便
- 传统的JDBC代码太复杂。简化。框架。自动化。
- 不用Mybatis也可以。只是学了之后更容易上手。**技术没有高低之分**



# 2.第一个Mybatis程序

思路：搭建环境-->导入Mybatis-->编写代码-->测试

## 2.1搭建环境

搭建数据库

```sql
CREATE DATABASE IF NOT EXISTS `mybatis`;

USE `mybatis`;

CREATE TABLE IF NOT EXISTS `user`(
    `id` INT(20) NOT NULL PRIMARY KEY,
    `name` VARCHAR(30) DEFAULT NULL,
    `pwd` VARCHAR(30) DEFAULT NULL
)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `user`(`id`,`name`,`pwd`) VALUES(1,"狂神","123456"),(2,"张三","123456"),(3,"李斯","3456")

```

新建项目

1. 新建一个项目

2. 删除src目录（父工程）

3. 导入maven依赖

   ```xml
       <dependencies>
   <!--        mysql驱动、mybatis、junit-->
           <dependency>
               <groupId>mysql</groupId>
               <artifactId>mysql-connector-java</artifactId>
               <version>5.1.47</version>
           </dependency>
           <dependency>
               <groupId>org.mybatis</groupId>
               <artifactId>mybatis</artifactId>
               <version>3.5.6</version>
           </dependency>
           <dependency>
               <groupId>junit</groupId>
               <artifactId>junit</artifactId>
               <version>4.12</version>
               <scope>test</scope>
           </dependency>
       </dependencies>
   ```

   

## 2.2、创建一个模块

- 编写mybatis的核心配置文件

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <!--configuration核心配置文件-->
  <configuration>
      <environments default="development">
          <environment id="development">
              <transactionManager type="JDBC"/>
              <dataSource type="POOLED">
                  <property name="driver" value="com.mysql.jdbc.Driver"/>
                  <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
                  <property name="username" value="root"/>
                  <property name="password" value="123456"/>
              </dataSource>
          </environment>
      </environments>
  
  </configuration>
  
  ```

  

- 编写mybatis工具类

  ```java
  package com.kuang.utils;
  
  import org.apache.ibatis.io.Resources;
  import org.apache.ibatis.session.SqlSession;
  import org.apache.ibatis.session.SqlSessionFactory;
  import org.apache.ibatis.session.SqlSessionFactoryBuilder;
  
  import java.io.IOException;
  import java.io.InputStream;
  
  //用来获取sqlSessionFactory
  public class MybatisUtils {
      private static  SqlSessionFactory sqlSessionFactory;
      static{
          try{
              //使用Mybatis第一步，获取SqlSessionFactory对象
              String resource = "mybatis-config.xml";
              InputStream inputStream = Resources.getResourceAsStream(resource);
              SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
      //既然有了 SqlSessionFactory，顾名思义，我们可以从中获得 SqlSession 的实例
      // 。SqlSession 提供了在数据库执行 SQL 命令所需的所有方法
      public static SqlSession getSqlSession(){
          return sqlSessionFactory.openSession();
      }
  }
  
  ```

  

## 2.3编写代码

- 实体类

  ```java
  package com.kuang.pojo;
  
  //实体类
  public class User {
      private int id;
      private String name;
      private String pwd;
  
      public User() {
      }
  
      public User(int id, String name, String pwd) {
          this.id = id;
          this.name = name;
          this.pwd = pwd;
      }
  
      public int getId() {
          return id;
      }
  
      public void setId(int id) {
          this.id = id;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public String getPwd() {
          return pwd;
      }
  
      public void setPwd(String pwd) {
          this.pwd = pwd;
      }
  
      @Override
      public String toString() {
          return "User{" +
                  "id=" + id +
                  ", name='" + name + '\'' +
                  ", pwd='" + pwd + '\'' +
                  '}';
      }
  }
  
  ```

  

- Dao接口

  ```java
  package com.kuang.dao;
  public interface UserDao {
      List<User> getUserList();
  }
  
  ```

  

- 接口实现类由原来的UserDaoImpl转变为一个配置文件。

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <!--namespace=绑定一个对用的Dao(Mapper)接口-->
  <mapper namespace="com.kuang.dao.UserDao">
  <!--    select查询语句-->
      <select id="getUserList" resultType="com.kuang.pojo.User">
          select * from mybatis.user
      </select>
  </mapper>
  
  ```

  

## 2.4测试

- 错误一

  ```java
  Caused by: java.io.IOException: Could not find resource com/kuang/dao/UserMapper.xml
  ```

  造成这个原因是UserMapper.xml资源文件我们放在了src/main/java文件夹下，但是maven中约定的是放在src/main/resources文件夹下，系统就找不到这个xml文件了，解决方法就是在maven配置文件中增加指定资源文件位置的标签。

  ```xml
  <build>
          <resources>
              <resource>
                  <directory>src/main/resources</directory>
                  <includes>
                      <include>**/*.properties</include>
                      <include>**/*.xml</include>
                  </includes>
                  <filtering>true</filtering>
              </resource>
              <resource>
                  <directory>src/main/java</directory>
                  <includes>
                      <include>**/*.properties</include>
                      <include>**/*.xml</include>
                  </includes>
                  <filtering>true</filtering>
              </resource>
          </resources>
      </build>
  ```

- 错误二

  ```java
  org.apache.ibatis.binding.BindingException: Type interface com.kuang.dao.UserDao is not known to the MapperRegistry.
  ```

  UserMapper.xm未注册到mybatis核心配置文件中，导致绑定失败，解决方法就是在核心配置文件中导入UserMapper.xml:

  ```xml
  <mappers>
      <mapper resource="com/kuang/dao/UserMapper.xml"/>
  </mappers>
  ```

  上述代码就告诉框架UserMapper是一个mapper

- 错误三

  ```java
  Caused by: com.sun.org.apache.xerces.internal.impl.io.MalformedByteSequenceException: 1 字节的 UTF-8 序列的字节 1 无效。
  ```

  这个错误是编码问题导致的，需要把配置文件的UTF-8改成UTF8(包括核心配置文件和mapper.xml文件):

  ```xml
  <?xml version="1.0" encoding="UTF8" ?>
  ```



# 3.CRUD

## 1.namespace

namespace中的包名要和Dao/Mapper接口的包名一致

## 2.select

选择、查询语句

- id：就是对应的namespace中的方法名
- resultType: Sql语句执行的返回值
- parameterType: 参数类型

## 3.insert

## 4.update

## 5.delete

- 注意点：增删改是需要提交事务，不然不起作用

## 6.分析错误

- 标签不要匹配错

- resource绑定mapper，需要使用路径'/'，不用'.'

## 7.万能Map

假如，我们的实体类，或者shu'ju'k中的表，字段或者参数过多，我们应当考虑Map！

接口代码：

```java
//万能的Map
    int addUser2(Map<String,Object> map);

```

测试代码：

```java

Map<String,Object> map = new HashMap<String,Object>();
        map.put("userid",6);
        map.put("username","hello1");
        map.put("password","55555");
        mapper.addUser2(map);
```

Mapper配置文件：

```xml
<insert id="addUser2" parameterType="map">
        insert into mybatis.user (id,name ,pwd) values (#{userid},#{username},#{password})
    </insert>
```

map传递参数，直接在sql中取出key即可

对象传递参数，直接在sql取对象的属性即可

只有一个基本类型参数的情况下，可以在sql中取到

多个参数用Map，或者**注解**

## 8.思考题

模糊查询怎么写？

1. java代码执行时，使用通配符% %

   ```java
   List<User> userLike = mapper.getUserLike("%周%");
   ```

   

2. 在sql拼接中使用通配符

   ```sql
   select * from mybatis.user where name like #{value}
   ```

   

# 4、配置解析

## 1、核心配置文件

- mybatis-config.xml(可以取别的名字)

- Mybatis的配置文件包含了会深深影响Mybatis行为的设置和属性信息

  ```xml
  configuration（配置）
  properties（属性）
  settings（设置）
  typeAliases（类型别名）
  typeHandlers（类型处理器）
  objectFactory（对象工厂）
  plugins（插件）
  environments（环境配置）
  environment（环境变量）
  transactionManager（事务管理器）
  dataSource（数据源）
  databaseIdProvider（数据库厂商标识）
  mappers（映射器）
  ```

  

## 2、环境配置（environments）

 MyBatis 可以配置成适应多种环境 

 **不过要记住：尽管可以配置多个环境，但每个 SqlSessionFactory 实例只能选择一种环境。** 

Mybatis 默认的事务管理器是JDBC；数据源连接池是POOLED

## 3、属性（properties）

我们可以通过properties属性来实现引用配置文件

 这些属性可以在外部进行配置，并可以进行动态替换。你既可以在典型的 Java 属性文件中配置这些属性，也可以在 properties 元素的子元素中设置 

![1611990981692](C:\Users\FlameZ\AppData\Roaming\Typora\typora-user-images\1611990981692.png)

编写一个配置文件：db.properties

```java
dirver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8
username=root
password=123456

```

在核心文件中引入：

```xml
<properties resource="dp.properties">
        <property name="password" value="111111"/>
    </properties>
```

- 可以直接引入外部文件
- 可以在其中增加一些属性配置
- 如果两个文件有同一个字段，优先使用外部配置文件的，也就是db.properties文件中的

## 4、类型别名（typeAliases）

-  类型别名可为 Java 类型设置一个缩写名字，如使用user代替com.kuang.pojo.User

- 它仅用于 XML 配置，意在降低冗余的全限定类名书写 

  ```xml
  <!--    可以给实体类起别名-->
      <typeAliases>
          <typeAlias type="com.kuang.pojo.User" alias="User"/>
      </typeAliases>
  ```

   也可以指定一个包名，MyBatis 会在包名下面搜索需要的 Java Bean

  扫描实体类的包，他的默认别名就为这个类的 类名 首字母小写

  ```xml
  <typeAliases>
  <!--        <typeAlias type="com.kuang.pojo.User" alias="User"/>-->
          <package name="com.kuang.pojo"/>
      </typeAliases>
  ```

  在实体类很少的时候，使用第一种；反之，使用第二种；第一种可以DIY别名，第二种则不行，如果需要该，需要在实体类上增加注解@Alias("别名")

  ```java
  @Alias("haha")
  public class User {
      ......
  }
  ```

  

## 5、设置（settings）

## 6、映射器（mappers）

方式一：

```xml
<!--每一个Mapper.xml都要在核心配置文件中注册！-->
<mappers>
        <mapper resource="com/kuang/dao/UserMapper.xml"/>
    </mappers>
```

方式二：使用class文件绑定注册

```xml
<!--每一个Mapper.xml都要在核心配置文件中注册！-->
<mappers>
        <mapper class="com/kuang/dao/UserMapper"/>
    </mappers>
```

注意点：

- 接口和他的Mapper配置文件必须同名
- 接口和他的Mapper配置文件必须在同一个文件夹下

方式三：使用扫描表包进行注入绑定（它会自动扫描包里面的Mapper）

```xml
<mappers>
        <package name="com.kuang.dao"/>
    </mappers>
```

## 7.生命周期和作用域

 生命周期和作用域是至关重要的，因为错误的使用会导致非常严重的并发问题 

SqlSessionFactoryBuilder

- 一旦创建了SqlSessionFactory，就不再需要它了
- 局部变量

SqlSessionFactory

- 可以想象为数据库连接池
- SqlSessionFactory一旦被创建就应该在应用的运行期间一致存在，**没有任何理由丢弃它或者创建另一个实例**
- 应用作用域，可以使用单例模式和静态单例模式

SqlSession

- 可以想象为连接到连接池的一个请求
- SqlSession不是线程安全的，不能被共享，所以他的最佳作用域是请求或方法作用域
- 用完之后需要赶紧关闭，否则资源被占用

# 5、解决数据库和实体类属性名不一致问题

## 1.问题

数据库中的字段：

![1612170503773](C:\Users\FlameZ\AppData\Roaming\Typora\typora-user-images\1612170503773.png)

新建一个项目，拷贝之前的，测试实体类字段不一致的情况：

```java
public class User {
    private int id;
    private String name;
    //与数据库中的字段不一致
    private String password;
}
```

测试出现问题：

![1614249471871](C:\Users\FlameZ\AppData\Roaming\Typora\typora-user-images\1614249471871.png)

数据库中是pwd字段，而实体类是password字段，对应不上。

解决方法一：改sql（起别名）

```
select id="getUserById" resultType="haha">
        select id,name,pwd as password from mybatis.user where id = #{id}
    </select>
```

解决方法二：resultMap

## 2.resultMap

结果集映射

```
id name pwd
id name password
```

```xml
<!--haha是实体类User的别名-->
<resultMap id="UM" type="haha">
<!--        column是数据库中的字段，preperty是实体类的属性名-->
        <result column="pwd" property="password"/>
        <result column="id" property="id"/>
        <result column="name" property="name"/>
    </resultMap>
    <select id="getUserById" resultMap="UM">
        select * from mybatis.user where id = #{id}
    </select>
```

-  `resultMap` 元素是 MyBatis 中最重要最强大的元素 ， 它可以让你从 90% 的 JDBC `ResultSets` 数据提取代码中解放出来 。

-  ResultMap 的设计思想是，对简单的语句做到零配置，对于复杂一点的语句，只需要描述语句之间的关系就行了。 

# 6.日志

## 6.1 日志工厂

如果数据库操作出现了异常，我们需要排错，日志就是最好的助手！

曾经：sout ，debug

现在：日志工厂！

![1614251720081](C:\Users\FlameZ\AppData\Roaming\Typora\typora-user-images\1614251720081.png)

 SLF4J 、LOG4J  和 STDOUT_LOGGING（标准日志输出） 比较常用，需要掌握

mybatis中具体使用哪一个日志实现，在设置中设定！

```xml
<settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>
```

![1614252307658](C:\Users\FlameZ\AppData\Roaming\Typora\typora-user-images\1614252307658.png)

```xml
    <settings>
        <setting name="logImpl" value="LOG4J"/>
    </settings>
```

LOG4J不像STDOUT_LOGGING，它需要导入包。

## 6.2 LOG4J

1.先导入LOG4J的包

```xml
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

2. LOG4J.properties   配置文件

```properties
### 配置根 ###
log4j.rootLogger = debug,console ,fileAppender,dailyRollingFile,ROLLING_FILE,MAIL,DATABASE

### 设置输出sql的级别，其中logger后面的内容全部为jar包中所包含的包名 ###
log4j.logger.org.apache=dubug
log4j.logger.java.sql.Connection=dubug
log4j.logger.java.sql.Statement=dubug
log4j.logger.java.sql.PreparedStatement=dubug
log4j.logger.java.sql.ResultSet=dubug
### 配置输出到控制台 ###
log4j.appender.console = org.apache.log4j.ConsoleAppender
log4j.appender.console.Target = System.out
log4j.appender.console.layout = org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern =  %d{ABSOLUTE} %5p %c{ 1 }:%L - %m%n

### 配置输出到文件 ###
log4j.appender.fileAppender = org.apache.log4j.FileAppender
log4j.appender.fileAppender.File = logs/log.log
log4j.appender.fileAppender.Append = true
log4j.appender.fileAppender.Threshold = DEBUG
log4j.appender.fileAppender.layout = org.apache.log4j.PatternLayout
log4j.appender.fileAppender.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ] - [ %p ]  %m%n
#
#### 配置输出到文件，并且每天都创建一个文件 ###
#log4j.appender.dailyRollingFile = org.apache.log4j.DailyRollingFileAppender
#log4j.appender.dailyRollingFile.File = logs/log.log
#log4j.appender.dailyRollingFile.Append = true
#log4j.appender.dailyRollingFile.Threshold = DEBUG
#log4j.appender.dailyRollingFile.layout = org.apache.log4j.PatternLayout
#log4j.appender.dailyRollingFile.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ] - [ %p ]  %m%n### 配置输出到文件，且大小到达指定尺寸的时候产生一个新的文件 ###log4j.appender.ROLLING_FILE=org.apache.log4j.RollingFileAppender log4j.appender.ROLLING_FILE.Threshold=ERROR log4j.appender.ROLLING_FILE.File=rolling.log log4j.appender.ROLLING_FILE.Append=true log4j.appender.ROLLING_FILE.MaxFileSize=10KB log4j.appender.ROLLING_FILE.MaxBackupIndex=1 log4j.appender.ROLLING_FILE.layout=org.apache.log4j.PatternLayout log4j.appender.ROLLING_FILE.layout.ConversionPattern=[framework] %d - %c -%-4r [%t] %-5p %c %x - %m%n
#
#### 配置输出到邮件 ###
#log4j.appender.MAIL=org.apache.log4j.net.SMTPAppender
#log4j.appender.MAIL.Threshold=FATAL
#log4j.appender.MAIL.BufferSize=10
#log4j.appender.MAIL.From=chenyl@yeqiangwei.com
#log4j.appender.MAIL.SMTPHost=mail.hollycrm.com
#log4j.appender.MAIL.Subject=Log4J Message
#log4j.appender.MAIL.To=chenyl@yeqiangwei.com
#log4j.appender.MAIL.layout=org.apache.log4j.PatternLayout
#log4j.appender.MAIL.layout.ConversionPattern=[framework] %d - %c -%-4r [%t] %-5p %c %x - %m%n
#
#### 配置输出到数据库 ###
#log4j.appender.DATABASE=org.apache.log4j.jdbc.JDBCAppender
#log4j.appender.DATABASE.URL=jdbc:mysql://localhost:3306/test
#log4j.appender.DATABASE.driver=com.mysql.jdbc.Driver
#log4j.appender.DATABASE.user=root
#log4j.appender.DATABASE.password=
#log4j.appender.DATABASE.sql=INSERT INTO LOG4J (Message) VALUES ('[framework] %d - %c -%-4r [%t] %-5p %c %x - %m%n')
#log4j.appender.DATABASE.layout=org.apache.log4j.PatternLayout
#log4j.appender.DATABASE.layout.ConversionPattern=[framework] %d - %c -%-4r [%t] %-5p %c %x - %m%n
#log4j.appender.A1=org.apache.log4j.DailyRollingFileAppender
#log4j.appender.A1.File=SampleMessages.log4j
#log4j.appender.A1.DatePattern=yyyyMMdd-HH'.log4j'
#log4j.appender.A1.layout=org.apache.log4j.xml.XMLLayout

```

3. 配置log4j为日志的实现：

   ```xml
       <settings>
           <setting name="logImpl" value="LOG4J"/>
       </settings>
   ```

4. log4j的使用

   1. 在要使用log4j的类中导包

   ```java
   import org.apache.log4j.Logger;
   ```

​          2.日志参数，参数为当前类的class

       ```java
static Logger logger = Logger.getLogger(MyTest.class);
       ```

​         3. 日志级别, 运行即可打印出日志（根据配置文件输出到控制台或文件）。

```java
public void testLog4j(){
        logger.info("log4j的info日志");
        logger.debug("log4j的debug日志");
        logger.error("log4j的error日志");
    }
```



# 7.分页

## 7.1使用limit实现分页：

```sql
select * from user limit startIndex,pageSize;
select * from user limit n;  #获取第1~n条记录
```

**使用mybatis实现分页，核心sql**（重点）

- 接口

  ```java
  List<User> getUsersByLimit(Map<String, Integer> map);
  ```

  

- Mapper.xml

  ```xml
  <select id="getUsersByLimit" parameterType="map" resultMap="UM">
          select * from mybatis.user limit #{startIndex},#{pageSize}
      </select>
  ```

  接口中传入Map，所以xml张使用parameterType配置来配置map

- 测试

## 7.2 使用RowBounds实现分页

不再使用sql实现分页，sql语句是一次性查出所有数据的形式

1.接口

````java
List<User> getUsersByRowBounds();
````

2.mapper.xml

```xml
<select id="getUsersByRowBounds" resultMap="UM">
select * from mybatis.user
    </select>
```

3.测试

```java
       SqlSession sqlSession = MybatisUtils.getSqlSession();
        RowBounds rowBounds = new RowBounds(1, 2);

        //通过java代码层面实现分页
        List<User> users = sqlSession.selectList("com.kuang.dao.UserMapper.getUsersByRowBounds",null,rowBounds);
        for (User user:users){
            System.out.println(user);
        }

        sqlSession.close(); 
```

## 7.3插件实现分页

pageHelper等

了解即可



# 8. 注解开发

## 8.1使用注解

1.注解在接口上实现

```java
@Select("select * from user")
    List<User> getUsers();
```

2.需要在核心配置文件中绑定接口

```xml
<mappers>
        <mapper class="com.kuang.dao.UserMapper"/>
    </mappers>
```

3.测试



本质：反射机制实现

底层：动态代理!

## 8.2 原理（面试前看，重要）

## 8.3 CRUD

我们可以在工具类创建的时候自动提交事务！

```java
public static SqlSession getSqlSession(){
        //这个openSession()可以有参数，true代表自动提交事务
        return sqlSessionFactory.openSession(true);
    }
```

编写接口，增加注解

```java
public interface UserMapper {
    @Select("select * from user")
    List<User> getUsers();

    //方法中存在一个或者多个参数，那么所有参数前必须加@Param()注解
    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") int id);

    @Insert("insert into user(id,name,pwd) values(#{id},#{name},#{password})")
    int addUser(User user);

    @Update("update user set name=#{name},pwd=#{password} where id=#{id}")
    int updateUser(User user);
}
```

测试类

- 注意，必须把接口注册到核心配置文件中。

**关于@Param()注解**

- 基本类型和String类型的参数需要加上
- 引用类型不需要加
- 如果只有一个基本类型的话，可以忽略，但是建议大家都加上！
- 我们在SQL语句中引用的就是我们这里的@Param(“uid”)中设定的属性名

# 9. lombok

是一个构建工具，插件

1.idea安装lombok插件，其部分注解如下：

```java
tures
@Getter and @Setter
@FieldNameConstants
@ToString
@EqualsAndHashCode
@AllArgsConstructor, @RequiredArgsConstructor and @NoArgsConstructor
@Log, @Log4j, @Log4j2, @Slf4j, @XSlf4j, @CommonsLog, @JBossLog, @Flogger, @CustomLog
@Data
@Builder
@SuperBuilder
@Singular
@Delegate
@Value
@Accessors
@Wither
@With
@SneakyThrows
@val
@var
@UtilityClass
```

@Data: 生成无参构造，getter，setter，toString，hashCode和equals

@AllArgsConstructor：生成有参构造

2.导入lombok的maven依赖

```xml
dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.10</version>
            <scope>provided</scope>
        </dependency>
```



3.改造实体类(加注解)

```java
//实体类
@Data   //lombok插件的注解，自动生成属性的getter、setter、toString等方法
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    //与数据库中的字段不一致
    private String password;
}

```



# 10. 多对一的处理

 场景：一个老师教多个学生，一个学生有个确定的老师

sql:

```sql
use mybatis;
drop table if exists `teacher` ;
create table `teacher`(
    `id` int(10) not null ,
    `name` varchar(30) default null,
    primary key (`id`)
)engine=innodb default charset =utf8;

insert into `teacher` values ("1","潘老师");

drop table if exists `student`;
create table `student`(
    `id` int(10) not null ,
    `name` varchar(30) default null,
    `tid` int(10) default null,
    primary key (`id`),
    key `fktid` (`tid`),
    constraint `fktid` foreign key (`tid`) references `teacher`(`id`)
)engine=innodb default charset =utf8;

insert into `student`(`id`,`name`,`tid`) values("1","小红","1");
insert into `student`(`id`,`name`,`tid`) values("2","消化","1");
insert into `student`(`id`,`name`,`tid`) values("3","校花","1");
insert into `student`(`id`,`name`,`tid`) values("4","小明","1");
insert into `student`(`id`,`name`,`tid`) values("5","小狗","1");

```

## 测试环境搭建

1.导入lombok

2.新建实体类Studeng、Teacher

3.建立Mapper接口

4.建立Mapper.xml文件

5.在核心配置文件中绑定注册所有Mapper.xml文件

6.测试查询是否成功



## 按照查询嵌套处理

```xml
<select id="getStudents1" resultMap="ST">
        select * from student
    </select>
    <resultMap id="ST" type="Student">
        <result property="id" column="id"/>
        <result property="name" column="name"/>
        <association property="teacher" column="tid" javaType="Teacher" select="getTeacher"/>
    </resultMap>
    <select id="getTeacher" resultType="Teacher">
        select * from teacher where id=#{tid}
    </select>
```





## 按照结果嵌套处理

```xml
    <select id="getStudents" resultMap="StuTea">
        select s.id sid, s.name sname, t.id tid, t.name tname
        from student s,teacher t
        where s.tid=t.id
    </select>
    <resultMap id="StuTea" type="Student">
        <result property="id" column="sid"/>
        <result property="name" column="sname"/>
        <association property="teacher" javaType="Teacher">
            <result property="id" column="tid"/>
            <result property="name" column="tname"/>
        </association>
    </resultMap>
```

回顾Mysql多对一查询方式：

- 子查询：按照结果嵌套处理
- 联表查询：按照查询嵌套处理

# 11.一对多处理

比如，一个老师对应多个学生，对于老师而言，学生就是多的关系

1.环境搭建

学生实体类

```java
@Data
public class Student {
    private int id;
    private String name;

    private int tid;
}
```

老师实体类

```java
@Data
public class Teacher {
    private int id;
    private String name;
    //一个老师对应拥有多个学生
    private List<Student> students;
}
```

1.按照结果嵌套查询

```xml
    <select id="getTeacher" resultMap="TS">
        select s.id sid,s.name sname, t.id tid, t.name tname from student s, teacher t
        where s.tid=t.id and t.id=#{tid}
    </select>
    <resultMap id="TS" type="Teacher">
        <result property="id" column="tid"/>
        <result property="name" column="tname"/>
        <collection property="students" ofType="Student">
            <result property="id" column="sid"/>
            <result property="name" column="sname"/>
            <result property="tid" column="tid"/>
        </collection>
    </resultMap>
```

2.按照查询嵌套处理

```xml
<select id="getTeacher1" resultMap="TS1">
        select * from teacher where id=#{tid}
    </select>
    <resultMap id="TS1" type="Teacher">
        <result property="id" column="id"/>
        <result property="name" column="name"/>
        <collection property="students" javaType="ArrayList" ofType="Student" select="getStudentByTid" column="id"/>
    </resultMap>

    <select id="getStudentByTid" resultType="Student">
        select * from student where tid=#{tid}
    </select>
```

## 小结

1.关联-association 【多对一】

2.集合-collection  【一对多】

3.javaType和ofType

- javaType用来指定实体类中属性的类型
- ofType用来指定映射到List或者集合中的pojo类型，即泛型中的约束类型

注意点：

- 保证SQL的可读性，尽量保证通俗易懂
- 注意一对多和多对一中，属性名和字段名的问题
- 如果问题不好排查错误，可以使用日志，建议使用Log4J



# 面试高频：

- Mysql引擎
- InnoDB底层原理
- 索引
- 索引优化

# 12.动态sql

什么是动态sql？动态sql就是根据不同得条件生成不同的SQL语句。

利用动态SQL可以彻底摆脱不同情况下不同sql条件的痛苦

## 12.1搭建环境

```sql
drop table if exists `blog`;
create table `blog`(
    `id` int(50) not null comment '博客id',
    `title` varchar(100) not null comment '博客标题',
    `author` varchar(30) not null comment '博客作者',
    `create_time` datetime not null comment '创建时间',
    `views` int(30) not null comment '浏览量'
)engine=InnoDB default charset=utf8;

```

创建一个基础工程

1、导包

2、编写配置文件

3、添加工具类

### **UUID**: 

**可以通过UUID来作为数据库中的id，因为机器在不同时刻生成的UUID都是不一样的。**

UUID（Universally Unique Identifier）：通用唯一识别码，是一种软件建构的标准。
UUID 目的是让分布式系统中的所有元素，都能有唯一的辨识信息，而不需要通过中央控制端来做辨识信息的指定。
    UUID是指在一台机器上生成的数字，它保证对在同一时空中的所有机器都是唯一的。
    UUID由以下几部分组合：(1）当前日期和时间，UUID的第一个部分与时间有关，如果你在生成一个UUID之后，过几秒又生成一个UUID，则第一个部分不同，其余相同。
(2）时钟序列。 （3）全局唯一的IEEE机器识别号，如果有网卡，从网卡MAC地址获得，没有网卡以其他方式获得。
UUID的唯一缺陷在于生成的结果串会比较长。

```java
public class IdUtils {

    public static String getId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    @Test
    public void test(){
        System.out.println(IdUtils.getId());
    }
}
```



4、编写实体类

```java
@Data
public class Blog {
    private int id;
    private String title;
    private String author;
    //java中使用驼峰命名法，数据库中是create_time
    //在核心配置文件中的setting标签中mapUnderscoreToCamelCase设置为true，即可自动映射为驼峰命名法
    private Date createTime;
    private int views;
}
```

注意：数据库中的字段create_time对应实体类中的属性值为createTime，可以在核心配置文件setting标签中设置mapUnderscoreToCamelCase设置为true：

```xml
<settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
<!--        开启数据库字段自动转换为驼峰命名法-->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
```



5、编写实体类对应的Mapper接口和 Mapper.xml文件

6、测试



若在代码中向mysql插入中文出现乱码问题，那么将

```sql
url=jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8
```

改为：

```sql
url=jdbc:mysql://localhost:3306/mybatis?useSSL=true&useUnicode=true&characterEncoding=UTF-8
```



## IF

```xml
<select id="queryBlogIF" parameterType="Map" resultType="Blog">
        select * from blog
        <if test="title != null">
            where title=#{title}
        </if>
        <if test="author != null">
            and anthor=#{author}
        </if>
    </select>
```

## choose(when,otherwise)

```txt
choose相当于java中的switch
when标签只会从上到下匹配一个先符合条件的句子
otherwise相当于default
```

```xml
<select id="queryBlogChoose" parameterType="Map" resultType="Blog">
        select * from blog
        <where>
            <choose>
                <when test="title!=null">
                    title=#{title}
                </when>
                <when test="author">
                    author=#{author}
                </when>
                <otherwise>
                    views=#{views}
                </otherwise>
            </choose>
        </where>
    </select>
```



## trim(where, set)

改在if标签的语句，where标签会自动加上满足条件的where字句，并且如果第一个条件不满足第二个条件满足，则拼接出的where子句会自动去除and、or等连接词（where后面不能直接跟and）

```xml
<select id="queryBlogIF" parameterType="Map" resultType="Blog">
        select * from blog
        <where>
            <if test="title != null">
                title=#{title}
            </if>
            <if test="author != null">
                and author=#{author}
            </if>
        </where>
    </select>
```

set标签用于更新过程中update，update语句中会有set关键字，set后会跟很多更新信息，这些更新信息会用","连接，set就是用来处理多余的“,"并增加set子句的：

```xml
<update id="updateBlog" parameterType="Map">
        update blog
        <set>
            <if test="title!=null">title=#{title},</if>
            <if test="author!=null">author=#{author},</if>
        </set>
        where id=#{id}
    </update>
```

所谓的动态sql，就是在sql语句层面能够有逻辑代码

# SQL片段

有的时候，我们会把一些功能的部分抽取出来，方便复用！

1.使用sql标签抽取公共的部分

```xml
<sql id="title-author">
        <if test="title != null">
            title=#{title}
        </if>
        <if test="author != null">
            and author=#{author}
        </if>
    </sql>
```

2.在需要的地方使用include标签引用即可

```xml
<select id="queryBlogIF1" parameterType="Map" resultType="Blog">
        select * from blog
        <where>
            <include refid="title-author"></include>
        </where>
    </select>
```

sql片段注意事项：

- 最好基于单表来定义SQL片段！
- 不要存在SQL标签



## Foreach

原始语句：

```sql
select * from blog where 1=1 and (id=1 or id=2 or id=3)
```

可以改造成：

```sql
select * from blog where 1=1 and
<foreach item="id" collections="ids"
open="(" separator="or" close=")">
     #{id}
 </foreach>
```

![image-20210228220232261](C:\Users\FlameZ\AppData\Roaming\Typora\typora-user-images\image-20210228220232261.png)





# 13、缓存

数据库重要知识点：

- 读写分离：保障并发
- 主从复制：保证数据一致性

## 13.1 Mybatis缓存

Mybatis系统默认定义了两种缓存：**一级缓存和二级缓存**

- 默认情况下，只开启一级缓存（sqlSession级别的缓存，也称为本地缓存）
- 二级缓存需要手动开启和配置，他是基于namespace级别的缓存
- 为了提高扩展性，Mybatis定义了缓存接口Cache，我们可以通过实现Cache接口来定义二级缓存

## 13.2 一级缓存

一级缓存也叫本地缓存：sqlSession

- 与数据库同一次会话期间查询到的数据放在本地缓存中
- 以后获取相同的数据，则直接从缓存中拿

1.开启日志

2.测试在一个sqlSession中查询两次相同记录

```java
@Test
    public void test(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUserById(1);
        System.out.println(user);

        User user1 = mapper.queryUserById(1);
        System.out.println(user1);

        System.out.println(user==user1);

        sqlSession.close();
    }
```

3.查看日志输出

![image-20210228233451291](C:\Users\FlameZ\AppData\Roaming\Typora\typora-user-images\image-20210228233451291.png)

可以看到两次查询只创建了一次connection，第二次查询的时候直接返回缓存里的数据

### 缓存失效

注意，在两次查询中增加增删改操作

```java
@Test
    public void test(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUserById(1);
        System.out.println(user);

        //mapper.updateUser(new User(2,"aaaaa", "bbbbbb"));//更新操作
        sqlSession.clearCache();  //手动清理缓存

        User user1 = mapper.queryUserById(1);
        System.out.println(user1);

        System.out.println(user==user1);

        sqlSession.close();
    }
```

结果为

![image-20210228234526376](C:\Users\FlameZ\AppData\Roaming\Typora\typora-user-images\image-20210228234526376.png)

可以看到最后结果为false，并且两次查询都构造了sql语句从数据库中查询数据

缓存失效的情况：

1. 增删改操作，可能会改变原来的数据，所以必定会刷新缓存
2. 查询不同的数据
3. 查询不同的mapper
4. 手动清理缓存时！

小结：一级缓存默认开启，关闭不掉，只在一次sqlSession中有效，也就是openSession()和sqlSession.close()代码段之间有效



## 13.3 二级缓存

- 二级缓存也叫全局缓存，一级缓存作用域太低了，所以诞生了二级缓存！
- 基于namespace级别的缓存，一个命名空间(mapper文件)，对应于一个二级缓存
- 工作机制
  1. 一个会话查询一条数据，这个数据就会放在当前会话的一级缓存中
  2. 如果当前会话关闭后，一级缓存就没了；我们想要的是，会话关闭了，一级缓存中的数据被保存在二级缓存中
  3. 新的会话查询信息，就可以直接从二级缓存中获取信息
  4. 不同的mapper查出的数据会放到自己对应的缓存空间中

步骤：

- 核心配置文件开启二级缓存

  ```xml
  <settings>
          <setting name="logImpl" value="STDOUT_LOGGING"/>
  <!--        开启全局二级缓存-->
          <setting name="cacheEnabled" value="true"/>
      </settings>
  ```

- 在mapper.xml中开启二级缓存

  ```xml
  <cache/>
  ```

  也可以自定义参数

  ```
  <!--    这个更高级的配置创建了一个 FIFO 缓存，每隔 60 秒刷新，最多可以存储结果对象或列表的 
  512 个引用，而且返回的对象被认为是只读的，因此对它们进行修改可能会在不同线程中的调用者产生冲突。-->
  <cache eviction="FIFO" flushInterval="60000" size="512" readOnly="true"/>
  ```

  可以显示的在mapper.xml中把各个select语句方法设置为使用缓存"useCache=true"

  ```xml
  <select id="queryUserById" resultType="User" useCache="true">
          select * from user where id=#{id}
      </select>
  ```

  

- 测试

  ```java
  @Test//测试二级缓存
      public void testSecondCache(){
          //开启两个不同的sqlSession
          SqlSession sqlSession = MybatisUtils.getSqlSession();
          SqlSession sqlSession1 = MybatisUtils.getSqlSession();
          UserMapper mapper = sqlSession.getMapper(UserMapper.class);
          UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
  
          User user = mapper.queryUserById(1);
          System.out.println(user);
          //第一个会话查完之后关闭，此时数据已被记录在二级缓存中
          sqlSession.close();
  
          //第二个会话重新查询数据，看是否访问了二级缓存
          User user2 = mapper1.queryUserById(1);
          System.out.println(user2);
          System.out.println(user==user2);
  
          User user1 = mapper1.queryUserById(2);
          System.out.println(user1);
  
          System.out.println(user2==user1);
  
          sqlSession1.close();
      }
  ```

- 结果如下

  ![image-20210301134619155](C:\Users\FlameZ\AppData\Roaming\Typora\typora-user-images\image-20210301134619155.png)

可以看到，第一个会话关闭后，第二个会话去取相同的数据会缓存击中，直接从缓存中读取出相同的数据。第二次查询数据不在缓存中，会生成新的sql语句重新查询出数据。



## 13.4 缓存原理

![image-20210301135033094](C:\Users\FlameZ\AppData\Roaming\Typora\typora-user-images\image-20210301135033094.png)



## 13.5 自定义缓存Ehcache

Ehcache是一种广泛使用的开源Java分布式缓存。主要面向通用缓存。

要在程序中使用ehcache，先要导包

```xml
<!-- https://mvnrepository.com/artifact/org.mybatis.caches/mybatis-ehcache -->
<dependency>
    <groupId>org.mybatis.caches</groupId>
    <artifactId>mybatis-ehcache</artifactId>
    <version>1.2.1</version>
</dependency>
```

在mapper.xml的cache标签中增加type设置成ehcache

```xml
<cache eviction="FIFO" flushInterval="60000" size="512" readOnly="true" type="org.mybatis.caches.ehcache.EhcacheCache"/>
```

增加单独的配置文件ehcache.xml。

```xml
     <?xml version="1.0" encoding="UTF-8"?>
    <ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd"
             updateCheck="false">
    
        <!--持久化磁盘路径-->
        <diskStore path="java.io.tmpdir"/>
    
    
    
        <!--默认缓存设置-->
        <defaultCache maxElementsInMemory="1000"
                       eternal="false"
                       timeToIdleSeconds="3600"
                       timeToLiveSeconds="0"
                       overflowToDisk="true"
                       maxElementsOnDisk="10000"
                       diskPersistent="false"
                       diskExpiryThreadIntervalSeconds="120"
                       memoryStoreEvictionPolicy="FIFO"
        />
        　
    
        <!--
            <cache     name 缓存名唯一标识
                       maxElementsInMemory="1000" 内存中最大缓存对象数
                       eternal="false" 是否永久缓存
                       timeToIdleSeconds="3600" 缓存清除时间 默认是0 即永不过期
                       timeToLiveSeconds="0" 缓存存活时间 默认是0 即永不过期
                       overflowToDisk="true" 缓存对象达到最大数后，将其写入硬盘
                       maxElementsOnDisk="10000"  磁盘最大缓存数
                       diskPersistent="false" 磁盘持久化
                       diskExpiryThreadIntervalSeconds="120" 磁盘缓存的清理线程运行间隔
                       memoryStoreEvictionPolicy="FIFO" 缓存清空策略
                       FIFO 先进先出
                       LFU  less frequently used  最少使用
                       LRU  least recently used 最近最少使用
        />
        -->
    
    
        <cache name="testCache"
               maxEntriesLocalHeap="2000"
               eternal="false"
               timeToIdleSeconds="3600"
               timeToLiveSeconds="0"
               overflowToDisk="false"
               statistics="true"
               memoryStoreEvictionPolicy="FIFO">
    
        </cache>
    
    
    </ehcache>
```

之后写测试类即可。



**注*意：现如今缓存基本用redis来做，ehcache很少使用了。****



# github：mybatis-study

具体的学习过程和代码在自己的github上，叫mybatis-study



# 练习：24道练习题实践！

