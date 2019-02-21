1. 单个参数：Mybatis不会做特殊处理-->
#{参数名}：取出参数值  
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

    #{指定的Key}即可取出对应的参数值
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
![List在mybatis处理后的结构](https://makedown-1257967443.cos.ap-guangzhou.myqcloud.com/List%E5%9C%A8mybatis%E5%A4%84%E7%90%86%E5%90%8E%E7%9A%84%E7%BB%93%E6%9E%84.png?q-sign-algorithm=sha1&q-ak=AKID6ASqQObo2DbLPGdBi85CuwIL5ZBKcV6v&q-sign-time=1550751351;1550753151&q-key-time=1550751351;1550753151&q-header-list=&q-url-param-list=&q-signature=d4536fb5021632c1894801d81b22ee9599995a26&x-cos-security-token=15634342977efbdfe40f629599b85ba1137aac9e10001)
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
