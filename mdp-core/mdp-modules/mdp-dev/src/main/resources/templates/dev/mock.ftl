import { ${entityName}s } from './${smallEntityName}';
let _${entityName}s = ${entityName}s;
/**
 * 拷贝下面两行到 mock/index.js中,即可支持客户端虚拟数据、免服务端连接
 * import ${entityName}Mock from './${topModuleName}/${largeModuleName}${smallModulePathExt}/${smallEntityName}Mock';
 * ${entityName}Mock.bootstrap(mock);
 */
export default {
  /**
   * mock bootstrap
   */
  bootstrap(mock) {    
  
    //获取列表 模糊查询、根据关键字查询
    mock.onGet('/${topModuleName}/${largeModuleName}${smallModulePathExt}/${smallEntityName}/listKey').reply(config => {
      let params = config.params;
      let mock${entityName}s = _${entityName}s.filter(${smallEntityName} => {
      	<#list columnList as column>
      	if(params.${column.camelsColumnName}||params.${column.camelsColumnName}==''){ if( ${smallEntityName}.${column.camelsColumnName}.indexOf(params.${column.camelsColumnName})>=0) return true;}
      	</#list>
        return false;
      });
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            data: mock${entityName}s
          }]);
        }, 1000);
      });
    });
    
    

    //获取列表（分页/不分页）
    mock.onGet('/${topModuleName}/${largeModuleName}${smallModulePathExt}/${smallEntityName}/list').reply(config => {
      let params = config.params;
      let mock${entityName}s = _${entityName}s.filter(${smallEntityName} => {
      	var isTrue=false;
	     <#list columnList as column>
      	if(params.${column.camelsColumnName}||params.${column.camelsColumnName}==''){if(${smallEntityName}.${column.camelsColumnName}.indexOf(params.${column.camelsColumnName})>=0){isTrue=true;}else{isTrue=false;}}
      	</#list>
      	return isTrue;
	  });
      if(!config.params.currentPage){ 
	      return new Promise((resolve, reject) => {
	        setTimeout(() => {
	          resolve([200, {
	            data: mock${entityName}s
	          }]);
	        }, 1000);
	      });
      }
    
      let {pageSize,currentPage, total} = config.params;
      total = mock${entityName}s.length;
      mock${entityName}s = mock${entityName}s.filter((${smallEntityName}, index) => index < pageSize * currentPage && index >= pageSize * (currentPage - 1));
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            pageInfo: {
            	total:total
            },
            data: mock${entityName}s,
            tips:{
            	isOk:true,
            	msg:'查询成功'
            }
          }]);
        }, 1000);
      });
    });

    //删除
    mock.onPost('/${topModuleName}/${largeModuleName}${smallModulePathExt}/${smallEntityName}/del').reply(config => {
      var params = JSON.parse( config.data );
      let {  ${primaryKeysList[0].camelsColumnName}  } = params;
      _${entityName}s = _${entityName}s.filter(${smallEntityName} => ${smallEntityName}.${primaryKeysList[0].camelsColumnName} !== ${primaryKeysList[0].camelsColumnName});
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            tips:{
            	isOk:true,
            	msg:'删除成功'
            }
          }]);
        }, 500);
      });
    });

    //批量删除
    mock.onPost('/${topModuleName}/${largeModuleName}${smallModulePathExt}/${smallEntityName}/batchDel').reply(config => {
      var params = JSON.parse( config.data );
      _${entityName}s = _${entityName}s.filter(${smallEntityName} => !params.includes(${smallEntityName}.${primaryKeysList[0].camelsColumnName}));
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
           tips:{
            	isOk:true,
            	msg:'删除成功'
            }
          }]);
        }, 500);
      });
    });

    //编辑
    mock.onPost('/${topModuleName}/${largeModuleName}${smallModulePathExt}/${smallEntityName}/edit').reply(config => {
      var params = JSON.parse( config.data );
      let { <#list columnList as column>${column.camelsColumnName}<#if column_has_next>,</#if></#list> } = params;
      _${entityName}s.some(${smallEntityName} => {
        if (${smallEntityName}.${primaryKeysList[0].camelsColumnName} === ${primaryKeysList[0].camelsColumnName}) {
           <#list columnList as column>
			${smallEntityName}.${column.camelsColumnName} = ${column.camelsColumnName}<#if column_has_next>;</#if>
			</#list>
          return true;
        }
      });
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            tips:{
            	isOk:true,
            	msg:'编辑成功'
            }
          }]);
        }, 500);
      });
    });

    //新增
    mock.onPost('/${topModuleName}/${largeModuleName}${smallModulePathExt}/${smallEntityName}/add').reply(config => {
      var params = JSON.parse( config.data );
      let { <#list columnList as column>${column.camelsColumnName}<#if column_has_next>,</#if></#list> } = params;
      _${entityName}s.push({
        <#list columnList as column>
		${column.camelsColumnName}:${column.camelsColumnName}<#if column_has_next>,</#if>
		</#list>
      });
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            tips:{
            	isOk:true,
            	msg:'新增成功'
            }
          }]);
        }, 500);
      });
    });

  }
};