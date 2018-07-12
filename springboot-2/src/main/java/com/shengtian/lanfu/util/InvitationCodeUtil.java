package com.shengtian.lanfu.util;



public class InvitationCodeUtil {
	

  
	
	/**
	 * 生成邀请码
	 * @return
	 */
	public static String generate(){
		
		String InvitationCode="YYSH";
		int code=(int)((Math.random()*9+1)*100);
		String newCode=InvitationCode+code;
		return newCode;
	}

}
