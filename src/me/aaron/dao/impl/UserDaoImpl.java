package me.aaron.dao.impl;

import me.aaron.dao.IUserDao;
import me.aaron.domain.User;
import me.aaron.util.XmlUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import java.text.SimpleDateFormat;

/**
 * Created by Rong Rong on 2017/9/6.
 */
public class UserDaoImpl implements IUserDao{


    @Override
    public User find(String userName, String userPwd) {
        try {
            Document document = XmlUtils.getDocument();
            Element e = (Element) document.selectSingleNode("//user[@userName='"+userName+"' and @userPsd='"+userPwd+"']");
            if (e == null){
                return null ;
            }
            User user = new User();
            user.setId(e.attributeValue("id"));
            user.setEmail(e.attributeValue("email"));
            user.setUserPwd(e.attributeValue("userPwd"));
            user.setUserName(e.attributeValue("userName"));
            String birth = e.attributeValue("birthday");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirthday(sdf.parse(birth));

            return user;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(User user) {

        try{
            Document document = XmlUtils.getDocument();
            Element root = document.getRootElement();
            Element user_node = root.addElement("user");
            user_node.addAttribute("id", user.getId());
            user_node.addAttribute("userName",user.getUserName());
            user_node.addAttribute("userPwd", user.getUserPwd());
            user_node.addAttribute("email", user.getEmail());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            user_node.addAttribute("birthday", sdf.format(user.getBirthday()));

            XmlUtils.write2Xml(document);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User find(String userName) {

        try {
            Document document = XmlUtils.getDocument();
            Element e = (Element) document.selectSingleNode("//user[@userName='" + userName + "']");
            if (e==null) {
                return null;
            }
            User user = new User();
            user.setId(e.attributeValue("id"));
            user.setUserName(e.attributeValue("userName"));
            user.setEmail(e.attributeValue("email"));
            user.setUserPwd(e.attributeValue("userPwd"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirthday(sdf.parse(e.attributeValue("birthday")));

            return user;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
