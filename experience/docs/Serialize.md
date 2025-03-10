## 序列化相关

### 1. Java 中，Serializable 与 Externalizable 的区别？

​	Serializable 接口是一个序列化 Java 类的接口，以便于它们可以在网络上传输或者可以将它们的状态保存在磁盘上，是 JVM 内嵌的默认序列化方式，成本高、脆弱而且不安全。Externalizable 允许你控制整个序列化过程，指定特定的二进制格式，增加安全机制。 



### 2. java序列化的方式？

实现Serializable接口、实现Externalizable接口（一般只希望序列化一部分数据，其他数据都使用transient修饰的话有点麻烦，这时候可以使用externalizable接口，指定序列化的属性）。



1、如果子类实现Serializable接口而父类未实现时，父类不会被序列化，但此时父类必须有个无参构造方法，否则会抛InvalidClassException异常。

2、静态变量不会被序列化，那是类的“菜”，不是对象的。

3、transient关键字修饰变量可以限制序列化。

4、虚拟机是否允许反序列化，不仅取决于类路径和功能代码是否一致，一个非常重要的一点是两个类的序列化 ID 是否一致，就是 private static final long serialVersionUID = 1L。

5、[Java](http://lib.csdn.net/base/javase) 序列化机制为了节省磁盘空间，具有特定的存储规则，当写入文件的为同一对象时，并不会再将对象的内容进行存储，而只是再次存储一份引用。反序列化时，恢复引用关系。

6、序列化到同一个文件时，如第二次修改了相同对象属性值再次保存时候，虚拟机根据引用关系知道已经有一个相同对象已经写入文件，因此只保存第二次写的引用，所以读取时，都是第一次保存的对象。读者在使用一个文件多次 writeObject 需要特别注意这个问题(基于第5点)。



- 当父类实现了Serializable接口的时候，所有的子类都能序列化
- 子类实现了Serializable接口，父类没有，父类中的属性不能被序列化(不报错，但是数据会丢失)
- 如果序列化的属性是对象，对象必须也能序列化，否则会报错
- 反序列化的时候，如果对象的属性有修改或则删减，修改的部分属性会丢失，但是不会报错
- 在反序列化的时候serialVersionUID被修改的话，会反序列化失败



## 1.1 序列化和反序列化

​	序列化(Serialization)是将对象的状态信息转换为可以存储或传输的形式的过程。一般将一个对象存储至一个存储媒介。在网络传输过程中，可以是字节或是XML等格式。而字节的或XML编码可以还原成完全相等的对象。

​	反序列化就是一个相反的过程，即把字节或XML编码可以还原成完全相等的对象。

​	在序列化过程中并不保存静态变量。使用Java对象序列化，在保存对象时，会把其状态保存为一组字节，在未来，再将这些字节组装成对象。必须注意地是，对象序列化保存的是对象的”状态”，即它的成员变量。由此可知，**对象序列化不会关注类中的静态变量**。 

​	Transient 关键字的作用是控制变量的序列化，在变量声明前加上该关键字，可以阻止该变量被序列化到文件中，在被反序列化后，transient 变量的值被设为初始值，如 int 型的是 0，对象型的是 null。 

## 1.2 Java对象的序列化和反序列化

 	在Java中，我们可以通过多种方式来创建对象，并且只要对象没有被回收我们都可以复用该对象。但是，我们创建出来的这些Java对象都是存在于JVM的堆内存中的。只有JVM处于运行状态的时候，这些对象才可能存在。一旦JVM停止运行，这些对象的状态也就随之而丢失了。 

## 1.3  Serializable接口

​	类通过实现java.io.Serializable 接口以启用其序列化功能。未实现此接口的类将无法使其任何状态序列化或反序列化。可序列化类的所有子类型本身都是可序列化的。 

​	当试图对一个对象进行序列化的时候，如果遇到不支持 Serializable 接口的对象。在此情况下，将抛出 `NotSerializableException`。

​	如果要序列化的类有父类，要想同时将在父类中定义过的变量持久化下来，那么父类也应该集成`java.io.Serializable`接口。

## 1.4 Externalizable接口

​	Externalizable继承了Serializable，该接口中定义了两个抽象方法：`writeExternal()`与`readExternal()`。当使用Externalizable接口来进行序列化与反序列化的时候需要开发人员重写`writeExternal()`与`readExternal()`方法。由于上面的代码中，并没有在这两个方法中定义序列化实现细节，所以输出的内容为空。

​	还有一点值得注意：在使用Externalizable进行序列化的时候，在读取对象时，会调用被序列化类的无参构造器去创建一个新的对象，然后再将被保存对象的字段的值分别填充到新对象中。所以，实现Externalizable接口的类必须要提供一个public的无参的构造器。 

## 1.5  Serializable是一个标志接口，怎样保证实现了该接口的类才能被序列化和反序列化？

​	当我们序列化一个没有实现Serializable的方法时，会抛出`java.io.NotSerializableException`

​	其实在序列化调用的类ObjectOutputStream的writeObject 方法的调用栈是这样的：

​	writeObject ---> writeObject0 --->writeOrdinaryObject--->writeSerialData--->invokeWriteObject 

​	在writeObject0方法中有这么一段代码： 

```
if (obj instanceof String) {
                writeString((String) obj, unshared);
            } else if (cl.isArray()) {
                writeArray(obj, desc, unshared);
            } else if (obj instanceof Enum) {
                writeEnum((Enum<?>) obj, desc, unshared);
            } else if (obj instanceof Serializable) {
                writeOrdinaryObject(obj, desc, unshared);
            } else {
                if (extendedDebugInfo) {
                    throw new NotSerializableException(
                        cl.getName() + "\n" + debugInfoStack.toString());
                } else {
                    throw new NotSerializableException(cl.getName());
                }
            }
```

​	在进行序列化操作时，会判断要被序列化的类是否是Enum、Array和Serializable类型，如果不是则直接抛出`NotSerializableException`。

## 1.6 关于序列化过程

​	在序列化过程中，如果被序列化的类中定义了writeObject 和 readObject 方法，虚拟机会试图调用对象类里的 writeObject 和 readObject 方法，进行用户自定义的序列化和反序列化。

​	如果没有这样的方法，则默认调用是 ObjectOutputStream 的 defaultWriteObject 方法以及 ObjectInputStream 的 defaultReadObject 方法。

​	用户自定义的 writeObject 和 readObject 方法可以允许用户控制序列化的过程，比如可以在序列化的过程中动态改变序列化的数值。

​	 **最大的体现就在于ArrayList中的序列化，在ArrayList中存储元素的数组elementData 是被声明为transient，按常理说这个成员变量不会被序列化 ，但是在ArrayList中定义了 `writeObject`和`readObject`两个方法，通过这两个方法实现了自定义的序列化和反序列化策略，自定义的策略优先级比transient的优先级高**

