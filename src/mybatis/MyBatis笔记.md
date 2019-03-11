 <style>
 table th:first-of-type {
    width: 100px;
 }
 </style>

1. #### MyBatis的基本运作流程
	1. 根据XML文件配置文件（全局配置文件），创建一个SqlSessionFactory对象
	2. 全局配置文件中链接到Sql映射文件，此处为EmployeeSQL.xml。该文件中配置了每一个sql以及sql的封装规则
	3. 将Sql文件注册到全局配置文件
		- 根据全局配置文件得到一个SqlSessionFactory对象
		- 使用SqlSessionFactory，获取到sqlSession对象，使用该对象来执行sql语句
		- 一个sqlSession就是代表与数据库的一次会话. 使用sql的唯一标示来告诉Mybatis执行哪个sql，sql都是保存在sql映射文件中
		<hr/>
    1. 接口式编程  
        原生：   Dao     --> DaoImpl  
        MyBatis：Mapper  --> xxMapper.xml  
    2. SqlSession代表与数据库的一次会话：用完必须释放资源  
    3. SqlSession与Connection一样，都是非线程安全的。每次使用都应该获取新的对象。  
    4. mapper接口没有实现类。但是myBatis会为这个接口生成一个代理对象。  
    (将接口与XML进行绑定)  
    EmployeeMapper employeeMapper = SqlSession.getMapper(EmployeeMapper.class)  
      - <a href = "https://github.com/ThemanerL/Study_framework/blob/master/conf/mybatis/mybatis-config.xml">myBatis的全局配置文件</a>：包含数据库连接池信息，事务管理器信息，系统运行环境信息等等。  
      - <a href = "https://github.com/ThemanerL/Study_framework/blob/master/conf/mybatis/dao/EmployeeMapper.xml">sql映射文件</a>：保存了每一个sql语句的映射信息。通过该文件抽取sql语句。  

