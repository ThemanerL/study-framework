1. #### Spring的基本介绍
    IOC:在IOC容器未出现之前，对象创建以及依赖管理通常通过硬编码创建对象(构造函数、工厂类)、
    主动实例化、直接获取依赖、主动装配实现(setXxx)。  
        ```
        A a = new AImpl();  
        B b = new BImpl();  
        a.setB(b); 
        ```
    1. 这样以来带来的问题是:
        - 更换实现需要重新编译源代码
        - 依赖变更很难更换实现、难于测试
        - 耦合实例生产者和实例消费者 
    
    1. IOC容器解决的问题:
        - 创建对象和装配对象、管理对象生命周期
        - 被动实例化，被动接受依赖，被动装配    
    IOC容器之前，bean的管理由应用程序管理，现在是IOC容器管理，从应用程序转移到了IoC容器，所以叫**反转**。
    1. 控制反转中，控制到底控制什么？  
    控制的是应用程序的所依赖的bean资源、文件资源、数据库资源等
    1. 为什么要去控制？  
    主要是将依赖和实现分离，解耦组件之间的关联关系。
    这样以来依赖bean的消费者不直接依赖于bean提供者的组件设计方式，是一种减少类与类之间依赖的设计原则。
    DI:仅是用一个单独的对象（装配器）来装配对象之间的依赖关系，
    一般有setter、构造、接口注入等，与IOC不是一回事，仅是IOC依赖管理层面的东西。
    1. IOC容器对Bean的生命周期的管理过程:
        1. 通过构造器或工厂方法创建Bean实例  
        2. 为Bean的属性设置值和对其他Bean的引用  
        3. 调用Bean的初始化方法(设置init-method属性，自定义为Bean中的某个方法)  
        4. 关闭容器时，调用Bean的销毁方法(设置destroy-method属性，自定义为Bean中的某个方法)    
    
        Bean的Autowire，是\<Bean\>级别的标签，所以一旦使用，当前bean的所有引用属性都要使用Autowire进行自动装配。
        autowireByType在有一个以上该引用类型时，不可用。(明确清晰的配置文档更重要)
        
1. #### Spring对Bean的配置

    1. 使用Bean的scope属性来配置bean的作用域
        - singleton(单例):  默认值，容器初始化时创建bean实例，在整个容器的生命周期内只创建这一个bean。  
        - prototype(原型的):容器初始化时不创建bean的实例，而在每次请求时都创建一个新的Bean实例，并返回。  
        - request:Request作用域针对的是每次的Http请求，Spring容器会根据相关的Bean的定义来创建一个全新的Bean实例。
        而且该Bean只在当前request内是有效的。  
        - session:针对http session起作用，Spring容器会根据该Bean的定义来创建一个全新的Bean的实例。
        而且该Bean只在当前http session内是有效的。  
        - global session:类似标准的http session作用域，不过仅仅在基于portlet的web应用当中才有意义。
        Portlet规范定义了全局的Session的概念。他被所有构成某个portlet外部应用中的各种不同的portlet所共享。
        在global session作用域中所定义的bean被限定于全局的portlet session的生命周期范围之内。
    1. 使用注解在ClassPath中扫描组件
        Spring能够从classpath下自动扫描，侦测和实例化具有特定注解的实例.所有标注了注解的类将被ICO容器管理
        - @Component: 基本注解，标识了一个受Spring管理的组件
        - @Respository: 标识持久化组件
        - @Service: 标识服务层(业务层)组件
        - @Controller: 标识表现层组件  
        可以使用@Autowired(@Inject和@Resource类似，@Resource按名字匹配)来自动装配具有某个兼容类型的单个Bean属性，一切具
        有参数的方法都可以应用该注解，同时所有被@Autowired
        注解的属性都需要被设置.    
        当在组件类上使用了特定的注解后，还需要在Spring的配置文件中声明\<context:component-scan\>  
        \<context:exclude-filter 子节点指定**排除**哪些表达式的组件type:\>  
        \<context:include-filter 子节点指定**包含**哪些表达式的组件\>默认是全部都包含，所以如果指定只包含某些组件的话，
        要把use-default-filters设为false.
        type可以被设定为以下五个值:  
        - annotation   标记某一个类型的注解
        - assignable   标记某一个类(接口)以及其所有的实现类\继承类
        - aspectj      指向一个AspectJ类型
        - regex        一个与类名匹配的正则表达式
        - custom       一个org.springframework.core.type.TypeFilter接口的自定义实现
