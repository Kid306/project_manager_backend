<template>
<#setting number_format="#">
    <el-form :model="editForm"  label-width="120px" :rules="editFormRules" ref="editFormRef">
    <#list columnList as column>
        <el-form-item label="${column.chinaName}" prop="${column.camelsColumnName}">
        <#if column.simpleJavaClassName=='String'>
            <el-input  v-model="editForm.${column.camelsColumnName}" placeholder="${column.chinaName}" :maxlength="${column.columnSize}" @change="editSomeFields(editForm,'${column.camelsColumnName}',$event)" :disabled="disField"/>
        <#elseif column.simpleJavaClassName=='Date'>
            <el-date-picker type="date" placeholder="选择日期"  v-model="editForm.${column.camelsColumnName}"  value-format="YYYY-MM-DD HH:mm:ss" format="YYYY-MM-DD" @change="editSomeFields(editForm,'${column.camelsColumnName}',$event)" :disabled="disField"/>
        <#elseif column.simpleJavaClassName=='boolean'>
            <el-radio-group  v-model="editForm.${column.camelsColumnName}" :disabled="disField" @change="editSomeFields(editForm,'${column.camelsColumnName}',$event)">
                <el-radio class="radio" :label="1">是</el-radio>
                <el-radio class="radio" :label="0">否</el-radio>
            </el-radio-group>
        <#elseif column.simpleJavaClassName=='Number'>
            <el-input-number  v-model="editForm.${column.camelsColumnName}" :min="0" :max="200" @change="editSomeFields(editForm,'${column.camelsColumnName}',$event)" :disabled="disField"/>
        <#elseif column.simpleJavaClassName=='Integer' || column.simpleJavaClassName=='int'>
            <el-input-number  v-model="editForm.${column.camelsColumnName}" :min="0" :max="200" :precision="0" @change="editSomeFields(editForm,'${column.camelsColumnName}',$event)" :disabled="disField"/>
        <#else>
            <el-input  v-model="editForm.${column.camelsColumnName}" placeholder="${column.chinaName}" :maxlength="${column.columnSize}" @change="editSomeFields(editForm,'${column.camelsColumnName}',$event)" :disabled="disField"/>
        </#if>
        </el-form-item>
    </#list>
    </el-form>
    <div class="footer">
        <el-button @click="close" icon="close">关闭</el-button>
        <el-button icon="success-filled" v-if="currOpType=='add'|| (currOpType=='edit' && someOpen==false)" v-loading="load.edit" type="primary" @click="saveSubmit" :disabled="disBtn('addBtn') || !checkBtnQx('addBtn',menuDefId) || load.edit">提交</el-button>
    </div>
</template>

<script>
import * as ${entityName}Api from '@/api/${apiPath}/${smallEntityName}';
import { MdpFormMixin } from '@/components/mdp-ui/mixin/MdpFormMixin.js';
import { mapState } from 'pinia'
import { useUserStore } from '@/store/modules/user'

export default {
    name:'${entityName}Form',
    mixins:[MdpFormMixin],
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
            pkNames:[<#list primaryKeysList as column>"${column.camelsColumnName}"<#if column_has_next>, </#if></#list>],//表格主键的java属性名称，驼峰命名，默认为id,支持多主键
            currOpType:'add',//表单 add、edit，所有按钮可动、detail-只看不能操作
            editFormRules: {
                <#list primaryKeysList as column>
                ${column.camelsColumnName}:[
                //{ required: true, message: '此项必填', trigger: 'change' },
                //{ min: 1,max: 200, message: '长度在1到200之间', trigger: 'change'}
                ]<#if column_has_next>,</#if>
                </#list>
            },
            editForm: {
                <#list columnList as column>${column.camelsColumnName}:''<#if column_has_next>,</#if></#list>
            },
            //增删改查(含批量)接口
            apis:{
                queryById: ${entityName}Api.query${entityName}ById,
                add: ${entityName}Api.add${entityName},
                edit: ${entityName}Api.edit${entityName},
                editSomeFields: ${entityName}Api.editSomeFields${entityName}
            },
        }
    },
    methods: {
         //由组件扩展添加其它的初始页面的逻辑(mounted+onOpen都会调用此函数，建议只添加公共逻辑)
         initCurrData(){

         },
        /**
         * 检查参数是否满足调用后台接口的条件
         * @returns true / false
         */
        preParamCheck(params){
            return true;
        },
        /**
         * 对修改的字段进行判断，返回false ,将取消更新数据库,由组件扩展
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