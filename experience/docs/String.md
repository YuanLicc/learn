## String 相关

### 1. String 能被继承吗？为什么？

不可以，因为 `String` 类有 `final` 修饰符，而 `fina` 不能被继承的，实现细节不允许改变。平常我们定义的 `String str = "a"` 和 `String str = new String("a")` 还是有差异的。前者默认调用的 `String.valueof` 来返回 `String` 的实例对象，至于调用哪个则取决于你的赋值，比如`String num = 1`，调用的是：

```java
public static String valueOf(int i) {
   return Integer.toString(i);
}
```

后者则是调用如下部分：

```java
public String(String original) { 
    this.value = original.value; 
    this.hash = original.hash; 
} 
```

最后我们的变量都存储在一个`char`数组中。



### 2. String，StringBuffer，StringBuilder 的区别？

`String` 字符串常量(final修饰，不可被继承)，`String` 是常量，当创建之后即不能更改。可以通过 `StringBuffer` 和 `StringBuilder` 创建 `String` 对象（常用的两个字符串操作类）。`StringBuffer` 字符串变量（线程安全），其也是 `final` 类别的，不允许被继承，其中的绝大多数方法都进行了同步处理，包括常用的 `append` 方法也做了同步处理。其自 `Jdk 1.0` 起就已经出现。其 `toString` 方法会进行对象缓存，以减少元素的复制开销。

```java
public synchronized String toString() { 
    if (toStringCache == null) { 
    	toStringCache = Arrays.copyOfRange(value, 0, count); 
    } 
    return new String(toStringCache, true); 
}
```

`StringBuilder` 字符串变量，（非线程安全）其自 `Jdk 1.5` 起开始出现。与 `StringBuffer` 一样都继承和实现同一个接口和类，方法除了没有使用 `synchronized` 修饰以外基本一致，不同之处在于最后 `toString` 的时候，会直接返回一个新对象。 

```java
public String toString() { 
	// Create a copy, don’t share the array 
    return new String(value, 0, count); 
}
```



#### 2.1 String，StringBuilder ，StringBuffer的区别？

`String` 类型和 `StringBuffer` 类型的主要性能区别其实在于 `String` 是不可变的对象。因此在每次对 `String` 类型进行改变的时候其实都等同于生成了一个新的 `String` 对象，然后将指针指向新的 `String` 对象，所以经常改变内容的字符串最好不要用 `String` ，因为每次生成对象都会对系统性能产生影响，特别当内存中无引用对象多了以后，`JVM` 的 `GC` 就会开始工作，影响性能，可以考虑使用可变字符序列`StringBuilder`。

ava平台提供了两种类型的字符串：String和StringBuffer/StringBuilder，它们可以储存和操作字符串。其中String是只读字符串，也就意味着String引用的字符串内容是不能被改变的。而StringBuffer/StringBuilder类表示的字符串对象可以直接进行修改。StringBuilder是Java 5中引入的，它和StringBuffer的方法完全相同，区别在于它是在单线程环境下使用的，因为它的所有方面都没有被synchronized修饰，因此它的效率也比StringBuffer要高。 

String一旦赋值或实例化后就不可更改，如果赋予新值将会重新开辟内存地址进行存储。而StringBuffer类使用append和insert等方法改变字符串值时只是在原有对象存储的内存地址上进行连续操作，减少了资源的开销。因此我们得出结论：如果使用少量的字符串操作，使用 (+运算符)连接字符串； 当需要进行频繁修改字符串的操作时先建立StringBuffer类对象进行操作，将最后结果转化成String类对象返回，这样效率会高很多。



### 3. 为什么 Java 中的 String 是不可变的（Immutable）？  

Java 中的 String 不可变是因为 Java 的设计者认为字符串使用非常频繁，将字符串设置为不可变可以允许多个客户端之间共享相同的字符串。 



### 4. 我们能在 Switch 中使用 String 吗？

从 Java 7 开始，我们可以在 switch case 中使用字符串，但这仅仅是一个语法糖。内部实现在 switch 中使用字符串的 hash code。 



### 5. 怎么检查一个字符串只包含数字？

```java
for(...) {
    Character.isDigit(ch)
}
```



### 6. 在不使用 StringBuffer 的前提下，怎么反转一个字符串？

```java
// author YuanLi
public static String reverse(String src) {
    if(src == null) return null;

    int length = src.length();

    if(length == 0) return src;

    char[] chars = src.toCharArray();

    for (int i = ((length - 1) >> 1); i >= 0; i--) {
        CharUtil.swap(chars, i, length - 1 - i);
    }

    return String.valueOf(chars);
}

// CharUtil.swap | author YuanLi
public static char[] swap(char[] chars, int swapIndex1, int swapIndex2) {
    if(chars == null) return null;

    int length = chars.length;

    if(length == 0) return chars;

    if(swapIndex1 < 0 || swapIndex1 >= length || swapIndex2 < 0 || swapIndex2 >= length) {
        throw new ArrayIndexOutOfBoundsException();
    }

    char temp = chars[swapIndex1];
    chars[swapIndex1] = chars[swapIndex2];
    chars[swapIndex2] = temp;

    return chars;
}
```



