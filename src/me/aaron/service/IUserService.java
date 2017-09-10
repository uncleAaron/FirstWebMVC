package me.aaron.service;

import me.aaron.domain.User;
import me.aaron.exception.UserExistException;

/**
 * service层(service层对web层提供所有的业务服务)
 *
 */
public interface IUserService {

    /**
     * 提供注册服务
     * @param user
     * @throws UserExistException
     */
    void registerUser(User user) throws UserExistException;

    /**
     * 提供登陆服务
     * @param userName
     * @param userPwd
     * @return
     */
    User loginUser(String userName, String userPwd);
}
