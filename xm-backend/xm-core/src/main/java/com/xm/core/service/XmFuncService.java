package com.xm.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Tips;
import com.mdp.core.service.BaseService;
import com.xm.core.entity.XmFunc;
import com.xm.core.mapper.XmFuncMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmFunc 表 xm_func 当前主键(包括多主键): id; 
 ***/
@Service("xm.core.xmFuncService")
public class XmFuncService extends BaseService<XmFuncMapper,XmFunc> {
	static Logger logger =LoggerFactory.getLogger(XmFuncService.class);

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

	public List<XmFunc> parentIdPathsCalcBeforeSave(List<XmFunc> nodes) {
		List<XmFunc> noExistsList = nodes.stream().filter(i -> !nodes.stream().filter(k -> k.getId().equals(i.getPid())).findAny().isPresent()).collect(Collectors.toList());
		noExistsList = noExistsList.stream().filter(i -> StringUtils.hasText(i.getPid())).collect(Collectors.toList());
		Map<String, String> hadCalcMap = new HashMap<>();
		for (XmFunc node : noExistsList) {
			if (hadCalcMap.containsKey(node.getPid())) {
				String idPaths = hadCalcMap.get(node.getPid());
				node.setPidPaths(idPaths + node.getId() + ",");
			} else {
				this.parentIdPathsCalcBeforeSave(node);
				String idPaths = node.getPidPaths();
				idPaths = idPaths.substring(0, idPaths.length() - node.getId().length() - 1);
				hadCalcMap.put(node.getPid(), idPaths);
			}
		}
		for (XmFunc node : nodes) {
			if (!StringUtils.hasText(node.getPid())) {
				node.setPidPaths("0," + node.getId() + ",");
				continue;
			}
			if (hadCalcMap.containsKey(node.getPid())) {
				String idPaths = hadCalcMap.get(node.getPid());
				node.setPidPaths(idPaths + node.getId() + ",");
			} else {
				List<XmFunc> pnodeList = this.getParentList(node, nodes);
				if (pnodeList == null || pnodeList.size() == 0) {
					node.setPidPaths("0," + node.getId() + ",");
					node.setPid(null);
					continue;
				}
				XmFunc topParent = pnodeList.get(pnodeList.size() - 1);
				String idPath = "0,";
				if (hadCalcMap.containsKey(topParent.getPid())) {
					idPath = hadCalcMap.get(topParent.getPid());
				}
				for (int i = pnodeList.size() - 1; i >= 0; i--) {
					idPath = idPath + pnodeList.get(i).getId() + ",";
				}
				node.setPidPaths(idPath + node.getId() + ",");
			}
		}
		for (XmFunc node : nodes) {
			String idPaths = node.getPidPaths();
			String[] idpss = idPaths.split(",");
			node.setLvl(idpss.length - 1);
		}
		return nodes;
	}

	public Tips parentIdPathsCalcBeforeSave(XmFunc currNode) {
		Tips tips = new Tips("成功");
		if (!StringUtils.hasText(currNode.getPid()) || "0".equals(currNode.getPid())) {
			currNode.setPidPaths("0," + currNode.getId() + ",");
			currNode.setLvl(1);
			return tips;
		} else {
			List<XmFunc> parentList = this.getParentList(currNode);
			if (parentList == null || parentList.size() == 0) {
				currNode.setPidPaths("0," + currNode.getId() + ",");
				currNode.setPid(null);
				currNode.setLvl(1);
				return tips;
			}
			String idPath = "0,";
			for (int i = parentList.size() - 1; i >= 0; i--) {
				idPath = idPath + parentList.get(i).getId() + ",";
			}
			currNode.setPidPaths(idPath + currNode.getId() + ",");

			String idPaths = currNode.getPidPaths();
			String[] idpss = idPaths.split(",");
			currNode.setLvl(idpss.length - 1);
		}
		return tips;
	}

	private List<XmFunc> getParentList(XmFunc currNode) {
		List<XmFunc> parentList = new ArrayList<>();
		XmFunc current = currNode;
		while (true) {
			if (!StringUtils.hasText(current.getPid()) || "0".equals(current.getPid()) || current.getId().equals(current.getPid())) {
				return parentList;
			}
			XmFunc query = new XmFunc();
			query.setId(current.getPid());
			current = this.selectOneObject(query);
			if (current == null) {
				return parentList;
			}
			parentList.add(current);
		}
	}

	private List<XmFunc> getParentList(XmFunc currNode, List<XmFunc> nodes) {
		List<XmFunc> parentList = new ArrayList<>();
		XmFunc current = currNode;
		while (true) {
			if (!StringUtils.hasText(current.getPid()) || "0".equals(current.getPid()) || current.getId().equals(current.getPid())) {
				return parentList;
			}
			XmFunc query = new XmFunc();
			query.setId(current.getPid());
			Optional<XmFunc> optional = nodes.stream().filter(i -> i.getId().equals(query.getId())).findFirst();
			if (optional.isPresent()) {
				current = optional.get();
				parentList.add(current);
			} else {
				return parentList;
			}
		}
	}

	@Transactional
	public void batchChangeParent(List<XmFunc> xmFuncs, XmFunc parentFunc) {
		if(parentFunc!=null){
			baseMapper.batchChangeParent(map("ids",xmFuncs.stream().map(i->i.getId()).collect(Collectors.toList()),"pid",parentFunc.getId(),"parentPidPaths",parentFunc.getPidPaths()));
		}else {
			baseMapper.batchChangeParent(map("ids",xmFuncs.stream().map(i->i.getId()).collect(Collectors.toList()),"pid","0","parentPidPaths","0,"));

		}
	}
}

