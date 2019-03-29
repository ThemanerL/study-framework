1. ### REST  
    即 Representational State Transfer。（资源）表现层状态转化。是目前最流行的一种互联网软件架构。它结构清晰、符合标准、
    易于理解、扩展方便所以正得到越来越多网站的采用  
    - 表现层（Representation）   
    把资源具体呈现出来的形式，叫做它的表现层Representation）。比如，文本可以用 txt 格式表现，也可以用 HTML 格式、XML 格
    式、JSON 格式表现，甚至可以采用二进制格式。  
    - 状态转化（State Transfer）  
    每发出一个请求，就代表了客户端和服务器的一次交互过程。HTTP协议，是一个无状态协议，即所有的状态都保存在服务器端。因此
    ，如果客户端想要操作服务器，必须通过某种手段，让服务器端发生“状态转化”（State Transfer）。而这种转化是建立在表现层
    之上的，所以就是 “表现层状态转化”。具体说，就是 HTTP 协议里面，四个表示操作方式的动词:GET、POST、PUT、DELETE。
    它们分别对应四种基本操作:GET 用来获取资源，POST 用来新建资源，PUT 用来更新资源，DELETE 用来删除资源。  
        - 示例  
        /order/1  HTTP GET:得到 id = 1 的 order      
        /order/1  HTTP DELETE:删除 id = 1的 order      
        /order/1  HTTP PUT:更新id = 1的 order      
        /order&nbsp;&nbsp;&nbsp;&nbsp;HTTP POST:新增 order    
    - HiddenHttpMethodFilter      
    浏览器 form 表单只支持 GET与 POST 请求，而DELETE、PUT 等 method 并不支持，Spring3.0 添加了一个过滤器，可以将这些请求
    转换为标准的 http 方法，使得支持 GET、POST、PUT 与DELETE 请求。
    注意:使用@RestController注解，可以解析form表单中的_method转为REST风格的请求，但是无法解析HTML和JSP.待研究
1. ### HTTP请求报文的一些办法:  

    |操作|意义|
    |:--|:--|
    |OPTION|请求一些选项的信息|
    |GET|请求读取由URL所标记的信息|
    |HEAD|请求读取由URL所标记的信息的首部|
    |POST|给服务器添加信息(例如:注释)|
    |PUT|在指明的URL下存储一个文档|
    |DELETE|删除指明的URL所标记的资源|
    |TRACE|用来进行环回测试(环回测试（loopback test）是一种测试，其中从通讯设备发出的信号又返回（环回）到原处，它是决定
    设备是否正常运行或是确定网络中失效节点的方式。)的请求报文|
    |CONNECT|用来代理服务器|  
    
1. ### 处理模型数据  
    - ModelAndView: 处理方法返回值类型为 ModelAndView时， 方法体即可通过该对象添加模型数据
    控制器处理方法的返回值如果为ModelAndView，则其即包含视图信息，也包含模型信息  
    - Map 及 Model: 入参为org.springframework.ui.Model、org.springframework.ui.ModelMap 或 java.uti.Map 时，处理方法返
    回时，Map中的数据会自动添加到模型中。  
    - @SessionAttributes: 将模型中的某个属性暂存到HttpSession 中，以便多个请求之间可以共享这个属性  
    - @ModelAttribute: 方法入参标注该注解后， 入参的对象就会放到数据模型中  
