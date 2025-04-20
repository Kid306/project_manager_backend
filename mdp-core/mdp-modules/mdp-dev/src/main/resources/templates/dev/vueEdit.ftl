<template>
	<section>
	    <el-row>
	    </el-row>
		<el-row>
		<!--编辑界面 ${entityName} ${tableRemarks}--> 
			<el-form :model="editForm"  label-width="120px" :rules="editFormRules" ref="editFormRef">
			<#list columnList as column>
				<el-form-item label="${column.remarks}" prop="${column.camelsColumnName}">
				<#if column.simpleJavaClassName=='String'> 
					<el-input v-model="editForm.${column.camelsColumnName}" placeholder="${column.remarks}" :maxlength="${column.columnSize}" @change="editSomeFields(editForm,'${column.camelsColumnName}',$event)"></el-input>
				<#elseif column.simpleJavaClassName=='Date'>
					<el-date-picker type="date" placeholder="选择日期" v-model="editForm.${column.camelsColumnName}"  value-format="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd"></el-date-picker>
				<#elseif column.simpleJavaClassName=='boolean'>
					<el-radio-group v-model="editForm.${column.camelsColumnName}">
						<el-radio class="radio" :label="1">是</el-radio>
						<el-radio class="radio" :label="0">否</el-radio>
					</el-radio-group>
				<#elseif column.simpleJavaClassName=='Number'>
					<el-input-number v-model="editForm.${column.camelsColumnName}" :min="0" :max="200"></el-input-number>
				<#elseif column.simpleJavaClassName=='Integer' || column.simpleJavaClassName=='int'>
					<el-input-number v-model="editForm.${column.camelsColumnName}" :min="0" :max="200"></el-input-number>
				<#else>
					<el-input v-model="editForm.${column.camelsColumnName}" placeholder="${column.remarks}" :maxlength="${column.columnSize}"></el-input>
				</#if>
				</el-form-item> 
			</#list>
			</el-form>
		</el-row>

		<el-row v-if="opType=='add'">
		    <el-button @click.native="handleCancel">取消</el-button>
            <el-button v-loading="load.edit" type="primary" @click.native="saveSubmit" :disabled="load.edit==true">提交</el-button>
		</el-row>
	</section>
</template>

<script>
	import util from '@/common/js/util';//全局公共库
	import config from "@/common/config"; //全局公共库import
 	import * as ${entityName}Api from '@/api/${apiPath}/${smallEntityName}';
	import { mapGetters } from 'vuex'
	
	export default {
	    name:'${smallEntityName}Form',
	    components: {
        },
		computed: {
		    ...mapGetters([ 'userInfo'  ]),
		},
		props:{
		    parentOpType:{
		        type:String,
		        default:'mng'//mng or list
		    }
		},
		watch: {

	    },
		data() {
			return {
			    currOpType:'add',//add/edit/detail
 				load:{ list: false, edit: false, del: false, add: false },//查询中...
				editFormRules: {
					${primaryKeysList[0].camelsColumnName}: [
						//{ required: true, message: '${primaryKeysList[0].remarks}不能为空', trigger: 'blur' }
					]
				},
				editForm: {
					<#list columnList as column>${column.camelsColumnName}:''<#if column_has_next>,</#if></#list>
				},
			}
		},
		methods: {

		    ...util,

			// 取消按钮点击 父组件监听@cancel="editFormVisible=false" 监听
			handleCancel:function(){
				this.$refs['editFormRef'].resetFields();
				this.$emit('cancel',this.currOpType);
			},
			//新增、编辑提交${entityName} ${tableRemarks}父组件监听@submit="afterEditSubmit"
			saveSubmit: function () {
				this.$refs.editFormRef.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => { 
							this.load.edit=true
							let params = Object.assign({}, this.editForm);
							var func=add${entityName}
							if(this.currOpType=='edit'){
							    func=edit${entityName}
							}
							func(params).then((res) => {
                                this.load.edit=false
                                var tips=res.data.tips;
                                if(tips.isOk){
                                    this.editForm=res.data.data
                                    this.initData()
                                    this.$emit('submit',{tips,res.data.data},tips.isOk,this.currOpType);//  @submit="afterFormSubmit
                                    this.currOpType="edit";
                                }
                                this.$notify({ position:'bottom-left',showClose:true, message: tips.msg, type: tips.isOk?'success':'error' });
                            }).catch( err =>this.load.edit=false);
						});
					}else{
					    this.$notify({ showClose:true, message: "表单验证不通过，请修改表单数据再提交", type: 'error' });
					}
				});
			},
			initData: function(){
			    if(this.${smallEntityName}){
                    this.editForm = Object.assign({},this.${smallEntityName});
                }

                if(this.opType=='edit'){

                }else{

                }
                this.editFormBak={...this.editForm}
            },

            editSomeFields(row,fieldName,$event){
                if(this.opType=='add'){
                    return;
                }
                let params={};
                <#if (primaryKeysList?size>1) >
                params['${pkAlias}']=[row].map(i=>{ return {<#list primaryKeysList as column> ${column.camelsColumnName}:i.${column.camelsColumnName}<#if column_has_next>, </#if></#list>}})
                </#if>
                <#if (primaryKeysList?size<2) >
                params['${pkAlias}']=[row].map(i=><#list primaryKeysList as column>i.${column.camelsColumnName}<#if column_has_next>, </#if></#list>)
                </#if>
                params[fieldName]=$event
                var func = editSomeFields${entityName}
                func(params).then(res=>{
                  let tips = res.data.tips;
                  if(tips.isOk){
                    this.editFormBak=[...this.editForm]
                  }else{
                    Object.assign(this.editForm,this.editFormBak)
                    this.$notify({position:'bottom-left',showClose:true,message:tips.msg,type:tips.isOk?'success':'error'})
                  }
                }).catch((e)=>Object.assign(this.editForm,this.editFormBak))
            },
		},//end method
		mounted() {
		    this.$nextTick(() => {
                this.initData()
            });
		}
	}

</script>

<style scoped>

</style>