pom.xml是maven的配置文件，在https://mvnrepository.com/artifact/org.springframework/spring-webmvc/5.2.0.RELEASE 可以看到<dependency> 把要用的东西的< dependency>拷贝到pom.xml中即可

#  spring-webmvc

spring导入web-mvc就会自动把其他模块如core、context之类的导入。

<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->

<dependency>

  <groupId>org.springframework</groupId>

  <artifactId>spring-webmvc</artifactId>

  <version>5.2.0.RELEASE</version>

</dependency>

另外会用到的有jdbc包

<dependency>

  <groupId>org.springframework</groupId>

  <artifactId>spring-jdbc</artifactId>

  <version>5.2.0.RELEASE</version>

</dependency>

 

 

# XML的配置元数据的基本结构

xml文件名可以随意起(官方叫做applicationcontext.xml)。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    https://www.springframework.org/schema/beans/spring-beans.xsd">
    

</beans>

```



 

Spring默认使用无参构造方法创建对象；bean默认是单例模式，可以在<bean>标签中的scope属性设置为prototype类型，每次从容器中getBean时都会产生一个新对象。

 

两种获取bean的方式：

User user = context.getBean("user", User.class);

User user1 = (User)context.getBean("user");

#  

# c、p命名空间

P命名空间和c命名空间：要使用在spring配置文件头加如下两句

xmlns:p="http://www.springframework.org/schema/p"

xmlns:c=http://www.springframework.org/schema/c

p:property   c:contructor_arg

 

# bean自动装配：

## 使用byName自动装配

## 使用byType自动装配

## 使用注解自动装配

注解：在配置文件中导入约束：

xmlns:context="http://www.springframework.org/schema/context"

配置注解支持：<context:annotation-config/>

完整的如下：

<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"

  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"

  xmlns:context="http://www.springframework.org/schema/context"

  xsi:schemaLocation="http://www.springframework.org/schema/beans

​    https://www.springframework.org/schema/beans/spring-beans.xsd

​    http://www.springframework.org/schema/context

​    https://www.springframework.org/schema/context/spring-context.xsd">

 

  <context:annotation-config/>

</beans>

 

Web开发中，会按照MVC的方式分层，每一层对应一个@Component的衍生注解：

dao：@Repository

service：@Service

controller: @Controller

上四个注解功能都是一样的，都是将某个类注入到容器中。

 

JavaConfig:完全替代掉xml的配置功能，使用java来配置整个项目的bean。

 

**注意：Xml与注解的最佳实践：Xml用来管理bean，注解用来属性值的注入**

#  

# 代理

代理模式：是spring AOP的底层 (springAOP和springMVC必会问)

代理模式分类：静态代理和动态代理

## 静态代理

静态代理角色分析：

\1. 抽象角色，一般会用接口或者抽象类来解决

\2. 真实角色，被代理的角色

\3. 代理角色，代理真实角色，代理真实角色后，我们一般会做一些附属操作

\4. 客户：访问代理角色的人。

静态代理模式好处：使真实角色的操作更存粹，公共业务部分就交给代理角色，实现业务的分工，公共业务发生扩展时方便集中管理；坏处就是代码量会翻倍。

##  动态代理和AOP

AOP在不改变原有代码的基础上新增功能：

![img](file:///C:/Users/FlameZ/AppData/Local/Temp/msohtmlclip1/01/clip_image002.png)

 

动态代理：静态代理的代码量会翻倍，为解决这个问题，可以用动态代理。

反射就是一种动态代理，也是spring的实现机制。

动态代理和静态代理角色一样；

动态代理的代理类是动态生成的，不是我们直接写好的；

动态代理分为两大类：基于接口的和基于类的代理；1.基于接口---JKD动态代理 2.基于类---cglib 3.java字节码：javassist（动态改变类的结构或者动态生成类）

 

动态代理需要了解两个类：Proxy,invocationHandler

InvocationHandler:用于重写功能

Proxy：需要被代理对象方法和invocationHander,最终返回一个代理对象。

 

AOP：面向切面编程，spring中的动态代理

使用AOP织入，需要导入一个依赖包：

<dependency>

  <groupId>org.aspectj </groupId>

  <artifactId>aspectjweaver</artifactId>

  <version>1.9.4</version>

</dependency>

AOP两种实现方式：1.使用Spring的API接口 2.自定义类来实现AOP 3.使用注解方式

 

![img](file:///C:/Users/FlameZ/AppData/Local/Temp/msohtmlclip1/01/clip_image004.png)

 

# 整合Mybatis

要导入junit，mybatis，mysql数据库，spring相关，aop织入，mybatis-spring：要用这些jar包

<dependency>

​      <groupId>junit</groupId>

​      <artifactId>junit</artifactId>

​      <version>4.12</version>

​    </dependency>

​    <dependency>

​      <groupId>mysql</groupId>

​      <artifactId>mysql-connector-java</artifactId>

​      <version>5.1.47</version>

​    </dependency>

​    <dependency>

​      <groupId>org.mybatis</groupId>

​      <artifactId>mybatis</artifactId>

​      <version>3.5.2</version>

​    </dependency>

​    <dependency>

​       <groupId>org.springframework</groupId>

​      <artifactId>spring-webmvc</artifactId>

​      <version>5.2.0.RELEASE</version>

​    </dependency>

​    <dependency>

​      <groupId>org.springframework</groupId>

​      <artifactId>spring-jdbc</artifactId>

​      <version>5.1.18.RELEASE</version>

​    </dependency>

​    <dependency>

​      <groupId>org.aspectj</groupId>

​      <artifactId>aspectjweaver</artifactId>

​      <version>1.9.4</version>

​    </dependency>

​    <dependency>

​      <groupId>org.mybatis</groupId>

​      <artifactId>mybatis-spring</artifactId>

​      <version>2.0.2</version>

​    </dependency>

 

Mybatis基本开发流程：1.编写实体类，2.配置核心配置文件，3.编写接口，4.编写Mapper.xml 5.测试。

##  方式一

Mybatis-Spring: MyBatis-Spring 会帮助你将 MyBatis 代码无缝地整合到 Spring 中

\1.   编写数据源dataSource替代mybatis中的数据源

\2.   编写sqlSessionFactory

\3.   编写sqlSessionTemplate

\4.   需要给接口加实现类

```java
public class UserMapperImpl implements UserMapper{
    SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<User> getUsers() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.getUsers();
    }
}
```

增加setSqlSession方法让spring注入sqlSession，从而使用sqlSession

\5.   将自己写的实现类注入到spring中，测试使用

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"

       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <import resource="spring-dao.xml"/>

    <bean id="UserMapper" class="com.kuang.mapper.UserMapperImpl">
        <property name="sqlSession" ref="sqlSession"/>
    </bean>
</beans>

```

