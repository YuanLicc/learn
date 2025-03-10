## WordCount 实例

hadoop 创建文件夹存放需要被统计的文件

```shell
hadoop fs -mkdir /usr
```

创建单子文件并写入内容，可在网上找几篇英文文章写入

```shell
vim word.txt
```

上传到 hdfs

```shell
hadoop fs -put word.txt /usr
```

利用 maven 创建 word-count 项目

```shell
mvn archetype:generate -DgroupId=personal.mr.test -DartifactId= word-count -DarchetypeArtifactId=maven-archetype-quickstart
```

pom.xml 引入需要的包

```xml
<properties>
	<hadoop-version>3.2.1</hadoop-version>
</properties>
<dependencies>
    <dependency>
        <groupId>org.apache.hadoop</groupId>
        <artifactId>hadoop-common</artifactId>
        <version>${hadoop-version}</version>
    </dependency>
    <dependency>
        <groupId>org.apache.hadoop</groupId>
        <artifactId>hadoop-mapreduce-client-core</artifactId>
        <version>${hadoop-version}</version>
    </dependency>
</dependencies>
```

编写 Mapper 程序

```java
package personal.mr.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.util.Arrays;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    private static  final Log logger = LogFactory.getLog(WordCountMapper.class);

    public void map(LongWritable key, Text value, Context context)  {
        logger.info("key =>" + key);
        logger.info("value =>" + value);

        Arrays.stream(value.toString().split(" ")).forEach(word -> {
            try {
                context.write(new Text(word), new LongWritable(1));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
```

编写 Reducer 程序

```java
package personal.mr.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

    private static final Log logger = LogFactory.getLog(WordCountReducer.class);

    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

        LongWritable longWritable = new LongWritable(0);

        values.forEach(value -> {
            longWritable.set(longWritable.get() + value.get());
        });

        context.write(key, longWritable);
    }
}
```

编写主程序

```java
package personal.mr.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Application {

    public static void main(String[] args) {
        try {
            Job job = Job.getInstance(new Configuration());
            job.setJarByClass(Application.class);

            job.setMapperClass(WordCountMapper.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(LongWritable.class);

            job.setReducerClass(WordCountReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(LongWritable.class);
		   // 参数1：逗号分隔，表示输入的文件路径，可指定多个输入文件
            FileInputFormat.addInputPaths(job, args[0]);
		   // 参数2：输出文件路径
            FileOutputFormat.setOutputPath(job, new Path(arg[1]));

            System.exit(job.waitForCompletion(true) ? 0 : 1);
        }
        catch (Exception e)  {
            e.printStackTrace();
        }
    }
}
```

pom.xml 指定程序的入口

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>3.1.0</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                    <configuration>
                        <transformers>
                            <transformer  implementation = "org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                <mainClass>personal.mr.test.Application</mainClass>
                            </transformer>
                        </transformers>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

程序打包

```shell
mvn package
```

在集群下运行打包好的 jar 文件

```shell
hadoop jar word-count-1.0-SNAPSHOT.jar /usr/word.txt /usr/out
```

等待程序执行完，可查 /usr/out 文件夹下的输出内容

```shell
hadoop fs -ls /usr/out
## 利用 cat 来查看文件内容
hadoop fs -cat /usr/out/....
```