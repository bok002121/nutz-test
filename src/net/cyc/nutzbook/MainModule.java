package net.cyc.nutzbook;

import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Localization;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.filter.CheckSession;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@SetupBy(value = MainSetup.class)
@IocBy(type=ComboIocProvider.class, args={"*js", "ioc/",
    "*anno", "net.cyc.nutzbook",
    "*tx"})
@Localization(value="msg/", defaultLocalizationKey="zh-CN")
@Ok("json:full")
@Fail("jsp:jsp.500")
@Modules(scanPackage=true)

//@Filters(@By(type = CheckSession.class, args ={ "me", "/" }))
public class MainModule
{

}
