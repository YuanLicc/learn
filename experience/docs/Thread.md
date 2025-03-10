## Thread 相关

### 1. 10 个线程和 2 个线程的同步代码，哪个更容易写？  

从写代码的角度来说，两者的复杂度是相同的，因为同步代码与线程数量是相互独立的。但是同步策略的选择依赖于线程的数量，因为越多的线程意味着更大的竞争，所以你需要利用同步技术，如锁分离，这要求更复杂的代码和专业知识。 



### 2. 你是如何调用 wait() 方法的？使用 if 块还是循环？为什么？  

`wait()` 方法应该在循环调用，因为当线程获取到 `CPU` 开始执行的时候，其他条件可能还没有满足，所以在处理前，循环检测条件是否满足会更好。下面是一段标准的使用 wait 和 notify 方法的代码：  

```java
// The standard idiom for using the wait method  
synchronized (obj) { 
    while (condition does not hold) 
        obj.wait();  
    // (Releases lock, and reacquires on wakeup) ... 
    // Perform action appropriate to condition 
} 
```

 参见 Effective Java 第 69 条，获取更多关于为什么应该在循环中来调用 wait 方法的内容。 



### 3. 什么是多线程环境下的伪共享（false sharing）？

伪共享是多线程系统（每个处理器有自己的局部缓存）中一个众所周知的性能问题。伪共享发生在不同处理器的上的线程对变量的修改依赖于相同的缓存行，伪共享问题很难被发现，因为线程可能访问完全不同的全局变量，内存中却碰巧在很相近的位置上。如其他诸多的并发问题，避免伪共享的最基本方式是仔细审查代码，根据缓存行来调整你的数据结构。



### 4. 什么是 Busy spin？我们为什么要使用它？

`Busy spin` 是一种在不释放 `CPU` 的基础上等待事件的技术。它经常用于避免丢失 `CPU` 缓存中的数据（如果线程先暂停，之后在其他`CPU`上运行就会丢失）。所以，如果你的工作要求低延迟，并且你的线程目前没有任何顺序，这样你就可以通过循环检测队列中的新消息来代替调用 sleep() 或 `wait()` 方法。它唯一的好处就是你只需等待很短的时间，如几微秒或几纳秒。LMAX 分布式框架是一个高性能线程间通信的库，该库有一个 BusySpinWaitStrategy 类就是基于这个概念实现的，使用 busy spin 循环 EventProcessors 等待屏障。  



### 5. Java 中怎么获取一份线程 dump 文件？

在 Linux 下，你可以通过命令 kill -3 PID （Java 进程的进程 ID）来获取 Java 应用的 dump 文件。在 Windows 下，你可以按下 Ctrl + Break 来获取。这样 JVM 就会将线程的 dump 文件打印到标准输出或错误文件中，它可能打印在控制台或者日志文件中，具体位置依赖应用的配置。如果你使用Tomcat。  



### 6. Swing 是线程安全的？

不是，Swing 不是线程安全的。你不能通过任何线程来更新 Swing 组件，如 JTable、JList 或 JPanel，事实上，它们只能通过 GUI 或 AWT 线程来更新。这就是为什么 Swing 提供 invokeAnd`wait()` 和 invokeLater() 方法来获取其他线程的 GUI 更新请求。这些方法将更新请求放入 AWT 的线程队列中，可以一直等待，也可以通过异步更新直接返回结果。你也可以在参考答案中查看和学习到更详细的内容。  



### 7. 什么是线程局部变量？

当使用`ThreadLocal`维护变量时,ThreadLocal为每个使用该变量的线程提供独立的变量副本,每个线程都可以独立地改变自己的副本,而不会影响其它线程所对应的副本,是线程隔离的。线程隔离的秘密在于ThreadLocalMap类(ThreadLocal的静态内部类)  线程局部变量是局限于线程内部的变量，属于线程自身所有，不在多个线程间共享。Java 提供 ThreadLocal 类来支持线程局部变量，是一种实现线程安全的方式。但是在管理环境下（如 web 服务器）使用线程局部变量的时候要特别小心，在这种情况下，工作线程的生命周期比任何应用变量的生命周期都要长。任何线程局部变量一旦在工作完成后没有释放，Java 应用就存在内存泄露的风险。  ThreadLocal的方法：void set(T value)、T get()以及T initialValue()。  ThreadLocal是如何为每个线程创建变量的副本的：  首先，在每个线程Thread内部有一个ThreadLocal.ThreadLocalMap类型的成员变量threadLocals，这个threadLocals就是用来存储实际的变量副本的，键值为当前ThreadLocal变量，value为变量副本（即T类型的变量）。初始时，在Thread里面，threadLocals为空，当通过ThreadLocal变量调用get()方法或者set()方法，就会对Thread类中的threadLocals进行初始化，并且以当前ThreadLocal变量为键值，以ThreadLocal要保存的副本变量为value，存到threadLocals。然后在当前线程里面，如果要使用副本变量，就可以通过get方法在threadLocals里面查找。  总结：  a、实际的通过ThreadLocal创建的副本是存储在每个线程自己的threadLocals中的  b、为何threadLocals的类型ThreadLocalMap的键值为ThreadLocal对象，因为每个线程中可有多个threadLocal变量，就像上面代码中的longLocal和stringLocal；  c、在进行get之前，必须先set，否则会报空指针异常；如果想在get之前不需要调用set就能正常访问的话，必须重写initialValue()方法  



