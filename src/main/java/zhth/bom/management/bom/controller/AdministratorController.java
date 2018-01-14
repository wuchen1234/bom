package zhth.bom.management.bom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdministratorController {

    @RequestMapping(value = "/admin-list" ,produces = "text/plain;charset=UTF-8")
    public String adminlist()throws  Exception{
        return "admin/admin-list";
    }

    @RequestMapping(value = "/admin-role" ,produces = "text/plain;charset=UTF-8")
    public String adminrole()throws  Exception{
        return "admin/admin-role";
    }

    @RequestMapping(value = "/admin-cate" ,produces = "text/plain;charset=UTF-8")
    public String admincate()throws  Exception{
        return "admin/admin-cate";
    }
    @RequestMapping(value = "/admin-rule" ,produces = "text/plain;charset=UTF-8")
    public String adminrule()throws  Exception{
        return "admin/admin-rule";
    }

}
