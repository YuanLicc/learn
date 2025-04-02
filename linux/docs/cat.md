#### cat

查看文件或则作为标准输入

```shell
cat [OPTION]... [FILE]...
```

当不指定 file 时，或则 file 不存在时，为标准输入

1）OPTION

```shell
$ cat words.txt
hello
world

hello world
hello hello world
```

- -A - 展示文件内所有内容

```shell
$ cat -A words.txt
hello$
world$
$
hello world$
hello hello world$
```

- -n 展示内容及行号

```shell
$ cat -n words.txt
     1  hello
     2  world
     3
     4  hello world
     5  hello hello world
```

- -b  展示内容及行号，空行不记录行号

```shell
$ cat -b words.txt
     1  hello
     2  world

     3  hello world
     4  hello hello world
```

