package zhth.bom.management.bom.controller;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zhth.bom.management.bom.service.serivceImpl.UserServiceImpl;

@Controller
public class MemberController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "member-list" ,produces = "text/plain;charset=UTF-8")
    public String memberlist(){
        return "member/member-list";
    }

    @RequestMapping(value = "member-add",produces = "text/plain;charset=UTF-8")
    public  String memberadd(){
        return  "member/member-add";
    }

    @RequestMapping(value = "member-password",produces = "text/plain;charset=UTF-8")
    public String memberpassword(String name, String password ,String updatapassword){
        return "member/member-password";
    }


    @RequestMapping(value = "user",produces = "text/plain;charset=UTF-8")
    public String user(){
        return "member/user";
    }


    @RequestMapping(value = "updatapassword" ,method = RequestMethod.POST)
    @ResponseBody
    public Integer updatapassword(String name, String password ,String updatapassword){
            Integer s=null;
            try{
               s=userService.updatapassword(name,password,updatapassword);
            }catch (Exception e){
                e.printStackTrace();
            }
            return s;
    }
}
