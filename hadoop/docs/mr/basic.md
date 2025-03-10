## Basic

hadoop 主要由两部分组成， 分布式文件系统和分布式计算框架 MR。分布式文件系统主要用于大规模数据的分布式存储，而 MR 则是构建在分布式文件系统之上，对存储在分布式文件系统中的数据进行分布式计算。

Hadoop 中，MR 底层的分布式文件系统是独立模块，用户可按照约定的一套接口实现自己的分布式文件系统。然后经过简单的配置后，存储在改文件系统的数据便可被 MR 处理。Hadoop 默认使用的分布式文件系统是 HDFS，与 MR 框架紧密结合。

##### HDFS 架构

是一个具有高度容错性的分布式文件系统，适合部署在廉价的机器上。能提供高吞吐量的数据访问，非常适合大规模=数据集上的应用。总体上采用  MASTER/SLAVE 架构，由 Client、NameNode、Secondary NamNode、DataNode 几个组件组成。

- Client

  通过与 NameNode 和 DataNode 交互访问 DHFS 中的文件。

- NameNode

  整个 Hadoop 集群中只有一个 NameNode。是整个系统的总管，负责管理 HDFS 的目录树和相关文件元数据信息。这些信息是以 fsimage 和 editlog 两种文件形式存放在本地磁盘，当 HDFS 重启时重新构造出来的。NameNode 还负责监控各个 DataNode 的健康状态，一旦发现某个 DataNode 宕机，则将 DataNode 移除 HDFS 并重新备份其上面的数据。

- Secondary NameNode

  Secondary NameNode 最重要的任务不是为 NameNode 元数据进行热备份，而是定期合并 fsimage 和 editlog 日志，并传输给 NameNode。为了减少 NameNode 的压力，NameNode 并不会合并 fsimage 和 editlog，而是交给 Secondary NameNode 完成合并和存储。

- DataNode

  每个 Slave 节点上安装一个 DataNode，负责实际的数据存储，并将数据信息定期汇报给 NameNode。DataNode 以固定大小的 block 为基本单位组织文件内容，默认情况 block 大小为 64MB。用户上传一个大文件到 HDFS 上时，改文件会被切分成若干个 block，分别存储到不同的 DataNode，为了保证数据可靠，会将同一个 block 以流水线方式写到若干个（默认 3 ）不同的 DataNode 上。

##### MR 架构

由  Client、JobTracker、TaskTracker 和 Task 几个组件组成。

- Client

  用户编写的 MR 程序通过 Client 提交到 JobTracker，同时，可通过 Client 提供的接口查看作业运行状态。Hadoop 用 作业（Job） 来表示 MR 程序。一个 MR 程序可对应弱冠个作业，儿每个作业会被分解成若干个 Map/Reduce 任务（Task）。

- JobTracker

  主要负责资源监控和作业调度，监控所有与 TaskTracker 与作业的健康状态