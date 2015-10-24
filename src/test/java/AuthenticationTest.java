
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import sun.util.logging.resources.logging;

/**
 * Created by Administrator on 15-10-24.
 * shiro通过配置文件测试
 */
public class AuthenticationTest {
    private Logger logger =Logger.getLogger(AuthenticationTest.class);

    @Test
    public void  testLoginAndLoginOut(){
        //通过ini文件创建securityManage
        Factory<org.apache.shiro.mgt.SecurityManager> securityManagerFactory=new IniSecurityManagerFactory("classpath:ini/shiro-first.ini");
        SecurityManager securityManager = securityManagerFactory.getInstance();
        //将securityManage创建到生成环境中
        SecurityUtils.setSecurityManager(securityManager);
        //从SecurtyUtils中构建一个subject
        Subject subject = SecurityUtils.getSubject();
        //认证提交token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("hh","1234567");
        try {
            subject.login(usernamePasswordToken);
        }catch (Exception e){
            logger.error("-------登录失败！msg："+e.getMessage());
        }
        logger.error("----------是否通过了认证："+subject.isAuthenticated());
        subject.logout();
        logger.error("----------是否通过了认证："+subject.isAuthenticated());





    }
}
