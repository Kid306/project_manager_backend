package com.xm.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Tips;
import com.xm.calc.service.XmCalcServiceApi;
import com.xm.core.entity.*;
import com.xm.core.entity.XmKpi;
import com.xm.core.queue.XmKpiSumParentsPushService;
import com.xm.rpt.XmRptQueryServiceApi;
import com.xm.tools.XmUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mdp.core.service.BaseService;

import com.xm.core.entity.XmKpi;
import com.xm.core.mapper.XmKpiMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author 唛盟开源 code-gen
 * @since 2025-4-4
 */
@Service
public class XmKpiService extends BaseService<XmKpiMapper, XmKpi> {
	static Logger logger =LoggerFactory.getLogger(XmKpiService.class);

	@Autowired
	XmKpiSumParentsPushService pushService;

	@Autowired
	XmCalcServiceApi xmCalcServiceApi;

	@Autowired
	XmRptQueryServiceApi xmRptQueryServiceApi;

	/**
	 * 自定义查询，支持多表关联
	 * @param page 分页条件
	 * @param ew 一定要，并且必须加@Param("ew")注解
	 * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
	 * @return
	 */
	public List<Map<String,Object>> selectListMapByWhere(IPage page, QueryWrapper ew, Map<String,Object> ext){
		return baseMapper.selectListMapByWhere(page,ew,ext);
	}



	public List<XmKpi> parentIdPathsCalcBeforeSave(List<XmKpi> nodes) {
		List<XmKpi> noExistsList=nodes.stream().filter(i->!nodes.stream().filter(k->k.getId().equals(i.getParentId())).findAny().isPresent()).collect(Collectors.toList());
		noExistsList=noExistsList.stream().filter(i-> StringUtils.hasText(i.getParentId()) && !"0".equals(i.getParentId())).collect(Collectors.toList());
		Map<String,String> hadCalcMap=new HashMap<>();
		for (XmKpi node : noExistsList) {
			if(hadCalcMap.containsKey(node.getParentId())){
				String idPaths=hadCalcMap.get(node.getParentId());
				node.setPidPaths(idPaths+node.getId()+",");
			}else{
				this.parentIdPathsCalcBeforeSave(node);
				String idPaths=node.getPidPaths();
				idPaths=idPaths.substring(0,idPaths.length()-node.getId().length()-1);
				hadCalcMap.put(node.getParentId(),idPaths);
			}
		}
		for (XmKpi node : nodes) {
			if(!StringUtils.hasText(node.getParentId())||"0".equals(node.getParentId())){
				node.setPidPaths("0,"+node.getId()+",");
				node.setParentId("0");
				continue;
			}
			if(hadCalcMap.containsKey(node.getParentId())){
				String idPaths=hadCalcMap.get(node.getParentId());
				node.setPidPaths(idPaths+node.getId()+",");
			}
		}

		for (XmKpi node : nodes) {
			if(!StringUtils.hasText(node.getParentId())||"0".equals(node.getParentId())){
				continue;
			}
			if(!hadCalcMap.containsKey(node.getParentId())){
				List<XmKpi> pnodeList=this.getParentList(node,nodes);
				if(pnodeList==null ||pnodeList.size()==0){//理论上不应该存在这种情况
					node.setPidPaths("0,"+node.getId()+",");
					node.setParentId("0");
					continue;
				}
				XmKpi topParent=pnodeList.get(pnodeList.size()-1);
				String idPath="0,";
				if(hadCalcMap.containsKey(topParent.getParentId())){
					idPath=hadCalcMap.get(topParent.getParentId());
				}
				for (int i = pnodeList.size() - 1; i >= 0; i--) {
					idPath=idPath+pnodeList.get(i).getId()+",";
				}
				node.setPidPaths(idPath+node.getId()+",");
			}
		}
		for (XmKpi node : nodes) {
			String idPaths=node.getPidPaths();
			String[] idpss=idPaths.split(",");
		}
		return nodes;
	}

	public static void main(String[] args) {
		String idpaths="0,1,2,3,";
		String[] idpss=idpaths.split(",");
		int lvl=idpss.length;

	}

	public Tips parentIdPathsCalcBeforeSave(XmKpi currNode) {
		Tips tips = new Tips("成功");
		if (!StringUtils.hasText(currNode.getParentId()) || "0".equals(currNode.getParentId())) {
			currNode.setPidPaths("0," + currNode.getId() + ",");
			currNode.setParentId("0");
			return tips;
		} else {
			List<XmKpi> parentList=this.getParentList(currNode);
			if(parentList==null ||parentList.size()==0){
				currNode.setPidPaths("0,"+currNode.getId()+",");
				currNode.setParentId("0");
				return tips;
			}
			String idPath="0,";
			for (int i = parentList.size() - 1; i >= 0; i--) {
				idPath=idPath+parentList.get(i).getId()+",";
			}
			currNode.setPidPaths(idPath+currNode.getId()+",");

			String idPaths=currNode.getPidPaths();
			String[] idpss=idPaths.split(",");
		}
		return tips;
	}

