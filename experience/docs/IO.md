## IO 相关

### 1. Java 中怎么创建 ByteBuffer？



### 2. Java 中，怎么读写 ByteBuffer ？



### 3. Java 采用的是大端还是小端？



### 4. ByteBuffer 中的字节序是什么？



### 5. Java 中，直接缓冲区与非直接缓冲器有什么区别？



### 6. Java 中的内存映射缓存区是什么？



### 7. socket 选项 TCP NO DELAY 是指什么？



### 8. TCP 协议与 UDP 协议有什么区别？



### 9. Java 中，ByteBuffer 与 StringBuffer有什么区别？ 



### 10. 说出 5 条 IO 的最佳实践?

IO 对 Java 应用的性能非常重要。理想情况下，你不应该在你应用的关键路径上避免 IO 操作。下面是一些你应该遵循的 Java IO 最佳实践： 

a）使用有缓冲区的 IO 类，而不要单独读取字节或字符。 

b）使用 NIO 和 NIO2 

c）在 finally 块中关闭流，或者使用 try-with-resource（Java7） 语句。 

d）使用内存映射文件获取更快的 IO。 