1. ### \@ModelAttribute分析
    使用前后对比:
    传统的方法是取出数据库中的对象，然后前台传回一个对象，将部分前台不能给的值从数据库对象中的值取出赋对前台传回的对象再存
    入数据库  
    ![springmvc_modelAttributrBefore](https://makedown-1257967443.cos.ap-guangzhou.myqcloud.com/springmvc_modelAttributrBefore.jpg)        
    使用该注解后可以将数据库中的某个对象取出通过MVC给到前台，前台进行操作后再把原对象返回  
    ![springmvc_modelAttributrAfter](https://makedown-1257967443.cos.ap-guangzhou.myqcloud.com/springmvc_modelAttributrAfter.jpg)  
    运行流程:
    1. 执行 @ModelAttribute 注解修饰的方法: 从数据库中取出对象， 把对象放入到了 Map 中。 键为: user
    2. SpringMVC 从 Map 中取出 User 对象， 并把表单的请求参数赋给该 User 对象的对应属性。
    3. SpringMVC 把上述对象传入目标方法的参数。   
    注意: 在 @ModelAttribute 修饰的方法中， 放入到 Map 时的键需要和目标方法入参类型的第一个字母小写的字符串一致!
    
    ### #SpringMVC 确定目标方法 POJO 类型入参的过程
    1. 确定一个 key:  
        1. 若目标方法的 POJO 类型的参数木有使用 @ModelAttribute 作为修饰， 则 key 为 POJO 类名第一个字母的小写  
        2. 若使用了  @ModelAttribute 来修饰， 则 key 为 @ModelAttribute 注解的 value 属性值。   
    2. 在 implicitModel 中查找 key 对应的对象， 若存在， 则作为入参传入  
        1. 若在 @ModelAttribute 标记的方法中在 Map 中保存过， 且 key 和 1 确定的 key 一致， 则会获取到。   
    3. 若 implicitModel 中不存在 key 对应的对象， 则检查当前的 Handler 是否使用 @SessionAttributes 注解修饰， 
    若使用了该注解， 且 @SessionAttributes 注解的 value 属性值中包含了 key， 则会从 HttpSession 中来获取 key 所
    对应的 value 值， 若存在则直接传入到目标方法的入参中。 若不存在则将抛出异常。   
    4. 若 Handler 没有标识 @SessionAttributes 注解或 @SessionAttributes 注解的 value 值中不包含 key， 则
    会通过反射来创建 POJO 类型的参数， 传入为目标方法的参数  
    5. SpringMVC 会把 key 和 POJO 类型的对象保存到 implicitModel 中， 进而会保存到 request 中。 
    
    ### #ModelAttribute源代码分析
    1. 调用 @ModelAttribute 注解修饰的方法。 实际上把 @ModelAttribute 方法中 Map 中的数据放在了 implicitModel 中。
    1. 解析请求处理器的目标参数， 实际上该目标参数来自于 WebDataBinder 对象的 target 属性
    1. 创建 WebDataBinder 对象:
        1. 确定 objectName 属性:   
        若传入的 attrName 属性值为 ""， 则 objectName 为类名第一个字母小写。 
        注意: attrName. 若目标方法的 POJO 属性使用了 @ModelAttribute 来修饰， 则 attrName 值即为 @ModelAttribute 
        的 value 属性值 
        2. 确定 target 属性:  
        在 implicitModel 中查找 attrName 对应的属性值。 若存在， ok
        若不存在: 则验证当前 Handler 是否使用了 @SessionAttributes 进行修饰， 若使用了， 则尝试从 Session 中
        获取 attrName 所对应的属性值。 若 session 中没有对应的属性值， 则抛出了异常。 
        若 Handler 没有使用 @SessionAttributes 进行修饰， 或 @SessionAttributes 中没有使用 value 值指定的 key
        和 attrName 相匹配， 则通过反射创建了 POJO 对象
    1. SpringMVC 把表单的请求参数赋给了 WebDataBinder 的 target 对应的属性。 
    1. SpringMVC 会把 WebDataBinder 的 attrName 和 target 给到 implicitModel。 
    近而传到 request 域对象中。 
    1. 把 WebDataBinder 的 target 作为参数传递给目标方法的入参。 
1. ### 视图解析
    - 视图的作用是渲染模型数据，将模型里的数据以某种形式呈现给客户。视图对象由视图解析器负责实例化。由于视图是无状态的，
    即每个请求都会创建一个新的视图所以他们不会有线程安全的问题
    请求处理方法执行完成后，最终返回一个 ModelAndView对象。对于那些返回 String，View 或 ModeMap 等类型的处理方法，Spring
    MVC 也会在内部将它们装配成一个ModelAndView 对象，它包含了逻辑名和模型对象的视图
    Spring MVC 借助视图解析器（ViewResolver）得到最终的视图对象（View），最终的视图可以是 JSP，也可能是Excel、JFreeChart
    等各种表现形式的视图
1. ### \<mcv:annotation-driven>  
    该注解会自动注册 RequestMappingHandlerMapping、RequestMappingHandlerAdapter与
    ExceptionHandlerExceptionResolver  三个bean。还将提供以下支持  
    - 支持使用 ConversionService 实例对表单参数进行类型转换  
    - 支持使用 @NumberFormat annotation、@DateTimeFormat  
    - 注解完成数据类型的格式化  
    - 支持使用 @Valid 注解对 JavaBean 实例进行 JSR 303 验证  
    - 支持使用 @RequestBody 和 @ResponseBody 注解  
1. 在需要格式化的Date时间上添加注解@DateTimeFormat(pattern="yyyy-MM-dd")指定时间字符串格式
1. ### 数据校验  
    Spring本身没有提供JSR303的实现，需要自行添加实现了JSE303的Jar包。
    在进行数据绑定时，可直接通过注解驱动同时调用校验框架完成数据校验工作，
    \<mvc:annotation-driven/> 会默认装配好一个LocalValidatorFactoryBean，通过在处理方法的入参上标注 @valid 注解即可让
    Spring MVC 在完成数据绑定后执行数据校验的工作
    - 在已经标注了 JSR303 注解的表单/命令对象前标注一个@Valid，Spring MVC 框架在将请求参数绑定到该入参对象后，就会调用校
    验框架根据注解声明的校验规则实施校验Spring MVC 是通过对处理方法签名的规约来保存校验结果的:前一个表单/命令对象的校验
    结果保存到随后的入参中，这个保存校验结果的入参必须是 BindingResult 或Errors 类型  
    **注意**:需校验的 Bean 对象和其绑定结果对象或错误对象时成对出现的，它们之间不允许声明其他的入参。必须挨着
    在 JSP 页面上可通过 \<form:errors path="userName">显示错误消息，如果需要定制错误消息，写一个国际化的配置文件，配置对SpringMVC即可
1. ### 使用SpringMVC处理Json
    1. 导入Json处理jar包，当前项目使用Gson
    2. 于MVC配置文件的\<mvc:annotation-driven>的子标签\<mvc:message-converters>中配置与引入Jar包相对应的消息转化器，
    3. 使用@ResponseBody注解希望返回Json格式数据的方法
    ![原理图](https://makedown-1257967443.cos.ap-guangzhou.myqcloud.com/springmvc_httpMessageConvert.jpg)
    - 使用HttpMessageConverter<T>
        使用 HttpMessageConverter<T> 将请求信息转化并绑定到处理方法的入参中或将响应结果转为对应类型的响应信息，Spring 提供了两种途径:
        - 使用 @RequestBody / @ResponseBody 对处理方法进行标注
        - 使用 HttpEntity<T> / ResponseEntity<T> 作为处理方法的入参或返回值  
    当控制器处理方法使用到 @RequestBody/@ResponseBody 或 HttpEntity<T>/ResponseEntity<T> 时， Spring 首先根据请求头或响应头的 
    Accept 属性选择匹配的 HttpMessageConverter，  进而根据参数类型或泛型类型的过滤得到匹配的 HttpMessageConverter， 若找不到可用的 
    HttpMessageConverter 将报错
1. ### 自定义拦截器
    - preHandle():这个方法在业务处理器处理请求之前被调用，在该方法中对用户请求 request 进行处理。如果程序员决定该拦截器对
      请求进行拦截处理后还要调用其他的拦截器和目标方法，则返回true；如果决定不需要再调用其他的拦截器和目标方法，则返回false。
      可以做权限，日志，事务等
    - postHandle():这个方法在业务处理器处理完请求后，但是DispatcherServlet 向客户端返回响应前被调用，在该方法中对用户请求request进行处理。
        目标方法之后，渲染视图之前。可以对视图或请求域中的属性进行修改。
    - afterHandle():释放资源
    在MVC配置文件的\<mvc:interceptors>中配置拦截器，在其子标签\<mvc:interceptor>中指定拦截/不拦截特定的URL  
    如果某个拦截器的preHandle已经执行了，然后别的拦截器在执行preHandle方法时返回了false，那么之前的已经执行preHandle方法的
    拦截器的后两个方法也会被执行
1. ### SpringMVC异常处理
    ExceptionHandlerExceptionResolver主要处理 Handler 中用 @ExceptionHandler 注解定义的方法。
    ExceptionHandlerMethodResolver内部若找不到@ExceptionHandler 注解的话，会找@ControllerAdvice 中的@ExceptionHandler 注解方法
    1. 在@ExceptionHandle注解的方法的入参中可以加入Exception类型的参数，该参数即对应发生的异常对象  
    2. 在@ExceptionHandle注解的方法的入参不能传入Map。如果希望把异常信息传到页面上，需要使用ModelAndView作为返回值
    3. 如果在当前Handler中找不到@ExceptionHandler注解的方法来处理当前方法出现的异常，则将去@ControllerAdvice标记的类中查找@ExceptionHandler
    注解的方法来处理异常
    - @ResponseStatus:例如定义一个 @ResponseStatus 注解修饰的异常类来处理UnauthorizedException异常,若在处理器方法中抛出了该异常,
    且ExceptionHandlerExceptionResolver 不解析述异常。由于触发的异常 UnauthorizedException 带有@ResponseStatus注解。因此会被
    ResponseStatusExceptionResolver 解析到。最后响应HttpStatus.* 给客户端。HttpStatus.UNAUTHORIZED 代表响应码401，无权限。
    关于其他的响应码请参考 HttpStatus 枚举类型源码
    - SimpleMappingExceptionResolver:如果希望对所有异常进行统一处理，可以在配置文件中配置SimpleMappingExceptionResolver，
    它将异常类名映射为视图名，即发生异常时使用对应的视图报告异常。
        ```xml
        <bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
            <property name="exceptionAttribute" value="ex"></property>
            <property name="exceptionMappings">
                <props>
                    <prop key="java.lang.ArrayIndexOutOfBoundsException">error</prop>
                </props>
            </property>
        </bean>	
        ```
1. ### SpringMVC执行流程  
    ![springMVC_执行流程](https://makedown-1257967443.cos.ap-guangzhou.myqcloud.com/springmvc_%E6%89%A7%E8%A1%8C%E6%B5%81%E7%A8%8B.png)
1. 在 Spring MVC 配置文件中引用业务层的 Bean多个 Spring IOC 容器之间可以设置为父子关系，以实现良好的解耦。Spring MVC WEB 
    层容器可作为 “业务层” Spring 容器的子容器：即 WEB 层容器可以引用业务层容器的 Bean，而业务层容器却访问不到 WEB 层容器的 Bean
---
##  Question   
1. Tomcat的首页设定为.html静态网页的时候，不能正常进入到静态页面。同时，视图解析器也不能解析转发到html?  
    **答**: 优雅的 REST 风格的资源URL 不希望带 .html 或 .do 等后缀，若将 DispatcherServlet 请求映射配置为 /，则 Spring MVC 将捕获  
    WEB容器的所有请求，包括静态资源的请求， SpringMVC 会将他们当成一个普通请求处理，因找不到对应处理器将导致错误。可以在 SpringMVC 的配置文件中配置 \<mvc:default-servlet-handler/\> 的方式解决静态资源的问题。  
    1. 激活Tomcat的defaultServlet来处理静态文件[detail](https://www.cnblogs.com/Jtianlin/p/5833669.html)  
        ```xml
        <servlet-mapping>
            <servlet-name>default</servlet-name>
            <url-pattern>.jpg</url-pattern>
        </servlet-mapping>
        <servlet-mapping>
            <servlet-name>default</servlet-name>
            <url-pattern>.js</url-pattern>
        </servlet-mapping>
        <servlet-mapping>
            <servlet-name>default</servlet-name>
            <url-pattern>.css</url-pattern>
        </servlet-mapping>
        ``` 
        要配置多个，每种文件配置一个，要写在DispatcherServlet的前面，让defaultServlet先拦截，这个就不会进入Spring了。
        Tomcat，Jetty，JBoss，and GlassFish默认Servlet的名字 — “default” 
        Google App Engine默认Servlet的名字 — “_ah_default”  
        Resin默认Servlet的名字 — “resin-file”   
        WebLogic默认Servlet的名字  — “FileServlet”  
        WebSphere默认Servlet的名字 — “SimpleFileServlet”  
    2. \<mvc:default-servlet-handler/> 将在 SpringMVC 上下文中定义一个DefaultServletHttpRequestHandler，它会对进入
    DispatcherServlet 的 请求进行筛查，如果发现是没有经过映射的请求，就将该请求交由 WEB应用服务器默认的 Servlet 处理，
    如果不是静态资源的请求，才由 DispatcherServlet 继续处理一般 WEB 应用服务器默认的 Servlet 的名称都是 default。若所使
    用的WEB 服务器的默认 Servlet 名称不是 default，则需要通过 default-servlet-name 属性显式指定
2. 	若 Spring 的 IOC 容器和 SpringMVC 的 IOC 容器扫描的包有重合的部分, 就会导致有的 bean 会被创建 2 次.  
    **答**:使用 exclude-filter 和 include-filter 子节点来规定只能扫描的注解，使 Spring 的 IOC 容器扫描的包和 SpringMVC 的
     IOC 容器扫描的包没有重合的部分即可    