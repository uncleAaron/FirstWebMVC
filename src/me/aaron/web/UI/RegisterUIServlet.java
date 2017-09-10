package me.aaron.web.UI;

import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * Created by Rong Rong on 2017/9/7.
 */
public class RegisterUIServlet extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
    }

    /**
     * 用户注册时如果填写的表单数据校验不通过，那么服务器端就将一个存储了错误提示消息和表单数据的formbean对象
     * 存储到request对象中，然后发送回register.jsp页面，因此我们需要在register.jsp页面中取出request对象中
     * formbean对象，然后将用户填写的表单数据重新回显到对应的表单项上面，将出错时的提示消息也显示到form表单上
     * 面，让用户知道是哪些数据填写不合法！
     */
}