### 7. 如何检查出两个给定的字符串是反序的？

1、StringBuilder.reverse();

2、

```java
// author YuanLi
public static boolean isReverseOrder(String source) {

    if(source == null) return false;

    int maxIndex = source.length() - 1;

    if(source.length() == 0) return true;

    for(int i = (maxIndex) >> 1; i >= 0; i--) {
        if(source.charAt(i) != source.charAt(maxIndex - i)) {
            return false;
        }
    }
    return true;
}
```



### 8. Java 中，怎么打印出一个字符串的所有排列？

```java
/**
 * 字符串全排
 * @author YuanLi
 */
public class AllPermutation {

    public static List allPermutations(String source) {

        if(source == null || source.length() == 0) return null;

        return allPermutations(source.toCharArray(), 0
              , source.length() - 1, new ArrayList());
    }

    private static List allPermutations(char[] source, int start
            , int end, List<String> permutations) {
        
        if(start == end) {
            permutations.add(String.valueOf(source));
            return permutations;
        }

        for(int i = start; i <= end; i++) {
            source = CharUtil.swap(source, i, start);

            allPermutations(source, start + 1, source.length - 1, permutations);

            source = CharUtil.swap(source, i, start);
        }

        return permutations;
    }

}
```



### 9. Java 中如何将字符串转换为整数？

Integer.valueOf();

Integer.parseInt();



### 10. 你能写出一个正则表达式来判断一个字符串是否是一个数字吗?

一个数字字符串，只能包含数字，如 0 到 9 以及 +、- 开头，通过这个信息，你可以下一个如下的正则表达式来判断给定的字符串是不是数字。 



### 数组有没有length()方法？String有没有length()方法

数组没有length()方法，有length 的属性。String 有length()方法。JavaScript中，获得字符串的长度是通过length属性得到的，这一点容易和Java混淆。 



### String str = new String(“abc”)  到底创建了几个对象？

两个对象，堆区(str)和常量池("abc")中。





## 概念

### String 源码学习

​	字符串是常量，在定义之后不能被改变，字符串缓冲区支持可变的字符串。因为 String 对象是不可变的，所以可以共享它们。 

```
String str = "abc";
```

相当于

```java
char data[] = {'a', 'b', 'c'};
String str = new String(data);
```

#### 2.1 定义

```java
public final class String implements java.io.Serializable, Comparable<String>, CharSequence {
    //...
}
```

​	从该类的声明中我们可以看出String是final类型的，表示该类不能被继承，同时该类实现了三个接口：`java.io.Serializable`、 `Comparable<String>`、 `CharSequence`

#### 2.2 属性

```java
private final char value[];
```

> 这是一个字符数组，并且是final类型，他用于存储字符串内容，从final这个关键字中我们可以看出，String的内容一旦被初始化了是不能被更改的。 虽然有这样的例子： String s = “a”; s = “b” 但是，这并不是对s的修改，而是重新指向了新的字符串， 从这里我们也能知道，**String其实就是用char[]实现的。**

```java
private int hash;
```

