#### 什么是pom
POM全名“Project Object Model”，它是一个名为pom.xml的XML文件，用来表示maven项目。对于maven来说，项目不仅仅是包含代码的文件的集合。一个项目包含了配置文件、开发人员及他们扮演的角色、缺陷跟踪系统、组织、许可证、代码所在URL、项目依赖与其它一些伴随项目的信息。pom是关于项目所有信息的集合，事实上，在maven的世界，一个项目除了pom.xml不需要包含任何代码。

#### 快速浏览

	<project xmlns="http://maven.apache.org/POM/4.0.0"
	  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
	                      http://maven.apache.org/xsd/maven-4.0.0.xsd">
	  <modelVersion>4.0.0</modelVersion>
	 
	  <!-- 基础项 -->
	  <groupId>...</groupId>
	  <artifactId>...</artifactId>
	  <version>...</version>
	  <packaging>...</packaging>
	  <dependencies>...</dependencies>
	  <parent>...</parent>
	  <dependencyManagement>...</dependencyManagement>
	  <modules>...</modules>
	  <properties>...</properties>
	 
	  <!-- Build配置 -->
	  <build>...</build>
	  <reporting>...</reporting>
	 
	  <!-- 项目信息项 -->
	  <name>...</name>
	  <description>...</description>
	  <url>...</url>
	  <inceptionYear>...</inceptionYear>
	  <licenses>...</licenses>
	  <organization>...</organization>
	  <developers>...</developers>
	  <contributors>...</contributors>
	 
	  <!-- 环境配置项 -->
	  <issueManagement>...</issueManagement>
	  <ciManagement>...</ciManagement>
	  <mailingLists>...</mailingLists>
	  <scm>...</scm>
	  <prerequisites>...</prerequisites>
	  <repositories>...</repositories>
	  <pluginRepositories>...</pluginRepositories>
	  <distributionManagement>...</distributionManagement>
	  <profiles>...</profiles>
	</project>
#### 基础项
POM包含了项目相关的所有必要的信息以及在build过程中被使用的插件配置项，有效的表述了"who"、 "what"、"where"，而build生命周期包含 "when"、"how"，但这并不是说POM不能影响build之后的生命周期。比如，通过配置插件maven-antrun-plugin，可以有效的嵌入ant任务到POM中，从而执行除build以外的任务。
	
	<project xmlns="http://maven.apache.org/POM/4.0.0"
	  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
	                      http://maven.apache.org/xsd/maven-4.0.0.xsd">
	  <modelVersion>4.0.0</modelVersion>
	 
	  <groupId>org.codehaus.mojo</groupId>
	  <artifactId>my-project</artifactId>
	  <version>1.0</version>
	</project>
###### Maven坐标
groupId:artifactId:version是必须定义的元素 (如果groupId、version继承自一个parent，那么不需要进行明确的定义)。这三个元素扮演这一个类似于地址一样的角色。标志着仓库内的指定位置，就像maven项目的坐标系统。

###### groupId
定义当前Maven项目隶属的实际项目。首先，Maven项目和实际项目不一定是一对一的关系。比如SpringFramework这一实际项目，其对应的Maven项目会有很多，如spring-core、spring-context等。这是由于Maven中模块的概念，因此，一个实际项目往往会被划分成很多模块。其次，groupId不应该对应项目隶属的组织或公司。原因很简单，一个组织下会有很多实际项目，如果groupId只定义到组织级别，而后面我们会看到，artifactId只能定义Maven项目（模块），那么实际项目这个层将难以定义。通常groupId在一个organization或者项目中是独一无二的。比如，所有的maven核心组件都共同在groupId为org.apache.maven下。groupId不一定非要使用点符号，比如Junit项目。注意，在使用点符号定义groupId时，没有必要一定要使得项目包结构与此对应，但这样确实是个好的做法。当安装项目到仓库时，groupId就像操作系统中的文件管理系统，其中点符号将会被转换为“/”符号，作为仓库存储此项目的相对路径，以阿里巴巴的fastjson为例，groupId为com.alibaba，那么它在仓库内的相对位置为：com/alibaba/。配置此项的格式如下：
	
	<groupId>com.alibaba</groupId>
	
###### artifactId
artifactId通常就是项目的名称。artifactId与groupId组合并与世界上的其他项目区分开来，也直接指出了项目的路径，比如阿里巴巴的fastjson，它的位置就在com/alibaba/fastjson/。配置格式如下：
	
	<artifactId>fastjson</artifactId>
###### version
groupId:artifactId代表了一个项目（模块），但是却不能准确的表明项目的版本，由于项目的版本的重要性不容忽略，所以此项也是必须填充的。配置格式如下：
	
	<version>1.0</version>

###### packaging
此标签将与groupId:artifactId:version真正的确定一个项目（模块）的位置，还是以阿里巴巴的fastjson为例，假设version为1.0，packaging定义为jar，那么位置为：com/alibaba/fastjson/1.0/fastjson-1.0.jar。配置格式如下：

	<packaging>war</packaging>
	<packaging>jar</packaging>
	<packaging>pom</packaging>

当没有定义packaging时，默认为jar。目前支持的类型有：
	
	pom, jar, maven-plugin, ejb, war, ear, rar, par

