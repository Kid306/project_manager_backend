package com.mdp.mo.ctrl;

import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.menu.entity.MenuModule;
import com.mdp.menu.entity.MenuModuleBranch;
import com.mdp.menu.service.MenuModuleBranchService;
import com.mdp.menu.service.MenuModuleService;
import com.mdp.mo.entity.AddUsersVo;
import com.mdp.mo.entity.CreateOrderVo;
import com.mdp.mo.entity.MoOrder;
import com.mdp.mo.entity.MoOrderModule;
import com.mdp.mo.service.MoOrderCreateService;
import com.mdp.mo.service.MoOrderModuleService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sys.entity.BranchInterests;
import com.mdp.sys.service.BranchInterestsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;




@RestController("mdp.mo.MoOrderAddUsersController")
@RequestMapping(value="/*/mo/moOrder/addUsers")
@Api(tags={"新增购买用户数"})
public class MoOrderAddUsersController {

    static Logger logger = LoggerFactory.getLogger(MoOrderController.class);

    @Autowired
    private MoOrderCreateService moOrderCreateService;

    @Autowired
    private MenuModuleService menuModuleService;

    @Autowired
    private MoOrderModuleService moOrderModuleService;

    @Autowired
    private MenuModuleBranchService menuModuleBranchService;

    @Autowired
    private BranchInterestsService branchInterestsService;

    @ApiOperation( value = "计算订单金额",notes=" ")
    @ApiResponses({
            @ApiResponse(code = 200,response= MoOrder.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/calcOrder",method= RequestMethod.GET)
    public Result calcOrder(  AddUsersVo createOrderVo) {
        createOrderVo.setCalc(true);
        return  addMoOrder(createOrderVo);
    }


    @ApiOperation( value = "创建订单",notes=" ")
    @ApiResponses({
            @ApiResponse(code = 200,response= MoOrder.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/create",method= RequestMethod.POST)
    public Result addMoOrder(@RequestBody AddUsersVo createOrderVo) {
        
        
        try{
            if(createOrderVo.getOusers()<=0){
                return Result.error("ousers-0","请选择购买人数");
            }
            User user= LoginUtils.getCurrentUserInfo();
            //判断人数是否正确，如果是首次购买，人数必须大于等于10，如果是续费，人数必须大于等于10，如果不是第一次购买，则人数必须根当前机构购买人数相同
            BranchInterests branchInterests=branchInterestsService.selectOneById(user.getBranchId());
            if(branchInterests==null||"0".equals(branchInterests.getMver())){//免费版
                if(createOrderVo.getOusers()<=10){
                    return Result.error("ousers-10","购买账户数最少10个");
                }
            }

            //获取模块

            MenuModuleBranch mmb=new MenuModuleBranch();
            mmb.setBranchId(user.getBranchId());
            List<MenuModuleBranch> hadModules=menuModuleBranchService.selectListByWhere(mmb);
            if(hadModules==null || hadModules.size()==0){
                return Result.error("hadModules-0", "未购买过产品，请直接购买产品。");
            }

            //过滤掉已过期的
            hadModules=hadModules.stream().filter(i->!"0".equals(i.getStatus())).collect(Collectors.toList());

            List<MenuModule> moduleListDb = menuModuleService.selectListByIds(hadModules.stream().map(i->i.getModuleId()).collect(Collectors.toList()));

            //过滤掉计费模式不是按人数计费的
            List<MenuModule> moduleList=moduleListDb.stream().filter(i->"1".equals(i.getBillMode())).collect(Collectors.toList());
            hadModules=hadModules.stream().filter(i->moduleList.stream().filter(k->k.getId().equals(i.getModuleId()) && "1".equals(k.getBillMode())).findAny().isPresent()).collect(Collectors.toList());

            //判断模块是否存在
            if(CollectionUtils.isEmpty(moduleList)) {return Result.error("modules-0", "模块已不存在。");}


            //判断是否开放状态
            Boolean isHaveClose = moOrderCreateService.isHaveCloseModule(moduleList);

            if(isHaveClose) {throw new BizException("所选模块存在未上架模块，请重新选择。");}

            CreateOrderVo createOrderVo2=new CreateOrderVo();
            BeanUtils.copyProperties(createOrderVo,createOrderVo2);
            createOrderVo2.setOoper("3");
            createOrderVo2.setModuleIds(moduleList.stream().map(i->i.getId()).collect(Collectors.toList()));

            //初始化订单信息
            MoOrder moOrder = moOrderCreateService.initOrder(createOrderVo2);

            //初始化订单模块关联信息
            List<MoOrderModule> moOrderModules = moOrderCreateService.initOrderModule(createOrderVo2,moOrder, moduleList);
            moOrder.setName("增购模块及人数【"+moduleList.stream().map(i->i.getName()).collect(Collectors.joining(","))+"】");
            moOrder.setRemark(moOrder.getName());
            //计算订单总价格
            moOrderCreateService.calcOrderAmountForAddUsers(moOrder, moOrderModules,hadModules);

            if(!createOrderVo.isCalc()){
                //插入数据库
                moOrderCreateService.insertOrder(moOrder,moOrderModules);
            }
            return Result.ok().setData( moOrder).put("modules",moOrderModules);
        }catch (BizException e) {
            logger.error("",e);
            return Result.error(e);
        }catch (Exception e) {
            logger.error("",e);
            return Result.error(e);
        }
    }



}
