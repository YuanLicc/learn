## 基础命令和目录结构



#### 标准输入输出

```shell
cat
```

在不指定文件名的情况下，cat 命令从 Linux 内核提供的默认标准输入流中获得输入数据，这时运行 cat 命令的终端就成为标准输入。

标准输出也与之类似。内核为每个进程提供一个标准输出流宫他们输出数据。cat 命令在终端运行的时候，标准输出就和该终端建立连接，cat 命令将数据输出到标准输出，就是屏幕上的结果。



### 基础命令

#### ls

```shell
ls -l 显式详情列表
```

#### cp

```shell
cp file1 file2 将文件 file1 复制到文件 file2
```

```shell
cp file1 ... file dir 将文件 file1 到 fileN 复制到目录 dir 中
```

#### mv

```shell
mv file1 file2 重命名文件 file1 为 file2
```

```shell
mv file1 ... fileN dir 移动到目录
```

#### touch

创建文件，若文件存在，则更新文件的时间戳。

#### rm 

```shell
rm file 删除文件，无法恢复
```

#### echo

将参数显示到标准输出

```shell
echo hello
```



#### 浏览目录

```shell
/.... 表绝对路径
```

```shell
.. 表上层目录
```

```shell
. 表当前目录
```

#### cd

用来设置当前工作目录：

```shell
cd dir
```

若不带参数，cd 命令会返回个人主目录，指的是你登录系统后进入的目录。

#### mkdir

创建新目录

```shell
mkdir cc
```

#### rmdir

删除目录

```shell
rmdir cc
```

如果要删除的目录内有内容，上面的目录会执行失败。因为 rmdir 只能删除空目录，可以使用 rm -rf 来删除一个目录及其中的所有内容。使用这个目录的时候要非常小心，尤其是当你是超级用户的时候。因为 -r 选项会依次删除 dir 中的所有文件和子目录，-f 选项代表强制删除。

#### 通配符

shell 可以使用通配符来匹配文件名和目录名。其他的操作系统也有通配符这个概念，比如 * 代表任意字符和数字。

```shell
echo * 这里表示当前目录下的所有文件，将打印这些文件的文件名
```

### 中间命令

#### grep 命令

grep 命令显式文件和输入流中和参数匹配的行。如下面的命令显式文件 /etc/passwd 中包含文本 root 的所有行：

```shell
grep root /etc/passwd
```

#### less

当要查看的文件过大或者内容多得需要滚动屏幕的时候，可以使用 less 命令。less 命令可以将内容分屏显式，按空格键可以查看下一屏，B 键查看上一屏，Q 键退出。

less 是 more 命令的加强版本。绝大多数 linux 系统总都有这个命令，极少数无此命令的系统可使用 more 命令。

less 命令的输出结果可以进行搜索：

```shell
?pattern
```

#### pwd

仅仅输出当前的目录名。

#### diff

查看两个文件的差异：

```shell
diff file1 file2
```

#### file

文件格式信息：

```shell
file file1
```

#### find 和 locate

查找文件：

```shell
find dir -name file -print
```

和 find 不同的是，locate 在系统创建的文件索引中查找文件。这个索引由操作系统周期性的进行更新，查找速度比 find 更快，但是 locate 对于查找新创建的文件可能会无能为力，因为他们有可能还没有被加入到索引中。

#### head 和 tail 命令

head 命令显式文件的前世 10 行内容。tail 命令显示文件的最后 10 行内容。可以设置显示的行数：

```shell
head -6 /etc/passwd
```