1. #### Spring的IOC 
    1. AOP(Aspect-Oriented Programming, 面向切面编程): 是一种新的方法论, 是对传统 OOP(Object-Oriented Programming, 面向对象编程) 的补充.
        AOP 的主要编程对象是切面(aspect), 而切面模块化横切关注点.在应用 AOP 编程时, 仍然需要定义公共功能, 
        但可以明确的定义这个功能在哪里, 以什么方式应用, 并且不必修改受影响的类. 这样一来横切关注点就被模块化到特殊的对象(切面)里.
        AOP 的好处:  
        每个事物逻辑位于一个位置, 代码不分散, 便于维护和升级。业务模块更简洁, 只包含核心业务代码.
    2. AOP术语:
        - 切面(Aspect):  横切关注点(跨越应用程序多个模块的功能)被模块化的特殊对象  
        - 通知(Advice):  切面必须要完成的工作  
        - 目标(Target): 被通知的对象  
        - 代理(Proxy): 向目标对象应用通知之后创建的对象  
        - 连接点（JoinPoint）:程序执行的某个特定位置:如类某个方法调用前、调用后、方法抛出异常后等。
            连接点由两个信息确定:方法表示的程序执行点；相对点表示的方位。例如 **Calculator.add() **方法执行前的连点**，
            执行点为 Calculator.add()； 方位为该方法执行前的位置  
        - 切点（pointcut）:每个类都拥有多个连接点:例如 ArithmethicCalculator 的所有方法实际上都是连接点，即连接点是程序
        类中客观存在的事务。AOP 通过切点定位到特定的连接点。类比:连接点相当于数据库中的记录，切点相当于查询条件。切点和
        连接点不是一对一的关系，一个切点匹配多个连接点，切点通过 org.springframework.aop.Pointcut 接口进行描述，它使用类和方法作为连接点的查询条件。  
        - 最典型的切入点表达式时根据方法的签名来匹配各种方法:
            execution * com.atguigu.main.java.spring.ArithmeticCalculator.*(..): 匹配 ArithmeticCalculator 中声明的所有方法,
            第一个 * 代表任意修饰符及任意返回值. 第二个 * 代表任意方法. .. 匹配任意数量的参数. 若目标类与接口与该切面在同一个包中, 可以省略包名.  
            execution public * ArithmeticCalculator.*(..): 匹配 ArithmeticCalculator 接口的所有公有方法.  
            execution public double ArithmeticCalculator.*(..): 匹配 ArithmeticCalculator 中返回 double 类型数值的方法   
            execution public double ArithmeticCalculator.*(double, ..): 匹配第一个参数为 double 类型的方法, .. 匹配任意数量任意类型的参数  
            execution public double ArithmeticCalculator.*(double, double): 匹配参数类型为 double, double 类型的方法.  
    3. 通知
        - 前置通知@Before:在方法执行之前
        - 后置通知@After:在后置通知中不能获得切点(目标方法)的执行的结果,因为无论方法中出现异常或者是出错,后置通知都将被执行
        - 返回通知@AfterReturning:在方法正常返回之后执行,在返回通过中可以获得方法的返回值
        - 异常通知@AfterThrowing:当目标方法发生指定异常的时候执行
        - 环绕通知@Around:类似于动态代理,需要携带ProcessingJoinPoint参数,该参数可以决定是否执行目标方法,有且必有一个返回值为目标方法的返回值
        当同时有多个通知工作时,通过指定@Order(n)来指定切面AspectJ的优先级,n越小优先级越高.  
        可以将切点@Pointcut声明切入点表达式将切点的声明从通知声明中抽离出来:
            ```java
              /**
               * 用 AspectJ 注解声明切面
               * 抽取切点以公用
               */
              @Pointcut(aspect)
              void declareJoinPointExpression(){}
            
              /**
               * 通过Before指定该方法在目标方法执行前执行
               */
              @Before("declareJoinPointExpression()")
              public void beforeMethod(JoinPoint point){
                System.out.println("LoggingAspect.before " + point.getSignature().getName() + " Method");
              }
              /**
            ```
        还可以通过XML文件的方式配置切面;使用
        
