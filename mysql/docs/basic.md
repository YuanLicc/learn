## MySQL 基础

### MySQL 客户机

与 `MySQL` 服务器结合在一起进行开发或与 `MySQL` 数据库协同工作的方法有许多种，最基本的就是使用界面程序 `MySQL` 客户机，使用该程序，可以在命令行环境下与服务器进行交互操作。

### 创建数据和表

#### 创建数据库

```mysql
create database [name];
```

例子：

```mysql
create database mydatabase;
```

```mysql
show databases;
```

#### 切换到数据库下

```mysql
use [database name];
```

例子：

```mysql
use mydatabse;
```

#### 创建表

```mysql
create table [table name] (

    [column name] [column type] [modifiers] ,
    ...
    primary key(column, column),
	...
);
```

例子：

```mysql
create table computer (
	id int(11) not null comment '唯一标识',
     name varchar(225) default null comment '电脑名称',
     primary key(id)
);
```

```mysql
alter table [table name]
change column [column name] [new column name] [modifiers],
add column [column name] [modifiers] 
    ...
;
```

```mysql
insert into [table name]
([columns]) values ([values]),([values]);
```

```mysql
select [columns] from [table name];
```

```mysql
update [table name]
set [column name] [value]
[condition];
```

```mysql
delete from [table name] [condition];
```

```mysql
date_format(date, "")
```

| %a   | 缩写星期名                                     |
| ---- | ---------------------------------------------- |
| %b   | 缩写月名                                       |
| %c   | 月，数值                                       |
| %D   | 带有英文前缀的月中的天                         |
| %d   | 月的天，数值(00-31)                            |
| %e   | 月的天，数值(0-31)                             |
| %f   | 微秒                                           |
| %H   | 小时 (00-23)                                   |
| %h   | 小时 (01-12)                                   |
| %I   | 小时 (01-12)                                   |
| %i   | 分钟，数值(00-59)                              |
| %j   | 年的天 (001-366)                               |
| %k   | 小时 (0-23)                                    |
| %l   | 小时 (1-12)                                    |
| %M   | 月名                                           |
| %m   | 月，数值(00-12)                                |
| %p   | AM 或 PM                                       |
| %r   | 时间，12-小时（hh:mm:ss AM 或 PM）             |
| %S   | 秒(00-59)                                      |
| %s   | 秒(00-59)                                      |
| %T   | 时间, 24-小时 (hh:mm:ss)                       |
| %U   | 周 (00-53) 星期日是一周的第一天                |
| %u   | 周 (00-53) 星期一是一周的第一天                |
| %V   | 周 (01-53) 星期日是一周的第一天，与 %X 使用    |
| %v   | 周 (01-53) 星期一是一周的第一天，与 %x 使用    |
| %W   | 星期名                                         |
| %w   | 周的天 （0=星期日, 6=星期六）                  |
| %X   | 年，其中的星期日是周的第一天，4 位，与 %V 使用 |
| %x   | 年，其中的星期一是周的第一天，4 位，与 %v 使用 |
| %Y   | 年，4 位                                       |
| %y   | 年，2 位                                       |

```mysql
update [table] set column=... 
```

```mysql
delete from [table] ...
```

```mysql
SET @[var] = ...
```

```mysql
create user '[name]'@'[%]|[host]' 
identified by '[password]';

drop user '[name]'@'[host]';

set password for '[name]'@'[host]'='[password]';

show grants for '[name]'@'[host]';

select * from mysql.user;

revoke all on *.* from '[name]'@'[host]';

flush privileges;

grant privilege [all|alter|create|delete|drop|insert|alter routime...] 
on 
[database|table|function|procedure|*].[table|*] 
to 
'[name]'@'[host]' [identified by password];
```

