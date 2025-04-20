## mybatis-plus的问题
源代码请 👉 <a target="_blank" href="https://gitee.com/qingqinkj/mdp-boot-starter-core/tree/master/mdp-mybatis-enhance">mdp-mybatis</a>  👈  
我们在使用mybatis-plus经常遇到的几个问题：
1. 不支持多主键的增删改查
2. 多表联合查询太麻烦
3. 前端动态条件，复杂条件支持有限
4. 对map类型返回结果字段不进行驼峰命名
本项目着重解决上述两个问题
   
### 多主键问题解决方案
通过在实体类中添加注解 @TableIds解决，所有服务类继承[BaseService](./src/main/java/com/mdp/core/service/BaseService.java)
此方案最简单最友好

#### 实现原理
1. 覆盖[TableInfoHelper.java](./src/main/java/com/baomidou/mybatisplus/core/metadata/TableInfoHelper.java)  
此类负责解析实体中的@TableIds及原生的@TableId注解。如果不存在@TableIds，则按原生的主键模型走
如果存在多个@TableIds，则解析为多主键，如果只存在一个，则按单主键模型走。

2. 重写[BaseService](./src/main/java/com/mdp/core/service/BaseService.java)基类
此类负责重写BaseService原生的方法，检测到实体是多主键，则按多主键方式执行，否则按单主键模式走原来的方法
   
### 多表联合查询的解决方案
支持以下两种方案
1. 通过@Select注解重写查询sql,通过com.mdp.Util@trimWhere(ew.customSqlSegment)动态拼接原生where条件
2. 通过调用xml的方式，书写联合查询sql,通过${@com.mdp.Util@trimWhere(ew.customSqlSegment)}动态拼接where条件


### 前端动态条件解决方案
支持以下两种解决方案并存
1. 前端构造树状结构化条件[QueryCondition.java](./src/main/java/com/mdp/core/query/QueryCondition.java)上送到服务端，服务端解析成对应QueryWrapper
2. 支持前端上送的参数带有条件操作符，比如:
   p1=*u*,后端解析成 p1 like %u%
   p1=$IN1,2,3,4 后端解析成 p1 in 1,2,3,4
   p1=$NOT NULL 后端解析成 p1 not null

### 对map类型返回结果字段不进行驼峰命名的解决方案
解决方案 通过注入类型包装器 [MyMapWrapper.java](./src/main/java/com/mdp/mybatis/MyMapWrapper.java)进行解决