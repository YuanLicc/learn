### tr

字符操作

```shell
$ cat words.txt
hello
world

hello world
hello hello world
```

- tr -d 删除字符串

```shell
$ cat words.txt | tr -d 'he'
llo
world

llo world
llo llo world
```

- tr 替换字符串

```shell
$ cat words.txt | tr 'hl' '14'
1e44o
wor4d

1e44o wor4d
1e44o 1e44o wor4d
```

