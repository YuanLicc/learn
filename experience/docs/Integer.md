

# 1  Integer 的定义

public final class Integer extends Number implements Comparable<Integer> 

​	从类定义中我们可以知道以下几点：

> 1、Integer类不能被继承
>
> 2、Integer类实现了Comparable接口，所以可以用compareTo进行比较并且Integer对象只能和Integer类型的对象进行比较，不能和其他类型比较（至少调用compareTo方法无法比较）。
>
> 3、Integer继承了Number类，所以该类可以调用longValue、floatValue、doubleValue等系列方法返回对应的类型的值。

# 2  属性

## 2.1  私有属性

​	Integer中定义了几个私有属性：

```
private final int value;
private static final long serialVersionUID = 1360826667806852920L;
```

​	私有属性value就是Integer对象中真正保存的int值 。

​	当我们使用new Integer(10) 时就会调用下面的构造方法：

```
public Integer(int value) {
    this.value = value;
}
```

​	从value的定义形式中可以看出value被定义成final类型，说明一旦一个Integer对象被初始化后，就无法再改变value的值。

## 2.2  公共属性

```
//值为 （－（2的31次方）） 的常量，它表示 int 类型能够表示的最小值。
public static final int   MIN_VALUE = 0x80000000;
//值为 （（2的31次方）－1） 的常量，它表示 int 类型能够表示的最大值。
public static final int   MAX_VALUE = 0x7fffffff;   
//表示基本类型 int 的 Class 实例。
public static final Class<Integer>  TYPE = (Class<Integer>) Class.getPrimitiveClass("int");
//用来以二进制补码形式表示 int 值的比特位数。
public static final int SIZE = 32;
//用来以二进制补码形式表示 int 值的字节数。1.8以后才有
public static final int BYTES = SIZE / Byte.SIZE;
```

# 3  方法

## 3.1 构造方法

​	Integer提供了两个构造方法：

```
//构造一个新分配的 Integer 对象，它表示指定的 int 值。
public Integer(int value) {
    this.value = value;
}

//构造一个新分配的 Integer 对象，它表示 String 参数所指示的 int 值。
public Integer(String s) throws NumberFormatException {
    this.value = parseInt(s, 10);
}
```

​	从构造方法中我们可以知道，初始化一个Integer对象的时候只能构造一个十进制的整数。

## 3.2  valueOf(int i) 方法

```
public static Integer valueOf(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];
    return new Integer(i);
}
```

​	在通常的情况下IntegerCache.low=-128 , IntegerCache.high=127(除非显示的声明这两个值)，在Integer中有一段动态代码块，该内容Integer类被加载的时候执行。

```
static {
        // high value may be configured by property
        int h = 127;
        String integerCacheHighPropValue =
            sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
        if (integerCacheHighPropValue != null) {
            try {
                int i = parseInt(integerCacheHighPropValue);
                i = Math.max(i, 127);
                // Maximum array size is Integer.MAX_VALUE
                h = Math.min(i, Integer.MAX_VALUE - (-low) -1);
            } catch( NumberFormatException nfe) {
                // If the property cannot be parsed into an int, ignore it.
            }
        }
        high = h;

        cache = new Integer[(high - low) + 1];
        int j = low;
        for(int k = 0; k < cache.length; k++)
            cache[k] = new Integer(j++);

        // range [-128, 127] must be interned (JLS7 5.1.7)
        assert IntegerCache.high >= 127;
    }	
```

​	也就是说，当Integer被加载时，就新建了-128到127的所有数字并把这些数字放到Integer数组的cache中。

​	所以说，当调用valueOf方法，如果参数的值在-128到127之间，则直接从缓存中返回一个已经存在的对象。如果参数值不在这个范围内，则new一个Integer对象返回。

​	当我们使用Integer i=100的时候，其实编译器会把这句话转换为Integer i= Integer.valueOf(100);

## 3.3  String转成Integer(int)的方法

```
Integer getInteger(String nm)
Integer getInteger(String nm, int val)
Integer getInteger(String nm, Integer val)
Integer decode(String nm)
Integer valueOf(String s)
Integer valueOf(String s, int radix)
int parseUnsignedInt(String s)
int parseUnsignedInt(String s, int radix)
int parseInt(String s)
int parseInt(String s, int radix)
```

## 3.4 compareTo方法

```
public int compareTo(Integer anotherInteger) {
    return compare(this.value, anotherInteger.value);
}

public static int compare(int x, int y) {
    return (x < y) ? -1 : ((x == y) ? 0 : 1);
}
```

​	compareTo是直接把Integer的value取出进行比较，在这都是进行了对属性的取值，所以compareTo方法不能把Integer和其他的进行比较（包括int）。









