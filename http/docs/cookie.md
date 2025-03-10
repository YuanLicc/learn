本文摘自书籍[《HTTP 权威指南》](https://www.amazon.cn/dp/B008XFDQ14/ref=sr_1_1?s=books&ie=UTF8&qid=1527513120&sr=1-1&keywords=http%E6%9D%83%E5%A8%81%E6%8C%87%E5%8D%97) 

## cookie机制

Web 服务器可能会同时与数千个不同的客户端进行对话。这些服务器通常要记录下它们在与谁交谈，而不会认为所有的请求都来自匿名的客户端。用户识别机制：

- 承载用户身份信息的 HTTP 首部。
- 客户端 IP 地址跟踪，通过用户的 IP 地址对其进行识别。
- 用户登录，用认证方式来识别用户。
- 胖 URL，一种在 URL 中嵌入识别信息的技术。
- cookie，一种功能强大且高效的持久身份识别技术。

### HTTP 首部

承载用户相关信息的HTTP首部

| 首部名称        | 首部类型     | 描述                                 |
| --------------- | ------------ | ------------------------------------ |
| From            | 请求         | 用户的 E-mail 地址                   |
| User-Agent      | 请求         | 用户的浏览器软件                     |
| Referer         | 请求         | 用户是从这个页面上依照链接跳转过来的 |
| Authorization   | 请求         | 用户名和密码                         |
| Client-IP       | 扩展（请求） | 客户端的 IP 地址                     |
| X-Forwarded-For | 扩展（请求） | 客户端的 IP 地址                     |
| Cookie          | 扩展（请求） | 服务器产生的 ID  标签                |

#### From

包含了用户的 E-mail 地址。每个用户都有不同的 E-mail 地址，所以在理想情况下，可以将这个地址作为可行的源端来识别用户。但由于担心那些不讲道德的服务器会搜集这些 E-mail 地址，用于垃圾邮件的散发，所以很少有浏览器会发送 From 首部。实际上，From 首部是由自动化的机器人或蜘蛛发送的，这样在出现问题时，网管还有个地方可以发送愤怒的投诉邮件。

#### User-Agent 

首部可以将用户所用浏览器的相关信息告知服务器，包括程序的名称和版本，通常还包含操作系统的相关信息。要实现定制内容与特定的浏览器及其属性间的良好互操作时，这个首部是非常有用的，但它并没有为识别特定的用户提供太多有意义的帮助。chrome浏览器：

```http
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36
```

Edge浏览器：

```http
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 Edge/16.16299
```

#### Referer

Referer 首部提供了用户来源页面的 URL。Referer 首部自身并不能完全标识用户，但它确实说明了用户之前访问过哪个页面。通过它可以更好地理解用户的浏览行为，以及用户的兴趣所在。

### 客户端IP地址

早期的 Web 先锋曾尝试着将客户端 IP 地址作为一种标识形式使用。如果每个用户都有不同的 IP 地址，IP 地址（如果会发生变化的话）也很少会发生变化，而且 Web 服务器可以判断出每条请求的客户端 IP 地址的话，这种方案是可行的。通常在 HTTP 首部并不提供客户端的IP 地址，但 Web 服务器可以找到承载 HTTP 请求的 TCP 连接另一端的IP 地址。使用客户端 IP 地址来识别用户存在着很多缺点，限制了将其作为用户识别技术的效能：

1. 客户端 IP 地址描述的是所用的机器，而不是用户。如果多个用户共享同一台计算机，就无法对其进行区分了。
2. 很多因特网服务提供商都会在用户登录时为其动态分配 IP 地址。用户每次登录时，都会得到一个不同的地址，因此 Web 服务器不能假设 IP 地址可以在各登录会话之间标识用户。
3. 为了提高安全性，并对稀缺的地址资源进行管理，很多用户都是通过网络地址转换（Network Address Translation，NAT）防火墙来浏览网络内容的。这些 NAT 设备隐藏了防火墙后面那些实际客户端的 IP 地址，将实际的客户端 IP 地址转换成了一个共享的防火墙 IP 地址（和不同的端口号）。
4. HTTP 代理和网关通常会打开一些新的、到原始服务器的 TCP 连接。Web 服务器看到的将是代理服务器的 IP 地址，而不是客户端的。有些代理为了绕过这个问题会添加特殊的 Client-IP 或 X-Forwarded-For 扩展首部来保存原始的 IP 地址。但并不是所有的代理都支持这种行为。

有些 Web 站点仍然使用客户端 IP 地址在会话之间跟踪用户的行为，但这种站点并不多。无法用 IP 地址确定目标的地方太多了。少数站点甚至将客户端 IP 地址作为一种安全特性使用，它们只向来自特定 IP 地址的用户提供文档。在内部网络中可能可以这么做，但在因特网上就不行了，主要是因为因特网上 IP 地址太容易被欺骗（伪造）了。路径上如果有拦截代理也会破坏此方案。

### 用户登录

Web 服务器无需被动地根据用户的 IP 地址来猜测他的身份，它可以要求用户通过用户名和密码进行认证（登录）来显式地询问用户是谁。为了使 Web 站点的登录更加简便，HTTP 中包含了一种内建机制，可以用 WWW-Authenticate 首部和 Authorization 首部向 Web 站点传送用户的相关信息。一旦登录，浏览器就可以不断地在每条发往这个站点的请求中发送这个登录信息了，这样，就总是有登录信息可用了。

如果服务器希望在为用户提供对站点的访问之前，先行登录，可以向浏览器回送一条 HTTP 响应代码 401 Login Required。然后，浏览器会显示一个登录对话框，并用 Authorization 首部在下一条对服务器的请求中提供这些信息。

### 胖 URL

有些 Web 站点会为每个用户生成特定版本的 URL 来追踪用户的身份。通常，会对真正的 URL 进行扩展，在 URL 路径开始或结束的地方添加一些状态信息。用户浏览站点时，Web 服务器会动态生成一些超链，继续维护 URL 中的状态信息。改动后包含了用户状态信息的 URL 被称为胖 URL（fat URL）。每个 URL 后面都附加了一个用户特有的标识码，这个标识码有助于在用户浏览商店内容时对其进行跟踪。

```html
...
<a href="/exec/obidos/tg/browse/-/229220/ref=gr_gifts/002-1145265-8016838">All Gifts</a><br>
<a href="/exec/obidos/wishlist/ref=gr_pl1_/002-1145265-8016838">Wish List</a><br>
...
```

可以通过胖 URL 将 Web 服务器上若干个独立的 HTTP 事务捆绑成一个“会话”或“访问”。用户首次访问这个 Web 站点时，会生成一个唯一的 ID，用服务器可以识别的方式将这个 ID 添加到 URL 中去，然后服务器就会将客户端重新导向这个胖 URL。不论什么时候，只要服务器收到了对胖 URL 的请求，就可以去查找与那个用户 ID 相关的所有增量状态（购物车、简介等），然后重写所有的输出超链，使其成为胖 URL，以维护用户的 ID。可以在用户浏览站点时，用胖 URL 对其进行识别。但这种技术存在几个很严重的问题：

- 丑陋的 URL

  浏览器中显示的胖 URL 会给新用户带来困扰。

- 无法共享 URL

  胖 URL 中包含了与特定用户和会话有关的状态信息。如果将这个 URL 发送给其他人，可能就在无意中将你积累的个人信息都共享出去了。

- 破坏缓存

  为每个 URL 生成用户特有的版本就意味着不再有可供公共访问的 URL 需要缓存了。

- 额外的服务器负荷

  服务器需要重写 HTML 页面使 URL 变胖。

- 逃逸口

  用户跳转到其他站点或者请求一个特定的 URL 时，就很容易在无意中“逃离”胖 URL 会话。只有当用户严格地追随预先修改过的链接时，胖 URL 才能工作。如果用户逃离此链接，就会丢失他的进展（可能是一个已经装满了东西的购物车）信息，得重新开始。

- 在会话间是非持久的

  除非用户收藏了特定的胖 URL，否则用户退出登录时，所有的信息都会丢失。

### cookie

cookie 是当前识别用户，实现持久会话的最好方式。前面各种技术中存在的很多问题对它们都没什么影响，但是通常会将它们与那些技术共用，以实现额外的价值。cookie 非常重要，而且它们定义了一些新的 HTTP 首部，所以我们要比前面那些技术更详细地介绍它们。cookie 的存在也影响了缓存，大多数缓存和浏览器都不允许对任何 cookie 的内容进行缓存。

#### cookie的类型

可以笼统地将 cookie 分为两类： 会话 cookie 和持久 cookie。

- 会话 cookie

  是一种临时 cookie，它记录了用户访问站点时的设置和偏好。用户退出浏览器时，会话 cookie 就被删除了。

- 持久 cookie

  持久 cookie 的生存时间更长一些；它们存储在硬盘上，浏览器退出，计算机重启时它们仍然存在。通常会用持久 cookie 维护某个用户会周期性访问的站点的配置文件或登录名。

会话 cookie 和持久 cookie 之间唯一的区别就是它们的过期时间。

#### cookie是如何工作的

cookie 就像服务器给用户贴的“嗨，我叫”的贴纸一样。用户访问一个 Web 站点时，这个 Web 站点就可以读取那个服务器贴在用户身上的所有贴纸。用户首次访问 Web 站点时，Web 服务器对用户一无所知。Web 服务器希望这个用户会再次回来，所以想给这个用户“拍上”一个独有的 cookie，这样以后它就可以识别出这个用户了。cookie 中包含了一个由名字 = 值（name=value）这样的信息构成的任意列表，并通过 Set-Cookie 或 Set-Cookie2 HTTP 响应（扩展）首部将其贴到用户身上去：

```http
HTTP/1.1 200 OK
Server: Server
Date: Tue, 29 May 2018 07:14:06 GMT
Content-Type: text/html;charset=UTF-8
Transfer-Encoding: chunked
Connection: keep-alive
Set-Cookie: session-id=459-8067989-7531953; path=/; domain=.www.amazon.cn; expires=Mon, 29-May-2006 07:14:06 GMT
Set-Cookie: session-id-time=-***; path=/; domain=.www.amazon.cn; expires=Mon, 29-May-2006 07:14:06 GMT
...
```

```http
GET /%E5%9B%BE%E4%B9%A6/b/ref=sa_menu_top_books_l1?ie=UTF8&node=658390051 HTTP/1.1
Host: www.amazon.cn
Connection: keep-alive
Cache-Control: max-age=0
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
Accept-Encoding: gzip, deflate, br
Accept-Language: zh-CN,zh;q=0.9
Cookie: session-id=459-8067989-7531953; ubid-acbcn=457-7159751-8503349;...
...
```

cookie 中可以包含任意信息，但它们通常都只包含一个服务器为了进行跟踪而产生的独特的识别码。浏览器会记住从服务器返回的 Set-Cookie 或 Set-Cookie2 首部中的 cookie 内容，并将 cookie 集存储在浏览器的 cookie 数据库中（把它当作一个贴有不同国家贴纸的旅行箱）。将来用户返回同一站点时，浏览器会挑中那个服务器贴到用户上的那些 cookie，并在一个 cookie 请求首部中将其传回去。

#### cookie罐：客户端的状态

cookie 的基本思想就是让浏览器积累一组服务器特有的信息，每次访问服务器时都将这些信息提供给它。因为浏览器要负责存储 cookie 信息，所以此系统被称为客户端侧状态（client-side state）。这个 cookie 规范的正式名称为 HTTP 状态管理机制（HTTP state management mechanism）。

#### 不同站点使用不同的cookie

浏览器内部的 cookie 罐中可以有成百上千个 cookie，但浏览器不会将每个 cookie 都发送给所有的站点。实际上，它们通常只向每个站点发送 2 ～ 3 个 cookie。原因如下：

- 对所有这些 cookie 字节进行传输会严重降低性能。浏览器实际传输的 cookie 字节数要比实际的内容字节数多。
- cookie 中包含的是服务器特有的名值对，所以对大部分站点来说，大多数 cookie 都只是无法识别的无用数据。
- 将所有的 cookie 发送给所有站点会引发潜在的隐私问题，那些你并不信任的站点也会获得你只想发给其他站点的信息。

总之，浏览器只向服务器发送服务器产生的那些 cookie。

#### cookie的域属性

产生 cookie 的服务器可以向 Set-Cookie 响应首部添加一个 Domain 属性来控制哪些站点可以看到那个 cookie。

```http
Set-Cookie: session-id=459-8067989-7531953; path=/; domain=.www.amazon.cn; expires=Mon, 29-May-2006 07:14:06 GMT
```

比如上面这个例子，就告诉浏览器将cookie`session-id=459-8067989-7531953`发送给域`.www.amazon.cn`中的所有站点。

#### cookie路径属性

cookie 规范甚至允许用户将 cookie 与部分 Web 站点关联起来。可以通过 Path 属性来实现这一功能，在这个属性列出的 URL 路径前缀下所有 cookie 都是有效的。

```http
Set-Cookie: count=1; path=/num/; domain=.www.amazon.cn; expires=Mon, 29-May-2006 07:14:06 GMT
```

上面cookie `count=1`只会在访问`www.amazon.cn/num/....`的URL才会被添加到请求cookie中。

### cookies版本0（Netscape）

最初的 cookie 规范是由网景公司定义的。这些“版本 0”的 cookie 定义了 Set- Cookie 响应首部、cookie 请求首部以及用于控制 cookie 的字段。版本 0 的 cookie 看起来如下所示：

```http
Set-Cookie: name=value [; expires=date] [; path=path] [; domain=domain]
[; secure]
```

#### NAME=VALUE

强制的。NAME 和 VALUE 都是字符序列，除非包含在双引号内，否则不包括分号、逗号、等号和空格。Web 服务器可以创建任意的 NAME=VALUE 关联，在后继对站点的访问中会将其送回给 Web 服务器。

####Expires

可选的。这个属性会指定一个日期字符串，用来定义cookie 的实际生存期。一旦到了过期日期，就不再存储或发布这个 cookie 了。日期的格式为：Weekday, DD-Mon-YY HH:MM:SS GMT唯一合法的时区为GMT，各日期元素之间的分隔符一定要是长划线。如果没有指定Expires， cookie 就会在用户会话结束时过期。

#### Domain

可选的。浏览器只向指定域中的服务器主机名发送cookie。这样服务器就将 cookie 限制在了特定的域中。acme.com 域就与 anvil.acme.com 和 shipping.crate.acme.com 相匹配，但与` www.cnn.com` 就不匹配了。只有指定域中的主机才能为一个域设置cookie，这些域中至少要有两个或三个句号，以防止出现.com、.edu 和 va.us 等形式的域。这里列出了一组固定的特定高层域，落在这个范围中的域只需要两个句号。所有其他域都至少需要三个句号。特定的高层域包括：.com、.edu、.net、.org、.gov、.mil、.int、.biz、.info、.name、.museum、.coop、.aero 和.pro。如果没有指定域，就默认为产生Set-Cookie 响应的服务器的主机名。

####Path

可选的。通过这个属性可以为服务器上特定的文档分配cookie。如果 Path 属性是一个 URL 路径前缀，就可以附加一个cookie。路径 /foo 与 /foobar 和 /foo/bar.html 相匹配。路径“/”与域名中所有内容都匹配。如果没有指定路径，就将其设置为产生 Set-Cookie 响应的 URL 的路径。

####Secure

可选的。如果包含了这一属性，就只有在 HTTP 使用 SSL 安全连接时才会发送 cookie。

```http
Cookie: name1=value1 [; name2=value2] ...
```

客户端发送请求时，会将所有与域、路径和安全过滤器相匹配的未过期 cookie 都发送给这个站点。所有 cookie 都被组合到一个 Cookie 首部中。

### cookies版本1（RFC 2965）

RFC 2965（以前的 RFC 2109）定义了一个 cookie 的扩展版本。这个版本 1 标准引入了 Set-Cookie2 首部和 Cookie2 首部，但它也能与版本 0 系统进行互操作。RFC 2965 cookie 标准比原始的网景公司的标准略微复杂一些，还未得到完全的支持。RFC 2965 cookie的主要改动包括下列内容：

- 为每个 cookie 关联上解释性文本，对其目的进行解释。
- 允许在浏览器退出时，不考虑过期时间，将 cookie 强制销毁。
- 用相对秒数，而不是绝对日期来表示 cookie 的 Max-Age。
- 通过 URL 端口号，而不仅仅是域和路径来控制 cookie 的能力。
- 通过 Cookie 首部回送域、端口和路径过滤器（如果有的话）。
- 为实现互操作性使用的版本号。
- 在 Cookie 首部从名字中区分出附加关键字的 $ 前缀。

语法如下：

```js
set-cookie      =      "Set-Cookie2:" cookies
cookies         =      1#cookie
cookie          =      NAME "=" VALUE *(";" set-cookie-av)
NAME            =      attr
VALUE           =      value
set-cookie-av   =      "Comment" "=" value
                |      "CommentURL" "=" <"> http_URL <">
                |      "Discard"
                |      "Domain" "=" value
                |      "Max-Age" "=" value
                |      "Path" "=" value
                |      "Port" [ "=" <"> portlist <"> ]
                |      "Secure"
                |      "Version" "=" 1*DIGIT
portlist        =      1#portnum
portnum         =      1*DIGIT
　
cookie          =      "Cookie:" cookie-version 1*((";" | ",") cookievalue)
cookie-value    =     NAME "=" VALUE [";" path] [";" domain] [";" port]
cookie-version   =    "$Version" "=" value
NAME             =    attr
VALUE            =    value
path             =    "$Path" "=" value
domain           =    "$Domain" "=" value
port             =    "$Port" [ "=" <"> value <"> ]
cookie2          =    "Cookie2:" cookie-version
```

#### NAME=VALUE

强制的。Web 服务器可以创建任意的 NAME=VALUE 关联，可以在后继对站点的访问中将其发回给 Web 服务器。“$ ”是保留字符，所以名字一定不能以它开头。

#### Version

强制的。这个属性的值是一个整数，对应于 cookie 规范的版本。RFC 2965 为版本1：

```http
Set-Cookie2: Part="Rocket_Launcher_0001"; Version="1" 
```

#### Comment

可选。这个属性说明了服务器准备如何使用这个cookie。用户可以通过检查此策略来确定是否允许使用带有这个 cookie 的会话。这个值必须采用 UTF-8 编码。

#### CommentURL

可选。这个属性提供了一个 URL 指针，指向详细描述了 cookie 目的及策略的文档。用户可以通过查看此策略来判定是否允许使用带有这个 cookie 的会话 。

#### Discard

可选。如果提供了这个属性，就会在客户端程序终止时，指示客户端放弃这个 cookie 。

#### Domain

可选。浏览器只向指定域中的服务器主机名发送 cookie。这样服务器就可以将 cookie 限制在特定域中了。acme.com 域与主机名 anvil.acme.com 和 shipping.crate.acme.com 相匹配，但不匹配于www.cnn.com。域名匹配的规则基本上与 Netscape cookie 一样，但有几条附加的规则。细节请参见 RFC 2965 

#### Max-Age

可选。这个属性的值是一个整数，用于设置以秒为单位的 cookie 生存期。客户端应该根据 HTTP/1.1 的使用期计算规则来计算 cookie 的使用期。cookie 的使用期比 Max-Age  大时，客户端就应该将这个 cookie 丢弃。值为零说明应该立即将那个 cookie 丢弃。

#### Path

可选。通过这个属性可以为服务器上的特定文档指定 cookie。如果 Path 属性是一个 URL 路径的前缀，就可以附加一个 cookie。路径 /foo 匹配于 /foobar 和 /foo/bar.html。路径“/”匹配于域中所有内容。如果没有指定路径，就将其设置为生成 Set-Cookie  响应的URL 的路径。

#### Port

可选。这个属性可以单独作为关键字使用，也可以包含一个由逗号分隔的、可以应用 cookie 的端口列表。如果有端口列表，就只能向端口与列表中的端口相匹配的服务器提供 cookie。如果单独提供关键字 Port 而没有值，就只能向当前响应服务器的端口号提供 cookie：

```http
Set-Cookie2: foo="bar"; Version="1"; 
Port="80,81,8080"
Set-Cookie2: foo="bar"; Version="1"; Port
```

#### Secure

可选。如果包含这个属性，就只有在 HTTP 使用 SSL 安全连接时才能发送 cookie。