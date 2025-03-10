## Clone 相关

### 1. 哪个类包含 clone 方法？是 Cloneable 还是 Object？

java.lang.Cloneable 是一个标示性接口，不包含任何方法，clone 方法在 object 类中定义。并且需要知道 clone() 方法是一个本地方法，这意味着它是由 c 或 c++ 或 其他本地语言实现的。 