类型经常和使用的打包方式对应， 尽管这也有例外。如果设置extensions为 true，就可以在 plugin里定义新的类型。所以前面的类型的例子不完整。普通的java项目定义为jar，web项目定义为war....

###### classifier
依赖的分类器。你也许偶尔会在项目坐标中发现第五个元素，那就是classifier。分类器可以区分属于同一个POM但不同构建方式的artifact。分类器名被附加到文件名的版本号后面。如果你想要构建两个单独的artifact成JAR，一个使用Java1.4编译器，另一个使用Java 6编译器，就可以使用分类器来生成两个单独的JAR artifact。配置格式如下：
	
	<classifier>...</classifier>
#### POM Relationships
maven一个非常强大的方面就是管理项目关系，包括依赖、继承、聚合。

###### Dependencies
POM的即使就是它的依赖。大多数项目都依赖于其它项目来摆正正确的build和run，所以此项将会给你减少大量的工作。

	<dependencies>
	  <dependency>
	    <groupId>...</groupId>
	    <artifactId>...</artifactId>
	    <version>...</version>
	    <type>...</type>
	    <classifier>...</classifier>
	    <scope>...</scope>
	    <systemPath></systemPath>
	    <optional>...</optional>
	  </dependency>
	  ...
	</dependencies>

前面几项就不用赘述，定义了依赖的坐标。
**type:** 也就是packaging
**scope:** 依赖的作用域，用来控制依赖与三种classpath(编译、运行、测试)的关系。值如下：
|值|传递性|作用阶段|其它|
|:---|---|-----|---|
|compile|有|编译、测试、运行|若没有定义scope，则默认为compile|
|provided|无|编译、测试|例子：servlet-api.jar，仅在编译测试时有效，而运行时tomcat等已经提供|
|runtime||测试、运行|例子：JDBC驱动|
|test|无|编译测试、运行测试||
|system||编译、测试|使用此项必须显示的使用元素systemPath|
**systemPath:** 依赖的路径，不推荐使用此元素来定义依赖的位置。仅仅在scope为system时使用此元素，否则，将会造成build失败。设置此值时必须为绝对路径，所以推荐与property搭配使用，由property来定义路径，在此元素处使用property即可。配置格式如下
	
	<systemPath>D://tomcat/bin/servlet-api.jar</systemPath>
	
**optional:** 当项目本身是一个依赖时，标记为可选的。例子，项目A依赖项目B来编译一部分可能不会在运行时使用的代码，那么我们就没有必要为整个项目提供项目B，所以如果项目X添加项目A作为自己的一个依赖，那么maven根本没有必要去安装项目B。
	
	<optional>true</optional>

###### Exclusions
定义此项目的是明确的告诉maven不依赖当前依赖的依赖，换句话说就是避免传递性依赖指定的依赖。比如，maven-embedder依赖maven-core，但是我们不希望使用它的依赖，那么我们可以添加exclusion。
	
	<dependencies>
	  <dependency>
	    <groupId>org.apache.maven</groupId>
	    <artifactId>maven-embedder</artifactId>
	    <version>2.0</version>
	    <exclusions>
	      <exclusion>
	        <groupId>org.apache.maven</groupId>
	        <artifactId>maven-core</artifactId>
	      </exclusion>
	    </exclusions>
	  </dependency>
	  ...
	</dependencies>
使用此项可以有效的减少传递性依赖。也可以使用通配符来定义此项：
	
	<dependencies>
	  <dependency>
	    <groupId>org.apache.maven</groupId>
	    <artifactId>maven-embedder</artifactId>
	    <version>3.1.0</version>
	    <exclusions>
	      <exclusion>
	        <groupId>*</groupId>
	        <artifactId>*</artifactId>
	      </exclusion>
	    </exclusions>
	  </dependency>
	  ...
	</dependencies>

#### 继承
maven带来了项目继承的概念，这很强大。尽管在构建系统中（如Ant），继承一定能被模拟出来，但是maven在使得项目继承对项目对象模型显式化做出了重要的一步。
	
	<modelVersion>4.0.0</modelVersion>
	<groupId>org.codehaus.mojo</groupId>
	<artifactId>my-parent</artifactId>
	<version>2.0</version>
	<packaging>pom</packaging>
对于聚合、父级项目，packaging必须为pom。 类型定义了绑定到一组生命周期阶段的目标。比如，如果packaging是jar，那么在package阶段将会执行jar:jar目标。如果packaging是pom，那么目标就是site:attach-descriptor。现在，我们将添加值到将被children及继承的父POM中，大多数的元素都将被它的children继承，包括：

	groupId
	version
	description
	url
	inceptionYear
	organization
	licenses
	developers
	contributors
	mailingLists
	scm
	issueManagement
	ciManagement
	properties
	dependencyManagement
	dependencies
	repositories
	pluginRepositories
	build
	plugin executions with matching ids
	plugin configuration
	etc.
	reporting
	profiles
	
不被继承的元素包括：

	artifactId
	name
	prerequisites
下面是一个children的例子：

	<modelVersion>4.0.0</modelVersion>
	
	<parent>
	  <groupId>org.codehaus.mojo</groupId>
	  <artifactId>my-parent</artifactId>
	  <version>2.0</version>
	  <relativePath>../my-parent</relativePath>
	</parent>
	
	<artifactId>my-project</artifactId>

