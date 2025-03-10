## Mysql 入门命令

#### 本地登录

通过客户机登录到 `Mysql`

```shell
mysql -u [userName] -p
||
mysql -u [username] -p[password] ## 密码与 `p` 不存在空格.
输入密码，若无密码直接回车即可。
```

例子：

```shell
> mysql -u root -p
> root
```

若成功则出现：

```mysql
mysql>
```



#### 远程登录

```shell
mysql -h [remote host] -u [username] -p
||
mysql -h [remote host] -u [username] -p[password]
```



#### 查询用户及数据库服务器地址

```mysql
select user, host from mysql.user;
```



#### 修改用户密码

```mysql
set password for '[username]'@'[host]'=password('[new password]');
```

例子：

```mysql
set password for 'root'@'localhost'=password('root');

set password for 'root'@'127.0.0.1'=password('root');
```



#### 删除用户

```mysql
delete from mysql.user where user='[username]';
delete from mysql.db where user='[username]';
```



#### 重新读取授权表

```mysql
flush privileges;
```



#### 创建用户并设置权限

```mysql
grant [权限, 权限] on [database].[table] To '[username]'@'[hostname]' identified by '[password]';
```

创建仅有查看数据权限的用户：

```mysql
grant select on *.* to 'usera'@'localhost' identified by 'usera';
```

创建拥有所有权限的用户：

```mysql
grant all on *.* to 'all'@'localhost' identified by 'all';
```

创建一个对数据库 `test` 拥有所有权限的用户：

```mysql
grant all on test.* to 'testUser'@'localhost' identified by 'testUser';
```