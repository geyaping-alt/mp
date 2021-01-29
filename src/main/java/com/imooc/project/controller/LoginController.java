package com.imooc.project.controller;

import com.imooc.project.dto.LoginDTO;
import com.imooc.project.service.AccountService;
import com.imooc.project.service.ResourceService;
import com.imooc.project.vo.ResourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("auth")
public class LoginController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ResourceService resourceService;

    /**
     * 用户登陆
     * @param userName
     * @param password
     * @return
     */
    @PostMapping("login")
    public String login(String userName, String password, HttpSession session,
                        RedirectAttributes redirectAttributes, Model model){
        LoginDTO loginDTO = accountService.login(userName, password);
        String error = loginDTO.getError();
        if(error == null){
            session.setAttribute("account",loginDTO.getAccount());
            List<ResourceVO> resourceVOS = resourceService.
                    listResourceByRoleId(loginDTO.getAccount().getRoleId());
            model.addAttribute("resources",resourceVOS);
        }else{
            redirectAttributes.addFlashAttribute("error",loginDTO.getError());
        }
        return loginDTO.getPath();
    }

    /**
     * 登出方法
     * @param session
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
