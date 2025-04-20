<template>
<#setting number_format="#">
    <ContentWrap>
        <template #header>
            <el-space wrap>
                <mdp-hi-query :column-configs="columnConfigs" v-model="hiQueryParams" @change="onHiQueryParamsChange" title="高级查询，定制任意复杂的查询条件"/>
                <el-button icon="zoom-out" @click="searchReset()" title="重置查询条件并查询"/>
                <el-button icon="download" @click="export2Excel()" title="导出查询结果到excel"/>
                <mdp-table-configs :column-configs="columnConfigs" v-model="checkedColumns"/>
                <el-button type="success" ref="kanbanBtn" plain @click="openKanban('kanbanDlg')" title="看板">板</el-button>
                <span v-if="currOpType=='mng'">
                    <el-button  :disabled="disBtn('addBtn') || !checkBtnQx('addBtn',menuDefId) " type="primary" @click="showAdd()" icon="plus"  plain />
                    <el-button :disabled="disBtn('delBtn') || !checkBtnQx('delBtn',menuDefId) || this.sels.length===0 || load.del==true" type="danger" v-loading="load.del" @click="batchDel" icon="delete"  plain />
                </span>
                <span v-else-if="currOpType=='select' &&  this.multiple==true">
                    <el-button :disabled="disBtn('selectBtn') || this.sels.length===0" type="primary" @click="selectListConfirm" icon="check"  plain />
                </span>
            </el-space>
        </template>
        <el-space wrap>
            <#list primaryKeysList as column>
            <el-input v-model="filters.${column.camelsColumnName}"  placeholder="${column.chinaName}" clearable title="支持>、<、 >=、<=、!=、*字符*、$IS NULL、$IN 1,2,3、$between 1,5等操作符"/>
            </#list>
            <el-button v-loading="load.list" :disabled="load.list==true" @click="searchTableDatas()" icon="search" type="primary" plain />
        </el-space>
    </ContentWrap>
    <!--列表 ${entityName} ${tableRemarks}-->
    <el-table ref="table" v-adaptive :data="tableDatas" @sort-change="sortChange" highlight-current-row v-loading="load.list" border @selection-change="selsChange" @row-click="rowClick" style="width: 100%;">
        <el-table-column  type="selection" width="55" fixed="left" v-if="currOpType=='mng' || this.multiple==true"/>
        <el-table-column sortable prop="name" width="300" fixed="left" label="名称">
            <template #default="scope">
                <el-popover placement="right-start" :width="200" show-after="200">
                    <template #reference>
                        <el-link @click="currOpType=='mng'?showEdit(scope.row):showDetail(scope.row)" type="primary">
                            {{ (scope.$index + 1) }}&nbsp; {{ scope.row.name }}
                        </el-link>
                    </template>
                    <template #default>
                        <el-button v-if="currOpType=='mng'" @click="copy2(scope.row, scope.$index)" icon="document-copy" type="warning"
                            title="复制一行除了主键不一样，其它都一样的数据">复制</el-button>
                        <el-button v-if="currOpType=='mng'" @click="showEdit(scope.row)" icon="edit" type="primary">编辑</el-button>
                    </template>
                </el-popover>
            </template>
        </el-table-column>
        <!-- 可编辑字段格式
        <el-table-column prop="objType"  label="对象类型" min-width="120" show-overflow-tooltip col-type="String" v-if="showCol('objType')">
            <template #default="scope">
                    <mdp-select itemCode="objType" show-style="tag" v-model="scope.row.objType" :maxlength="50" @change="editSomeFields(scope.row,'objType',$event)" :disabled="!someOpen||disField"/>
            </template>
        </el-table-column>
        -->
        <#list columnExcludePkList as column>
        <el-table-column prop="${column.camelsColumnName}"  label="${column.chinaName}" min-width="120" show-overflow-tooltip col-type="${column.simpleJavaClassName}" v-if="showCol('${column.camelsColumnName}')" sortable/>
        </#list>
        <#list primaryKeysList as column>
        <el-table-column prop="${column.camelsColumnName}" label="${column.chinaName}" min-width="120" show-overflow-tooltip  col-type="${column.simpleJavaClassName}" v-if="showCol('${column.camelsColumnName}')" sortable/>
        </#list>
        <el-table-column label="操作" width="100" fixed="right" v-if="currOpType=='select' && this.multiple!=true" >
            <template #default="scope">
                <el-button   type="primary" @click="selectConfirm(scope.row)" icon="check"/>
            </template>
        </el-table-column>
    </el-table>
    <el-pagination
        layout="slot,total, sizes, prev, next,pager,jumper"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"
        :page-sizes="[10,20, 50, 100, 500]"
        :current-page="pageInfo.pageNum"
        :page-size="pageInfo.pageSize"
        :total="pageInfo.total"
        style="float:right;"
    />
    <!--新增修改明细 ${entityName} ${tableRemarks}界面-->
    <mdp-dialog ref="formDlg" :title="menuDefName" width="80%">
        <template v-slot="{visible,data}">
             <${entityName}Form ref="${entityName}Form" :someOpen="true" :visible="visible" :parentOpType="currOpType" :subOpType="data.subOpType" :formData="data.formData" @close="onFormClose" @submit="afterFormSubmit" @edit-fields="afterEditFields"/>
        </template>
    </mdp-dialog>
    <MdpDialog ref="kanbanDlg" fullscreen :title="menuDefName">
        <MdpTableKanban
            :hiddenCfg="{delBtn:false,addBtn:false}"
            @del="(d,cb)=>handleDel(d)"
            @plus="(cb)=>showAdd()"
            @edit="(d,cb)=>showEdit(d)"
            v-model="tableDatas"
            :columnConfigs="columnConfigs"
            :itemKey="pkNames[0]"
            :contentFun="(item)=>item[pkNames[0]]"
        />
         <!--
            <template #toolbar="{element}">
                <MdpSelect showStyle="tag" itemCode="xmProductPstatus" disabled v-model="element.pstatus"/>
            </template>
        </MdpTableKanban>
        -->
    </MdpDialog>
