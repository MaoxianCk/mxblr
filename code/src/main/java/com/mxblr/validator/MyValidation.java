package com.mxblr.validator;

import com.mxblr.error.BusinessException;
import com.mxblr.error.EmBusinessErr;
import org.apache.commons.lang3.StringUtils;
/**
 * 校验函数
 */
public final class MyValidation {
    /**
     * 检查字符串是否为null或空字符串
     * 如果是，则抛出参数不合法异常
     */
    public static void checkStrNull(String... array) throws BusinessException {
        for (String var : array) {
            if (var == null || StringUtils.isEmpty(var.trim())) {
                throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR);
            }
        }
    }

    /**
     * 检查Integer是否为null或小于0
     * 如果是，则抛出参数不合法异常
     */
    public static void checkIntNull(Integer... array) throws BusinessException {
        for (Integer var : array) {
            if (var == null || var < 0) {
                throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR);
            }
        }
    }

    /**
     * 检查Object是否为null，用于校验其他类型，例如Boolean
     * 如果是，则抛出参数不合法异常
     */
    public static void checkObjectNull(Object... objects) throws BusinessException {
        for (Object var : objects) {
            if (var == null) {
                throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR);
            }
        }
    }
}
