package com.mdp.mo.ctrl;

import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.menu.entity.MenuModule;
import com.mdp.menu.service.MenuModuleService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;




@RestController("mdp.mo.moOrderCreateController")
@RequestMapping(value="/*/mo/moOrder")
@Api(tags={"mo_order操作接口"})
public class MoOrderCreateController {

    static Logger logger = LoggerFactory.getLogger(MoOrderController.class);

    @Autowired
    private MoOrderCreateService moOrderCreateService;

    @Autowired
    private MenuModuleService menuModuleService;

    @Autowired
    private MoOrderModuleService moOrderModuleService;

    @Autowired
    private BranchInterestsService branchInterestsService;

    @ApiOperation( value = "计算订单金额",notes=" ")
    @ApiResponses({
            @ApiResponse(code = 200,response= MoOrder.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/calcOrder",method= RequestMethod.GET)
    public Result calcOrder(  CreateOrderVo createOrderVo) {
        createOrderVo.setCalc(true);
        return  addMoOrder(createOrderVo);
    }


    @ApiOperation( value = "创建订单",notes=" ")
    @ApiResponses({
            @ApiResponse(code = 200,response= MoOrder.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/create",method= RequestMethod.POST)
    public Result addMoOrder(@RequestBody CreateOrderVo createOrderVo) {
        
        
        try{
            if(CollectionUtils.isEmpty(createOrderVo.getModuleIds())) {
                return Result.error("module-not-allow-empty","请先选择要购买的模块");
            }
            if(createOrderVo.getOdays()<=0){
                return Result.error("odays-0","请选择购买天数");
            }

            if(createOrderVo.getOusers()<=0){
                return Result.error("ousers-0","请选择购买人数");
            }

            if(!StringUtils.hasText(createOrderVo.getOoper())){
                return Result.error("ooper-0","订单操作类型不能为空");
            }
            User user= LoginUtils.getCurrentUserInfo();

            //判断人数是否正确，如果是首次购买，人数必须大于等于10，如果是续费，人数必须大于等于10，如果不是第一次购买，则人数必须根当前机构购买人数相同
            BranchInterests branchInterests=branchInterestsService.selectOneById(user.getBranchId());
            if(branchInterests==null||"0".equals(branchInterests.getMver())){//免费版
                if(createOrderVo.getOusers()<=10){
                    return Result.error("ousers-10","购买账户数最少10个");
                }
            }else{
                if(createOrderVo.getOusers()<branchInterests.getMaxUsers()){
                    return Result.error("ousers-err",String.format("您当前企业人数为%s,购买账户数必须为%s个",branchInterests.getMaxUsers(),branchInterests.getMaxUsers()));
                }
            }

            List<String> moduleIds = createOrderVo.getModuleIds();
            //获取模块
            List<MenuModule> moduleList = menuModuleService.selectListByIds(moduleIds);

            //判断模块是否存在
            if(CollectionUtils.isEmpty(moduleList)) {throw new BizException("请先选择要购买的模块");}

            //判断是否开放状态
            Boolean isHaveClose = moOrderCreateService.isHaveCloseModule(moduleList);

            if(isHaveClose) {throw new BizException("所选模块存在未上架模块，请重新选择。");}

            //初始化订单信息
            MoOrder moOrder = moOrderCreateService.initOrder(createOrderVo);
            moOrder.setName("开通模块【"+moduleList.stream().map(i->i.getName()).collect(Collectors.joining(","))+"】");
            moOrder.setRemark(moOrder.getName());
            //初始化订单模块关联信息
            List<MoOrderModule> moOrderModules = moOrderCreateService.initOrderModule(createOrderVo,moOrder, moduleList);

            //计算订单总价格
            moOrderCreateService.calcOrderAmount(moOrder, moOrderModules);

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
