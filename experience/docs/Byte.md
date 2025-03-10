## Byte 相关

### 1. 怎么将 byte 转换为 String？

可以使用 String 接收 byte[] 参数的构造器来进行转换，需要注意的点是要使用的正确的编码，否则会使用平台默认编码，这个编码可能跟原来的编码相同，也可能不同。  



### 2. Java 中怎样将 bytes 转换为 long 类型？

String接收bytes的构造器转成String，再Long.parseLong 



### 3. 我们能将 int 强制转换为 byte 类型的变量吗？如果该值大于 byte 类型的范围，将会出现什么现象？

是的，我们可以做强制转换，但是 Java 中 int 是 32 位的，而 byte 是 8 位的，所以，如果强制转化时，int 类型的高 24 位将会被丢弃，byte 类型的范围是从 -128 到 127。 



### 4. 写一段 Java 程序将 byte 转换为 long？

