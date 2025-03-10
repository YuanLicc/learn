#### JobTracker

MR 2.x 开始不再使用

#### 包

| 包                          | 描述                                                         |
| --------------------------- | ------------------------------------------------------------ |
| 编程                        |                                                              |
| mapred.lib.*                | 用户编写 MR 程序可使用此包减少编写代码量                     |
| mapred.jobcontrol           | 管理相互依赖的作业 DAG                                       |
| mapred.join                 | 实现 MAP SIDE JOIN 算法，Map Task 实现 join 算法             |
| mapred.pipes                | 允许用户 C、C++ 编写 MR 作业                                 |
| mapreduce                   | 新版本变成接口，比旧版本封装性好                             |
| 计算框架                    |                                                              |
| mapred.filecache            | DistributedCache 实现，是 Hadoop 的数据分发工具，将用户应用程序中需要的文件分发到各个节点上 |
| mapred.tools                | 管理控制 MR，允许用户动态更新服务级别的授权策略和访问权限控制属性（ACL） |
| mapreduce.split             | 作业的 InputFormat 生成相应的输入 split                      |
| mapreduce.server.jobtracker | 维护 JobTracker 可见的 TaskTracker 状态信息和资源使用情况    |
| 安全机制                    |                                                              |
| mapreduce.security.*        | 实现了 MR 安全机制                                           |

