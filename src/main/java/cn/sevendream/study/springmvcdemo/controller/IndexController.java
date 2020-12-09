package cn.sevendream.study.springmvcdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 首页
 * @author zhangxue
 * @date 2018-08-23 上午10:26
 */
@Controller
public class IndexController {
    /**
     * todo 
     * @param 
     * @return java.lang.String
     * @author zhangxue9
     * @date 2020/12/9 18:25
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }


}
