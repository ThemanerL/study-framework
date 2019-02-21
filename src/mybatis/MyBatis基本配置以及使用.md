#### MyBatis的基本运作流程
1. 根据XML文件配置文件（全局配置文件），创建一个SqlSessionFactory对象
2. 全局配置文件中链接到Sql映射文件，此处为EmployeeSQL.xml。该文件中配置了每一个sql以及sql的封装规则
3. 将Sql文件注册到全局配置文件
- 根据全局配置文件得到一个SqlSessionFactory对象
- 使用SqlSessionFactory，获取到sqlSession对象，使用该对象来执行sql语句
- 一个sqlSession就是代表与数据库的一次会话. 使用sql的唯一标示来告诉Mybatis执行哪个sql，sql都是保存在sql映射文件中
<hr/>
#### 注意事项
1. 接口式编程
    原生：   Dao     --> DaoImpl
    MyBatis：Mapper  --> xxMapper.xml
2. SqlSession代表与数据库的一次会话：用完必须释放资源
3. SqlSession与Connection一样，都是非线程安全的。每次使用都应该获取新的对象。
4. mapper接口没有实现类。但是myBatis会为这个接口生成一个代理对象。
(将接口与XML进行绑定)
EmployeeMapper employeeMapper = SqlSession.getMapper(EmployeeMapper.class)
- myBatis的全局配置文件：包含数据库连接池信息，事务管理器信息，系统运行环境信息等等。
- <a href = "https://github.com/ThemanerL/Study_framework/blob/master/conf/mybatis/dao/EmployeeMapper.xml">sql映射文件</a>：保存了每一个sql语句的映射信息。通过该文件抽取sql语句。