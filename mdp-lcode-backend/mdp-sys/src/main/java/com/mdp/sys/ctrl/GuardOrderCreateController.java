package com.mdp.sys.ctrl;

import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.Guard;
import com.mdp.sys.entity.GuardOrder;
import com.mdp.sys.entity.GuardOrderCreateVo;
import com.mdp.sys.service.GuardOrderCreateService;
import com.mdp.sys.service.GuardService;
import com.mdp.sys.service.UserInterestsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Calendar;



@RestController("mdp.sys.guardOrderCreateController")
@RequestMapping(value="/*/sys/guardOrder")
@Api(tags={"服务保障押金交付操作接口"})
public class GuardOrderCreateController {

    static Logger logger = LoggerFactory.getLogger(GuardOrderCreateController.class);

    @Autowired
    private GuardOrderCreateService guardOrderCreateService;

    @Autowired
    private GuardService guardService;
 

    @Autowired
    private UserInterestsService userInterestsService;

    @ApiOperation( value = "计算订单金额",notes=" ")
    @ApiEntityParams( GuardOrderCreateVo.class )
    @ApiResponses({
            @ApiResponse(code = 200,response= GuardOrder.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/calcOrder",method= RequestMethod.GET)
    public Result calcOrder(  GuardOrderCreateVo createOrderVo) {
        createOrderVo.setCalc(true);
        return  addGuardOrder(createOrderVo);
    }


    @ApiOperation( value = "创建订单",notes=" ")
    @ApiEntityParams( GuardOrderCreateVo.class )
    @ApiResponses({
            @ApiResponse(code = 200,response= GuardOrder.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/create",method= RequestMethod.POST)
    public Result addGuardOrder(@RequestBody GuardOrderCreateVo createOrderVo) {
        
        
        try{
            if(!StringUtils.hasText(createOrderVo.getGuardId())) {
                return Result.error("guard-id-required","请先选择要购买的服务保障");
            }  

            if(!StringUtils.hasText(createOrderVo.getOoper())){
                return Result.error("ooper-0","订单操作类型不能为空");
            }

            User user= LoginUtils.getCurrentUserInfo();



            String guardId = createOrderVo.getGuardId();
            //获取模块
            Guard guardDb = guardService.selectOneById(guardId);

            //判断模块是否存在
            if(guardDb==null) {throw new BizException("请先选择要购买的服务保障");}


            //初始化订单信息
            GuardOrder guardOrder = guardOrderCreateService.initOrder(createOrderVo);
            String name="";
            if(user.getGuardId()==null){
                guardOrder.setOoper("1");
                name="新购";
            }else if(user.getGuardId().equals(guardOrder.getGuardId())){
                guardOrder.setOoper("2");
                name="续费";
            }else{
                guardOrder.setOoper("3");
                name="升级";
            }
            guardOrder.setName(name+"服务保障等级【"+guardDb.getName()+"】;可在锁定期之后申请退回剩余的保障金余款;");
            guardOrder.setRemark(guardOrder.getName());
            //计算订单总价格
            guardOrderCreateService.calcOrderAmount(guardOrder, guardDb);

            //消减差价
            guardOrderCreateService.subHisFee(guardOrder,guardDb);
            if(guardOrder.getOfinalFee().compareTo(BigDecimal.ZERO)==0){
                return Result.error("ofinalFee-0","您保证金账户余额与本次应付金额一致，您无须再购买此保障等级。");
            }
            if(!createOrderVo.isCalc()){
                //插入数据库
                guardOrderCreateService.insertOrder(guardOrder);
            }
            return Result.ok().setData( guardOrder).put("guard",guardDb);
        }catch (BizException e) {
            logger.error("",e);
            return Result.error(e);
        }catch (Exception e) {
            logger.error("",e);
            return Result.error(e);
        }
    }


    public static void main(String[] args) {

        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR,2023);
        System.out.println(calendar.compareTo(Calendar.getInstance()));
        Calendar calendar2=Calendar.getInstance();
        calendar2.set(Calendar.YEAR,2021);
        System.out.println(calendar2.compareTo(Calendar.getInstance()));
    }


}
