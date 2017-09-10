package me.aaron.web.formbean;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import java.util.HashMap;
import java.util.Map;


public class RegisterFormBean {

    //RegisterFormBean中的属性与register.jsp中的表单输入项的name一一对应
    //<input type="text" name="id"/>
    private String userName;
    private String userPwd;
    private String email;
    private String birthday;
    private String confirmPwd;

    /**
     * 存储校验不通过时给用户的错误提示信息
     */
    private Map<String, String> errors = new HashMap<>();

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    /*
 * validate方法负责校验表单输入项
 * 表单输入项校验规则：
 *         private String userName; 用户名不能为空，并且要是3-8的字母 abcdABcd
 *         private String userPwd; 密码不能为空，并且要是3-8的数字
 *         private String confirmPwd; 两次密码要一致
 *         private String email; 可以为空，不为空要是一个合法的邮箱
 *         private String birthday; 可以为空，不为空时，要是一个合法的日期
 */
    public boolean validate() {

        boolean isOk = true;

        if (this.userName == null || this.userName.trim().equals("")) {
            isOk = false;
            errors.put("userName", "用户名不能为空！！");
        } else {
            if (!this.userName.matches("[a-zA-Z]{3,8}")) {
                isOk = false;
                errors.put("userName", "用户名必须是3-8位的字母！！");
            }
        }

        if (this.userPwd == null || this.userPwd.trim().equals("")) {
            isOk = false;
            errors.put("userPwd", "密码不能为空！！");
        } else {
            if (!this.userPwd.matches("\\d{3,8}")) {
                isOk = false;
                errors.put("userPwd", "密码必须是3-8位的数字！！");
            }
        }

        // private String password2; 两次密码要一致
        if (this.confirmPwd != null) {
            if (!this.confirmPwd.equals(this.userPwd)) {
                isOk = false;
                errors.put("confirmPwd", "两次密码不一致！！");
            }
        }

        // private String email; 可以为空，不为空要是一个合法的邮箱
        if (this.email != null && !this.email.trim().equals("")) {
            if (!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {
                isOk = false;
                errors.put("email", "邮箱不是一个合法邮箱！！");
            }
        }

        // private String birthday; 可以为空，不为空时，要是一个合法的日期
        if (this.birthday != null && !this.birthday.trim().equals("")) {
            try {
                DateLocaleConverter conver = new DateLocaleConverter();
                conver.convert(this.birthday);
            } catch (Exception e) {
                isOk = false;
                errors.put("birthday", "生日必须要是一个日期！！");
            }
        }

        return isOk;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }
}