</template>

<script>

import { MdpTableMixin } from '@/components/mdp-ui/mixin/MdpTableMixin.js';
import * as ${entityName}Api from '@/api/${apiPath}/${smallEntityName}';
import  ${entityName}Form from'./Form.vue';//新增修改明细界面
import { mapState } from 'pinia'
import { useUserStore } from '@/store/modules/user'

export default {
    name:'${entityName}Mng',
    mixins:[MdpTableMixin],
    components: {
        ${entityName}Form
    },
    computed: {
        ...mapState(useUserStore,['userInfo'])
    },
    props:{
         // 是否开启editSomeField模式
         someOpen:{
            type: Boolean,
            default: true,
        },
    },
    data() {
        return {
            menuDefId:'',//menu_def.id 菜单表菜单编号，用于按钮权限判断
            menuDefName:'${tableRemarks}',//menu_def.name 功能名称，用于导出excel等文件名
            pkNames:[<#list primaryKeysList as column>"${column.camelsColumnName}"<#if column_has_next>, </#if></#list>],//表格主键的java属性名称，驼峰命名，默认为id,支持多主键
            currOpType:'mng',//表格 mng-综合管理具有最大权限，所有按钮可动、detail-只看不能操作
            filters:{//查询参数

            },
            defaultFilters:{//默认查询参数,第一次打开界面的时候用到，恢复默认值的时候用到

            },
            pageInfo:{//分页数据
                total:0,//服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算。
                pageSize: this.pageable?10:1500,//每页数据
                count:false,//是否需要重新计算总记录数
                pageNum:1,//当前页码、从1开始计算
                orderFields:[],//排序列 如 ['sex','student_id']，必须为数据库字段
                orderDirs:[]//升序 asc,降序desc 如 性别 升序、学生编号降序 ['asc','desc']
            },
            //增删改查(含批量)接口
            apis:{
                list: ${entityName}Api.list${entityName},
                add: ${entityName}Api.add${entityName},
                del: ${entityName}Api.del${entityName},
                edit: ${entityName}Api.edit${entityName},
                editSomeFields: ${entityName}Api.editSomeFields${entityName},
                batchAdd: ${entityName}Api.batchAdd${entityName},
                batchDel: ${entityName}Api.batchDel${entityName},
                batchEdit: ${entityName}Api.batchEdit${entityName},
            }
        }
    },
    methods: {
         //页面初始化需要配置的特殊逻辑写这里
          initCurrData(){
             this.searchTableDatas();
          },

          /**
           * 检查参数是否满足调用后台接口的条件
           *
           * @param params 提交给后台的参数池,map类型
           * @returns true / false
           */
          preQueryParamCheck(params){
              return true;
          },

          //页面数据加载完后需要对数据进行加工处理的
          afterList(tableDatas,isOk,apiName){

          },

          /**
           * 对修改的字段进行判断，返回false ,将取消更新数据库
           * @param {*} row 当前选中的行
           * @param {*} fieldName 修改的字段名
           * @param {*} $event 修改后的值
           * @param {*} params 将要提交服务器的参数
           * @returns true/false 返回false ,将取消更新数据库
           */
          editSomeFieldsCheck(row,fieldName,$event,params){
              params[fieldName]=$event
              return true;
          },
    },
    mounted() {

    }
}

</script>

<style scoped>
</style>