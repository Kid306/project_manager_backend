package com.mdp.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryCondition;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.BaseUtils;
import com.mdp.sys.entity.Branch;
import com.mdp.sys.service.BranchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * BranchService的测试案例
 * 组织 com.qqkj<br>
 * 顶级模块 mdp<br>
 * 大模块 sys<br>
 * 小模块 <br>
 * 表 ADMIN.sys_branch 管理端机构表（机构下面若干部门）<br>
 * 实体 Branch<br>
 * 表是指数据库结构中的表,实体是指java类型中的实体类<br>
 * 当前实体所有属性名:<br>
 *	id,branchName,enabled,industryCategory,cuserid,cdate,cusername,phoneNo,emaill;<br>
 * 当前表的所有字段名:<br>
 *	id,branch_name,enabled,industry_category,cuserid,cdate,cusername,phone_no,emaill;<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 ***/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestOkBranchService {

	@Autowired
	BranchService branchService;


	@Test
	public void save(){
		Branch branch1=new Branch();
		branch1.setId("000-000-1");
		branch1.setAdmUserid("user-00001");
		branch1.setBranchName("xxxxxx1");
		branchService.insert(branch1);

		Branch branch2=new Branch();
		branch2.setId("000-000-2");
		branch2.setAdmUserid("user-00002");
		branch2.setBranchName("xxxxxx2");
		branchService.insertOrUpdate(branch2);

		Branch branch3=new Branch();
		branch3.setId("000-000-3");
		branch3.setAdmUserid("user-00003");
		branch3.setBranchName("xxxxxx3");
		branch3.setEmaill("xxxxxxxxxfffffffff");
		branch3.setBranchName("xxxxxx4444");
		branchService.save(branch3);


		List<Branch> branches=Arrays.asList(branch1,branch2,branch3);
		branchService.saveBatch(branches);
	}

	@Test
	public void update(){
		Branch branch=new Branch();
		branch.setId("000-000-1");
		branch.setBranchName("xxxxxx22222");
		branchService.updateById(branch,false);

		branch.setBranchName("xxxxxx333");
		branchService.updateSomeFieldByPk(branch);
		branch.setEmaill("xxxxxxxxxfffffffff");
		branch.setBranchName("xxxxxx4444");
		branchService.updateByPk(branch);
	}

	@Test
	public void updateBatchById(){
		Branch branch1=new Branch();
		branch1.setId("000-000-1");
		branch1.setBranchName("xxxxxx22222-1");


		Branch branch2=new Branch();
		branch2.setBranchName("xxxxxx333");
		branch2.setEmaill("xxxxxxxxxfffffffff");
		branch2.setBranchName("xxxxxx4444-2");
		List<Branch> branches=Arrays.asList(branch1,branch2);
		branchService.updateBatchById(branches);
	}

	@Test
	public void queryOne(){
		//Branch branch=branchService.getBaseMapper().queryOne("000");
	}

	@Test
	public void editSomeFields(){
		Map<String,Object> maps=new HashMap<>();
		List<Map<String,Object>> ids=new ArrayList<>();
		Map<String,Object> id1= BaseUtils.map("id","001","adminUserid","002");
		Map<String,Object> id2= BaseUtils.map("id","002","adminUserid","003");
		ids.add(id1);
		ids.add(id2);
		maps.put("ids", ids);
		maps.put("branchName","003");
		maps.put("emaill","003@xxx.com");
		branchService.editSomeFields(maps);

		branchService.editSomeFields(ids,BaseUtils.fromMap(maps,Branch.class));
	}

	@Test
	public void pageList(){
		Branch branch=new Branch();

		Map<String,Object> params=new HashMap();
		params.put("branchName","*12*");
		params.put("bcode",">=1");

		int[] ids=new int[3];
		ids[0]=1;
		ids[1]=2;
		ids[2]=3;
		params.put("id", ids);

		params.put("$orderBy","id desc,branchName asc");


		params.put("branchName","$in xxxxxxxx1,2222,4444");

		String[] extColoumn1=new String[2];
		extColoumn1[0]="xxxxxxxx1";
		extColoumn1[1]="xxxxxxxx2";
		params.put("extColoumn1",extColoumn1);

		params.put("cuserid","$between 1,2");
		List<QueryCondition> cs=new ArrayList<>();
		QueryCondition qc0=new QueryCondition();
		qc0.setProperty("cuserid");
		qc0.setSqlVal("cyc0");
		qc0.setSqlOper("!=");
		cs.add(qc0);
		List<QueryCondition> children0=new ArrayList<>();

		QueryCondition qc01=new QueryCondition();
		qc01.setProperty("cuserid");
		qc01.setSqlVal("cyc0");
		qc01.setSqlOper("!=");
		children0.add(qc01);

		QueryCondition qc02=new QueryCondition();
		qc02.setProperty("cuserid");
		qc02.setSqlVal("cyc0");
		qc02.setSqlOper("!=");
		children0.add(qc02);

		QueryCondition qc03=new QueryCondition();
		qc03.setProperty("cuserid");
		qc03.setSqlVal("cyc0");
		qc03.setSqlOper("!=");
		children0.add(qc03);

		QueryCondition qc04=new QueryCondition();
		qc04.setProperty("cuserid");
		qc04.setSqlVal("cyc0");
		qc04.setSqlOper("!=");
		children0.add(qc04);


		List<QueryCondition> children01=new ArrayList<>();

		QueryCondition qc05=new QueryCondition();
		qc05.setProperty("cuserid");
		qc05.setSqlVal("cyc0");
		qc05.setSqlOper("!=");
		children01.add(qc05);

		QueryCondition qc06=new QueryCondition();
		qc06.setProperty("cuserid");
		qc06.setSqlVal("cyc0");
		qc06.setSqlOper("!=");
		children01.add(qc06);
		qc0.setChildren( children0.toArray(new QueryCondition[children0.size()]));
		qc0.setSqlLink("AND");
		qc04.setChildren(( children01.toArray(new QueryCondition[children01.size()])));
		qc04.setSqlLink("OR");
		params.put(QueryTools.HI_QUERY_PARAMS,cs);

		Map<String,Object> m=BaseUtils.toMap(qc04);

		QueryCondition qcFromMap=BaseUtils.fromMap(m, QueryCondition.class);

		QueryWrapper<Branch> queryWrapper = QueryTools.initQueryWrapper(Branch.class,params);
		Page<Branch> page = new Page<Branch>(1, 10);
		IPage<Branch> pageList = branchService.page(page, queryWrapper);
		Result result= Result.ok().setData(pageList);

		Map<String,Object> params2=BaseUtils.map("branchName","*擎*");
		QueryWrapper<Branch> queryWrapper2 = QueryTools.initQueryWrapper(Branch.class,params2);
		Page<Branch> page2 = new Page<Branch>(1, 10);
		IPage<Branch> pageList2 = branchService.page(page2, queryWrapper2);
		Result result2=Result.ok().setData(pageList2).setTotal(page2.getTotal());
	}

	@Test
	public void pageListLikeTest(){

		Map<String,Object> params=new HashMap();
		params.put("branchName","*20*");
		QueryWrapper<Branch> queryWrapper = QueryTools.initQueryWrapper(Branch.class,params);
		Page<Branch> page = new Page<Branch>(1, 10);
		IPage<Branch> pageList = branchService.page(page, queryWrapper);
		Result result=Result.ok().setData(pageList);
	}
	@Test
	public void test$pks(){

		Map<String,Object> params=new HashMap();
		params.put("branchName","xxx");
		List<String> pks=new ArrayList<>();
		pks.add("1");
		pks.add("2");
		params.put(QueryTools.PKS,pks);
		 branchService.editSomeFields(params);
	}
	@Test
	public void test$pks2(){

		Map<String,Object> params=new HashMap();
		params.put("branchName","xxx");
		List<Map<String,Object>> pks=new ArrayList<>();

		pks.add(BaseUtils.map("id","1","cuserid","1.1"));
		pks.add(BaseUtils.map("id","2","cuserid","2.1"));
		params.put(QueryTools.PKS,pks);
		branchService.editSomeFields(params);
	}
	@Test
	public void pageList2(){
		Branch branch=new Branch();

		Map<String,Object> params=new HashMap();

		int[] ids=new int[3];
		ids[0]=1;
		ids[1]=2;
		ids[2]=3;
		params.put("id",ids);

		Double[] bcode=new Double[3];
		bcode[0]=1D;
		bcode[1]=2D;
		bcode[2]=3D;
		params.put("bcode$in",bcode);
		QueryWrapper<Branch> queryWrapper = QueryTools.initQueryWrapper(Branch.class,params);
		Page<Branch> page = new Page<Branch>(1, 10);
		IPage<Branch> pageList = branchService.page(page, queryWrapper);
		Result result=Result.ok().setData(pageList);
	}

	@Test
	public void pageListTableAlias(){
		Branch branch=new Branch();

		Map<String,Object> params=new HashMap();

		params.put("res1.cusername","cusername001");

		params.put("lphoneNo","lphoneNo");
		params.put("adUserid","adUserid11");
		params.put("lphoneNo","lphoneNo");
		params.put("branch_name","branchName01");
		QueryWrapper<Branch> queryWrapper = QueryTools.initQueryWrapper(Branch.class,params,"res1.*");
		Page<Branch> page = new Page<Branch>(1, 10);
		IPage<Branch> pageList = branchService.page(page, queryWrapper);
		Result result=Result.ok().setData(pageList);
	}
	@Test
	public void selectByMaps(){
		Branch branch=new Branch();

		Map<String,String[]> params=new HashMap();
		String[] branchName=new String[1];
		branchName[0]="*1";
		params.put("branchName",branchName);
		String[] bcode=new String[1];
		bcode[0]=">=1";
		params.put("bcode",bcode);

		String[] ids=new String[3];
		ids[0]="1";
		ids[1]="2";
		ids[2]="3";
		params.put("id$in",ids);


		String[] orderBy=new String[1];
		ids[0]="id,branchName desc";
		params.put("$orderBy",orderBy);


		String[] idBegin=new String[1];
		idBegin[0]="xxxxxxxx1";
		params.put("id$begin",idBegin);


		String[] idEnd=new String[1];
		idEnd[0]="xxxxxxxx2";
		params.put("id$end",idBegin);

		QueryWrapper<Branch> queryWrapper=new QueryWrapper<>();
 		List<Map<String, Object>> data=branchService.listMaps(queryWrapper);

 		IPage page=new Page(1,10);
		branchService.pageMaps(page,queryWrapper);
		//queryWrapper.like("s.branch_name","擎");
		Map<String,Object> pare=new HashMap<>();
		pare.put("admUserid","aaaa");
		queryWrapper.eq("cuserid","cuseridxxxx");
		List<Map<String,Object>> pageData=branchService.getBaseMapper().selectListMapByWhere(page,queryWrapper,pare);

		System.out.printf(pageData.toString());
	}
	@Test
	public void delete(){
		Branch branch=new Branch();
		branch.setId("000-000-1");
		branch.setAdmUserid("user-00001");
		branch.setBranchName("xxxxxx1");
		branchService.deleteByPk(branch);

		branch.setId("000-000-2");
		branch.setAdmUserid("user-00002");
		branch.setBranchName("xxxxxx2");
		branchService.deleteByWhere(branch);
		List<Map<String,Object>> ids=new ArrayList<>();
		Map<String,Object> id1= BaseUtils.map("id","001","adminUserid","002");
		Map<String,Object> id2= BaseUtils.map("id","002","adminUserid","003");
		ids.add(id1);
		ids.add(id2);
		branchService.removeByIds( ids);
		branchService.removeById(id1);



		Branch branch1=new Branch();
		branch1.setId("000-000-1");
		branch1.setAdmUserid("user-00001");
		branch1.setBranchName("xxxxxx1");
		branchService.insert(branch1);

		Branch branch2=new Branch();
		branch2.setId("000-000-2");
		branch2.setAdmUserid("user-00002");
		branch2.setBranchName("xxxxxx2");
		branchService.insertOrUpdate(branch2);

		Branch branch3=new Branch();
		branch3.setId("000-000-3");
		branch3.setAdmUserid("user-00003");
		branch3.setBranchName("xxxxxx3");
		branch3.setEmaill("xxxxxxxxxfffffffff");
		branch3.setBranchName("xxxxxx4444");
		List<Branch> branches=Arrays.asList(branch1,branch2,branch3);

		branchService.batchDelete(branches);
	}

}
