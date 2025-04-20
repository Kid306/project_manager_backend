<p align="center">
	<a href="http://111.230.141.161"  target="_blank">
	    <img src="./docs/images/index/mm_logo_big.png" width="400" alt="logo">
	</a>
</p>
<p align="center">
	<strong>唛盟后端开发底座(mdp-core)：多功能、高效率、低代码(支持0代码) 的后端开发底座</strong>
</p>

<p align="center">
	<a target="_blank" href="https://gitee.com/maimengcloud/mdp-lcode-ui-web">
        <img src='https://gitee.com/maimengcloud/mdp-lcode-ui-web/badge/star.svg?theme=gvp' alt='gitee star'/>
    </a> 
</p>
<p align="center">
	👉 <a target="_blank" href="http://111.230.141.161/lcode">http://111.230.141.161 </a>  👈
</p>



## 快速导航  

- [唛盟-后端开发底座](https://gitee.com/maimengcloud/mdp-core)   
- [前端组件](https://gitee.com/maimengcloud/mdp-lcode-ui-web)   
- [后端服务](https://gitee.com/maimengcloud/mdp-lcode-backend)    
- [体验环境](http://111.230.141.161/lcode/)   
  登陆界面上选择演示账号登陆或者直接扫码登陆，无须注册   
- 👉[教程]b站上搜素 [唛盟9哥教你撸前后端代码](https://www.bilibili.com/video/BV111421S72r/?spm_id_from=333.337.search-card.all.click&vd_source=93be23d03863773d50b81112985b9237)


## 📢 简介[唛盟后端开发底座mdp-core](/)
本工程属于唛盟生态中的后端开发底座
唛盟低代码开发平台简称唛盟或者mdp. 👉[唛盟-总体介绍](https://www.bilibili.com/video/BV111421S72r/?spm_id_from=333.337.search-card.all.click&vd_source=93be23d03863773d50b81112985b9237)
唛盟旨在为企业开发业务系统提供一整套解决方案，唛盟具有高效率、低代码、支持0代码、功能丰富等特点。企业可以在唛盟工程之上，加入更多其它业务功能；也可以以唛盟作为模板，创建新的工程，用于开发其它业务。使用唛盟构建应用，您不用考虑多租户、登录、统一认证中心、权限、菜单管理、系统管理、公共组件、公共api、代码冗余、数据字典、图片库、文件库、智能表单、工作流、微服务互相调用、全局跟踪定位bug、多主键crud,复杂sql查询等各种问题，这些问题的解决方案都作为扩展功能内置唛盟工程中了。  
💪给你一个使用唛盟的理由：代码大量减少、开发so easy、前后端MIT协议、全部开源、永久免费  

## 📢 唛盟生态

💪唛盟生态遵循 <strong>“一个底座+N个专业子系统”</strong> 的架构，基于同一个底座的各个专业子系统可以任意组合形成一个大的业务系统。👉[聊聊唛盟生态1+n架构](https://www.bilibili.com/video/BV1BD421V7Bu/?spm_id_from=333.337.search-card.all.click&vd_source=93be23d03863773d50b81112985b9237)  

### 底座 mdp-core 
  [mdp-core](https://gitee.com/maimengcloud/mdp-core)   
### N个专业子系统
以下子系统构建于唛盟底座  
| 唛盟子系统                        | 说明               | 版本     |
|----------------------------------------------------------------------|------------------|--------|
| [低代码、0代码](https://gitee.com/maimengcloud/mdp-lcode-ui-web)| 低代码、0代码框架           | 3.0.0 |
| [账户、权限、组织管理](https://gitee.com/maimengcloud/mdp-lcode-backend/tree/master/mdp-sys)| 账户、权限、组织管理，支持多租户 | 3.0.0  |
| [数据模型](https://gitee.com/maimengcloud/mdp-dm-backend/tree/master/mdp-dm-backend)|表结构设计、表数据增删改查等ddl、dml操作，在线执行sql等  | 3.0.0 |
| [第三方支付登录等](https://gitee.com/maimengcloud/mdp-tpa-backend)  | 微信支付、支付宝支付、paypal支付、第三方登录   | 3.0.0  |
| [统一认证中心](https://gitee.com/maimengcloud/mdp-oauth2-backend) | 短信、微信、账号登录 | 3.0.0 |
| [统一流程中心](https://gitee.com/maimengcloud/mdp-workflow-backend)| 审批、代办、设计流程、监控流程等  | 3.0.0 |
| [短信](https://gitee.com/maimengcloud/mdp-sms-ui-web) | 群发消息              | 3.0.0  |
| [代码生成器](https://gitee.com/maimengcloud/mdp-code-generator)| 自动生成前后端代码          | 3.0.0  |
| [研发项目管理](https://gitee.com/maimengcloud/xm-ui-web)| 产品管理、需求管理、任务计划、迭代、测试、效能等         | 3.0.0  |
| [即时通讯](https://gitee.com/maimengcloud/mdp-im-web)  | 即时通讯、消息、聊天            | 3.0.0  |
| [财务](https://gitee.com/maimengcloud/ac-core-ui-web)| 财务子系统，凭证、报销、会计记账、成本、结算 等          | 3.0.0 |
| [协同办公](https://gitee.com/maimengcloud/oa-ui-web)  | 办公用品、会议、车辆、资产、档案、用印、采购、绩效等功能           | 3.0.0 |

## ⚙ 技术栈
1. 前端  
   [前端](https://gitee.com/maimengcloud/mdp-lcode-ui-web)
2. 后端  

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

后端  
| 插件名                           | 功能                       |
|-------------------------------|--------------------------|
| idea                        | java 开发工具    社区版、企业版都可            |

## ⚙ 快速开始
此工程为开发底座工程


### 1下载并安装
```bash

# 克隆开发底座项目
git clone https://gitee.com/maimengcloud/mdp-core.git
# 安装开发底座依赖
mvn install 

```
### 2在业务工程引入mdp-core 
在业务工程根目录的pom.xml文件中做以下修改  

1. 添加mdp.version 属性
```xml
	<properties> 
		<mdp.version>${revision}</mdp.version> 
	</properties>
```
2. 添加依赖

```xml
    <dependencies> 
        <dependency>
            <groupId>com.mdp</groupId>
            <artifactId>mdp-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>com.mdp</groupId>
            <artifactId>mdp-boot-starter-web</artifactId>
        </dependency> 
    </dependencies>
```
`
3. 添加唛盟提供的公共依赖

```xml
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.mdp</groupId>
                <artifactId>mdp-dependencies</artifactId>
                <version>${mdp.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```
 至此，唛盟底座已经加入到你的工程中拉

## ⚙  唛盟底座的工程结构说明
| 子工程           | 功能                              |
|---------------|---------------------------------|
| mdp-boots     | 各种组件的启动器入口                      |
| mdp-clients   | 各种客户端(指java分布式环境下的各个微服务实例)运行的技术组件 |
| mdp-framework | 底座底层技术代码                        |
| mdp-modules   | 底座扩展的解决某方面的共性问题的技术组件            |
| mdp-sample    | 唛盟建议的业务工程结构例子                   |

## 💻 样例项目

- [低代码平台] (http://111.230.141.161/lcode/)
- [系统管理] (http://111.230.141.161/sys)
- [协同办公] (http://111.230.141.161/oa)
- [项目管理] (http://111.230.141.161/xm)
- [流程管理] (http://111.230.141.161/wf)

## ⚙ 快速开始
  
具体以[mdp-lcode-backend](https://gitee.com/maimengcloud/mdp-lcode-backend)为准
 
## 💯 实践案例

1. [低代码平台](http://111.230.141.161/lcode)
1. [系统管理](http://111.230.141.161/lcode)
2. [协同办公](http://111.230.141.161/oa)
3. [唛盟众包-网页](http://111.230.141.161) 
3. [项目管理-网页](http://111.230.141.161/xm) 
4. 项目管理-小程序   
   <img src="./docs/images/index/mm_wxapp_qrcode.png" alt="drawing" width="200"/>
5. [流程管理](http://111.230.141.161/lcode/)
 
### 项目分支说明

| 分支            | 说明                                                   |
|---------------|------------------------------------------------------|
| master        | 主分支，3.5.0+,受保护分支，此分支不接受 PR。在 dev 分支后经过测试没问题后会合并到此分支。 |
| 3.5.0-RELEASE | 已发布的版本3.5。                                           | 
| 3.0.0-RELEASE | 已发布的版本3.0。                                           | 
| 2.0.0-RELEASE | 已发布的版本2.0。                                           |

## 🐞 交流讨论 、反馈 BUG、提出建议等

1. 快扫描下方左侧微信二维码和我们一起交流讨论吧！（备注 唛盟-mdp 进群） 
<img src="./docs/images/index/mm_wx_kf_qrcode.png" alt="drawing" width="200"/>

2. 唛盟微信公众号查看一些基础教程  
<img src="./docs/images/index/mm_wxpub_qrcode.png" alt="drawing" width="200"/>

3. 反馈 BUG、提出建议，欢迎新建：[issues](https://gitee.com/maimengcloud/mdp-lcode-ui-web/issues)，开发人员会不定时查看回复。
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

 