1. #### Spring的事务管理
    - 事务是企业级应用程序开发中必不可少的技术,用来确保数据的完整性和一致性
    1. Spring 既支持编程式事务管理, 也支持声明式的事务管理. 
        - 编程式事务管理: 将事务管理代码嵌入到业务方法中来控制事务的提交和回滚.在编程式管理事务时, 必须在每个事务操作中包含额外的事务管理代码. 
        - 声明式事务管理: 大多数情况下比编程式事务管理更好用. 它将事务管理代码从业务方法中分离出来,
     以声明的方式来实现事务管理. 事务管理作为一种横切关注点, 可以通过 AOP 方法模块化. Spring 通过 Spring AOP 框架支持声明式事务管理.
    1. 事务的传播行为:在Spring中通过@Transactional注解的propagation键值对来指定
        - REQUIRED--如果有事务在运行,当前的方法就在这个事务内运行，假设当前没有事务。就新建一个事务。默认配置。
        - REQUIRED_NEW--当前的方法必须启动一个新的事务并在它自己的事务内运行，假设当前有事务正在运行。把正在运行的事务挂起。 
        - SUPPORTS--如果当前有事务在运行,当前的方法就在这个事务内运行,否则它可以不运行在事务中.
        - MANDATORY--当前运行的方法必须运行在事务内部,如果没有正在运行的事务,就抛出异常.
        - NOT_SUPPORTED--以非事务方式运行操作。假设当前存在事务，就把当前事务挂起。
        - NEVER--当前的方法不应当运行在事务中,如果有事务正在运行就抛出异常
        - NESTED--如果有事务在运行,当前的方法就应该在这个事务的嵌套事务内运行,否则就启动一个新的事务,并在自己的事务内运行 
    1. 事务的隔离级别:在Spring中通过@Transactional注解的isolation键值对来指定
        [详](https://github.com/ThemanerL/Study_framework/blob/master/src/interview/%E6%95%B0%E6%8D%AE%E5%BA%93%E7%9B%B8%E5%85%B3.md)
        默认情况下Spring对所有的运行时异常进行回滚,也可以自定义对某些异常进行回滚或者定义对某些异常不进行回滚.
    1. 事务属性
        - 只读事务readOnly=true:表示这个事务只读取数据但不更新数据,这样可以帮助数据库引擎优化事务
        - 事务超时timeOut:在事务回滚之前事务的最大可占用时间
    1. 也可以的XML文件中配置事务的属性,包括根据方法名指定事务的属性
        ```xml
        <tx:advice id="txAdvice" transaction-mangager="transactionManager">
          <tx:attributues>
            <tx:method name = "purchaseBook" propagation="REQUIRES_NEW" read-only="true">
          </tx:attributues>
        </tx:advice>
        ```
        以及配置事务切入点,和把事务切入点和事务属性关联起来
        ```xml
        <aop:config>
          <aop:pointcut expression="execution(* …… )" id="txPointCut"/>
          <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointCut"/>
        </aop:config>
        ```
1. Spring在web应用
    - Spring应用于web应用的思路:
        1. 应该在Web应用被初始化的时候就加载IOC容器,即在```ServletContextListener#ContextInitialized(ServletContextEvent sec)```
        方法中创建IOC容器
        1. 创建完IOC容器后,可以把IOC容器放在ServletContext(即application域)的一个属性中,以便被web应用中的其他组件访问。
        另外,Spring配置文件的名字也位置也应该是在java文件外配置的,可以将其配置到当前web应用的初始化参数中
    - 在web下使用Spring
        ```xml
        <!--  配置Spring框架的名称和位置 -->
        <context-param>
          <param-name>contextConfiguration</param-name>
          <param-value>classpath:applicationContext.xml</param-value>
        </context-param>
        
        <!-- 启动IOC容器的ServletContextListener -->
        <listener>
          <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
        </listener>
        ```
### Question
