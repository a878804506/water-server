package com.cyh.water.controller;

import com.cyh.water.utils.CheckCodeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class ImageCodeController {

    @RequestMapping("createImageCode")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成随机字串
        String checkCode = CheckCodeUtil.generateVerifyCode(4);
        //存入会话session
        HttpSession session = request.getSession(true);
        session.setAttribute("checkCode", checkCode.toLowerCase());
        //生成图片
        int width = 120, height = 47;
        CheckCodeUtil.outputImage(width, height, response.getOutputStream(), checkCode);
    }
}
