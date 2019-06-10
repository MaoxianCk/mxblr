package com.mxblr.controller.login;

import com.mxblr.controller.user.BaseController;
import com.mxblr.data.dataObject.UserInfoDO;
import com.mxblr.data.vo.UserInfoVO;
import com.mxblr.error.BusinessException;
import com.mxblr.response.CommonReturnType;
import com.mxblr.service.UserService;
import com.mxblr.utils.Constants;
import com.mxblr.utils.MyLog;
import com.mxblr.utils.MySessionUtil;
import com.mxblr.validator.MyValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public CommonReturnType login(@RequestParam(name = "account") String account,
                                  @RequestParam(name = "password") String password,
                                  HttpServletRequest request) throws BusinessException {
        //TODO null valiad
        MyLog.info("Request : /maoxianUser/login");
        UserInfoDO userInfo = userService.userLogin(account, password, request);
        return CommonReturnType.create(userInfo);
    }

    /**
     * @author Ck
     * 管理员增加用户, 新加的用户只能是写手, 编辑只能由超管手动修改, 超管只能手动修改数据库得到
     */
    @PostMapping("addUser")
    @ResponseBody
    public CommonReturnType addUser(@RequestParam(name = "account") String account,
                                    @RequestParam(name = "password") String password, HttpServletRequest httpServletRequest) throws BusinessException {
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
    public CommonReturnType modifyRole(@RequestParam(name = "userId") Integer userId,
                                       @RequestParam(name = "role") Byte role) throws BusinessException {
        //TODO
        MyValidation.checkIntNull(userId);
        MyLog.info("Request : /maoxianUser/modifyRole");
        userService.modifyRole(userId, role);
        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 用户修改密码
     */
    @PostMapping("modifyPassword")
    @ResponseBody
    public CommonReturnType modifyPassword(@RequestParam(name = "userId") Integer userId,
                                           @RequestParam(name = "password") String password) throws BusinessException {
        MyValidation.checkIntNull(userId);
        MyValidation.checkStrNull(password);
        MyLog.info("Request : /maoxianUser/modifyPassword");
        userService.modifyPassword(userId, password);
        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 用户修改信息
     */
    @PostMapping("modifyUserInfo")
    @ResponseBody
    public CommonReturnType modifyUserInfo(@RequestBody UserInfoVO userInfoVO) throws BusinessException {
        MyLog.info("Request : /maoxianUser/modifyUserInfo");
        userService.modifyUserInfo(userInfoVO);
        return CommonReturnType.create(null);
    }
}
