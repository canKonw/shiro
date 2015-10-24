import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by Administrator on 15-10-24.
 * realm测试
 */
public class RealmTest extends AuthorizingRealm{
    /**
     * 必须重写以下两个方法
     */
    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String code = (String)authenticationToken.getPrincipal();
        //以下模拟用户数据库查询
        if(!code.equals("hh")){
            return null;
        }
        String passWord = "123456";
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(code,passWord,"customRealm");
        return  info;
    }
    //用户授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
