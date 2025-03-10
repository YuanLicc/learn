## Spring 相关

### 1. @transactional注解在什么情况下会失效，为什么？

一个目标对象的方法调用改目标对象的另外一个方法时，即使被调用的方法已使用了@Transactional注解标记，事务也不会有效执行；Spring的官方说明在代理下（默认或者配置为proxy-targer-class="true"），只有当前代理类的外部方法调用注解方法时代理才会被拦截。



### 2. Spring使用了哪些设计模式？

1）工厂模式，在各种BeanFactory以及ApplicationContext创建中都用到了。

2）模板模式，也是在各种BeanFactory以及ApplicationContext创建中都用到了。

3）代理模式，在AOP实现中用到了JDK的动态代理。

4）单例模式，比如创建bean的时候。

5）策略模式，第一个地方，加载资源文件的地方，使用了不同的方法，比如：classPathResource，FileSystemResource，ServletContextResource，UrlResource但他们都有共同的接口Resource。第二个地方就是AOP的实现中，采用了不同的方式，JDK动态代理和CGLIB代理。



### 3. SpringMvc工作原理

1）用户发送请求至前端控制器DispatcherServlet。

2）DispatcherServlet收到请求调用HandlerMapping处理映射器。

3）处理器映射器找到具体的处理器（可以根据xml配置、注解进行查找），生成处理器对象及处理器拦截器（如有则生成）一并返回给DispatcherServlet。

4）DispatcherServlet调用HandlerAdapter处理器映射器。

5）HandlerAdapter经过适配调用具体的处理器（Controller，也叫后端控制器）。

6）Controller执行完成返回ModelAndView。

7）HandlerAdapter将Controller执行结果ModelAndView返回给DispatcherServlet。

8）DispatcherServlet将ModelAndView传给ViewResolver视图解析器。

9）ViewResolver解析后返回具体的view。

10）DispatcherServlet根据view进行试图渲染（即将模型数据填充至视图中）。

11）DispatcherServlet响应用户。　

以下组件通常使用框架提供实现：

DispatcherServlet：作为前端控制器，整个流程控制的中心，控制其它组件执行，统一调度，降低组件之间的耦合性，提高每个组件的扩展性。

HandlerMapping：通过扩展处理器映射器实现不同的映射方式，例如：配置文件方式，实现接口方式，注解方式等。 

HandlAdapter：通过扩展处理器适配器，支持更多类型的处理器。

ViewResolver：通过扩展视图解析器，支持更多类型的视图解析，例如：jsp、freemarker、pdf、excel等。



### 4. Spring的IOC有什么优势？

要了解IOC首先要明白依赖倒置原则（Dependency Inversion Principle），就是把原本的高层建筑依赖底层建筑倒置过来，变成底层建筑依赖高层建筑。高层建筑决定需要什么，底层去实现这样的需求，但是高层并不用管底层的是怎么实现的；而控制反转（Inversion of Control）就是依赖倒置原则的一种代码的设计思路。IOC思想的核心，资源不由使用资源的双方管理，由不适用资源的第三方管理。

优势：资源集中管理，实现资源的可配置和易管理；降低了使用资源双方的依赖程度，也就是降低了耦合度。