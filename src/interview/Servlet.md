1. 过滤器
    浏览器有很多的请求发送到servlet,虽然每一个请求的操作,参数可能都是不一样的,都是对于有些东西是一样的,比如说对设定编码
    格式,比如在响应的时候给所有的图片加水印不涉及任何业务逻辑处理.
    通过的请求对于资源的预处理相同的部分,就把这部分代码提取为公共的,先预处理后再调转到servlet,对服务器资源进行管理,保护
    servlet
    - 作用:对服务器接收的请求资源和响应给浏览器的资源进行管理  
    - 使用:
        1. 实现Filter接口实现init()、doFilter()、destroy()方法  
        - init():该方法服务器启动即执行,资源初始化  
        - doFilter():拦截请求的方法,在此方法中可以对资源实现管理.  
           注意:需要对请求进行手动放行chain.doFilter(request,response)  
        - destroy():在服务器关闭之前执行,进行资源释放等操作.  
        2. 在web.xml中配置过滤器  
        ```xml
          <filter>
              <filter-name></filter-name>
              <filter-class></filter-class>
          </filter>
          <filter-mapping>
              <filter-name></filter-name>
              <url-pattern></url-pattern>
          </filter-mapping>
        ```
        url-pattern:/* 拦截**所有**请求.
        3. 过滤器的生命周期  
            服务器启动到服务器关闭
        4. 适用案例:统一编码格式/session管理/权限管理/资源管理(统一水印,和谐词汇)
1. 监听器
    1. 专门监听Request,Session,Application的创建,销毁和内容的改变  
        Request:
        - ServletRequestListener:监听Request的创建和销毁:形参可以获取监听的Request对象sre.getServletRequest()
        - ServletRequestAttributeListener:监听Request对象作用域数据的变更(增加,删除,修改)方法的形参可以获取到被监听的数据
        srae.getName()和srae.getValue()
        Session:
        - HttpSessionListener:监听Session的创建和销毁,形参可以获取被监听的Session对象,se.getSession()
        - HttpSessionAttributeListener:监听Session数据的变更
        Application:
        - ServletContextListener:监听Application对象的初始化和销毁也就是在服务器启动和关闭的时候
        - ServletContextAttributeListener:监听Application的数据变更
    2. 在web.xml中配置
    ```xml
    <listener>
       <listener-class>监听器的全类名</listener-class>
    </listener>
    ```