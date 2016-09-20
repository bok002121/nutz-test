package net.cyc.nutzbook.module;

import javax.servlet.http.HttpSession;

import net.cyc.nutzbook.bean.User;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;

@IocBean
@At("/user")
@Ok("json")
@Fail("http:500")
public class UserModule extends BaseModule
{
	@At
	public Object login(@Param("username") String name,
			@Param("password") String password, HttpSession session)
	{
		User user = dao.fetch(User.class,
				Cnd.where("name", "=", name).and("password", "=", password));
		if (user == null)
		{
			return false;
		} else
		{
			session.setAttribute("me", user.getId());
			return true;
		}
	}

	@At
	@Ok(">>:/")
	public void logout(HttpSession session)
	{
		session.invalidate();
	}
	
	@At
	@Ok("jsp:jsp.user.hello")
	public View hello()
	{
		return new JspView("jsp.user.world");
	}
}
