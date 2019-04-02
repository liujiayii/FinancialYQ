package com.fendo.shiro;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ShiroRealm")
public class ShiroRealm extends AuthorizingRealm{
	
	//@Autowired
	//private PopedomService popedomService;
	//@Autowired
	//private UserService userService;
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Subject subject = SecurityUtils.getSubject();
		System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
	//	User user = (User)subject.getPrincipal();
		//调用业务层，查询权限
    //	List<Popedom> popedoms = popedomService.findPopedomByUserId(user.getId());
    //	for (Popedom popedom : popedoms) {
		//	authorizationInfo.addStringPermission(popedom.getPopedomUrl());
		//}
		return authorizationInfo;
	}
	
	//验证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken uptoken = (UsernamePasswordToken)token;
		//User user = userService.findUserByUserName(uptoken.getUsername());
		//if(user==null){//账号不存
		//	throw new UnknownAccountException("用户不存在");
	//	}else{
		//	if(user.getStatus()==2){//2为锁定，1为默认
		//		throw new LockedAccountException("用户被锁定");
		//	}
	//	}
		//1)principal：认证的实体信息
		//Object principal = user.getUserName();
		//2)credentials：密码
	//	Object credentials = user.getPassWord();
		//3)realmName：当前realm对象的name，调用父类的getName()方法即可
		//String realmName = getName();
		//4)credentialsSalt盐值,使用账号作为盐值
	//	ByteSource credentialsSalt = ByteSource.Util.bytes(principal);
		
		//SimpleAuthenticationInfo info =new SimpleAuthenticationInfo(user, credentials, credentialsSalt, realmName);
		return null;
	}
	
}
