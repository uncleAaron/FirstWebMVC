package aaron.dao;

import aaron.domain.User;

/**
 * Created by Rong Rong on 2017/9/6.
 */
public interface IUserDao {
    /**大学
     * 根据用户名和密码来查找用户
     * @param userName 用户名
     * @param userPwd 密码
     * @return 查找到的用户
     */
    User find(String userName, String userPwd);

    /**
     * 添加哟过户
     * @param user 用户对象
     */
    void add(User user);

    /**
     * 根据用户名查找用户
     * @param userName 用户名
     * @return 查找到的用户
     */
    User find(String userName);
}
