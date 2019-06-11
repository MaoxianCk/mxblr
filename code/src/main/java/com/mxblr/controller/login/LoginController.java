package com.mxblr.controller.login;

import com.mxblr.controller.BaseController;
import com.mxblr.data.dataObject.UserInfoDO;
import com.mxblr.error.BusinessException;
import com.mxblr.response.CommonReturnType;
import com.mxblr.service.UserService;
import com.mxblr.utils.MyLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * #date 2019/05/28 22:15
 */
@RequestMapping("/maoxianUser/")
@RestController
public class LoginController extends BaseController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录
     */
    @PostMapping("login")
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "account") String account,
                                  @RequestParam(name = "password") String password,
                                  HttpServletRequest request) throws BusinessException {
        //TODO null valiad
        MyLog.info("Request : /maoxianUser/login");
        UserInfoDO userInfo = userService.userLogin(account, password, request);
        return CommonReturnType.create(userInfo);
    }
}