注意上面的relativePath元素，它不是必须的，但是maven会首先搜索此本地路径然后是远程仓库。

#### 超级POM
类似于面向对象中对象的继承，POMs继承至parent并且从parent继承了部分值。 此外，就像JAVA中所有对象最终都继承至java.lang.Object，所有的POM都继承至一个基础的超级POM。下面是Maven 3.0.4的超级POM的片段：

	<modelVersion>4.0.0</modelVersion>
	
	<repositories>
	  <repository>
	    <id>central</id>
	    <name>Central Repository</name>
	    <url>http://repo.maven.apache.org/maven2</url>
	    <layout>default</layout>
	    <snapshots>
	      <enabled>false</enabled>
	    </snapshots>
	  </repository>
	</repositories>
	
	<pluginRepositories>
	  <pluginRepository>
	    <id>central</id>
	    <name>Central Repository</name>
	    <url>http://repo.maven.apache.org/maven2</url>
	    <layout>default</layout>
	    <snapshots>
	      <enabled>false</enabled>
	    </snapshots>
	    <releases>
	      <updatePolicy>never</updatePolicy>
	    </releases>
	  </pluginRepository>
	</pluginRepositories>
	
	<build>
	  <directory>${project.basedir}/target</directory>
	  <outputDirectory>${project.build.directory}/classes</outputDirectory>
	  <finalName>${project.artifactId}-${project.version}</finalName>
	  <testOutputDirectory>${project.build.directory}/test-classes</testOutputDirectory>
	  <sourceDirectory>${project.basedir}/src/main/java</sourceDirectory>
	  <scriptSourceDirectory>src/main/scripts</scriptSourceDirectory>
	  <testSourceDirectory>${project.basedir}/src/test/java</testSourceDirectory>
	  <resources>
	    <resource>
	      <directory>${project.basedir}/src/main/resources</directory>
	    </resource>
	  </resources>
	  <testResources>
	    <testResource>
	      <directory>${project.basedir}/src/test/resources</directory>
	    </testResource>
	  </testResources>
	  <pluginManagement>
	    <!-- NOTE: These plugins will be removed from future versions of the super POM -->
	    <!-- They are kept for the moment as they are very unlikely to conflict with lifecycle mappings (MNG-4453) -->
	    <plugins>
	      <plugin>
	        <artifactId>maven-antrun-plugin</artifactId>
	        <version>1.3</version>
	      </plugin>
	      <plugin>
	        <artifactId>maven-assembly-plugin</artifactId>
	        <version>2.2-beta-5</version>
	      </plugin>
	      <plugin>
	        <artifactId>maven-dependency-plugin</artifactId>
	        <version>2.1</version>
	      </plugin>
	      <plugin>
	        <artifactId>maven-release-plugin</artifactId>
	        <version>2.0</version>
	      </plugin>
	    </plugins>
	  </pluginManagement>
	</build>
	
	<reporting>
	  <outputDirectory>${project.build.directory}/site</outputDirectory>
	</reporting>
	
	<profiles>
	  <!-- NOTE: The release profile will be removed from future versions of the super POM -->
	  <profile>
	    <id>release-profile</id>
	
	    <activation>
	      <property>
	        <name>performRelease</name>
	        <value>true</value>
	      </property>
	    </activation>
	
	    <build>
	      <plugins>
	        <plugin>
	          <inherited>true</inherited>
	          <artifactId>maven-source-plugin</artifactId>
	          <executions>
	            <execution>
	              <id>attach-sources</id>
	              <goals>
	                <goal>jar</goal>
	              </goals>
	            </execution>
	          </executions>
	        </plugin>
	        <plugin>
	          <inherited>true</inherited>
	          <artifactId>maven-javadoc-plugin</artifactId>
	          <executions>
	            <execution>
	              <id>attach-javadocs</id>
	              <goals>
	                <goal>jar</goal>
	              </goals>
	            </execution>
	          </executions>
	        </plugin>
	        <plugin>
	          <inherited>true</inherited>
	          <artifactId>maven-deploy-plugin</artifactId>
	          <configuration>
	            <updateReleaseInfo>true</updateReleaseInfo>
	          </configuration>
	        </plugin>
	      </plugins>
	    </build>
	  </profile>
	</profiles>
 
你可以查看超级POM给你创建的POM中带来了哪些影响（或POM从超级POM继承了哪些元素）：
	
	mvn help:effective-pom

#### 依赖管理 dependencyManagement
除了继承某些顶层元素外，parents有一些元素为child与传递性依赖配置的元素，其中一种就是依赖管理。被POM用来帮助管理所有children的依赖信息。比如在my-parent这样一个项目中，使用dependencyManagement来定义了一个依赖：junit:junit:4.0，那么继承至my-parent的pom可以添加这样一个依赖：groupId=junit、artifactId=junit，然后maven会通过my-parent填充version配置，这种方法的好处显而易见。依赖关系可以设置在一个父POM中，它将传播到所有继承的poms中。注意，通过传递性依赖合并的artifact的version和scope也被以来管理的版本规范所控制。这可能会产生意想不到的结果。思考这样一个情况：在你的项目中用到了两个依赖： dep1、dep2，dep2同样也在使用dep1，而且需要特定的低版本才能运行，如果你使用了dependencyManagement定义了一个旧版本，dep2被迫使用旧版本并且失败了，所以你必须小心的检查整个依赖树来避免这样的问题，查看依赖树：
	
	mvn dependency:tree

