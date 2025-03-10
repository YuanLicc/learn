## volatile 相关

### 1. Java 中能创建 volatile 数组吗？

​	能，`Java` 中可以创建 `volatile` 类型数组，不过只是一个指向数组的引用，而不是整个数组。我的意思是，如果改变引用指向的数组，将会受到 `volatile` 的保护，但是如果多个线程同时改变数组的元素，`volatile` 标示符就不能起到之前的保护作用了。  

### 2. volatile 能使得一个非原子操作变成原子操作吗？  

​	能，一个典型的例子是在类中有一个 `long` 类型的成员变量。如果你知道该成员变量会被多个线程访问，如计数器、价格等，你最好是将其设置为 `volatile`。为什么？因为 `Java` 中读取 `long` 类型变量不是原子的，需要分成两步，如果一个线程正在修改该 `long` 变量的值，另一个线程可能只能看到该值的一半（前 32 位）。但是对一个 `volatile` 型的 `long` 或 `double` 变量的读写是原子。  

### 3. volatile 修饰符的有过什么实践？

​	一种实践是用 `volatile` 修饰 `long` 和 `double` 变量，使其能按原子类型来读写。`double` 和 `long` 都是64位宽，因此对这两种类型的读是分为两部分的，第一次读取第一个 32 位，然后再读剩下的 32 位，这个过程不是原子的，但 `Java` 中 `volatile` 型的 `long` 或 `double` 变量的读写是原子的。																	        							volatile` 修饰符的另一个作用是提供内存屏障（`memory barrier`），例如在分布式框架中的应用。简单的说，就是当你写一个 `volatile` 变量之前，`Java` 内存模型会插入一个写屏障（`write barrier`），读一个 `volatile` 变量之前，会插入一个读屏障（`read barrier`）。意思就是说，在你写一个 `volatile` 域时，能保证任何线程都能看到你写的值，同时，在写之前，也能保证任何数值的更新对所有线程是可见的，因为内存屏障会将其他所有写的值更新到缓存。

### 4. volatile 类型变量提供什么保证？

​	volatile` 变量提供顺序和可见性保证。` 

​	例如`JVM` 或者 `JIT`为了获得更好的性能会对语句重排序，但是 `volatile` 类型变量即使在没有同步块的情况下赋值也不会与其他语句重排序。 `volatile` 提供 `happens-before` 的保证，确保一个线程的修改能对其他线程是可见的。某些情况下，`volatile` 还能提供原子性，如读 64 位数据类型，像 `long` 和 `double` 都不是原子的，但 `volatile` 类型的 `double` 和 `long` 就是原子的。 



## volatile知识点

​	关键词volatile可以说是Java虚拟机提供的最轻量级的同步机制，当一个变量定义为volatile之后，它将具备两种特性，第一是**保证此变量对所有线程的可见性**，这里的可见性是指当一个线程修改了这个变量的值，新值对于其他线程来说是可以立即得知的。

​	volatile变量对所有线程是立即可见的，对volatile变量所有的写操作都能立刻反应到其它线程之中，换句话说，volatile变量在各个线程中是一致的，**但并不能说基于volatile变量的运算在并发条件下是安全的**。volatile变量在各个线程的工作内存中不存在一致性问题（在各个线程的工作内存中，volatile变量也可以存在不一致的情况，但由于每次使用之前都要先刷新，执行引擎看不到不一致的情况，因此可以认为不存在一致性问题），但是Java里面的运算并非原子操作，导致volatile变量的运算在并发下一样是不安全的。

```java
/**
* volatile变量自增运算测试
*/
public class VolatileTest {
    public static volatile int race = 0;
    
    public static void increase() {
        race++;
    }
    
    private static final int THREADS_COUNT = 20;
    
    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        
        // 等待所有累加线程都结束
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        
        System.out.println(race);
    }
}
```

​	上述代码发起了20个线程，每个线程对race变量进行10000次自增操作，如果这段代码能够正确并发的话，最后输出的结果应该是200000。当我们实际运行程序后会发现，每次运行的结果都不一样，并且都小于200000。问题出现在“race++”之中，利用Javap反编译这段代码后我们会发现increase()方法在Class文件中是由4条字节码指令构成的：`getstatic`、`iconst_1`、`iadd`、`putstatic`，当`getstatic`指令把race的值取到操作栈顶时，volatile关键字保证了race的值在此时是正确的，但是在执行`iconst_1`、`iadd`这些指令的时候，其它线程可能已经把race的值加大了，而在操作栈顶的值就变成了过期的数据，所以`putstatic`指令执行后就可能把较小的race值同步回主内存中。

​	由于volatile变量只能保证可见性，在不符合以下两条规则的运算场景中，我们仍然要通过加锁（synchronized或java.util.concurrent中的原子类）来保证原子性。

- 运算结果并不依赖变量的当前值，或者能够保证只有单一的线程修改变量的值。
- 变量不需要与其他的状态变量共同参与不变约束。

​        使用volatile变量的第二个语义是**禁止指令重排序优化**，普通的变量仅仅会保证在该方法执行过程中所有依赖赋值结果的地方都能获取到正确的结果，而不能保证变量赋值操作的顺序与程序代码中的执行顺序一致。因为在一个线程的方法执行过程中无法感知到这点，这也就是Java内存模型中描述的所谓的“线程内表现为串行的语义”。

```java
Map configOptions;
char[] configText;
// 此变量必须定义为volatile
volatile boolean initialized = false;

