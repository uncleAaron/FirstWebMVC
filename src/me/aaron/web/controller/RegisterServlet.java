package me.aaron.web.controller;

import me.aaron.domain.User;
import me.aaron.exception.UserExistException;
import me.aaron.service.IUserService;
import me.aaron.service.impl.UserServiceImpl;
import me.aaron.util.WebUtils;
import me.aaron.web.formbean.RegisterFormBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * Created by Rong Rong on 2017/9/7.
 */
public class RegisterServlet extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        //将客户端提交的表单数据封装到RegisterFormBean中
        RegisterFormBean formBean = WebUtils.request2Bean(request,RegisterFormBean.class);
        //校验用户注册填写的表单数据
        if (!formBean.validate()) { //如果校验失败
            //将封装了用户填写的表单数据的formbean对象发送回register.jsp页面的form表单中进行显示
            request.setAttribute("formBean",formBean);
            //校验失败就说明是用户填写的表单数据问题，那么跳转会到register.jsp
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request,response);
            return;
        }

        User user = new User();
        try {
            ConvertUtils.register(new DateLocaleConverter(), Date.class);
            BeanUtils.copyProperties(user,formBean);
            user.setId(WebUtils.makeId());
            IUserService service = new UserServiceImpl();
            //调用service层提供的注册用户服务实现用户注册
            service.registerUser(user);
            String message = String.format(
                    "注册成功！！3秒后为您自动跳到登录页面！！<meta http-equiv='refresh' content='3;url=%s'/>",
                    request.getContextPath()+"/servlet/LoginUIServlet");
            request.setAttribute("message", message);
            request.getRequestDispatcher("/message.jsp").forward(request, response);

        } catch (UserExistException e) {
            formBean.getErrors().put("userName","注册用户已存在！！！");
            request.setAttribute("formBean", formBean);
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
        } catch (InvocationTargetException | IllegalAccessException e) {
            //接收到BeanUtils.copyProperties的异常
            e.printStackTrace();//在后台记录异常
            request.setAttribute("message", "对不起，注册失败！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }

    /**
     * 用户注册时如果填写的表单数据校验不通过，那么服务器端就将一个存储了错误提示消息和表单数据的formbean对象
     * 存储到request对象中，然后发送回register.jsp页面，因此我们需要在register.jsp页面中取出request对象中
     * formbean对象，然后将用户填写的表单数据重新回显到对应的表单项上面，将出错时的提示消息也显示到form表单上
     * 面，让用户知道是哪些数据填写不合法！
     */
}
