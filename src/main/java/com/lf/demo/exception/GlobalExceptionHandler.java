package com.lf.demo.exception;

import com.lf.demo.util.StateUtil;
import com.lf.demo.vo.ResultBody;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.molgenis.data.excel.ExcelUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author linfei
 * @version 1.0
 * @date 2019/6/24
 * @desc 全局异常处理
 * @see
 * @since 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBody defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        ResultBody body = new ResultBody();
        if (e instanceof ServiceException) {
            ServiceException se = (ServiceException) e;
            body.setErrorCode(se.getErrorCode());
        } else if (e instanceof RequestNotFoundException) {
            body.setErrorCode(StateUtil.REQUEST_NOT_FOUND);
        } else if (e instanceof ParamsErrorException) {
            body.setErrorCode(StateUtil.PARAMS_NOT_NULL);
        } else if (e instanceof UserNotLoginException) {
            body.setErrorCode(StateUtil.USER_NOT_LOGIN);
        } else if (e instanceof AdminNotLoginException) {
            body.setErrorCode(StateUtil.ADMIN_NOT_LOGIN);
        } else {
            body.setErrorCode(StateUtil.SYSTEM_EXCEPTION);
            body.setStack(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        body.setMsg(StateUtil.message.get(body.getErrorCode()));
        //TODO 语言

        return body;
    }
}
