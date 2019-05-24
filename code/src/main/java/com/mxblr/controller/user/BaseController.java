package com.mxblr.controller.user;

import com.mxblr.error.BusinessException;
import com.mxblr.error.EmBusinessErr;
import com.mxblr.response.CommonReturnType;
import com.mxblr.utils.MyExceptionUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BaseController {
    /**
     * 异常处理函数
     * 处理所有Controller类抛出的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(Exception ex) {
        Map<String, Object> responseData = new HashMap<>();
        BusinessException be;
        if (ex instanceof BusinessException) {
            be = (BusinessException) ex;
        } else {
            System.out.println(MyExceptionUtil.getErrorMsg(ex));
            be = new BusinessException(EmBusinessErr.UNKNOWN_ERROR);
        }
        responseData.put("errCode", be.getErrCode());
        responseData.put("errMsg", be.getErrMsg());
        return CommonReturnType.create(responseData, "fail");
    }
}
