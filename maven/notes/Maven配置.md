#### 配置MAVEN_OPTS环境变量
###### 情景
在执行mvn命令时，Maven实际上执行了Java命令，既然执行了Java命令，那么肯定与JVM相关，有时候我们对JVM的配置成为了必要的事情，比如项目较大，JVM默认内存配置由于过小往往会造成java.lang.OutOfMemeoryError。当然我们也有可能是出于JVM调优以达到mvn命令运行的更快的目的下，对此项进行配置。
###### 配置
新增环境变量
	
	MAVEN_OPTS
结合JVM参数的相关知识对环境变量进行配置，形如:
	
	-Xms128m-Xmx512m
#### 配置settings.xml
两种形式：
	
	配置$M2_HOME/conf/settings.xml
	复制$M2_HOME/conf/settings.xml到者 ～/.m2/文件夹下，再进行配置
前者配置为全局配置，当前机器下的所有用户都使用此配置，后者仅针对当前用户。为了避免影响到机器的其他用户使用，推荐使用后者进行配置。当需要进行全局配置时，才使用前者。另外一个原因是，后者在maven更新后只要该文件没有被删除，就会生效，而前者会随着我们删除maven或者替换更新maven版本时消逝。

###### localRepository
配置本地仓库路径，Maven在下载依赖时，默认将依赖存储在:
	
	~/.m2/repository
说实话这个文件夹确实不容易被我们光顾，所以配置一个我们容易管理的位置是极好的，格式如下：
	
	<localRepository>PATH</localRepository>
	PATH : 即我们想要配置的路径，可以任意指定，形如：
	<localRepository>D:\repository</localRepository>
###### interactiveMode
当执行命令时，可能出现maven需要你输入值的情况，配置此项可以决定它是否提醒你进行输入。如果设置为false，maven将使用默认值代替输入，当然也可能是基于某种配置的输入，默认值为true，格式如下：
	
	<interactiveMode>true</interactiveMode>
###### offline
是否离线：当maven在执行build时，是否尝试连接网络，这将会影响依赖的下载等。默认值为false，即允许连接网络，配置格式如下:
	
	<offline>false</offline>
###### pluginGroups
当插件的groupId没有显式提供时，供搜寻插件groupId的列表。该元素包含一个pluginGroup元素列表，每个子元素包含了一个groupId。当我们使用某个插件，并且没有在命令行为其提供groupId的时候，Maven就会使用该列表。默认情况下该列表包含了org.apache.maven.plugins和org.codehaus.mojo
	
	<pluginGroups>
	    <pluginGroup>org.apache.maven.plugins</pluginGroup>
	</pluginGroups>
	
假设有这样一个插件，它的groupId=org.apache.maven.plugins, artifactId=delete-maven-plugin，它的目标为remove，当我们要使用此插件进行remove时，根据上面的配置，我们可以这样：

	mvn delete:remove
maven实际上可能会执行下面这样的命令：

	mvn org.apache.maven.plugins:delete-maven-plugin:remove
###### proxies
配置代理，代理的用途和使用理由有很多，可以是某站点拉取依赖太慢了需要代理或是要求使用安全代理访问等，配置格式如下：
	
	<proxies>
	  <proxy>
	    <id>给这个代理取个名字</id>
	    <active>标记是否可以使用此带来（ture/false）</active>
	    <protocol>协议（http）</protocol>
	    <username>代理用户名</username>
	    <password>代理密码</password>
	    <host>代理host</host>
	    <port>代理端口</port>
	    <nonProxyHosts>不被代理的列表（local.net|some.host.com）</nonProxyHosts>
	  </proxy>
	</proxies>
上面的配置均见名知意，就不用赘述了。

###### servers
认证配置项列表，系统内server-id将被键入并使用。每当maven必须与远端的服务端（任意服务端，tomcat. etc）进行连接时，将会使用认证项。当连接指定服务端时，会指定使用认证信息，确保在当前系统内认证信息的名称（id）是唯一的。你应该定义username/password或者privateKey/passphrase，因为这是被成对使用的。此项将被使用于下载或者部署pom.xml中定义的repositories和distributionManagement，由于username、password等信息不应该包含在pom.xml中，因此这类信息存在于settings.xml文件中，格式如下：

	<server>
	  <id>id（取个名字，并确保唯一）</id>
	  <username>admin（服务端进行认证的用户名）</username>
	  <password>123456（用户名对应的密码）</password>
	  <privateKey>/path/to/private/key（认证key文件路径）</privateKey>
	  <passphrase>私钥密码，可选项;如果不使用此项认证配置，请留空，当此项不为空时，username、password失效</passphrase>
	  <filePermissions>664</filePermissions>
      <directoryPermissions>775</directoryPermissions>
      前两项：在进行部署时创建仓库文件或者目录的权限，也就是这些文件或目录的读写等权限，此项必须为数字，且要符合linux中对权限的定义。
      <configuration></configuration>
	</server>

#### mirrors
被用来从远程仓库下载依赖的配置项，pom.xml中定义了依赖，但是默认的远程仓库由于网络问题，下载缓慢或链接失败，定义此项将会使用定义的远程仓库以改善这些问题。下面定义了阿里云的仓库：

	<mirrors>
	  <mirror>
	    <id>alimaven</id>
	    <name>aliyun maven</name>
	    <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
	    <mirrorOf>central</mirrorOf>
	  </mirror>
	</mirrors>
**id/name:** 当前配置中保证唯一，id被用来区分不同的mirror并且将会匹配使用<servers>中配置的对应id的连接认证信息来连接此项定义的远程仓库。
**url:** 远程仓库的url。
**mirrorOf:** 镜像仓库的ID。若为中央仓库，设置central，查看详细内容请移步：[官网](https://maven.apache.org/guides/mini/guide-mirror-settings.html)
#### profiles
是pom.xml中profiler的截断版本，由activation、repositories、pluginRepositories和properties元素组成。如果settings.xml配置了一个profile，这将覆盖pom.xml中定义的相同id的profile项。由于此项与pom.xml中的配置部分重合，将会在后续的pom.xml配置文章中进行详解，当前只需要注意settings.xml中此项配置只包含四中元素即可。

#### activeProfiles
这是settings.xml中最后一个元素。它与actionProfile元素有关，它会激活指定的actionProfile配置，不管任何环境设置。格式如下：

	<activeProfiles>
	  <activeProfile>env-test</activeProfile>
	</activeProfiles>
如上，若env-test是一个定义在某pom.xml中的profile，那么这个profile配置项将会被激活。