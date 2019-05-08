1. ### JSP
- JSP九大内置对象
    1. request对象javax.servlet.http.HttpServletRequest 
        - request对象代表了客户端的请求信息，主要用于接受通过HTTP协议传送到服务器的数据。（包括头信息、系统信息、请求方式以及请求参数等）。
    2. response对象 javax.servlet.http.HttpServletResponse 
        - response 代表的是对客户端的响应，主要是将JSP容器处理过的对象传回到客户端。response对象也具有作用域，它只在JSP页面内有效。
    3. session对象 javax.servlet.http.HttpSession 
        ![Session时序图](https://www.ibm.com/developerworks/cn/java/j-lo-servlet/image023.jpg)
        - session 对象是由服务器自动创建的与用户请求相关的对象。服务器为每个用户都生成一个session对象，用于保存该用户的信息，跟踪用户的操作状态。
        1. 在Jsp/Servlet中，如果浏览器不支持**会话Cookie**，可以通过**URL重写**来实现，就是将一些额外数据追加到表示回话的每个URL末尾，服务器在该标识符与其存储的有关的该会话的数据之间建立关联。如hello.jsp?jsessionid=1234 
        2. 可以通过程序来终止一个会话。如果客户端在一定时间内没有操作，服务器会自动终止对话。
        3. 通过HttpSession来读写Session
    4. application对象javax.servlet.ServletContext 
        - application 对象可将信息保存在服务器中，直到服务器关闭，否则application对象中保存的信息会在整个应用中都有效。
    5. out 对象javax.servlet.jsp.jspWriter 
        - out 对象用于在Web浏览器内输出信息，并且管理应用服务器上的输出缓冲区。在使用 out 对象输出数据时，可以对数据缓冲区进行操作，及时清除缓冲区中的残余数据，为其他的输出让出缓冲空间。待数据输出完毕后，要及时关闭输出流。
    6. pageContext 对象javax.servlet.jsp.PageContext 
        - pageContext 对象的作用是取得任何范围的参数，通过它可以获取 JSP页面的out、request、reponse、session、application 等对象。
    7. config 对象javax.servlet.ServletConfig 
        - config 对象的主要作用是取得服务器的配置信息。
    8. cookie 对象
        - Cookie是Web服务器保存在用户硬盘上的一段文本。Cookie允许一个Web站点在用户电脑上保存信息并且随后再取回它。
    9. exception 对象java.lang.Throwable :
        - 显示异常信息，只有在包含 isErrorPage=”true” 的页面中才可以被使用，在一般的JSP页面中使用该对象将无法编译JSP文件。
- ---
1. ### servlet的生命周期
    1. Servlet类加载:
    启动web容器后，容器去寻找应用的部署描述文件（web.xml），从部署描述文件中读取到上下文初始化参数，此时创建一个ServletContext对象，应用的所有部分共享此上下文；创建Servlet对象，通过服务器反射机制创建Servlet对象，第一次请求时才会创建。（默认）
    2. 调用Servlet对象的init()方法，初始化Servlet的信息，init()方法只会在创建后被调用一次；
    3. 每次请求到来，都会调用service方法，在HttpServlet中，service方法用于判断请求的方法（不用重写），而去重写doGet方法或doPost方法。
    4. 在长时间没有被调用或者是服务器关闭时，会调用destroy()方法来销毁Servlet对象。
- Servlet中常用来存储数据的三大作用域:按照使用范围从小到大排列为: HttpServletRequest、HttpSession、ServletContext三个作用域，下边详细介绍这三个作用域。
    1. HttpServletRequest作用域:
        - 存入数据的方法request.setAttribute("User",user);(这里是把user放入到request作用域中，key是User,value是user),此作用域保存的数据只是针对一次请求。使用该对象保存数据，一次请求内数据有效。请求转发是属于一次请求的，所以放在此作用域中的数据，在一个页面转发多个页面数据都是有效的。
        - 作用域创建时间:客户端向服务器发送一次请求时创建。
        - 销毁时间:服务器为这次请求作出响应之后，销毁request.
    2. HttpSession作用域:
        - 针对一次会话，使用该对象保存数据，数据保存在服务器上，一次会话（多个请求）内数据有效，如果关闭一次浏览器，结束这次回话，再次打开的时候session就失效了。
        - 创建时间:服务器第一次调用getSession()方法的时候，服务器创建session对象。
        - 销毁时间:销毁有三种情况
            1. 服务器非正常关闭（正常关闭时:Session被序列化）；
            1. Session过期，xml文件配置默认时间是30分钟。
            1. 手动调用Session的invalidate的方法。
    3. ServletContext作用域:
        - 针对一个web应用。一个web应用只有一个ServletContext对象，使用该对象保存的数据在整个web应用中都有效。
        - 创建时间:服务器启动的时候。
        - 销毁时间:服务器关闭的时候或者项目移除的时候。
        - 总结:如果数据保存在request对象中，一般使用请求转发，来获取。因为请求转发是只有一次请求的。但是如果是重定向，因为重定向是多次请求，所以不能使用request中的数据，可以使用session或context中的。
- web.xml中真正的加载顺序为:context-param -> listener -> filter -> servlet
- \<jsp:forward>与response.sendRedirect的区别
    - \<jsp:forward>使用同一个request
    - response.sendRedirect是不同的request
- Servlet和CGI的区别。
    - Servlet被服务器实例化后，容器运行其init方法，请求到达时运行其service方法，service方法自动派遣运行与请求对应的doXXX方法（doGet，doPost）等，当服务器决定将实例销毁的时候调用其destroy方法。 
    - 与cgi的区别在于servlet处于服务器进程中，它通过多线程方式运行其service方法，一个实例可以服务于多个请求，并且其实例一般不会销毁，而CGI对每个请求都产生新的进程，服务完成后就销毁，所以效率上低于servlet。
---
1. #### 过滤器  
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
1. #### 监听器
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