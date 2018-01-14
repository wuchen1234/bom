package zhth.bom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import zhth.bom.management.bom.repository.UserRepository;
import zhth.bom.management.bom.service.serivceImpl.UserServiceImpl;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BomApplicationTests {

    @Autowired
	private UserServiceImpl userService;

	@Test
	public void contextLoads() {
		String name="lishi2";
		String password="wuchen5";
		String updatapassword="wuchenqq";
		try{
			Integer q=userService.updatapassword(name,password,updatapassword);
			System.out.println(q);

		}catch (Exception e){
			e.printStackTrace();
		}

	}

}
