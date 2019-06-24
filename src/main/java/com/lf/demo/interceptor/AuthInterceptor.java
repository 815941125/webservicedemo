package com.lf.demo.interceptor;

import com.lf.demo.annotation.NeedAdminLogin;
import com.lf.demo.annotation.NeedUserLogin;
import com.lf.demo.constants.BaseConstants;
import com.lf.demo.constants.LangEnum;
import com.lf.demo.context.AdminSystemContext;
import com.lf.demo.context.SystemContextHolder;
import com.lf.demo.context.UserSystemContext;
import com.lf.demo.exception.*;
import com.lf.demo.service.TokenService;
import com.lf.demo.util.BeanManager;
import com.lf.demo.util.CheckUtil;
import com.lf.demo.util.StateUtil;
import com.lf.demo.vo.Token;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author linfei
 * @version 1.0
 * @date 2019/6/24
 * @desc 认证拦截器
 * @see
 * @since 1.0
 */
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        auth(request, response, object);
        return false;
    }



    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 认证
     * @param request
     * @param response
     * @param object
     */
    private void auth(HttpServletRequest request, HttpServletResponse response, Object object) throws RequestNotFoundException, ParamsErrorException, UserNotLoginException, AdminNotLoginException {
        HandlerMethod method = (HandlerMethod) object;
        if (method.getBean() instanceof BasicErrorController) {
            throw new RequestNotFoundException();
        }
        //TODO 设置语言
        String language = httpClientLangHandle(request);
        SystemContextHolder.put(language);

        if (method.getMethodAnnotation(NeedUserLogin.class) != null) {
            //判断方法是否需要登录
            String code = request.getHeader(BaseConstants.HEAD_KEY_TOKEN);
            if (CheckUtil.isEmpty(code)) {
                code = request.getParameter(BaseConstants.HEAD_KEY_TOKEN);
            }
            if (!CheckUtil.isEmpty(code)) {
                Token token = BeanManager.getBean(TokenService.class).parseToken(code);
                if (token != null && token.getId() != null) {
                    tokenOverTime(token.getTs());
                    if (BaseConstants.TOKEN_USER.equals(token.getType())) {
                        UserSystemContext userSystemContext = new UserSystemContext(request, response,
                                request.getRequestURI());
                        userSystemContext.setDoLongin(true);
                        userSystemContext.setId(token.getId());
                        userSystemContext.setLanguage(language);
                        SystemContextHolder.put(userSystemContext);
                    } else {
                        throw new UserNotLoginException();
                    }
                } else {
                    throw new ParamsErrorException();
                }
            } else {
                throw new ParamsErrorException();
            }
        } else if (method.getMethodAnnotation(NeedAdminLogin.class) != null) {
            //判断方法是否需要登录
            String code = request.getHeader(BaseConstants.HEAD_KEY_TOKEN);
            if (CheckUtil.isEmpty(code)) {
                code = request.getParameter(BaseConstants.HEAD_KEY_TOKEN);
            }
            if (!CheckUtil.isEmpty(code)) {
                Token token = BeanManager.getBean(TokenService.class).parseToken(code);
                if (token != null && token.getId() != null) {
                    tokenOverTime(token.getTs());
                    if (BaseConstants.TOKEN_ADMIN.equals(token.getType())) {
                        AdminSystemContext adminSystemContext = new AdminSystemContext(request, response,
                                request.getRequestURI());
                        adminSystemContext.setDoLongin(true);
                        adminSystemContext.setId(token.getId());
                        adminSystemContext.setLanguage(language);
                        SystemContextHolder.put(adminSystemContext);
                    } else {
                        throw new AdminNotLoginException();
                    }
                } else {
                    throw new ParamsErrorException();
                }
            } else {
                throw new ParamsErrorException();
            }
        }

    }


    /**
     * 处理语言
     */
    private String httpClientLangHandle(HttpServletRequest request) {
        String language = request.getParameter(BaseConstants.HTTP_HEAD_KEY_LANG);
        if (StringUtils.isEmpty(language)) {
            language = request.getHeader(BaseConstants.HTTP_HEAD_KEY_LANG);
        }

        if (StringUtils.isEmpty(language) || !LangEnum.exist(language)) {
            //设置默认语言
            language = LangEnum.LANG_ZH_CN.getLanguage();
        }
        return language;
    }

    private static void tokenOverTime(Date createTokenTime) {
        Long oneDayTimes = 30 * 24 * 60 * 60 * 1000L;
        if ((System.currentTimeMillis() - createTokenTime.getTime()) > oneDayTimes) {
            throw new ServiceException(StateUtil.TOKEN_OVER_TIME);
        }
    }
}
