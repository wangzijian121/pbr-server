package interceptor;

import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.dao.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    private static final Logger logger = LogManager.getLogger(LoginHandlerInterceptor.class);
    @Autowired
    private UserMapper userMapper;

    /**
     * 使用拦截器对登录进行校验
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // get token
        String userId = request.getHeader("userId");
        User user;

        user = userMapper.selectById(userId);
        if (user == null) {
            response.setStatus(401);
            logger.info("用户非法！");
            return false;
        }
        request.setAttribute("session.user", user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
    }
}