同时导入mybatis-dao.xml这个myabtis的基础配置文件

## mybatis-dao.xml

 ```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"

       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

<!--    datasource使用Spring的数据源替换Mybatis的配置  c3p0  dbcp  druid
  我们这里使用spring提供的jdbc
-->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="username" value="root"/>
        <property name="password" value="123456"/>
        <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
    </bean>

    <!--sqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
<!--        绑定mybatis配置文件-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:com/kuang/mapper/*.xml"/>
    </bean>

<!--    sqlSessionTemplate就是我们使用的sqlSession-->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
<!--        只能使用构造器注入sqlSession，因为它没有set方法-->
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>
</beans>

 ```

上面的代码是整合mybatis到spring的配置过程，代码是固定的，可以放在一个文件mybatis-dao中，在新项目中直接导入到spring核心bean管理文件ApplicationContext.xml中。上述文件的功能包含的步骤1-3步，从配置dataSource到sqlSession

##  方式二

与方式一的区别仅仅是实现类和配置文件内容的区别，方式一主要用在配置文件中向实现类注入sqlSession，同时实现类中要有sqlSession属性；方式二简化了一中的sqlSession属性，只需要继承SqlSessionDaoSupport类即可，但SqlSessionDaoSupport需要注入sqlSessionFactory

1、实现类修改

```java
public class UserMapperImpl1 extends SqlSessionDaoSupport implements UserMapper{

    @Override
    public List<User> getUsers() {
        //getSqlSession()方法是SqlSessionDaoSupport中的内容
        return getSqlSession().getMapper(UserMapper.class).getUsers();
    }
}
```

可以看到此类中省略了sqlSession属性对象。

2、添加这个实现类的bean

```xml
<!--    方式二-->
    <bean id="UserMapper1" class="com.kuang.mapper.UserMapperImpl1">
        <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
    </bean>
```

父类SqlSessionDaoSupport需要sqlSessionFactory属性，所以需要注入。

3、测试



# 声明式事务

ACID原则：

- 原子性
- 一致性
- 持久性
  - 事务一旦被提交，无论系统发生什么问题，结果不会被影响，被持久化写入到磁盘中。
- 隔离性
  - 多个业务可操作一个资源，防止数据损坏



人为制造错误:

1. java查询语句，中先插入，后删除人

```java
public List<User> getUsers() {
        User user = new User(7, "杨红焰", "lololo");
        addUser(user);
        deleteUser(7);
        return getSqlSession().getMapper(UserMapper.class).getUsers();
    }
```

2、人为写错delete的sql语句，人为制造错误，让程序跑不下去

```xml
<delete id="deleteUser" parameterType="int">
        deletes from user where id=#{id}
    </delete>

```

delete 写成了deletes

3、程序报错

![image-20210301234707516](C:\Users\FlameZ\AppData\Roaming\Typora\typora-user-images\image-20210301234707516.png)

4、数据库数据

![image-20210301234735650](C:\Users\FlameZ\AppData\Roaming\Typora\typora-user-images\image-20210301234735650.png)

发现虽然程序报错，但是数据还是添加到了数据库中。解决这种问题的方法就是用事务。



配置声明式事务：

```xml
<!--    配置声明式事务-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
<!--    结合AOP实现事务的织入-->
<!--    配置事务通知-->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
<!--        给哪些方法配置事务-->
<!--        配置事务的传播特性：new-->
        <tx:attributes>
            <tx:method name="add" propagation="REQUIRED"/>
            <tx:method name="delete" propagation="REQUIRED"/>
            <tx:method name="update" propagation="REQUIRED"/>
            <tx:method name="query" read-only="true"/>
            <tx:method name="*" propagation="REQUIRED"/>
        </tx:attributes>
    </tx:advice>
<!--aop-->
    <aop:config>
        <aop:pointcut id="txPointCut" expression="execution(* com.kuang.mapper.*.*(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointCut"/>
    </aop:config>
```

然后就可以用了

# Junit

Junit包工具用于@Test标签测试，之后点击函数右侧的运行按钮就可以运行这个函数。常用于测试函数功能有无报错。在maven配置文件中加入如下即可：
     <dependency>

​      <groupId>junit</groupId>

​      <artifactId>junit</artifactId>

​      <version>4.12</version>

​    </dependency>





# github:spring-study

学习过程的代码框架在自己的github上，叫spring-study