> 缓存字符串的[hash Code](http://www.hollischuang.com/archives/99#hashCode)，默认值为 0

```java
private static final long serialVersionUID = -6849794470754667710L;
private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[0];
```

> 因为`String`实现了`Serializable`接口，所以支持序列化和反序列化支持。Java的序列化机制是通过在运行时判断类的`serialVersionUID`来验证版本一致性的。在进行反序列化时，JVM会把传来的字节流中的`serialVersionUID`与本地相应实体（类）的`serialVersionUID`进行比较，如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常(`InvalidCastException`)。

#### 2.3 构造方法

String类作为一个java.lang包中比较常用的类,自然有很多重载的构造方法.在这里介绍几种典型的构造方法:

#### **2.3.1 使用字符数组、字符串构造一个String**

我们知道，其实String就是使用字符数组（char[]）实现的。所以我们可以使用一个字符数组来创建一个String，那么这里值得注意的是，**当我们使用字符数组创建String的时候，会用到Arrays.copyOf方法和Arrays.copyOfRange方法。这两个方法是将原有的字符数组中的内容逐一的复制到String中的字符数组中。**同样，我们也可以用一个String类型的对象来初始化一个String。这里将直接将`源String`中的`value`和`hash`两个属性直接赋值给`目标String`。因为String一旦定义之后是不可以改变的，所以也就不用担心改变`源String`的值会影响到`目标String`的值。

> 当然，在使用字符数组来创建一个新的String对象的时候，不仅可以使用整个字符数组，也可以使用字符数组的一部分，只要多传入两个参数`int offset`和`int count`就可以了。

 #####**2.3.2 使用字节数组构造一个String**

在Java中，String实例中保存有一个`char[]`字符数组，`char[]`字符数组是以unicode码来存储的，String 和 char 为内存形式，byte是网络传输或存储的序列化形式。所以在很多传输和存储的过程中需要将byte[]数组和String进行相互转化。所以，String提供了一系列重载的构造方法来将一个字符数组转化成String，提到byte[]和String之间的相互转换就不得不关注编码问题。**String(byte[] bytes, Charset charset)是指通过charset来解码指定的byte数组，将其解码成unicode的char[]数组，够造成新的String。**

> **这里的bytes字节流是使用charset进行编码的，想要将他转换成unicode的char[]数组，而又保证不出现乱码，那就要指定其解码方式**

同样使用字节数组来构造String也有很多种形式，按照是否指定解码方式分的话可以分为两种：

```java
String(byte bytes[]) String(byte bytes[], int offset, int length)
String(byte bytes[], Charset charset)
String(byte bytes[], String charsetName)
String(byte bytes[], int offset, int length, Charset charset)
String(byte bytes[], int offset, int length, String charsetName)
```



#### 2.3.3 **使用StringBuffer和StringBuider构造一个String**

​	作为String的两个“兄弟”，StringBuffer和StringBuider也可以被当做构造String的参数。

```java
public String(StringBuffer buffer) {
    synchronized(buffer) {
       this.value = Arrays.copyOf(buffer.getValue(), buffer.length());
     }
}

public String(StringBuilder builder) {
	this.value = Arrays.copyOf(builder.getValue(), builder.length());
}
```

​	当然，这两个构造方法是很少用到的，至少我从来没有使用过，因为当我们有了StringBuffer或者StringBuilfer对象之后可以直接使用他们的toString方法来得到String。关于效率问题，Java的官方文档有提到说使用StringBuilder的toString方法会更快一些，原因是StringBuffer的`toString`方法是synchronized的，在牺牲了效率的情况下保证了线程安全。

```java
 public String toString() {
    // Create a copy, don't share the array
    return new String(value, 0, count);
 }

this.value = Arrays.copyOfRange(value, offset, offset+count);
```

#### 2.3.4 比较方法

```java
boolean equals(Object anObject)；
boolean contentEquals(StringBuffer sb)；
boolean contentEquals(CharSequence cs)；
boolean equalsIgnoreCase(String anotherString)；
int compareTo(String anotherString)；
int compareToIgnoreCase(String str)；
boolean regionMatches(int toffset, String other, int ooffset,int len)  //局部匹配
boolean regionMatches(boolean ignoreCase, int toffset,String other, int ooffset, int len)   //局部匹配
```

#### 2.3.5 String 的 equals方法

```java
public boolean equals(Object anObject) {
    if (this == anObject) {
        return true;
    }
    if (anObject instanceof String) {
        String anotherString = (String) anObject;
        int n = value.length;
        if (n == anotherString.value.length) {
            char v1[] = value;
            char v2[] = anotherString.value;
            int i = 0;
            while (n-- != 0) {
                if (v1[i] != v2[i])
                    return false;
                i++;
            }
            return true;
        }
    }
    return false;
}
```

​	该方法首先判断`this == anObject ？`， 也就是说判断要比较的对象和当前对象是不是同一个对象，如果是直接返回true，如不是再继续比较，然后在判断`anObject`是不是`String`类型的，如果不是，直接返回false,如果是再继续比较，到了能终于比较字符数组的时候，他还是先比较了两个数组的长度，不一样直接返回false，一样再逐一比较值。 虽然代码写的内容比较多，但是可以很大程度上提高比较的效率。 

#### 2.3.6 String 对 “+” 的重载

​	Java是不支持重载运算符，String的“+”是java中唯一的一个重载运算符，那么java使如何实现这个加号的呢？我们先看一段代码：

```java
public static void main(String[] args) {
    String string="hollis";
    String string2 = string + "chuang";
}
```

​	然后我们将这段代码**反编译**：

```java
public static void main(String args[]){
   String string = "hollis";
   String string2 = (new StringBuilder(String.valueOf(string))).append("chuang").toString();
}
```

​	看了反编译之后的代码我们发现，其实String对“+”的支持其实就是使用了StringBuilder以及他的append、toString两个方法。

​	在进行多次字符串+操作**注意**

​	每次“+”操作其实都会---new StringBuilder() --在进行append ，所以在多次+操作时最好先创建一个StringBuilder对象，之后再在这个的基础上进行append方法的调用，这样可以减少内存的消耗。

#### 2.3.7 String.valueOf和Integer.toString的区别

​	接下来我们看以下这段代码，我们有三种方式将一个int类型的变量变成呢过 `String` 类型，那么他们有什么区别？

```java
1.int i = 5;
2.String i1 = "" + i;
3.String i2 = String.valueOf(i);
4.String i3 = Integer.toString(i);
```

> 1、第三行和第四行没有任何区别，因为 `String.valueOf(i)` 也是调用 `Integer.toString(i)` 来实现的。
>
> 2、第二行代码其实是 `String i1 = (new StringBuilder()).append(i).toString();`，首先创建一个 `StringBuilder` 对象，然后再调用 `append` 方法，再调用 `toString` 方法。





