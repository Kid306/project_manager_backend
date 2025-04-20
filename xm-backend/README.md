<p align="center">
	<a href="http://111.230.141.161/xm"  target="_blank">
	    <img src="./docs/images/index/mm_logo_big.png" width="400" alt="logo">
	</a>
</p>
<p align="center">
	<strong>唛盟xm：涵盖项目规划、需求管理、开发迭代、版本控制、缺陷跟踪、测试管理、工时管理、效能分析等环
节，实现项目全过程、全方位管理的一站式企业研发项目管理解决方案</strong>
</p>

<p align="center">
	<a target="_blank" href="https://gitee.com/maimengcloud/xm-ui-web">
        <img src='https://gitee.com/maimengcloud/xm-ui-web/badge/star.svg?theme=gvp' alt='gitee star'/>
    </a> 
</p>
<p align="center">
	👉 <a target="_blank" href="http://111.230.141.161/xm">http://111.230.141.161/xm</a>  👈
</p>

## 快速导航
- [前端组件](https://gitee.com/maimengcloud/xm-ui-web)
- [后端服务](https://gitee.com/maimengcloud/xm-backend)
- [体验环境](http://111.230.141.161/xm)
  登陆界面上选择演示账号登陆或者直接扫码登陆，无须注册

## 📢 简介[唛盟xm](/)
唛盟企业级研发管理系统简称唛盟xm,属于唛盟生态的专业子系统之一，以研发管理为核心，涵盖项目规划、需求管理、开发迭代、版本控制、缺陷跟踪、测试管理、工时管理、效能分析等环节，实现全过程、全方位的研发管理。通过该系统，企业能够优化研发流程，提高研发效率，降低研发成本，提高市场竞争力。
💪唛盟生态遵循 <strong>“一个底座+N个专业子系统”</strong> 的架构，基于同一个底座的各个专业子系统可以任意组合形成一个大的业务系统  


## 📢 唛盟生态

💪唛盟生态遵循 <strong>“一个底座+N个专业子系统”</strong> 的架构，基于同一个底座的各个专业子系统可以任意组合形成一个大的业务系统。👉[聊聊唛盟生态1+n架构](https://www.bilibili.com/video/BV1BD421V7Bu/?spm_id_from=333.337.search-card.all.click&vd_source=93be23d03863773d50b81112985b9237)

### 底座 mdp-core
[mdp-core](https://gitee.com/maimengcloud/mdp-core)
### N个专业子系统
以下专业子系统都是基于唛盟底座 + 唛盟低代码 搭建起来的，本工程为其中的研发项目管理子系统后端服务工程

| 唛盟子系统                        | 说明               | 版本     |
|----------------------------------------------------------------------|------------------|--------|
| [低代码、0代码](https://gitee.com/maimengcloud/mdp-lcode-ui-web)| 低代码、0代码框架           | 3.5.0 |
| [账户、权限、组织管理](https://gitee.com/maimengcloud/mdp-lcode-backend/tree/master/mdp-sys)| 账户、权限、组织管理，支持多租户 | 3.5.0  |
| [数据模型](https://gitee.com/maimengcloud/mdp-dm-backend/tree/master/mdp-dm-backend)|表结构设计、表数据增删改查等ddl、dml操作，在线执行sql等  | 3.5.0 |
| [第三方支付登录等](https://gitee.com/maimengcloud/mdp-tpa-backend)  | 微信支付、支付宝支付、paypal支付、第三方登录   | 3.5.0  |
| [统一认证中心](https://gitee.com/maimengcloud/mdp-oauth2-backend) | 短信、微信、账号登录 | 3.5.0 |
| [统一流程中心](https://gitee.com/maimengcloud/mdp-workflow-backend)| 审批、代办、设计流程、监控流程等  | 3.5.0 |
| [短信](https://gitee.com/maimengcloud/mdp-sms-ui-web) | 群发消息              | 3.5.0  |
| [代码生成器](https://gitee.com/maimengcloud/mdp-code-generator)| 自动生成前后端代码          | 3.5.0  |
| [研发项目管理](https://gitee.com/maimengcloud/xm-ui-web)| 产品管理、需求管理、任务计划、迭代、测试、效能等         | 3.5.0  |
| [即时通讯](https://gitee.com/maimengcloud/mdp-im-web)  | 即时通讯、消息、聊天            | 3.5.0  |
| [财务](https://gitee.com/maimengcloud/ac-core-ui-web)| 财务子系统，凭证、报销、会计记账、成本、结算 等          | 3.5.0 |
| [协同办公](https://gitee.com/maimengcloud/oa-ui-web)  | 办公用品、会议、车辆、资产、档案、用印、采购、绩效等功能           | 3.5.0 |



## 💪 内置功能、界面展示
本工程为后端服务，功能列表以前端介绍为准，请点以下链接可查阅  
[内置功能](https://gitee.com/maimengcloud/xm-ui-web)

## ⚙ 技术栈

| 框架                                                                   | 说明               | 版本       |
|----------------------------------------------------------------------|------------------|----------|
| spring boot                               | spring boot 框架           | 2.6.11   |
| mybatis plus                                      | 数据库操作框架          | 3.5.3.1  |
| spring security                                   | 安全框架 | 2.1.7    |
| jsqlparse                                     | sql解析引擎            | 4.7+     |
| swagger                                     | 接口说明框架            | 2.2.8    |
| logback                                     | 日志框架            | 1.2.3    |
| jexl13                                     | 表达式引擎            | 3.1      |
| flowable                      | 流程引擎-可换     | 6.4.2    |
| spring cloud                                     | cloud框架-可换            | 2020.0.0 |
| spring cloud consul                                    | cloud框架-可换            | 1.10+    |
| spring cloud consul                                    | cloud框架-可换            | 1.10+    |
| spring oauth2                   | 统一认证中心-可换   | 5.2.2    |

## ⚙ 开发工具

| 插件名   | 功能                      |
|-------|-------------------------|
| idea  | java 开发工具    社区版、企业版都可  |
| mysql | 数据库 mysql8以上，其它数据库请联系客服 |

## ⚙  工程结构说明
| 子工程                | 功能                                                 |
|--------------------|----------------------------------------------------|
| xm-api             | 提供一些需要特殊定制的公共api接口，比如商业版才有的功能，需要先抽取成接口，再由商业版提供具体实现 |
| xm-core            | xm的主体工程，具体业务功能代码都应该放在此工程                           |
| xm-pro             | 商业版的实现代码                                           | |
| xm-bootstrap       | 打包部署工程 单体版                                         |
| xm-cloud-bootstrap | 打包部署工程 cloud版                                      |

## ⚙ 快速开始

### 环境要求
数据库版本mysql8,jdk版本java-8-openjdk-amd64,部署时需要用到redis版本3.0.6以上

### 开发
```bash

# 克隆开发底座项目
git clone https://gitee.com/maimengcloud/mdp-core.git
# 安装开发底座依赖
mvn install 

# 克隆oauth2项目(非必须,接入认证中心才需要)
git https://gitee.com/maimengcloud/mdp-oauth2-backend
# 安装oauth2依赖
mvn install

# 克隆cloud项目(非必须，部署cloud版本才需要)
git https://gitee.com/maimengcloud/mdp-cloud-backend
# 安装cloud依赖
mvn install

# 克隆workflow项目(非必须，需要使用流程审核相关功能才需要)
git clone https://gitee.com/maimengcloud/mdp-workflow-backend.git
# 安装依赖
mvn install

# 克隆lcode项目，具体请到唛盟低代码看如何安装
git clone https://gitee.com/maimengcloud/mdp-lcode-backend.git
# 安装lcode依赖
mvn install

# 克隆xm项目
git clone https://gitee.com/maimengcloud/xm-backend.git
# 安装xm依赖
mvn install

# 导入数据库脚本

# 1.创建wf库 如果不想用工作流，可忽略
[wf.sql](https://gitee.com/maimengcloud/mdp-workflow-backend/blob/master/sql/wf.sql)

# 2.创建lcode库
[lcode_with_base_data.sql](https://gitee.com/maimengcloud/mdp-lcode-backend/blob/master/sql/lcode_with_base_data.sql)

# 3.创建xm库
[xm.sql](https://gitee.com/maimengcloud/xm-backend/blob/master/sql/xm.sql)

# 配置数据库链接链接(需要提前准备数据库)
[application-dev.yml](./xm-core/src/main/resources/application-dev.yml)

# 启动服务 ⚠注意启动类放在test下面，
找到并运行[XmApplication.java](./xm-core/src/test/java/com/xm/XmApplication.java) 
```

访问端口 http://localhost:7067

### 发布
#### 服务器的目录结构
假设工作空间为/home/m1/

```
/hom/m1/
	├── start-service          => 唛盟一键启停脚本存放目录
	├── config                 => spring boot的外置配置文件存放目录
	├── lib                    => jar包存放目录
	├── conf                   => 其它配置文件存放目录
	├── consul                 => consul安装目录
	├── logs                   => 唛盟日志存放目录
	├── backup-log             => 日志备份存放目录
	├── arcfile                => 唛盟内容素材上传下载访问的目录(素材存放目录)
	├── bin                    => 一些公共脚本目录，如日志定时清理脚本、微服务注销脚本
	 
```
#### 构建
```bash
# 构建 
在xm-backend下执行 
mvn instal
 ```

#### 发布及启停服务
```bash
# 部署 指部署jar包到nexus私服，如果企业没有nexus私服，则忽略此步骤
在xm-backend下执行
mvn deploy

# 手工发布到测试环境或者生产环境
如果是单体应用则拷贝并推送到服务器上[xm-bootstrap-3.5.0-RELEASE.jar](./xm-bootstrap/target/xm-bootstrap-3.5.0-RELEASE.jar)
如果是微服务则拷贝并推送到服务器上[xm-cloud-bootstrap-3.5.0-RELEASE.jar](./xm-cloud-bootstrap/target/xm-cloud-bootstrap-3.5.0-RELEASE.jar)

# 启动应用（单体、cloud对jar包的引用不同，需要根据情况修改下脚本bootstrap-xm-00.sh，默认是单体）初次部署把./start-service/bootstrap-xm-00.sh拷贝到服务器上
sh bootstrap-xm-00.sh

```


### 接入统一认证中心
1.如果部署了统一认证中心，需要做配置更改。
统一认证中心相关的配置项：
```properties
mdp.jwt.connect-oauth2-server=true|false  如果不需要对接统一认证中心，connect-oauth2-server设为false,则对jwt token的解析将采用HS256算法解密，默认为true
mdp.oauth2.server.uri=统一认证中心的访问地址  如http://127.0.0.1:7000，如果是cloud环境下可以设置为http://oauth2server
```
```yaml

mdp:
  jwt:
    connect-oauth2-server: true
  oauth2:
    server:
      uri: http://127.0.0.1:7000
```


2.本地起的后端服务pom.xml文件需要引入[mdp-client-oauth2](https://gitee.com/maimengcloud/mdp-core/tree/master/mdp-clients/mdp-client-oauth2)
或者[mdp-client-oauth2-cloud](https://gitee.com/maimengcloud/mdp-core/tree/master/mdp-clients/mdp-client-oauth2-cloud)
单体应用下：
```xml
      <!-- mdp-client-oauth2 必须放在业务包之上优先加载 -->
      <dependency>
          <groupId>com.mdp</groupId>
          <artifactId>mdp-client-oauth2</artifactId>
      </dependency>
```
cloud环境下
```xml
      <!-- mdp-client-oauth2-cloud 必须放在业务包之上优先加载 -->
      <dependency>
          <groupId>com.mdp</groupId>
          <artifactId>mdp-client-oauth2-cloud</artifactId>
      </dependency>
```
#### 场景1 开发时，登录时对接统一认证中心进行登录，本地起了部分后端服务
此场景下，本地起的后端服务需要配置以下几个选项，让本地后端服务对jwt令牌的解析验证调用统一认证中心，否则会报令牌错误。
```properties
mdp.jwt.connect-oauth2-server=true
mdp.oauth2.server.uri=统一认证中心的访问地址
```

#### 场景2 生产测试环境
此场景下配置根场景1一致



### 接入spring cloud
唛盟提供了完整的部署例子[xm-cloud-bootstrap](./xm-cloud-bootstrap)
#### 1 下载[mdp-cloud-backend](https://gitee.com/maimengcloud/mdp-cloud-backend)并安装
#### 2 在*-cloud-bootstrap/pom.xml文件中添加cloud客户端的引用
```xml 
  <dependency>
    <groupId>com.mdp</groupId>
    <artifactId>mdp-boot-starter-cloud</artifactId>
  </dependency>
```
#### 3 需要在配置文件中配置spring.cloud*相关的配置项。
1. spring cloud如何配置以官方文档为准
2. 唛盟相关的有以下几个配置项需要做特殊处理
```properties
# 网关地址 如果接入cloud,指cpringcloud的gate服务的访问地址 http://gate，如果不接入cloud,这里填写nginx的地址，或者注释掉
mdp.api-gate= http://gate
# 统一认证中心的访问地址  如http://127.0.0.1:7000，如果是cloud环境下可以设置为http://oauth2server
mdp.oauth2.server.uri= http://oauth2server
```
#### 4 打包部署
注意，部署的是*-cloud-bootstrap*.jar包，对应的一键启停脚本也需要做部分修改。具体例子查看[bootstrap-xm-00.sh](./start-service/bootstrap-xm-00.sh))

### 异构目录下如何加载唛盟包
唛盟的java代码统一在com.mdp下，异构目录下如何加载唛盟的spring bean
假设企业的业务包在com.ipcm下
有两种办法：
1. 把启动类置于com下。
2. 在**Application.java同目录下创建AutoConfig.java
```java
 /**
  * 要使用mdp平台功能，必须 扫码com.mdp包
  * 一些默认公共配置
  */
  @ComponentScan(basePackages={"com.mdp"})
  @Configuration
  public class AutoConfig {
  }
```

### 服务端多语言支持
服务端支持多语言0编码切换，主要涉及以下内容

#### 1 配置文件
在配置文件application-*.yml中指定语言

```yaml 
mdp:
  # 站点语言类型cn\en等
  site-type: cn
  
```

#### 2 语言包
[msgtpl.properties](./xm/src/main/resources/msgtpl.properties)
格式为:  
mdp.msg.tpl.${tipscode}@${mdp.site-type}=消息内容

举例如下：
```properties
mdp.msg.tpl.tipscode1@cn=%s先生/女士,你好，恭喜获得奖励￥ %s 
mdp.msg.tpl.tipscode1@en=Hello %s, congratulations on receiving a reward of ￥ %s 
```

${tipscode}来自以下几种地方：

1.[BizException.java](https://gitee.com/maimengcloud/mdp-core/blob/master/mdp-utils/src/main/java/com/mdp/core/err/BizException.java)抛出的异常，唛盟框架会自动匹配语言
```java
    throw new BizException(LangTips.errMsg("tipscode1","%s先生/女士,你好，恭喜获得奖励￥ %s","陈天天","1000"));
```

2. 控制层返回结果后，唛盟框架会自动做语言切换，前端会收到已切换的消息
```java
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result list(){
		 return Result.ok("tipscode1","%s先生/女士,你好，恭喜获得奖励￥ %s","陈天天","1000"); 
	}
```
### 🔔️ 特别提醒


mdp 4.0 版本已经开始规划更新了，尽请期待新版本的诞生吧
 
## 💯 项目管理演示环境

1. [账户管理平台](http://111.230.141.161/lcode)  
2. [唛盟众包-网页](http://111.230.141.161/crowd)
3. [项目管理-网页](http://111.230.141.161/xm)
4. 项目管理-小程序   
   <img src="./docs/images/index/mm_wxapp_qrcode.png" alt="drawing" width="200"/>


## 🐞 交流讨论 、反馈 BUG、提出建议等

1. 快扫描下方左侧微信二维码和我们一起交流讨论吧！（备注 唛盟-mdp 进群）
   <img src="./docs/images/index/mm_wx_kf_qrcode.png" alt="drawing" width="200"/>

2. 唛盟微信公众号查看一些基础教程  
   <img src="./docs/images/index/mm_wxpub_qrcode.png" alt="drawing" width="200"/>

3. 反馈 BUG、提出建议，欢迎新建：[issues](https://gitee.com/maimengcloud/xm-ui-web/issues)，开发人员会不定时查看回复。
4. 参与贡献，请查看[贡献指南](#🔨贡献指南)。

## 💲 打赏
**感谢所有赞赏以及参与贡献的小伙伴，你们的支持是我们不断更新前进的动力！微信扫一扫，赏杯咖啡呗！**    
<img src="./docs/images/index/mm_pay_coffee.jpg" alt="drawing" width="300"/>

## 🔔商务合作

序号|合作项目| 详细介绍 | 费用说明|
----------------------|------------|----------------------|-----|
1.| 打赏获得赞助商名额| 在赞助商列表展示（添加微信沟通） |不限额度|
2.| 新组件开发| 提供组件扩展、优化服务 |视复杂程度而定|
3.| 开发问题解答|如果使用该产品遇到棘手问题需要解决，添加微信进行沟通解决 |免费|
4.| 开发培训|提供开发流程介绍、技术介绍、功能介绍、部署流程介绍，仅限线上培训 |加微信详聊|
5.| 扩展问题解答|如果需要使用该产品进行自有业务系统研发，需要我方提供意见建议，我方收取一定费用后提供相应服务 |加微信详聊|
6.| 广告合作|广告位(精品项目推荐、赞助商展位) |加微信沟通|



