#Mybatis学习

1. #### MyBatis的基本运作流程
	1. 根据XML文件配置文件（全局配置文件），创建一个SqlSessionFactory对象
	2. 全局配置文件中链接到Sql映射文件，此处为EmployeeSQL.xml。该文件中配置了每一个sql以及sql的封装规则
	3. 将Sql文件注册到全局配置文件
		- 根据全局配置文件得到一个SqlSessionFactory对象
		- 使用SqlSessionFactory，获取到sqlSession对象，使用该对象来执行sql语句
		- 一个sqlSession就是代表与数据库的一次会话. 使用sql的唯一标示来告诉Mybatis执行哪个sql，sql都是保存在sql映射文件中
		<hr/>
    1. 接口式编程  
        原生:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dao &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->&nbsp;DaoImpl  
        MyBatis:Mapper&nbsp;&nbsp;&nbsp;&nbsp;-->&nbsp;xxMapper.xml  
    2. SqlSession代表与数据库的一次会话：用完必须释放资源,jdk1,7之后可以使用try-with-resource语句  
    3. SqlSession与Connection一样，都是非线程安全的。每次使用都应该获取新的对象。  
    4. mapper接口没有实现类。但是myBatis会为这个接口生成一个代理对象。  
    (将接口与XML进行绑定)  
    EmployeeMapper employeeMapper = SqlSession.getMapper(EmployeeMapper.class)  
      - <a href = "https://github.com/ThemanerL/Study_framework/blob/master/conf/mybatis/mybatis-config.xml">myBatis的全局配置文件</a>：包含数据库连接池信息，事务管理器信息，系统运行环境信息等等。  
      - <a href = "https://github.com/ThemanerL/Study_framework/blob/master/conf/mybatis/dao/EmployeeMapper.xml">sql映射文件</a>：保存了每一个sql语句的映射信息。通过该文件抽取sql语句。  

2. #### Mybatis的参数处理
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
3. #### Mybatis的缓存机制
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

             |SqlSession|NameSpace|Bean|
             |:---:|:---|:---|
             |&nbsp;|EmployeeMapper  |Employee |
             |&nbsp;|DepartmentMapper|Department|
              
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
    - **注意!!!** 在二级缓存开启的情况下,即使是在同一个SqlSession中进行查询,还是会先去二级缓存中查询,再去一级缓存,再去数据库
