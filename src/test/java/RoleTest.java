import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Administrator on 15-10-24.
 * shiro角色测试
 */
public class RoleTest {
private Logger logger = Logger.getLogger(RoleTest.class);
    @Test
    public void test(){
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:ini/shiro-role.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject =SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("hh","123456");
        subject.login(usernamePasswordToken);
        logger.error("----是否认证："+subject.isAuthenticated());
        //判断是否权限认证
        Boolean isPermitted = subject.isPermitted("user:create");
        logger.error("------单个权限认证："+isPermitted);
        Boolean isPermittedAll = subject.isPermittedAll("user:create","user:aa");
        logger.error("------多个权限认证:"+isPermittedAll);
        //判断是否角色认证
        subject.logout();//退出后将认证不通过
        Boolean hasRole = subject.hasRole("role1");
        logger.error("-----单个角色认证："+hasRole);
        Boolean hasRoleAll  = subject.hasAllRoles(Arrays.asList("role1","role2"));
        logger.error("-----多个角色认证："+hasRoleAll);

    }
}
