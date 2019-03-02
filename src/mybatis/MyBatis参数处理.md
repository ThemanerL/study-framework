1. 单个参数：Mybatis不会做特殊处理-->
\#{参数名}：取出参数值  
2. 多个参数：Mybatis会做特殊处理。--> 多个参数会被封装为一个Map，@{
}就是从Map中取得指定Key的值  
Map<Key:param1,param2 ... Value:传入的参数值1, 值2, ...>  
Key也可以为参数的索引
3. 命名参数：在接口声明方法时，对参数添加注解明确指定封装参数时的Map的Key  
    ```
     /**
       * 根据ID和名字 获取员工
       * @param id /
       * @param lastName /
       * @return /
       */
      Employee getEmpByIdXLastName(@Param("id") Integer id, @Param("lastName") String lastName);
    ```
    > Key:使用@Param注解指定的值  
    > Value:参数值  

    \#{指定的Key}即可取出对应的参数值
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
    ```
    public Employee getEmpById(List<Integer> ids)
    取值:取出第一个id的值#{list[0]}
    
    源码：
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
<hr>

#### #{ }与${ }取值的区别：
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
    
    
