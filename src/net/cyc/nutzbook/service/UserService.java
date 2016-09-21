package net.cyc.nutzbook.service;

import java.util.Date;

import net.cyc.nutzbook.bean.User;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.random.R;
import org.nutz.service.IdNameEntityService;

@IocBean(fields="dao")
public class UserService extends IdNameEntityService<User> {

    public User add(String name, String password) {
        User user = new User();
        user.setName(name.trim());
        user.setSalt(R.UU16());
        user.setPassword(password);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return dao().insert(user);
    }

    public int fetch(String username, String password) {
        User user = fetch(username);
        if (user == null) {
            return -1;
        }
        String _pass = password;
        if(_pass.equalsIgnoreCase(user.getPassword())) {
            return user.getId();
        }
        return -1;
    }

    public void updatePassword(int userId, String password) {
        User user = fetch(userId);
        if (user == null) {
            return;
        }
        user.setSalt(R.UU16());
        user.setPassword(password);
        user.setUpdateTime(new Date());
        dao().update(user, "^(password|salt|updateTime)$");
    }
}