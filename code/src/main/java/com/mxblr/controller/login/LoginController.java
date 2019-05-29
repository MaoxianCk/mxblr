package com.mxblr.controller.login;

import com.mxblr.controller.user.BaseController;
import com.mxblr.error.BusinessException;
import com.mxblr.response.CommonReturnType;
import com.mxblr.service.UserService;
import com.mxblr.utils.Constants;
import com.mxblr.utils.MyLog;
import com.mxblr.utils.MySessionUtil;
import com.mxblr.validator.MyValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ck
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
     * @author Ck
     * 用户登录
     */
    @PostMapping("login")
    @ResponseBody
    public CommonReturnType login(String account, String password, HttpServletRequest request) throws BusinessException {
        //TODO null valiad
        userService.userLogin(account, password, request);
        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 管理员增加用户, 新加的用户只能是写手, 编辑只能由超管手动修改, 超管只能手动修改数据库得到
     */
    @PostMapping("addUser")
    @ResponseBody
    public CommonReturnType addUser(String account, String password, HttpServletRequest httpServletRequest) throws BusinessException {
        if (Constants.TEST) {
            MySessionUtil.setAttribute(httpServletRequest, Constants.SESSION_USER_ROLE, Constants.USER_ROLE_SUPER_ADMIN);
        }
        //只有超级管理员可以增加用户
        Byte role = (Byte) MySessionUtil.getAttribute(httpServletRequest, Constants.SESSION_USER_ROLE);
        MyValidation.checkPermission(role, Constants.USER_ROLE_SUPER_ADMIN);

        MyLog.info("Request : /maoxianUser/addUser");
        userService.addUser(account, password);

        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 管理员修改用户角色, 超管只能修改用户为编辑或者写手, 并且不能修改超管
     */
    @PostMapping("modifyRole")
    @ResponseBody
    public CommonReturnType modifyRole(Integer userId, Byte role) throws BusinessException {
        //TODO
        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 用户修改密码
     */
    @PostMapping("modifyPassword")
    @ResponseBody
    public CommonReturnType modifyPassword(Integer userId, String password) throws BusinessException {
        //TODO
        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 用户修改信息
     */
    @PostMapping("modifyUserInfo")
    @ResponseBody
    public CommonReturnType modifyUserInfo() throws BusinessException {
        //TODO
        return CommonReturnType.create(null);
    }
}
