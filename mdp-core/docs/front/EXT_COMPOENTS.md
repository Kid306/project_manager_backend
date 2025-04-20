# mdp-ui 组件库
>⚠️注意：该项目使用 element-ui@2.3.0+ 版本，所以最低兼容 vue@2.5.0+

## 组件列表 前端ui框架以[mdp-sys-ui-web](https://gitee.com/qingqinkj/mdp-sys-ui-web)作为项目模板，提供以下基础性组件：
>⚠️ 注意：ui库基于elementui进行改造
1.  [mdp-dialog]弹框，可以把任意页面装配成弹框，无须定义多余的变量及函数
2.  [mdp-table]表格，内置了增、删、改、查、高级查询、重置查询、导出、列配置、分页、批量编辑等功能、内置了对按钮权限的控制机制
3.  [mdp-select]下拉列表，支持对数据字典、元数据的引用，支持对任意小表表格数据的引用，支持参数化加载后台数据，对后台加载的数据进行缓存
4.  [mdp-select-table]超大表格下拉列表，与mdp-select相比，该组件具有分页查询功能
5.  [mdp-select-user]用户选择下拉列表，与mdp-select-table组件类似，仅仅针对用户的头像做了特殊处理
6.  [mdp-input]输入框
7.  [mdp-date]日期
8.  [mdp-daterange]区间日期
9.  [mdp-number]数字输入
10. [mdp-hi-query]高级查询，可以由用户自定义任意复杂的查询条件
11. [mdp-table-configs]表格配置，用于控制表格的列显示与否
12. [mdp-transfer]穿梭框
13. [mdp-images]图片库,支持图片的上传下载、上传后的统一管理、共享
14. [mdp-rich-text]富文本编辑器，整合了mdp-images作为插件

## 开发
```bash

# 引入 mdp-ui 基础组件
import MdpComponents from '@/components/Mdp/index.js'
Vue.use(MdpComponents)

# 引入拓展的组件
import MdpExtComponents from '@/components/MdpExt/index.js'
Vue.use(MdpExtComponents) 

# 在页面上就可以应用组件
<mdp-xxx/>

````

## 组件详细介绍

### mdp-input 
