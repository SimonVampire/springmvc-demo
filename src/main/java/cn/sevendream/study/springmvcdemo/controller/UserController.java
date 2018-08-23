package cn.sevendream.study.springmvcdemo.controller;

import cn.sevendream.study.springmvcdemo.model.UserloginEntity;
import cn.sevendream.study.springmvcdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author zhangxue
 * @date 2018-08-23 下午2:05
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getUsers(ModelMap modelMap) {
        // 查询user表中所有记录
        List<UserloginEntity> userList = userRepository.findAll();

        // 将所有记录传递给要返回的jsp页面，放在userList当中
        modelMap.addAttribute("userList", userList);

        return "user/allUsers";
    }

    // get请求，访问添加用户 页面
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser() {
        // 转到 admin/addUser.jsp页面
        return "user/addUser";
    }

    // post请求，处理添加用户请求，并重定向到用户管理页面
    @RequestMapping(value = "/add/process", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("user") UserloginEntity userEntity) {
        // 注意此处，post请求传递过来的是一个UserEntity对象，里面包含了该用户的信息
        // 通过@ModelAttribute()注解可以获取传递过来的'user'，并创建这个对象

        // 数据库中添加一个用户，该步暂时不会刷新缓存
        //userRepository.save(userEntity);

        // 数据库中添加一个用户，并立即刷新缓存
        userRepository.saveAndFlush(userEntity);

        return "redirect:/user/all";
    }

    // 查看用户详情
    // @PathVariable可以收集url中的变量，需匹配的变量用{}括起来
    // 例如：访问 localhost:8080/admin/users/show/1 ，将匹配 id = 1
    @RequestMapping(value = "/info/show/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") Integer userId, ModelMap modelMap) {

        // 找到userId所表示的用户
        UserloginEntity userEntity = userRepository.findOne(userId);

        // 传递给请求页面
        modelMap.addAttribute("user", userEntity);
        return "user/userDetail";
    }


    // 更新用户信息 页面
    @RequestMapping(value = "/info/update/{id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("id") Integer userId, ModelMap modelMap) {

        // 找到userId所表示的用户
        UserloginEntity userEntity = userRepository.findOne(userId);

        // 传递给请求页面
        modelMap.addAttribute("user", userEntity);
        return "user/updateUser";
    }

    // 更新用户信息 操作
    @RequestMapping(value = "/info/update/process", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("user") UserloginEntity user) {

        // 更新用户信息
        userRepository.updateUser(user.getPassword(), user.getUserId());
        userRepository.flush(); // 刷新缓冲区
        return "redirect:/user/all";
    }

    // 删除用户
    @RequestMapping(value = "/info/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Integer userId) {

        // 删除id为userId的用户
        userRepository.delete(userId);
        // 立即刷新
        userRepository.flush();
        return "redirect:/user/all";
    }
}