#### 聚合
一个项目含有多个模块被称为多模块或聚合项目。使用modules来列出被聚合的模块（项目）, 如下：
	
	<modelVersion>4.0.0</modelVersion>
	
	<groupId>org.codehaus.mojo</groupId>
	<artifactId>my-parent</artifactId>
	<version>2.0</version>
	<packaging>pom</packaging>
	
	<modules>
	  <module>my-project</module>
	  <module>another-project</module>
	  <module>third-project/pom-example.xml</module>
	</modules>
	
你不用考虑内部模块间相互依赖的关系，即，不用担心列出这些模块的顺序问题，maven会对这些模块进行拓扑排序。对于继承与聚合的一个注意项：继承和聚合通过一个单一的、高级别的POM来动态的控制构建。你会经常看见既是parent又是聚合的项目。比如，整个maven核心通过一个基POM（org.apache.maven:maven）来运行。一个maven项目能否被执行可以通过一个命令验证：

	mvn compile

###### Properties
Properties是理解POM基础知识的最后一个必需部分。Maven properties是值占位符，就像Ant中的properties一样。他们的值可以被POM的任意地方通过符号${X}来使用（这里的X是一个property），或者他们会被插件所使用的，即使没有显示的使用：

	<properties>
	  <maven.compiler.source>1.7</maven.compiler.source>
	  <maven.compiler.target>1.7</maven.compiler.target>
	  <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
	  <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
	</properties>
  
他们有五种不同的格式：

- **env.X**
在变量前加一个“env.”，将会返回环境变量。比如：
	
		${env.PATH}
	
	注意：在windows上环境变量不区分大小写，但查询properties是区分大小写的。换句换说，windows上%PATH% 和%Path%会返回相同的值，而maven中不一样。

- **project.x**
使用点符号可以访问POM元素值，比如：
	
		<project><version>1.0</version></project>
可以通过下面的表达式进行访问：
	
		 ${project.version}
- **settings.x**
使用“settings.”符号可以访问settings.xml中的元素值，比如：
		
		<settings><offline>false</offline></settings>
可以通过下面的表达式进行访问：
	
		${settings.offline}
- **Java System Properties**
所有通过java.lang.System.getProperties()可以被访问的properties都可以作为maven的properties来使用，比如：
		
		${java.home}
- **x**
在POM中使用<properties />元素进行配置的properties，比如： 
	
		<properties><someVar>value</someVar></properties>
可以通过下面的表达式进行访问：
	
		${someVar}
#### Build配置
在声称自己掌握POM基础知识之前，还有两个元素必须了解。那就是build元素，它处理注入声明项目的目录结构和管理插件之类的东西，还有reporting元素，可以为你的项目生成report。

###### Build
根据POM 4.0.0 XSD，build元素在概念上被分为两部分：BaseBuild类型（profile build），包含一些对于所有的build元素的公共的元素集；Build类型（project build），包含BaseBuild的元素集以及更多顶层定义的元素。首先分析两类公共的元素：

	<!-- "Project Build"比"profile build"含有更多的元素 -->
	<build>...</build>
	
	<profiles>
	  <profile>
	    <!-- "Profile Build"是"Project Build"的一个子集 -->
	    <build>...</build>
	  </profile>
	</profiles>
##### BaseBuild元素配置
BaseBuild就像它的表意一样：是POM中两种build元素得到基础配置元素。

	<build>
	  <defaultGoal>install</defaultGoal>
	  <directory>${basedir}/target</directory>
	  <finalName>${artifactId}-${version}</finalName>
	  <filters>
	    <filter>filters/filter1.properties</filter>
	  </filters>
	  ...
	</build>
**defaultGoal**
如果没有给定目标或阶段，默认执行的目标或阶段。如果目标已经给定（在命令行输入如 jar:jar）；如果阶段已经给定也是相似的（在命令行输入如install）。
**directory** 
表示build文件存储的目录，按照maven中的说法，就是build的target。默认为：
	
	${basedir}/target
**finalName**
这是项目最终build时的名字，不需要填写文件扩展名。默认为：
	
	${artifactId}-${version}

**filter**
定义包含了properties的*.properties文件能够访问的资源，并进行值替换 ，举个例子：
在maven项目下的resource下有这样一个配置文件，jdbc.properties:
	
	jdbc.url=${jdbcUrl}
filters/filter1.properties文件如下：
	
	jdbcUrl=****:3306/aaa/
filters/filter2.propertiesw恩建如下：
	
	jdbcUrl=****:3306/bbb/
我们在build中有这样的配置：

	<filters>
	    <filter>filters/filter1.properties</filter>
	</filters>
	<resources>
       <resource>
           <directory>src/main/resources</directory>
           <filtering>true</filtering>
       </resource>
	</resources>