### 8. 用 wait-notify 写一段代码来解决生产者-消费者问题？

请参考答案中的示例代码。只要记住在同步块中调用 `wait()` 和 notify()方法，如果阻塞，通过循环来测试等待条件。  



###  9. 用 Java 写一个线程安全的单例模式（Singleton）？

请参考答案中的示例代码，这里面一步一步教你创建一个线程安全的 Java 单例类。当我们说线程安全时，意思是即使初始化是在多线程环境中，仍然能保证单个实例。Java 中，使用枚举作为单例类是最简单的方式来创建线程安全单例模式的方式。 

 

###  10. Java 中 sleep 方法和 wait 方法的区别？

虽然两者都是用来暂停当前运行的线程，但是 sleep() 实际上只是短暂停顿，因为它不会释放锁，而 `wait()` 意味着条件等待，这就是为什么该方法要释放锁，因为只有这样，其他等待的线程才能在满足条件时获取到该锁。



### 11. Java 中，编写多线程程序的时候你会遵循哪些最佳实践？

这是我在写Java 并发程序的时候遵循的一些最佳实践：  

a）给线程命名，这样可以帮助调试。 

b）如果可以，更偏向于使用 volatile 而不是 synchronized。  

c）优先使用并发集合，而不是对集合进行同步。并发集合提供更好的可扩展性。 



### 12. 说出至少 5 点在 Java 中使用线程的最佳实践。

对线程来说，你应该： 

a）对线程命名。

b）将线程和任务分离，使用线程池执行器来执行 Runnable 或 Callable。 

c）使用线程池 。



### 13. JAVA实现多线程的几种方式

a、继承Thread类实现。

b、实现Runnable接口。

c、使用ExecutorService、Callable、Future实现有返回结果的多线程。



### 14. ABC三个线程如何保证顺序执行？

用Thread.join() 方法，或者线程池newSingleThreadExecutor（原理是会将所有线程放入一个队列，而队列则保证了FIFO）,也可以通过ReentrantLock，state整数用阿里判断轮到谁来执行。



### 15. 线程的状态都有哪些（五大状态）？

- 新建状态（new）：当用new操作符创建一个线程时，如new Thread()，线程还没有开始运行，此时处于仙剑状态。

- 就绪状态（runnable）：一个新创建的线程并不自动开始运行，要执行线程，必须要调用线程的start()方法，当线程对象调用start()方法即启动了线程，start()方法创建线程运行的系统资源，并调度线程运行run()方法。当start（）方法返回后，线程就处于就绪状态。
- 运行状态（running）：当线程获得cpu时间后，他才进入运行状态，真正开始实行run()方法。

- 阻塞状态（blocked）：当线程运行过程中，可能由于各种原因进入阻塞状态。

　　　　a.线程通过调用sleep方法进入睡眠状态。

　　　　b.线程调用一个在I/O上被阻塞的操作，即该操作在输入输出操作完成之前不会返回到它的调用者。

　　　　c.线程试图得到一个锁，而该锁正被其他线程持有。

　　　　d.线程正等待某个触发条件。

- 死亡状态（dead）：run方法自然退出而自然死亡，或者一个未捕获的异常终止了run方法而使线程猝死。

 

### 16. sleep和wait的区别？

首先，sleep()方法属于Thread类的，而wait()方法是属于Object类的；sleep()方法导致了程序暂停执行指定的时间，让出cpu给其他线程，但是他的监控状态依然保持，当指定的时间到了又自动回恢复运行状态，调用了sleep()方法的过程中，线程不会释放对象锁；而当调用了wait()方法的时候，线程回放弃对象锁，进入等待此对象的等待锁定池，只有针对此对象调用notify()方法后本线程才进入对象锁定池准备。

 

### 17. notify()和notifyAll()的区别？

notify()方法表示，当前线程已经放弃对资源的占有，通知等待的线程来获取对资源的占有权，但是只有一个线程能够从wait状态中恢复；notifyAll()方法表示，当前的线程已经放弃对资源的占有，通知所有的等待线程从wait()方法后的语句开始执行，但最终只有一个线程能竞争获得锁并执行；notify()是对notifyAll()的一个优化。

 

### 18. ThreadLocal 的了解，实现原理。

ThreadLocal，线程本地变量。定义了一个ThreadLocal，每个线程往这个ThreadLocal中读写都是线程隔离的，互相之间不会影响，他提供了一种将可变数据通过每个线程有自己的独立副本从而实现线程封闭的机制；实现的思路，Thread类有一个类型为ThreadLocal.ThreadLocalMap的实例变量threadLocals，也就是说每个线程都有一个自己的ThreadLocalMap。ThreadLocalMap有自己的独立实现，可以简单的将它的key视作ThreadLocal，value为代码中放入的值（实际上key并不是ThreadLocal本省，而是它的一个弱引用）。每个线程在往ThreadLocal里set值的时候，都会往自己的ThreadLocalMap里存，读也是已某个ThreadLocal作为引用，在自己的map里找对应的key，从而实现了线程的隔离。[ThreadLocal源码解读](https://www.cnblogs.com/micrari/p/6790229.html)

