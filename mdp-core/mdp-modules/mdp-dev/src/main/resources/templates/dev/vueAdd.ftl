<template>
	<section class="page-container padding">
	    <el-row class="page-header">
	    </el-row>
		<el-row class="page-main">
			<!--新增界面 ${entityName} ${tableRemarks}--> 
			<el-form :model="addForm"  label-width="120px" :rules="addFormRules" ref="addForm">
			<#list columnList as column>
				<el-form-item label="${column.remarks}" prop="${column.camelsColumnName}">
				<#if column.simpleJavaClassName=='String'> 
					<el-input v-model="addForm.${column.camelsColumnName}" placeholder="${column.remarks}" ></el-input>
				<#elseif column.simpleJavaClassName=='Date'>
					<el-date-picker type="date" placeholder="选择日期" v-model="addForm.${column.camelsColumnName}" value-format="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd"></el-date-picker>
				<#elseif column.simpleJavaClassName=='boolean'>
					<el-radio-group v-model="addForm.${column.camelsColumnName}">
						<el-radio class="radio" :label="1">是</el-radio>
						<el-radio class="radio" :label="0">否</el-radio>
					</el-radio-group>
				<#elseif column.simpleJavaClassName=='Number'>
					<el-input-number v-model="addForm.${column.camelsColumnName}" :min="0" :max="200"></el-input-number>
				<#elseif column.simpleJavaClassName=='Integer' || column.simpleJavaClassName=='int'>
					<el-input-number v-model="addForm.${column.camelsColumnName}" :min="0" :max="200"></el-input-number>
				<#else>
					<el-input v-model="addForm.${column.camelsColumnName}" placeholder="${column.remarks}"></el-input>
				</#if>
				</el-form-item> 
			</#list>
			</el-form>
		</el-row>
		<el-row  class="page-bottom bottom-fixed">
            <el-button @click.native="handleCancel">取消</el-button>
            <el-button v-loading="load.add" type="primary" @click.native="addSubmit" :disabled="load.add==true">提交</el-button>
	    </el-row>
	</section>
</template>

<script>
	import util from '@/common/js/util';//全局公共库
	import config from "@/common/config"; //全局公共库import
	import { getDicts,initSimpleDicts } from '@/api/mdp/meta/item';//字典表
	import { add${entityName} } from '@/api/${apiPath}/${smallEntityName}';
	import { mapGetters } from 'vuex'
	
	export default {
	    name:'${smallEntityName}Add',
		computed: {
		    ...mapGetters([ 'userInfo' ]),
		},
		props:['${smallEntityName}','visible','opType'],
		watch: {
	      '${smallEntityName}':function( ${smallEntityName} ) {
	        this.addForm = ${smallEntityName};
	      },
	      'visible':function(visible) { 
	      	if(visible==true){
	      		//从新打开页面时某些数据需要重新加载，可以在这里添加
	      	}
	      } 
	    },
		data() {
			return {
 				load:{ list: false, edit: false, del: false, add: false },//查询中...
				dicts:[],//下拉选择框的所有静态数据 params={categoryId:'all',itemCodes:['sex','gradeLvl']} 返回结果 { sex:[{id:'1',name:'男'},{id:'2',name:'女'}],gradeLvl:[{id:xxx,name:xxx},{id:xxx,name:xxxx}]}
				addFormRules: {
					${primaryKeysList[0].camelsColumnName}: [
						//{ required: true, message: '${primaryKeysList[0].remarks}不能为空', trigger: 'blur' }
					]
				},
				//新增界面数据 ${tableRemarks}
				addForm: {
					<#list columnList as column>${column.camelsColumnName}:''<#if column_has_next>,</#if></#list>
				}
				/**begin 在下面加自定义属性,记得补上面的一个逗号**/
				
				/**end 在上面加自定义属性**/
			}//end return
		},//end data
		methods: {
			// 取消按钮点击 父组件监听@cancel="addFormVisible=false" 监听
			handleCancel:function(){
				this.$refs['addForm'].resetFields();
				this.$emit('cancel');
			},
			//新增提交${entityName} ${tableRemarks} 父组件监听@submit="afterAddSubmit"
			addSubmit: function () {
				
				this.$refs.addForm.validate((valid) => {
					if (valid) {
						
						this.$confirm('确认提交吗？', '提示', {}).then(() => { 
							this.load.add=true
							let params = Object.assign({}, this.addForm); 
							add${entityName}(params).then((res) => {
								this.load.add=false
								var tips=res.data.tips;
								if(tips.isOk){
									this.$refs['addForm'].resetFields();
									this.$emit('submit');//  @submit="afterAddSubmit"
								}
								this.$message({ message: tips.msg, type: tips.isOk?'success':'error' }); 
							}).catch( err  => this.load.add=false);
						});
					}
				});
			},
			/**begin 在下面加自定义方法,记得补上面的一个逗号**/
				
			/**end 在上面加自定义方法**/
			
		},//end method
		components: {  
		    //在下面添加其它组件 '${h5SmallEntityName}-edit':${entityName}Edit
		},
		mounted() {
		    //initSimpleDicts('all',['sex','gradeLvl'])
			this.addForm=Object.assign(this.addForm, this.${smallEntityName});
		}//end mounted
	}

</script>

<style scoped>

</style>