那么，在build时，将会使用filters/filter1.properties文件中的值对resources下使用到jdbcUrl的properties文件进行值替换（此处必须将filtering设置为true）。
#### Resources
Resources不是指的代码，它们不需要被编译，但确实项目所需要的。比如，jdbc.properties等，为了项目能被正确的build，你需要指定resources，如下：
	
	<build>
	  ...
	  <resources>
	    <resource>
	      <targetPath>META-INF/plexus</targetPath>
	      <filtering>false</filtering>
	      <directory>${basedir}/src/main/plexus</directory>
	      <includes>
	        <include>configuration.xml</include>
	      </includes>
	      <excludes>
	        <exclude>**/*.properties</exclude>
	      </excludes>
	    </resource>
	  </resources>
	  <testResources>
	    ...
	  </testResources>
	  ...
	</build>
**resources**
包含一个或多个resource元素的元素，每一个resource都定义了与项目相关的文件及其文件位置。
**targetPath**
指定用于放置build资源的目录。
**filtering**
true或false，表示是否对资源启用过滤。
**directory**
resource的位置，默认为：
	
	${basedir}/src/main/resources
**includes**
作为resource所包含的文件集，可以使用*通配符
**excludes**
排除，也就是作为resources所忽略的文件，和includes类似。
**testResources**
指定测试resource的位置，与resource类似，但仅仅作用与test阶段。默认为：
	
	${basedir}/src/test/resources
#### Plugins

	<build>
	  ...
	  <plugins>
	    <plugin>
	      <groupId>org.apache.maven.plugins</groupId>
	      <artifactId>maven-jar-plugin</artifactId>
	      <version>2.6</version>
	      <extensions>false</extensions>
	      <inherited>true</inherited>
	      <configuration>
	        <classifier>test</classifier>
	      </configuration>
	      <dependencies>...</dependencies>
	      <executions>...</executions>
	    </plugin>
	  </plugins>
	</build>
除了标准的坐标（groupId:artifactId:version），还有一些配置插件或与插件交互的元素。

**extensions**
true或false，是否加载此插件的扩展，默认为false。 将在后面详细介绍。

**inherited**
true或false，是否将此插件的配置适用于继承至此插件的children，默认为true。

**configuration**
配置当前插件所需的相关参数。请思考下面来至一个parent的POM片段：

	<plugin>
	  <groupId>my.group</groupId>
	  <artifactId>my-plugin</artifactId>
	  <configuration>
	    <items>
	      <item>parent-1</item>
	      <item>parent-2</item>
	    </items>
	    <properties>
	      <parentKey>parent</parentKey>
	    </properties>
	  </configuration>
	</plugin>
下面是一个项目，继承至上面的parent，请思考其传递性：

	<plugin>
	  <groupId>my.group</groupId>
	  <artifactId>my-plugin</artifactId>
	  <configuration>
	    <items>
	      <item>child-1</item>
	    </items>
	    <properties>
	      <childKey>child</childKey>
	    </properties>
	  </configuration>
	</plugin>
默认行为是根据元素名来合并元素的内容，但是如果child中指定了值，那么将以child中配置的值为准，若child中没有配置，而parent中配置了值，就以parent中的值为准。下面是上面的例子的结果：

	<plugin>
	  <groupId>my.group</groupId>
	  <artifactId>my-plugin</artifactId>
	  <configuration>
	    <items>
	      <item>child-1</item>
	    </items>
	    <properties>
	      <childKey>child</childKey>
	      <parentKey>parent</parentKey>
	    </properties>
	  </configuration>
	</plugin>
你可以在标签上使用属性来控制配置的继承。属性“combine.children”与“combine.self”，被用在child中来控制与parent中配置的组合。下面是修改之前例子中child的片段：

	<configuration>
	  <items combine.children="append">
	    <!-- combine.children="merge" is the default -->
	    <item>child-1</item>
	  </items>
	  <properties combine.self="override">
	    <!-- combine.self="merge" is the default -->
	    <childKey>child</childKey>
	  </properties>
	</configuration>
	
实际结果如下：

	<configuration>
	  <items combine.children="append">
	    <item>parent-1</item>
	    <item>parent-2</item>
	    <item>child-1</item>
	  </items>
	  <properties combine.self="override">
	    <childKey>child</childKey>
	  </properties>
	</configuration>
**combine.children="append"**
组合parent、child中的配置。

**combine.self="override"**
禁止parent的配置。若你同时使用combine.self="override"与combine.children="append"到同一个元素，会按照前者来，也就是禁止怕人同的配置。注意，这些属性只适用于configuration元素内，并且不能传递到嵌套元素。并且这写属性有传递性，应该小心使用，以确保不会对继承至当前项目的项目不受影响。

**dependencies**
Dependencies在POM中被广泛使用，而这里指的是plugins元素块下的dependencies元素。这种元素与普通的dependencies元素有着相同的结构，唯一的区别就是，不是作为项目依赖来使用的，而是作为插件的依赖来使用的。它的功能就是改变插件的依赖，可能是通过禁止来删除某些在运行时不曾使用的依赖，或者修改依赖的版本。

**executions**
一个插件可能有很多个目标，记住这一点很重要，每一个目标可能有单独的配置，或绑定一个插件的目标到不同的阶段，使用executions配置插件的目标的执行。比如，你想绑定antrun:run目标到verify阶段，并且执行时显示build目录、避免它将配置传递给children（配置inherited为false），配置如下：

	<build>
	  <plugins>
	    <plugin>
	      <artifactId>maven-antrun-plugin</artifactId>
	      <version>1.1</version>
	      <executions>
	        <execution>
	          <id>echodir</id>
	          <goals>
	            <goal>run</goal>
	          </goals>
	          <phase>verify</phase>
	          <inherited>false</inherited>
	          <configuration>
	            <tasks>
	              <echo>Build Dir: ${project.build.directory}</echo>
	            </tasks>
	          </configuration>
	        </execution>
	      </executions>
	
	    </plugin>
	  </plugins>
	</build>
**id**
任意，最好表现此execution的含义。将执行到定义的阶段时，id会被展示：[plugin:goal execution: id]，在这个例子中： [antrun:run execution: echodir]。

**goals**
此插件的目标列表。

**phase**
目标列表将要被执行的阶段。允许将任何目标绑定到build生命周期中的任意阶段、改变maven的默认行为。

**inherited**
false，禁止maven将excution传递给children。

**configuration**
只局限于当前插件的指定目标列表，不会影响未列出的其他目标的行为。

#### Plugin Management
**pluginManagement**
包含plugin元素，除了不是为这个特定的项目build配置的插件信息，也就是配置此项后，它的children也会继承这些插件配置。但是children有权覆盖pluginManagement的定义。例子：

	<build>
	  ...
	  <pluginManagement>
	    <plugins>
	      <plugin>
	        <groupId>org.apache.maven.plugins</groupId>
	        <artifactId>maven-jar-plugin</artifactId>
	        <version>2.6</version>
	        <executions>
	          <execution>
	            <id>pre-process-classes</id>
	            <phase>compile</phase>
	            <goals>
	              <goal>jar</goal>
	            </goals>
	            <configuration>
	              <classifier>pre-process</classifier>
	            </configuration>
	          </execution>
	        </executions>
	      </plugin>
	    </plugins>
	  </pluginManagement>
	  ...
	</build>
	
如果我们仅仅添加这些配置到plugins，那么他们将只作用于单一的POM（也就是当前的POM），但是假如我们将这些插件配置到pluginManagement元素下，那么当前POM以及children都会添加这些插件并且执行配置的executions。

	<build>
	  ...
	  <plugins>
	    <plugin>
	      <groupId>org.apache.maven.plugins</groupId>
	      <artifactId>maven-jar-plugin</artifactId>
	    </plugin>
	  </plugins>
	  ...
	</build>
	
#### build
XSD中的构建类型表示那些仅适用于“项​​目构建”的元素。尽管有一些额外的元素(6个)，实际上只有两组元素directories、extensions。

**Directories**
一些目录元素的集合。

	<build>
	  <sourceDirectory>${basedir}/src/main/java</sourceDirectory>
	  <scriptSourceDirectory>${basedir}/src/main/scripts</scriptSourceDirectory>
	  <testSourceDirectory>${basedir}/src/test/java</testSourceDirectory>
	  <outputDirectory>${basedir}/target/classes</outputDirectory>
	  <testOutputDirectory>${basedir}/target/test-classes</testOutputDirectory>
	  ...
	</build>

**Extensions**
扩展项，是一系列artifact的集合，被用于当前artifact的构建中。

	<build>
	  ...
	  <extensions>
	    <extension>
	      <groupId>org.apache.maven.wagon</groupId>
	      <artifactId>wagon-ftp</artifactId>
	      <version>1.0-alpha-3</version>
	    </extension>
	  </extensions>
	  ...
	</build>
#### Reporting
包含一系列元素，用于site阶段（生成报告、站点）。某些用于生成report的插件将会被定义到此元素下。

	<reporting>
	  <outputDirectory>${basedir}/target/site</outputDirectory>
	  <plugins>
	    <plugin>
	      <artifactId>maven-project-info-reports-plugin</artifactId>
	      <version>2.0.1</version>
	      <reportSets>
	        <reportSet></reportSet>
	      </reportSets>
	    </plugin>
	  </plugins>
	</reporting>
	
outputDirectory也就是site阶段生成的文件的目录，默认值为：
	
	${basedir}/target/site

**Report Sets**
与build下的execution类似，我们知道插件可以有很多目标，所以我们还得配置execution来配置插件的目标，但和build下的execution不同的是，不能绑定report到其它的阶段。比如，假设你想配置javadoc:javadoc目标链接到 "http://java.sun.com/j2se/1.5.0/docs/api/"，那么你的配置应该是这样的：

	<reporting>
	  <plugins>
	    <plugin>
	      ...
	      <reportSets>
	        <reportSet>
	          <id>sunlink</id>
	          <reports>
	            <report>javadoc</report>
	          </reports>
	          <inherited>true</inherited>
	          <configuration>
	            <links>
	              <link>http://java.sun.com/j2se/1.5.0/docs/api/</link>
	            </links>
	          </configuration>
	        </reportSet>
	      </reportSets>
	    </plugin>
	  </plugins>
	</reporting>

#### 其它项目信息
尽管上面的配置信息已经足够去掌握POM的配置，但这里还有很多让开发人员更加轻松的配置元素，许多这类元素都与生成站点有关。

**name**
项目应该有一个别名，就像SUN公司不会称他们的项目为 "java-1.5"，而是"Tiger"一样。可以通过配置此项元素来取个别名。

**description**
描述项目是一个好的实践，尽管这不能取代正式的文档，但对任何读者进行快速了解项目是有帮助的。

**url**
配置项目所在的地址。
	
	 <url>http://maven.apache.org</url>

**inceptionYear**
项目成立时间。

**Licenses**
项目许可证。
	
	<licenses>
	  <license>
	    <name>Apache License, Version 2.0</name>
	    <url>https://www.apache.org/licenses/LICENSE-2.0.txt</url>
	    <distribution>repo</distribution>
	    <comments>A business-friendly OSS license</comments>
	  </license>
	</licenses>

**Organization**
项目的组织。

	<organization>
	  <name>Codehaus Mojo</name>
	  <url>http://mojo.codehaus.org</url>
	</organization>
	
**Developers**
开发人员列表。

	<developers>
	  <developer>
	    <id>jdoe</id>
	    <name>John Doe</name>
	    <email>jdoe@example.com</email>
	    <url>http://www.example.com/jdoe</url>
	    <organization>ACME</organization>
	    <organizationUrl>http://www.example.com</organizationUrl>
	    <roles>
	      <role>architect</role>
	      <role>developer</role>
	    </roles>
	    <timezone>America/New_York</timezone>
	    <properties>
	      <picUrl>http://www.example.com/jdoe/pic</picUrl>
	    </properties>
	  </developer>
	</developers>

**Contributors**
贡献者列表。

	<contributors>
	  <contributor>
	    <name>Noelle</name>
	    <email>some.name@gmail.com</email>
	    <url>http://noellemarie.com</url>
	    <organization>Noelle Marie</organization>
	    <organizationUrl>http://noellemarie.com</organizationUrl>
	    <roles>
	      <role>tester</role>
	    </roles>
	    <timezone>America/Vancouver</timezone>
	    <properties>
	      <gtalk>some.name@gmail.com</gtalk>
	    </properties>
	  </contributor>
	</contributors>

#### 环境配置
**Issue Management**
项目使用的缺陷跟踪系统 (Bugzilla, TestTrack, ClearQuest, etc) 。

	<issueManagement>
	  <system>Bugzilla</system>
	  <url>http://127.0.0.1/bugzilla/</url>
	</issueManagement>
	
**Continuous Integration Management**
项目持续集成构建系统信息。

	<ciManagement>
	  <system>continuum</system>
	  <url>http://127.0.0.1:8080/continuum</url>
	  <notifiers>
	    <notifier>
	      <type>mail</type>
	      <sendOnError>true</sendOnError>
	      <sendOnFailure>true</sendOnFailure>
	      <sendOnSuccess>false</sendOnSuccess>
	      <sendOnWarning>false</sendOnWarning>
	      <configuration><address>continuum@127.0.0.1</address></configuration>
	    </notifier>
	  </notifiers>
	</ciManagement>
	
**Mailing Lists**
邮箱列表。

	<mailingLists>
	  <mailingList>
	    <name>User List</name>
	    <subscribe>user-subscribe@127.0.0.1</subscribe>
	    <unsubscribe>user-unsubscribe@127.0.0.1</unsubscribe>
	    <post>user@127.0.0.1</post>
	    <archive>http://127.0.0.1/user/</archive>
	    <otherArchives>
	      <otherArchive>http://base.google.com/base/1/127.0.0.1</otherArchive>
	    </otherArchives>
	  </mailingList>
	</mailingLists>
	
**SCM**
版本控制信息（svn、git）

	<scm>
	  <connection>scm:svn:http://127.0.0.1/svn/my-project</connection>
	  <developerConnection>scm:svn:https://127.0.0.1/svn/my-project</developerConnection>
	  <tag>HEAD</tag>
	  <url>http://127.0.0.1/websvn/my-project</url>
	</scm>

关于git
	
	<scm>
		<url>https://github.com/XXX/XXX</url>
		<tag>HEAD</tag>
		<connection>scm:git:https://git@github.com/XXX/XXX.git</connection>
	</scm>

**Prerequisites**
POM可能有某些条件（先决条件） ，比如依赖于某个版本。

	<prerequisites>
	  <maven>2.0.6</maven>
	</prerequisites>
	
**Repositories**
配置项目的部署的仓库信息。

	<repositories>
	  <repository>
	    <releases>
	      <enabled>false</enabled>
	      <updatePolicy>always</updatePolicy>
	      <checksumPolicy>warn</checksumPolicy>
	    </releases>
	    <snapshots>
	      <enabled>true</enabled>
	      <updatePolicy>never</updatePolicy>
	      <checksumPolicy>fail</checksumPolicy>
	    </snapshots>
	    <id>codehausSnapshots</id>
	    <name>Codehaus Snapshots</name>
	    <url>http://snapshots.maven.codehaus.org/maven2</url>
	    <layout>default</layout>
	  </repository>
	</repositories>
	<pluginRepositories>
	  ...
	</pluginRepositories>
releases, snapshots
项目的类型，（发布版、快照）

enabled
是否启用类型（releases, snapshots）

updatePolicy
此元素指定更新尝试发生的频率，包括: always,、daily (default)、 interval:X (分钟数) 、never。

checksumPolicy
当maven将文件部署到仓库时，它也会部署相应的校验和文件。 包括：ignore、fail、warn on missing、incorrect checksums。

layout
在仓库中的布局。

Plugin Repositories
插件仓库，部分项目属于maven插件项目，有时候maven插件库与普通项目仓库是分离的，需要进行配置。

Distribution Management
分发管理，它管理整个构建过程中生成的artifact和辅助文件的分发。

	<distributionManagement>
	  ...
	  <downloadUrl>http://mojo.codehaus.org/my-project</downloadUrl>
	  <status>deployed</status>
	</distributionManagement>
downloadUrl
表示artifact的下载地址。

status
maven会在将项目传输到存储库时设置项目的状态。其有效类型如下：
|值|描述|
|--|--|
|none|默认值|
|converted| 仓库的管理员将pom从早期版本转换为maven 2
|partner|同步，意思是，此artifact已经被同步到同伴仓库
|deployed|最常见的状态，意思是artifact是从maven2或3部署的，这是使用命令行在deploy阶段手动部署时的结果。
|verified|项目已被确认

Repository
在repositories元素下定义了当前artifact使用的远程artifact的POM的下载位置和方式，distributionManagement定义当项目进行deploy时获取远程仓库的位置和方式。如果snapshotRepository没有被配置，那么repository元素将用于快照的分发。

	<distributionManagement>
	  <repository>
	    <uniqueVersion>false</uniqueVersion>
	    <id>corp1</id>
	    <name>Corporate Repository</name>
	    <url>scp://repo/maven2</url>
	    <layout>default</layout>
	  </repository>
	  <snapshotRepository>
	    <uniqueVersion>true</uniqueVersion>
	    <id>propSnap</id>
	    <name>Propellors Snapshots</name>
	    <url>sftp://propellers.net/maven</url>
	    <layout>legacy</layout>
	  </snapshotRepository>
	  ...
	</distributionManagement>
id, name
id唯一标识仓库，name为任意可读名（可理解的）。

uniqueVersion
使用true、false来表示artifact部署到仓库时是否应该获取一个唯一的生成版本号，或使用版本号作为地址的一部分。

url
它指定了用于将构建的artifact（以及pom文件和校验和数据）传输到存储库的位置和传输协议。

layout
在仓库中的布局。 

Site Distribution
配置站点和文档的部署。

	<distributionManagement>
	  ...
	  <site>
	    <id>mojo.website</id>
	    <name>Mojo Website</name>
	    <url>scp://beaver.codehaus.org/home/projects/mojo/public_html/</url>
	  </site>
	  ...
	</distributionManagement>
id, name, url
与distributionManagement的repository元素相同。

Relocation

	<distributionManagement>
	  ...
	  <relocation>
	    <groupId>org.apache</groupId>
	    <artifactId>my-project</artifactId>
	    <version>1.0</version>
	    <message>We have moved the Project under Apache</message>
	  </relocation>
	  ...
	</distributionManagement>
随着项目的发展，一件常见的事情就是他们被迫搬到更合适的地方。用于配置项目的迁移。

Profiles
POM 4.0的新特性，在进行项目build时可以根据环境来改变配置的元素。 profile元素包含触发条件与对POM配置的改变，比如，项目在测试环境下指向的数据库与最终部署的不一样，或者根据运行时的JDK版本拉取不同版本的依赖等。

	<profiles>
	  <profile>
	    <id>test</id>
	    <activation>...</activation>
	    <build>...</build>
	    <modules>...</modules>
	    <repositories>...</repositories>
	    <pluginRepositories>...</pluginRepositories>
	    <dependencies>...</dependencies>
	    <reporting>...</reporting>
	    <dependencyManagement>...</dependencyManagement>
	    <distributionManagement>...</distributionManagement>
	  </profile>
	</profiles>
	
**Activation**
此项就是profile的激活条件配置。

	<profiles>
	  <profile>
	    <id>test</id>
	    <activation>
	      <activeByDefault>false</activeByDefault>
	      <jdk>1.5</jdk>
	      <os>
	        <name>Windows XP</name>
	        <family>Windows</family>
	        <arch>x86</arch>
	        <version>5.1.2600</version>
	      </os>
	      <property>
	        <name>sparrow-type</name>
	        <value>African</value>
	      </property>
	      <file>
	        <exists>${basedir}/file2.properties</exists>
	        <missing>${basedir}/file1.properties</missing>
	      </file>
	    </activation>
	    ...
	  </profile>
	</profiles>

当定义的条件符合时，maven会将profile中配置的改变应用。条件列表如下：
jdk
jdk版本。

os
操作系统。

property
属性。

file
文件是否存在也会激活。

查看那些profile将会被激活：
	
	mvn help:active-profiles

实际的例子：

	<profile>
		<id>beta</id>
		<activation>
			<property>
				<name>beta</name>
				<value>beta</value>
			</property>
		</activation>
		<properties>
			<jdbc.url>123456</jdbc.url>
		</properties>
	</profile>
可以发现，当上面的profile激活时，jdbc.url将会被替换为123456。