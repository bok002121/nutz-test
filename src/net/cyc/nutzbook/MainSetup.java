package net.cyc.nutzbook;

import java.util.Date;

import net.cyc.nutzbook.bean.User;

import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

public class MainSetup implements Setup
{
	public void init(NutConfig conf)
	{
		Ioc ioc = conf.getIoc();
		Dao dao = ioc.get(Dao.class);
		Daos.createTablesInPackage(dao, "net.cyc.nutzbook", false);
		
		if(dao.count(User.class) == 0)
		{
			User user = new User();
			user.setName("admin1");
			user.setPassword("123456");
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			dao.insert(user);
		}
		
	}
	
	public void destroy(NutConfig conf)
	{
		
	}
}
