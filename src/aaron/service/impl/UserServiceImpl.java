package aaron.service.impl;

import aaron.dao.IUserDao;
import aaron.dao.impl.UserDaoImpl;
import aaron.domain.User;
import aaron.exception.UserExistException;
import aaron.service.IUserService;

/**
 * UserServiceImpl类为IUserService接口的具体实现类
 * service层(service层对web层提供所有的业务服务)
 */
public class UserServiceImpl implements IUserService{

    private IUserDao userDao  = new UserDaoImpl();

    @Override
    public void registerUser(User user) throws UserExistException {
        if (userDao.find(user.getUserName()) != null ){
            throw new UserExistException("注册的用户名已存在！");
        }
        userDao.add(user);
    }

    @Override
    public User loginUser(String userName, String userPwd) {
        return userDao.find(userName, userPwd);
    }
}