// 假设以下代码在线程A中执行
// 模拟对去配置信息，当读取完成后将initialized设置为true以通知其他线程配置可用
configOptions = new HashMap();
configText = readConfigFile(fileName);
processConfigOptions(configText, configOptions);
initialized = true;

// 假设以下代码在线程B中执行
// 等待initialized为true，代表线程A已经把配置信息初始化完成
while (!initialized) {
    sleep();
}

// 使用线程A中初始化好的配置信息
doSomethingWithConfig();
```

​	上述代码中，如果定义initialized变量时没有使用volatile修饰，就可能会由于指令重排序的优化，导致位于线程A中最后一句的代码`initialized = true`被提前执行（这里虽然使用Java作为伪代码，但所指的重排序优化是机器级的优化操作，提前执行是指这句话对应的汇编代码被提前执行），这样在线程B中使用配置信息的代码就可能出现错误，而volatile关键字则可以避免此类情况的发生。

​	这里需要注意一点，volatile屏蔽指令重排序的语义在JDK1.5中才被完全修复，此前的JDK中即使将变量声明为volatile也仍然不能完全避免重排序所导致的问题（主要是volatile变量前后的代码仍然存在重排序问题），这点也是在JDK1.5之前的Java中无法安全的使用DCL（双锁检查）来实现单例模式的原因。

```java
/**
* DCL
*/
public class Singleton {
    private volatile static Singleton instance;
    
    private Singleton{}
    
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
    
    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
```

​	通过获取上述代码的JIT的汇编代码（HSDIS插件），对比没有volatile修饰的代码，会发现在volatile修饰的变量赋值后多执行了一个`lock addl $0x0, (%esp)`操作，这个操作相当于一个内存屏障（指令重排序时不能把后面的指令重排序到内存屏障之前的位置）。这句指令中的`addl $0x0, (%esp)`（把ESP寄存器的值加0）显然是一个空操作（采用这个空操作而不是采用空操作的指令nop是因为IA32手册规定lock前缀不允许配合nop指令使用），关键在于lock前缀，它的作用是使得本CPU的Cache写入了内存，该写入动作也会引起别的CPU或者别的内核无效化其Cache，这种操作相当于对Cache中的变量做了一次Java内存模型中所说的`store`和`write`操作。所以通过这样一个空操作，可让前面volatile变量的修改对其他CPU立即可见。

​	在某些情况下volatile的同步机制的性能确实要优于锁（使用synchronized关键字或java.util.concurrent包里面的锁），但是由于虚拟机对锁实行了许多消除和优化，使得我们很难量化的认为volatile就会比synchronized快多少。如果让volatile自己与自己比较，那就可以确定一个原则：volatile变量读操作的性能消耗与普通变量几乎没有什么差别，但是写操作则可能会慢一些，因为它需要在本地代码中插入许多内存屏障指令来保证处理器不发生乱序执行。不多即便如此，大多数场景下volatile的总开销仍然要比锁低，我们在volatile和锁之中选择的唯一依据仅仅是volatile的语义是否满足使用场景的需求。

​	Java内存模型中对volatile变量定义了一些特殊规则，假定T表示一个线程，V和W分别表示两个volatile型变量，那么在进行read、load、use、assign、store和write操作时需要满足如下规则：

- 在工作内存中，每次使用V前都必须先从主内存刷新最新的值，用于保证能看见其它线程对变量V所做的修改后的值。
- 在工作内存中，每次修改V后都必须立刻同步回主内存中，用于保证其它线程可以看到自己对变量V所做的修改。
- volatile修饰的变量不会被指令重排序优化，保证代码的执行顺序与程序的顺序相同。

从硬件架构上来讲，指令重排序是指CPU采用了允许将多条指令不按程序规定的顺序分开发送给相应电路单元处理。但并不是说指令任意重排，CPU需要能正确处理指令依赖情况以保障程序能得出正确的执行结果。譬如指令1把地址A中的值加10，指令2把地址A中的值乘以2，指令3把地址B中的值减去3，这是指令1和指令2是有依赖的，他们之间的顺序不能重排——`(A + 10) * 2`与`A * 2 + 10`显然不相等，但是指令3可以重排到指令1、2之前或者中间，只要保证CPU执行后面依赖到A、B值的操作时能获取到正确的A和B的值即可。