	private List<XmKpi> getParentList(XmKpi currNode){
		List<XmKpi> parentList=new ArrayList<>();
		XmKpi current=currNode;
		while (true){
			if(!StringUtils.hasText(current.getParentId()) || "0".equals(current.getParentId())){
				return parentList;
			}
			current=this.getById(current.getParentId());
			if(current==null){
				return parentList;
			}
			parentList.add(current);
		}
	}

	private List<XmKpi> getParentList(XmKpi currNode,List<XmKpi> nodes){
		List<XmKpi> parentList=new ArrayList<>();
		XmKpi current=currNode;
		while (true){
			if(!StringUtils.hasText(current.getParentId()) || "0".equals(current.getParentId())){
				return parentList;
			}
			XmKpi query=new XmKpi();
			query.setId(current.getParentId());
			Optional<XmKpi> optional=nodes.stream().filter(i->i.getId().equals(query.getId())).findFirst();
			if(optional.isPresent()){
				current=optional.get();
				parentList.add(current);
			}else{
				return parentList;
			}

		}
	}

	@Transactional
	public void batchChangeParent(List<XmKpi> xmKpis,XmKpi parentKpi) {
		if(parentKpi!=null){
			baseMapper.batchChangeParent(map("kpiIds",xmKpis.stream().map(i->i.getId()).collect(Collectors.toList()),"parentId",parentKpi.getId(),"parentPidPaths",parentKpi.getPidPaths()));
			xmCalcServiceApi.xmKpiSumParents(Arrays.stream(parentKpi.getPidPaths().split(",")).collect(Collectors.toList()));
			//pushService.pushXmKpi(parentKpi);

		}else {
			baseMapper.batchChangeParent(map("kpiIds",xmKpis.stream().map(i->i.getId()).collect(Collectors.toList()),"parentId","0","parentPidPaths","0,"));

		}

		// 同一个事物内，需要把原来的上级的子孙数目更新一下
		Set<String> oldPids=new HashSet<>();
		for (XmKpi xmKpi : xmKpis) {
			oldPids.add(xmKpi.getParentId());

		}
		if(parentKpi!=null){
			oldPids.add(parentKpi.getId());
		}
		oldPids.remove("0");
		if(oldPids.size()>0){

			this.updateChildrenCntByIds(oldPids.stream().collect(Collectors.toList()));
		}
	}
	@Transactional
	public void batchImportFromTemplate(List<XmKpi> xmKpis) {
		this.batchInsert(xmKpis);
		this.pushService.pushXmKpis(xmKpis);

	}

	/**
	 * 查出我有权限修改的一批数据
	 * @param ids
	 * @param kselfUserid
	 * @return
	 */
	public List<XmKpi> listHasQxKpisByIds(Collection<String> ids,String kselfUserid){
		return baseMapper.listHasQxKpisByIds(map("ids",ids,"kselfUserid",kselfUserid));
	}

	public void updateChildrenCntByIds(List<String> ids) {
		baseMapper.updateChildrenCntByIds(ids);
	}

	@Transactional
	public void sumParents(XmKpi node,String ignorId){
		xmCalcServiceApi.xmKpiSumParents(XmUtils.parsePidPaths(node.getPidPaths(),1,ignorId));
	}

	@Override
	public String createKey(String keyName) {
		return this.getSequenceService().getCommonNo("K{date62}{rands:2}");
	}

	@Transactional
	public void batchSumParents(List<XmKpi> xmKpis) {
		List<String> ids=xmKpis.stream().filter(k->k.getChildrenCnt()<=0).map(k->k.getId()).collect(Collectors.toList());
		List<Set<String>> pidPaths= XmUtils.parsePidPaths(xmKpis.stream().map(k->k.getPidPaths()).collect(Collectors.toList()), 1, ids.toArray(new String[ids.size()]));
		for (Set<String> pidPath : pidPaths) {
			this.xmCalcServiceApi.xmKpiBatchSumParents(pidPath.stream().collect(Collectors.toList()));
		}
	}

	public void loadToHis(List<String> ids) {
		baseMapper.loadToHis(ids);
	}

	public List<Map<String,Object>> getXmKpiAttDist(IPage page, QueryWrapper qw, Map<String,Object> ext) {
		return xmRptQueryServiceApi.getXmKpiAttDist(page,qw,ext);
	}

	public List<Map<String, Object>> getXmKpiHis(String id) {
		return xmRptQueryServiceApi.getXmKpiHis(id);
	}
}

