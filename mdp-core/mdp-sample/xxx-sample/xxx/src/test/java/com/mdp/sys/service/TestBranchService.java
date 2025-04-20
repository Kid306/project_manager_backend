package  com.mdp.sys.service;

import com.mdp.core.utils.BaseUtils;
import com.mdp.sys.entity.Branch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Map;
/**
 * BranchService的测试案例
 * 组织 com<br>
 * 顶级模块 mdp<br>
 * 大模块 sys<br>
 * 小模块 <br>
 * 表 sys_branch 管理端机构表（机构下面若干部门）<br>
 * 实体 Branch<br>
 * 表是指数据库结构中的表,实体是指java类型中的实体类<br>
 * 当前实体所有属性名:<br>
 *	id,branchName,enabled,industryCategory,cuserid,cdate,cusername,lphoneNo,emaill,bizProcInstId,bizFlowState,pbranchId,admUserid,admUsername,lusername,luserid,address,btype,imgUrl,bcode,blicense,legalPerson,regCapital,remark,validLvls;<br>
 * 当前表的所有字段名:<br>
 *	id,branch_name,enabled,industry_category,cuserid,cdate,cusername,lphone_no,emaill,biz_proc_inst_id,biz_flow_state,pbranch_id,adm_userid,adm_username,lusername,luserid,address,btype,img_url,bcode,blicense,legal_person,reg_capital,remark,valid_lvls;<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 ***/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestBranchService  {

	@Autowired
	BranchService branchService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("id","VVX4","branchName","sA39","enabled","C","industryCategory","fxlH","cuserid","tO9m","cdate",new Date("2023-07-21 22:36:21"),"cusername","oc8E","lphoneNo","0WPM","emaill","gTz3","bizProcInstId","MK49","bizFlowState","1","pbranchId","r0Hn","admUserid","aP0K","admUsername","h7l5","lusername","sDnG","luserid","2EiS","address","Gt9L","btype","p","imgUrl","9xAd","bcode","345P","blicense","S60y","legalPerson","5zmd","regCapital",7200.91,"remark","Vs11","validLvls","fZ02");
		Branch branch=BaseUtils.fromMap(p,Branch.class);
		branchService.save(branch);
		//Assert.assertEquals(1, result);
	}
	 
}