4. #### MyBatis Generator(MyBatis代码生成器)   

    MyBatis Generator (MBG)  will introspect a database table (or many tables) and will generate artifacts that can be used to access the table(s). This lessens the initial nuisance of setting up objects and configuration files to interact with database tables.
    1. 一定要在Mybatis配置文件中添加对Mapper映射文件所在包扫描
        ```
        <mappers>
               <package name="mybatis.generator.dao"/>
        </mappers>
        ```
    2. [配置generator.xml](http://www.mybatis.org/generator/configreference/xmlconfig.html)

5. #### Mybatis运行原理
    ![层次结构图](https://makedown-1257967443.cos.ap-guangzhou.myqcloud.com/mybatisStructure.png)  
    1. 获取SqlSessionFactory对象:解析所有配置文件的信息包含在Configuration中,返回包含Configuration的DefaultSqlSessionFactory
    其中MappedStatement中的每一个元素代表一条SQL语句的详细信息、  
    ![MappedStatement](https://makedown-1257967443.cos.ap-guangzhou.myqcloud.com/config_mappedStatement.png)  
    而Mapper被注册在configuration.mapperRegistry.knownMappers中.  
    ![knownMappers](https://makedown-1257967443.cos.ap-guangzhou.myqcloud.com/mybatis_mapperRegistry.png)  
        1. 新建一个SqlSessionFactoryBuilder对象,调用SqlSessionFactoryBuilder.Builder(InputStream)方法
        ```SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);```
        2. Builder先new一个XMLConfigBuilder对象,传入配置文件(InputStream).
        ``` XMLConfigBuilder parser = new XMLConfigBuilder(inputStream, environment, properties);```
        3. 调用```parser.parse()```也就是&nbsp;&nbsp;**XMLConfigBuilder.parse()**
        4. parse()方法是专门来解析XML文件的解析器
            1. ```parseConfiguration(parser.evalNode("/configuration"))```开始解析节点"/configuration"
            2. 在parseConfiguration中包含了对所有的配置文件所有的标签的解析
                ```java
                private void parseConfiguration(XNode root) {
                   try {
                     Properties settings = settingsAsPropertiess(root.evalNode("settings"));
                     //issue #117 read properties first
                     propertiesElement(root.evalNode("properties"));
                     loadCustomVfs(settings);
                     typeAliasesElement(root.evalNode("typeAliases"));
                     pluginElement(root.evalNode("plugins"));
                     objectFactoryElement(root.evalNode("objectFactory"));
                     objectWrapperFactoryElement(root.evalNode("objectWrapperFactory"));
                     reflectorFactoryElement(root.evalNode("reflectorFactory"));
                     settingsElement(settings);
                     // read it after objectFactory and objectWrapperFactory issue #631
                     environmentsElement(root.evalNode("environments"));
                     databaseIdProviderElement(root.evalNode("databaseIdProvider"));
                     typeHandlerElement(root.evalNode("typeHandlers"));
                     // 在这里解析配置文件中的<mappers>标签
                     mapperElement(root.evalNode("mappers"));
                   } catch (Exception e) {
                     throw new BuilderException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
                   }
               ```
               1. 在mapper元素解析方中,遍历<mappers>节点中的元素                                                  
                    ```java
                    private void mapperElement(XNode parent) throws Exception {
                        if (parent != null) {
                          for (XNode child : parent.getChildren()) { 
                            ……
                            configuration.addMappers(mapperPackage);
                            mapperParser.parse();
                            configuration.addMapper(mapperInterface);
                            ……
                          }
                        }
                    }
                    ```
               2. 调用configuration.addMappersmapperRegistry.addMapper(mapperClass);         
                    ```java
                    knownMappers.put(type, new MapperProxyFactory<T>(type));
                    MapperAnnotationBuilder parser = new MapperAnnotationBuilder(config, type);
                    parser.parse();
                    ```
                    该方法对每个进行Mapper文件注册到Map<Class<?>, MapperProxyFactory<?>> knownMappers中,为每一个mapper映射创建一个MapperProxyFactory
                    其中```parser.parse();``` 再将接口中的方法信息也添加到configuration对象中
        5. 这些所有的操作都是在将所有的配置文件的信息添加到configuration对象中。
        ```qlSessionFactoryBuilder.Builder(InputStream)```最终会返回一个```new DefaultSqlSessionFactory(config)```;
        一个默认的SqlSessionFactory其中包含一个包含所有配置信息的Configuration对象
        ![SessionFactory创建时序图](https://makedown-1257967443.cos.ap-guangzhou.myqcloud.com/Mybatis_CreateSqlSessionFactory.png)
    2. 获取SqlSession对象:返回一个DefaultSqlSession对象,包括Configuration和一个executor
        1. SqlSessionFactory.openSession()传入configuration中Executor的类型(SIMPLE/REUSE/BATCH)
        2. 根据configuration中的environment配置创建一个事务工厂,创建一个事务  
        ```java
        final TransactionFactory transactionFactory = getTransactionFactoryFromEnvironment(environment);
        tx = transactionFactory.newTransaction(environment.getDataSource(), level, autoCommit);
        ```
        3. 传入刚刚new的事务和mybatis配置文件中配置的Executor的类型、生成对应的executor,
        ```java
         public Executor newExecutor(Transaction transaction, ExecutorType executorType) {
           executorType = executorType == null ? defaultExecutorType : executorType;
           executorType = executorType == null ? ExecutorType.SIMPLE : executorType;
           Executor executor;
           // 判断Executor的类型,生成相对应的Exector
           if (ExecutorType.BATCH == executorType) {
             executor = new BatchExecutor(this, transaction);
           } else if (ExecutorType.REUSE == executorType) {
             executor = new ReuseExecutor(this, transaction);
           } else {
             executor = new SimpleExecutor(this, transaction);
           }
           // 如果开启了缓存,使用CachingExecutor对executor进行包装
           if (cacheEnabled) {
             executor = new CachingExecutor(executor);
           }
           // 使用拦截链对Executor进行处理
           executor = (Executor) interceptorChain.pluginAll(executor);
           return executor;
         }
        ```
        4. 返回处理后的Executor,```return new DefaultSqlSession(configuration, executor, autoCommit);```返回SqlSession,该SqlSession中包括所有的配置信息,和一个生成好的executor  
        ![获得SqlSession](https://makedown-1257967443.cos.ap-guangzhou.myqcloud.com/mybatis_CreateSqlSession.png)  
    3. 获得接口代理对象(MapperProxy):MapperProxy中包含了DefaultSqlSession,而SqlSession中包含了Executor对象,Executor是一个执行器,通过它来执行CURD
        1. 执行```SqlSession.getMapper(Class<T> type)```
        方法,传入Mapper的类型类(类名.class)  
        2. 调用configuration的getMapper方法(configuration对象存储在SqlSession中)  
            1. mapperRegistry.getMapper(type, sqlSession)方法,在初始加载配置文件时在该对象的knownMappers属性中存储了接口的类型信息和Mapper的代理工厂对象
                ```Map<Class<?>, MapperProxyFactory<?>> knownMapper``` 此时根据穿入的类型信息取出对应的MapperProxyFactory  
            2. 创建一个实现了InvocationHandler接口(动态代理接口)的Mapper代理对象```MapperProxy<T> mapperProxy = new MapperProxy<T>(sqlSession, mapperInterface, methodCache);```  
            3. 调用 ```java
                return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, mapperProxy);```
                返回MapperProxy实例  
        ![获得MapperProxy](https://makedown-1257967443.cos.ap-guangzhou.myqcloud.com/mybatis_CreateMapperProxy.png)  
    4. 执行CRUD方法  
        1. MapperProxy执行invoke方法, MapperProxy是一个InvocationHandler类型, 执行目标真正方法之前会执行InvocationHandler.invoke()  
        2. 先判断要执行的方法是不是Object类的方法如果是则直接执行,先把当前方法method包装成一个MapperMethod,调用```mapperMethod.execute(SqlSession sqlSession, Object[] args)```
        (该调用发生在MapperProxy内,MapperProxy中拥有成员变量sqlSession)  
        3. 在MapperProxy初始化的时候,有新建一个```Map<Method, MapperMethod> methodCache;```  
            1. 在methodCache中查找是否已经存在当前传入的方法与方法映射,如果有,取出方法映射.如果没有,新建一个```mapperMethod = new MapperMethod(mapperInterface, method, sqlSession.getConfiguration());```
            put到methodCache中,返回刚刚new好的mapperMethod  
        4. 执行```mapperMethod.execute(sqlSession, args) ```
            传入SqlSession和参数(参数可以是条件,将被拼接至SQL)  
           1. mapperMethod.execute根据方法的类型(CRUD)和返回类型,选择相对应的execute方法  
           对于```employeeMapper.selectByExample(employeeExample);```方法而言,选择执行executeForMany方法对参数进行转化,然后执行```DefaultSqlSession.SelectList(statement, parameter);```方法  
           ```java
            private <E> Object executeForMany(SqlSession sqlSession, Object[] args) {
                List<E> result;
                Object param = method.convertArgsToSqlCommandParam(args);
                if (method.hasRowBounds()) {
                  RowBounds rowBounds = method.extractRowBounds(args);
                  result = sqlSession.<E>selectList(command.getName(), param, rowBounds);
                } else {
                  result = sqlSession.<E>selectList(command.getName(), param);
                }
                ……
                return result;
            ```  
           此时如果执行的是返回一个对象的select方法,最终也是会执行DefaultSqlSession.SelectList(statement, parameter);方法.不过是只有一个元素的List    
           statement其实就是command.getName(),也就是方法的全类名.此处为mybatis.generator.dao.EmployeeMapper.selectByExample  
            ```java
             public <T> T selectOne(String statement, Object parameter) {
               // Popular vote was to return null on 0 results and throw exception on too many.
               List<T> list = this.<T>selectList(statement, parameter);
               if (list.size() == 1) {
                 return list.get(0);
               } 
             ……
             }
           ```    
            1. 从 configuration中根据statement取出对应的MapperStatement(存放着SQL语句的详细信息)  
            2. 对传入的parameter进行判断如果参数类型是Collection或者List或者Array,将参数进行包装,key分别为collection、list、array  
            ```java
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
            3. 执行```CachingExecutor.query(MappedStatement ms, Object parameterObject, RowBounds rowBounds, ResultHandler resultHandler)```方法  
                1. 调用MaapperStatement.getBoundSql()得到SQL语句以及对应的参数信息,在该方法中调用的DynamicSql.getBoundSql()来将参数拼接到SQL语句中  
                ![获得MapperProxy](https://makedown-1257967443.cos.ap-guangzhou.myqcloud.com/mybatis_BoundSql.png)  
                2. 根据一系列信息生成一个CacheKey  
                3. 执行```CachingExecutor.query(MappedStatement ms, Object parameterObject, RowBounds rowBounds, ResultHandler resultHandler, CacheKey key, BoundSql boundSql)```方法  
                    - 如果缓存不为空,判断是否刷新缓存在缓存中取数据,在缓存中查数据,查完后加入到缓存   
                    - 如果缓存为空,再去本地缓存取值(**此处验证了先查二级缓存再查一级缓存**).  
                    - 如果本地缓存中还是没有值,才真正执行```BaseExecutor.queryFromDatabase(ms, parameter, rowBounds, resultHandler, key, boundSql)```
                        此时会将缓存的Key加入到本地缓存中```localCache.putObject(key, EXECUTION_PLACEHOLDER);```(这个key会在finally中被删除,然后如果查询成功,会再将Key,Value放入缓存)  
                    - 执行```SimpleExecutor.doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql)```  
                        1. new一个RoutingStatementHandler根据SQL语句的Statement类型,
                        创建对应的PreparedStatementHandler或者SimpleStatementHandler或者CallableStatementHandler,在创建该**StatementHandler**时,同时创建了**ResultSetHandler**和**ParameterHandler**  
                        2. 使用拦截链对StatementHandler进行处理  
                        3. 调用```RoutingStatementHandler.Statement prepare(Connection connection, Integer transactionTimeout)```转为一个statement对象  
                            1. SimpleExecutor.prepareStatement进行参数预编译  
                            2. 调用```DefaultParameterHandler.setParameters(PreparedStatement ps)```设置参数  
                            3. 调用```PreparedStatement.execute();```执行SQL语句  
                            4. 调用```resultSetHandler.<E> handleResultSets(ps);```对结果进行封装,将数据库中的字段根据对应到Bean中的类型这其中也用到TypeHandle处理类型转化  
                            5. 返回结果.一次查询完成  
        ![操作数据库](https://makedown-1257967443.cos.ap-guangzhou.myqcloud.com/mybatis_Execute.png)  

----
### QUESTION:
1. 当方法重载的时在Mapper.xml文件中的SQL语句怎么写？  
    ```
    public interface EmployeeMapper {
      Employee getEmpByID(List id);
      Employee getEmpByID(Integer id);
    }
    ```
    错误示例  
    ```
    <select id="getEmpByID" resultType="Employee" databaseId="mysql">
               select id, last_name, gender, email
                 from tbl_employee
                 where id = #{id}
    </select>
    <select id="getEmpByID" resultType="Employee" databaseId="mysql">
                  select id, last_name, gender, email
                    from tbl_employee
                    where id = #{list[0]}
    </select>
    ```
2. 在[mapper](https://github.com/ThemanerL/Study_framework/blob/master/src/mybatis/dao/EmployeeMapper.java)映射文件中加入了<cache>标签,且在[mybatis-config.xml](https://github.com/ThemanerL/Study_framework/tree/master/conf/mybatis/mybatis-config.xml)中配置了<setting
 name="cacheEnabled" value="true"/>,并且sqlSession已经使用try-with-resource包围
 后 **Cache Hit Ratio \[EmployeeMapper]: 0.0**** 依然为0,并且SQL执行了两次.[源码](https://github.com/ThemanerL/Study_framework/blob/master/src/test/mybatis/dao/CacheTest.java)