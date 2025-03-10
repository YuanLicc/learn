## Time 相关

### 1. 在多线程环境下，SimpleDateFormat 是线程安全的吗？

不是，非常不幸，DateFormat 的所有实现，包括 SimpleDateFormat 都不是线程安全的，因此你不应该在多线程序中使用，除非是在对外线程安全的环境中使用，如 将 SimpleDateFormat 限制在 ThreadLocal 中。如果你不这么做，在解析或者格式化日期的时候，可能会获取到一个不正确的结果。因此，从日期、时间处理的所有实践来说，我强力推荐 joda-time 库。



### 2. Java 中如何格式化一个日期？如格式化为 ddMMyyyy 的形式？

Java 中，可以使用 SimpleDateFormat 类或者 joda-time 库来格式日期。DateFormat 类允许你使用多种流行的格式来格式化日期。参见答案中的示例代码，代码中演示了将日期格式化成不同的格式，如 dd-MM-yyyy 或 ddMMyyyy。



### 3. Java 中，怎么在格式化的日期中显示时区？

pattern中加z yyyy-MM-dd HH:mm:ss.SSS Z



### 4. Java 中 java.util.Date 与 java.sql.Date 有什么区别？

java.sql.Date是针对SQL语句使用的，它只包含日期而没有时间部分,它们都有getTime方法返回毫秒数，自然就可以直接构建。java.util.Date 是 java.sql.Date 的父类，前者是常用的表示时间的类，我们通常格式化或者得到当前时间都是用他，后者之后在读写数据库的时候用他，因为PreparedStament的setDate()的第2参数和ResultSet的getDate()方法的第2个参数都是java.sql.Date。



### 5. Java 中，如何计算两个日期之间的差距？

```java
public static int dateDiff(Date d1, Date d2) throws Exception { 
    long n1 = d1.getTime(); 
    long n2 = d2.getTime(); 
    long diff = Math.abs(n1 - n2); 
    diff /= 3600  1000  24; 
    return diff; 
}
```



### 6. Java 中，如何将字符串 YYYYMMDD 转换为日期？

SimpleDateFormat的parse方法