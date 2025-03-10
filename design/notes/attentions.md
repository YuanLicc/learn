[本文部分摘自书籍《大话设计模式》](https://www.amazon.cn/dp/B00YXHUF10/ref=sr_1_4?s=books&ie=UTF8&qid=1525329136&sr=1-4&keywords=%E5%A4%A7%E8%AF%9D%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F)

## 注意事项

​Java中除了语法以外的一些注意事项。

### 构造器

- 构造器中不创建自身的实例，下面是不好的做法：

  ```java
  class A {
      private A a;
      public A(){
          a = new A();
          A b = new A();
      }
  }
  ```

- 如果你在构造器中调用当前类中的方法，那么被调用的方法应该被`private`修饰，原因是考虑到当前类可能被继承，而`private`确保了不被子类重载，避免了重载造成的递归调用父类构造器时多态调用子类重载的方法，使得父类构造器的行为出现错误。例子如下：

  ```java
  class A {
      private int num;
      
      public A(){
          defaultNum();
      }
      
      public void defaultNum(){
          num = 20;
      }
  }
  
  class B extends A {
      public B(){
          //nothing to do
      }
      
      @override
      public void defaultNum(){
          //nothing to do
      }
  }
  ```

  