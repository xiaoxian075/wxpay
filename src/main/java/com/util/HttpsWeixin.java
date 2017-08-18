package com.util;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.weixin.client.node.OathTokenNode;
import com.weixin.client.node.WXCInfoNode;
import com.weixin.client.node.WeixinAccessTokenNode;
import com.weixin.client.node.WeixinCallbackIPNode;
import com.weixin.client.node.WeixinMenuNode;

public class HttpsWeixin {
	private static final Logger logger = Logger.getLogger(HttpsWeixin.class);
    
    /**
    * 获取微信access_token
    * @param appid
    * @param secret
    * @return access_token
    */
	public static WeixinAccessTokenNode getAccessToken(String appid,String secret) {
		if(StringUtils.isEmpty(appid)|| StringUtils.isEmpty(secret)) {
        	logger.error("param is null");
            return null;
        }

		String url = geturlAccessToken(appid,secret);
		String token = httpsGetSend(url);
		if (token==null)
			return null;
		
		if (!check(token))
			return null;

		return XmlUtil.fromJson(token, WeixinAccessTokenNode.class);
	}
	
	/**
	    * 获取微信服务器IP
	    * @param appid
	    * @param secret
	    * @return access_token
	    */
	public static WeixinCallbackIPNode getWXIP(String accesstoken) {
		if(StringUtils.isEmpty(accesstoken)) {
			logger.error("param is null");
			return null;
		}
		String url = geturlCallbackIP(accesstoken);
		String token = httpsGetSend(url);
		if (token==null)
			return null;
		if (!check(token))
			return null;

		return XmlUtil.fromJson(token, WeixinCallbackIPNode.class);
	}
		
	/**
	 * 添加菜单
	 * @param accesstoken
	 * @param body
	 * @return
	 */
	public static boolean menuAdd(String accesstoken,String body) {
		if(StringUtils.isEmpty(accesstoken)) {
        	logger.error("param is null");
            return false;
        }
		
		String url = geturlMenuAdd(accesstoken);
		String token = httpsPostSend(url,body);
		if (token==null)
			return false;
		
		httpsRsp rsp = XmlUtil.fromJson(token, httpsRsp.class);
		if (rsp==null || rsp.getErrcode()!=0)
			return false;
		return true;
	}
	
	/**
	 * 删除菜单
	 * @param accesstoken
	 * @return
	 */
	public static boolean menuDel(String accesstoken) {
		if(StringUtils.isEmpty(accesstoken)) {
        	logger.error("param is null");
            return false;
        }

		String url = geturlMenuDelete(accesstoken);
		String token = httpsGetSend(url);
		if (token==null)
			return false;
		
		httpsRsp rsp = XmlUtil.fromJson(token, httpsRsp.class);
		if (rsp==null || rsp.getErrcode()!=0)
			return false;
		return true;		
	}
	
	/**
	 * 获取菜单信息
	 * @param accesstoken
	 * @return
	 */
	public static WeixinMenuNode menuInfo(String accesstoken) {
		if(StringUtils.isEmpty(accesstoken)) {
        	logger.error("param is null");
            return null;
        }

		String url = geturlMenuSelect(accesstoken);
		String token = httpsGetSend(url);
		if (token==null)
			return null;
		if (!check(token))
			return null;
		
		return XmlUtil.fromJson(token, WeixinMenuNode.class);		
	}
	
	/**
	 * 认证步骤二：以code换accept_token
	 * @param appid
	 * @param secret
	 * @param code
	 * @return
	 */
	public static OathTokenNode getOathToken(String appid,String secret,String code) {
		if(StringUtils.isEmpty(appid) || StringUtils.isEmpty(secret) || StringUtils.isEmpty(code)) {
			logger.error("param is null");
			return null;
		}
		String url = geturlOathToken(appid,secret,code);
		String token = httpsGetSend(url);
		if (token==null)
			return null;
		if (!check(token))
			return null;

		return XmlUtil.fromJson(token, OathTokenNode.class);
	}
	
	/**
	 * 获取微信玩家信息
	 * @param accesstoken
	 * @param openid
	 * @return
	 */
	public static WXCInfoNode getWxui(String accesstoken,String openid) {
		if(StringUtils.isEmpty(accesstoken) || StringUtils.isEmpty(openid)) {
			logger.error("param is null");
			return null;
		}
		String url = geturlWxui(accesstoken,openid);
		String token = httpsGetSend(url);
		if (token==null)
			return null;
		if (!check(token))
			return null;

		return XmlUtil.fromJson(token, WXCInfoNode.class);
	}
	
	
	
	
	
	private static boolean check(String str) {
		httpsRsp rsp = XmlUtil.fromJson(str, httpsRsp.class);
		if (rsp!=null) {
			if (rsp.getErrcode()!=0 || rsp.getErrmsg()!=null) {
				logger.info("errcode:"+rsp.getErrcode()+",errmsg:"+rsp.getErrmsg());
				return false;
			}
		}
		return true;
	}
	
	private static String httpsPostSend(String url, String body) {
		String strRsp = null;
		try {
			httpsRsp rsp = ComUtil.httpsPostSend(url,body);
			if (rsp==null) {
				logger.error("url="+url+",return null");
			} else if (rsp.getErrcode()==200) {
				strRsp = rsp.getErrmsg();
			} else {
				logger.info("url="+url+", code="+rsp.getErrcode()+",desc="+rsp.getErrmsg());
			}
		} catch (Exception e) {
			strRsp = null;
			logger.error(e);
		}
		return strRsp;
	}
	
	private static String httpsGetSend(String url) {
		String strRsp = null;
		try {
			httpsRsp rsp = ComUtil.httpsGetSend(url);
			if (rsp==null) {
				logger.error("url="+url+",return null");
			} else if (rsp.getErrcode()==200) {
				strRsp = rsp.getErrmsg();
			} else {
				logger.info("url="+url+", code="+rsp.getErrcode()+",desc="+rsp.getErrmsg());
			}
		} catch (Exception e) {
			strRsp = null;
			logger.error(e);
		}
		return strRsp;
	}
	
	private static String geturlAccessToken(String appid,String secret) {
		return "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
	}
	private static String geturlCallbackIP(String accesstoken) {
		return "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token="+accesstoken;
	}
	private static String geturlMenuAdd(String accesstoken) {
		return "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accesstoken;
	}
	private static String geturlMenuDelete(String accesstoken) {
		return "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+accesstoken;
	}
	private static String geturlMenuSelect(String accesstoken) {
		return "https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+accesstoken;
	}
	private static String geturlOathToken(String appid,String secret,String code) {
		return "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
	}
	private static String geturlWxui(String accesstoken,String openid) {
		return "https://api.weixin.qq.com/sns/userinfo?access_token="+accesstoken+"&openid="+openid+"&lang=zh_CN";
		
	}
	 
}
