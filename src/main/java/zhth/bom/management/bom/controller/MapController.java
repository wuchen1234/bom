package zhth.bom.management.bom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MapController {

    @RequestMapping(value = "/echarts1" ,produces = "text/plain;charset=UTF-8")
    public String echarts1()throws  Exception{
        return "Map/echarts1";
    }

    @RequestMapping(value = "/echarts2" ,produces = "text/plain;charset=UTF-8")
    public String echarts2()throws  Exception{
        return "Map/echarts2";
    }

    @RequestMapping(value = "/echarts3" ,produces = "text/plain;charset=UTF-8")
    public String echarts3()throws  Exception{
        return "Map/echarts3";
    }

    @RequestMapping(value = "/echarts4" ,produces = "text/plain;charset=UTF-8")
    public String echarts4()throws  Exception{
        return "Map/echarts4";
    }

    @RequestMapping(value = "/echarts5" ,produces = "text/plain;charset=UTF-8")
    public String echarts5()throws  Exception{
        return "Map/echarts5";
    }

    @RequestMapping(value = "/echarts6" ,produces = "text/plain;charset=UTF-8")
    public String echarts6()throws  Exception{
        return "Map/echarts6";
    }

    @RequestMapping(value = "/echarts7" ,produces = "text/plain;charset=UTF-8")
    public String echarts7()throws  Exception{
        return "Map/echarts7";
    }

    @RequestMapping(value = "/echarts8" ,produces = "text/plain;charset=UTF-8")
    public String echarts8()throws  Exception{
        return "Map/echarts8";
    }
}