2. ####Mybatis的参数处理
    1. 单个参数：Mybatis不会做特殊处理-->
    ```#{参数名}：取出参数值```
    2. 多个参数：Mybatis会做特殊处理。--> 多个参数会被封装为一个Map，#{
    }就是从Map中取得指定Key的值  
    Map<Key:param1,param2 ... Value:传入的参数值1, 值2, ...>  
    Key也可以为参数的索引
    3. 命名参数：在接口声明方法时，对参数添加注解明确指定封装参数时的Map<Key:使用@Param注解指定的值,
                                                     Value:参数值  >的Key  
        ```
         /**
           * 
           * 根据ID和名字 获取员工
           * @param id /
           * @param lastName /
           * @return /
           */
          Employee getEmpByIdXLastName(@Param("id") Integer id, @Param("lastName") String lastName);
        ```
           
        ```#{指定的Key}即可取出对应的参数值```
    4. 多参数传参的具体运用   
        - Pojo  
          如果多个参数正好是我们业务逻辑的数据模型，我们就可以直接传入pojo  
          ```
          #{属性名}:取出传入的pojo的值
        - Map  
          如果多个参数是业务模型的数据，没有对应的pojo，**不经常使用**，可以传入map
          ```
          #{key}:取出map中对应的值
        - TO  
          如果多个参数不是业务模型中的数据，但是经常要使用，推荐来编写一个TO(Transfer Object)数据输出对象
          ```
            Page{
                int index;
                int size;
            }
          ```
        <hr/>
    1. 如果是Collection(List、Set)类型或者是数组，也会特殊处理。也是把传入的list或者数组封装在map中
    Key:Collection(collection),如果是List还可以使用 Key(list)、数组(array)  
    ![List在mybatis处理后的结构](https://makedown-1257967443.cos.ap-guangzhou.myqcloud.com/listInMybatis.png)
  
        public Employee getEmpById(List<Integer> ids)  
        取值:取出第一个id的值#{list[0]}
        
        源码：
          ```
          private Object wrapCollection(final Object object) {
            if (object instanceof Collection) {
              StrictMap<Object> map = new StrictMap<Object>();
              map.put("collection", object);
              if (object instanceof List) {
                map.put("list", object);
              }
              return map;
            } else if (object != null && object.getClass().isArray()) {
              StrictMap<Object> map = new StrictMap<Object>();
              map.put("array", object);
              return map;
            }
            return object;
          }
        ```
    <hr/>
  
    **#{ }与${ }取值的区别**：
    1. \#{}是以预编译的形式将参数设置到sql中:PreparedStatement防止sql注入;**大多情况下使用#{}**
    本质就是占位符  
        1. 规定参数的规则:
        javaType、jdbcType、mode(用于存储过程)、numericScale(规定保留小数位数)、resultMap、typeHandler(类型处理器)、jdbcTypeName、expression  
        jdbcType通常在某种特定的条件下被设置:在数据为null的时候，有些数据库不能识别mybatis对于null的默认处理，  
        比如Oracle(报错):JdbcType OTHER 无效类型;(mybatis对所有的null都映射的是原生jdbc OTHER,oracle不能正确处理)
        由于全局配置中，jdbcTypeForNull = OTHER;oracle不支持:  
            - 在oracle的sql映射文件中写sql语句时，针对可能为null的字段:\#{email, jdbcType = NULL};
            - 在mybatis的全局配置文件中添加\<setting name="jdbcTypeForNull" value="null"/\>;
    2. ${}取出的值直接拼装在sql语句中;  
        1. 原生sql不支持占位符的地方，我们就可以使用${}进行取值, 比如分表、排序;按照年份分表拆分  
        select * from ${year}_salary where ***;  
        select * from tbl_employee order by ${f_name} ${desc/asc}
3. ####Mybatis的缓存机制
    Mybatis中设定了两级缓存
    1. 一级缓存 SqlSession级别的缓存,一个Session级别的Map,下次查询先查询Map中有没有,没有的话再操作数据库  
          一级缓存失效情况:
          1. 没有使用到当前以及缓存的情况下.还需要向数据库发出查询
          2. SqlSession不同,缓存失效
          3. SqlSession相同,sql语句不同(当前一级缓存还未被缓存)
          4. SqlSession相同,两次查询之间进行了增删改操作
          5. SqlSession相同,手动清空缓存
    2. 二级缓存(全局缓存) 基于namespace级别,一个namespace对应一个二级缓存,有自己的一个map  
        - 工作机制:
        - 一个会话,查询一个数据,这个数据就会被放在当前会话的一次缓存中,
        - 仅且只有会话关闭或者提交之后,一级缓存中的数据会被保存在二级缓存中,此时新的会话执行查询操作时,就可以参照二级缓存中的内容  

         Session|NameSpace|Bean|
         |:---:|:---|:---|
         |\|EmployeeMapper  |Employee |
         |\|DepartmentMapper|Department|
              
        EmployeeMapper与DepartmentMapper是不同的namespace,不同的Mapper文件中分别查出两种对象
        不同的namespace查出的数据会放在自己对应的缓存中使用:
        1. 开启二级缓存配置(显式配置,防止版本更替)
        2. 去mapper.xml中配置启用二级缓存
        3. 我们的POJO需要实现序列化接口
    - mybatis默认缓存设置相关:  
         1. cacheEnabled(T/F)  全局二级缓存开关    
         1. useCache(T/F)      单独控制某一个sql是否使用缓存  
         1. flushCache         每个增删改默认为true,执行后刷新所有缓存(包括一级和二级).对于查询操作,
                                  查询时不使用缓存且查询后刷新所有缓存  
         1. SqlSession.clearCache() 只针对一级缓存  
         1. localCacheScope    本地缓存作用域(一级缓存Session和Statement 值STATEMENT:相当于禁用一级缓存)
    - 第三方缓存整合:
         1).导入第三方jar包  
         2).导入与第三方缓存整合的适配包,(Mybatis官方已经提供)  
         3).mapper.xml中引用何时的缓存类型(通过cache的type属性)  
    - **注意**!!! 在二级缓存开启的情况下,即使是在同一个SqlSession中进行查询,还是会先去二级缓存中查询,再去一级缓存,再去数据库
    
        
    




