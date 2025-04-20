package  com.mdp.sys.service;

import com.mdp.core.utils.BaseUtils;
import com.mdp.sys.entity.UserLoginRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Map;
/**
 * @author code-gen
 * @since 2023-10-1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestUserLoginRecordService  {

	@Autowired
	UserLoginRecordService userLoginRecordService;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map("userid","AWuf","shopId","sL8N","locationId","gJ8L","loginShopid","8ViA","loginLocationid","t3aB","branchId","0SJE","loginType","P","loginTime",new Date("2023-10-01 6:38:52"),"loginBranchId","48UW","username","2802","authId","jIW7","mdpAppid","8XFe","lockNo","gn0p","lockStatus","h","lockTime",new Date("2023-10-01 6:38:52"),"phoneno","Dk1c","loginIp","NYay","userType","1gVA","loginDeviceId","Y5S6","loginDeviceSn","tuKj","userAgent","vDG9","reqNo","h6Mk","deviceType","SZQ2","os","1Cnq","osVersion","sJ3m","osName","vr5z","renderingEngine","Ty2i","deviceManufacturer","lraa","browerGroup","Q4cK","borderName","Q7Z1","borderVersion","v0Ep","id","Th6s","longitude",2609.0823889,"latitude",3986.42968123,"regionId","rQCZ","regionName","07z1","formatAddress","q870","districtId","3j5T","memType","j","loginStatus","o","loginMsg","q87C","authType","NmwS","grantType","bfcL");
		UserLoginRecord userLoginRecord=BaseUtils.fromMap(p,UserLoginRecord.class);
		userLoginRecordService.save(userLoginRecord);
		//Assert.assertEquals(1, result);
	}
	 
}
