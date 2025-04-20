import axios from '@/config/maxios'

import config from '@/api/mdp_pub/mdp_config'

let base = config.get${bigContextName}Ctx();

/**
 * ${tableRemarks}
 * 1 默认只开放普通查询，所有查询，只要上传	 分页参数 {pageNum:当前页码从1开始,pageSize:每页记录数,total:总记录【数如果是0后台会自动计算总记录数非0不会自动计算】}，后台都会自动按分页查询 其它 api用到再打开，没用到的api请注释掉，
 * 2 查询、新增、修改的参数格式  params={<#list primaryKeysList as column>${column.camelsColumnName}:'${column.remarks} 主键',</#list><#list columnExcludePkList as column>${column.camelsColumnName}:'${column.remarks}'<#if column_has_next>,</#if></#list>}
 * @author maimeng-mdp code-gen
 * @since ${.now?date}
 **/
 
//普通查询 条件之间and关系  
export const list${entityName} = params => { return axios.get(`${r'${base}'}/${apiPath}/${smallEntityName}/list`, { params: params }); };

//普通查询 条件之间and关系
export const query${entityName}ById = params => { return axios.get(`${r'${base}'}/${apiPath}/${smallEntityName}/queryById`, { params: params }); };

//删除一条${tableRemarks} params={<#list primaryKeysList as column>${column.camelsColumnName}:'${column.remarks} 主键'<#if column_has_next>,</#if></#list>}
export const del${entityName} = params => { return axios.post(`${r'${base}'}/${apiPath}/${smallEntityName}/del`,params); };

//批量删除${tableRemarks}  params=[{<#list primaryKeysList as column>${column.camelsColumnName}:'${column.remarks} 主键'<#if column_has_next>,</#if></#list>}]
export const batchAdd${entityName} = params => { return axios.post(`${r'${base}'}/${apiPath}/${smallEntityName}/batchAdd`, params); };

//批量删除${tableRemarks}  params=[{<#list primaryKeysList as column>${column.camelsColumnName}:'${column.remarks} 主键'<#if column_has_next>,</#if></#list>}]
export const batchDel${entityName} = params => { return axios.post(`${r'${base}'}/${apiPath}/${smallEntityName}/batchDel`, params); };

//修改一条${tableRemarks}记录
export const edit${entityName} = params => { return axios.post(`${r'${base}'}/${apiPath}/${smallEntityName}/edit`, params); };

//新增一条${tableRemarks}
export const add${entityName} = params => { return axios.post(`${r'${base}'}/${apiPath}/${smallEntityName}/add`, params); };

//批量修改某些字段
export const editSomeFields${entityName} = params => { return axios.post(`${r'${base}'}/${apiPath}/${smallEntityName}/editSomeFields`, params); };