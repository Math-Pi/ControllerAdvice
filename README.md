# SpringMVC 中 @ControllerAdvice 注解

@ControllerAdvice 的三个功能：

1. 全局异常处理
2. 全局数据绑定
3. 全局数据预处理

## 一、web.xml

```xml
<!-- 配置Spring MVC前端核心控制器 -->
<servlet>
    <servlet-name>springmvc</servlet-name>
	<servlet-class>
		    org.springframework.web.servlet.DispatcherServlet
	</servlet-class>
	<init-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>classpath:springmvc-config.xml</param-value>
	</init-param>
	<!-- 配置服务器启动后立即加载Spring MVC配置文件 -->
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
	<servlet-name>springmvc</servlet-name>
	<url-pattern>/</url-pattern>
</servlet-mapping>
```

## 二、springmvc-config.xml

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
  xmlns:mvc="http://www.springframework.org/schema/mvc"
  xmlns:context="http://www.springframework.org/schema/context"
  xmlns:tx="http://www.springframework.org/schema/tx"
  xsi:schemaLocation="http://www.springframework.org/schema/beans 
  http://www.springframework.org/schema/beans/spring-beans-4.3.xsd 
  http://www.springframework.org/schema/mvc 
  http://www.springframework.org/schema/mvc/spring-mvc-4.3.xsd 
  http://www.springframework.org/schema/context 
  http://www.springframework.org/schema/context/spring-context-4.3.xsd">
	<!-- 配置包扫描器，扫描@Controller注解的类 -->
	<context:component-scan base-package="com.cjm" />
	<!-- 加载注解驱动 -->
	<mvc:annotation-driven />
	<!-- 配置视图解析器 -->
	<bean class=
    "org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/jsp/" />
		<property name="suffix" value=".jsp" />
	</bean>
</beans>
```

## 三、@ControllerAdvice类

```java
@ControllerAdvice
public class MyGlobalExceptionHandler {
	/**
	 * 全局异常处理
	 * @return
	 */
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ModelAndView customException(Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "数组下标溢出");
        mav.setViewName("myerror");
        return mav;
    }
    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView customException(ArithmeticException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", e.getMessage());
        mav.setViewName("myerror");
        return mav;
    }
    /**
     * 	全局数据绑定
     *  @ModelAttribute 注解标记该方法的返回数据是一个全局数据
     */
    @ModelAttribute(name = "user")
    public Map<String,Object> mydata() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Tom");
        map.put("age", "23");
        return map;
    }
    /**
     *	  全局数据预处理
     */
    @InitBinder("s")
    public void student(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("s.");
    }
    @InitBinder("t")
    public void teacher(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("t.");
    }
}
```

## 四、实体类

- Student类

```java
public class Student {
	private String name;
	private String sno;
    //省略getter、setter方法
}
```

- Teacher类

```java
public class Teacher {
	private String name;
	private String tno;
    //省略getter、setter方法
}
```

## 五、控制类

```java
@RestController
public class MyController {
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
	int[] arr=new int[] {1,2,3};
	@RequestMapping("/test")
	public void test() {
		System.out.println(arr[3]);
	}
	@GetMapping("/user")
    public String user(Model model) {
        Map<String, Object> map = model.asMap();
        System.out.println(map);
        int i = 1 / 0;
        return "数据绑定";
    }
	@GetMapping("/info")
	public String addBook(@ModelAttribute("s") Student student, @ModelAttribute("t") Teacher teacher) {
	    System.out.println(student);
	    System.out.println(teacher);
	    return "数据预处理";
	}
}
```

