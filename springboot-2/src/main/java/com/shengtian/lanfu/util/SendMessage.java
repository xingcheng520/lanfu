package com.shengtian.lanfu.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SendMessage {
public static void main(String[] args) throws Exception {
		
		/*// 用户名
		String name="17783857285"; 
		// 密码
		String pwd="A847AE3047615D05370959C01B0A"; 
		// 电话号码字符串，中间用英文逗号间隔
		StringBuffer mobileString=new StringBuffer("18580447210");
		// 内容字符串
		StringBuffer contextString=new StringBuffer("【赢亿石化】赢亿石化验证码为@（妥善保管好验证码，请勿告知他人）,5分钟内有效。如非本人操作，请忽略本短信。");
		// 签名
		String sign="【赢亿石化】";
		// 追加发送时间，可为空，为空为及时发送
		String stime="";
		// 扩展码，必须为数字 可为空
		StringBuffer extno=new StringBuffer();
		System.out.println((int)((Math.random()*9+1)*100000));
		
        System.out.println(doPost("18580447210"));*/
	    //doPost("18580447210",201598);
	
    }
	
	/**
	 * 发送短信
	 * 
	 * @param name			用户名
	 * @param pwd			密码
	 * @param mobileString	电话号码字符串，中间用英文逗号间隔
	 * @param contextString	内容字符串
	 * @param sign			签名
	 * @param stime			追加发送时间，可为空，为空为及时发送
	 * @param extno			扩展码，必须为数字 可为空
	 * @return				
	 * @throws Exception
	 */
    public static String doPost( String mobileString,int number) throws Exception {
    	
    	//随机生成一个6位数的验证码
    	String number2=number+"";
    	// 用户名
    	String name="17783857285"; 
    	// 密码
    	String pwd="A847AE3047615D05370959C01B0A";
    	// 内容字符串
		StringBuffer contextString=new StringBuffer("【赢亿石化】赢亿石化验证码为"+number2+"（妥善保管好验证码，请勿告知他人）,5分钟内有效。如非本人操作，请忽略本短信。");
		// 签名
		String sign="【赢亿石化】";
		// 追加发送时间，可为空，为空为及时发送
		String stime="";
		// 扩展码，必须为数字 可为空
		StringBuffer extno=new StringBuffer();
    	StringBuffer param = new StringBuffer();
    	param.append("name="+name);
    	param.append("&pwd="+pwd);
    	param.append("&mobile=").append(mobileString);
    	param.append("&content=").append(URLEncoder.encode(contextString.toString(),"UTF-8"));
    	param.append("&stime="+stime);
    	param.append("&sign=").append(URLEncoder.encode(sign,"UTF-8"));
    	param.append("&type=pt");
    	param.append("&extno=").append(extno);
        
        URL localURL = new URL("http://web.cr6868.com/asmx/smsservice.aspx");
        URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
        
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(param.length()));
        
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        String resultBuffer = "";
        
        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);
            
            outputStreamWriter.write(param.toString());
            outputStreamWriter.flush();
            
            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }
            
            inputStream = httpURLConnection.getInputStream();
            resultBuffer = convertStreamToString(inputStream);
            
        } finally {
            
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
            
            if (outputStream != null) {
                outputStream.close();
            }
            
            if (reader != null) {
                reader.close();
            }
            
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            
            if (inputStream != null) {
                inputStream.close();
            }
            
        }

        return resultBuffer;
    }
	
	
	/**
	 * 转换返回值类型为UTF-8格式.
	 * @param is
	 * @return
	 */
	public static String convertStreamToString(InputStream is) {    
        StringBuilder sb1 = new StringBuilder();    
        byte[] bytes = new byte[4096];  
        int size = 0;  
        
        try {    
        	while ((size = is.read(bytes)) > 0) {  
                String str = new String(bytes, 0, size, "UTF-8");  
                sb1.append(str);  
            }  
        } catch (IOException e) {    
            e.printStackTrace();    
        } finally {    
            try {    
                is.close();    
            } catch (IOException e) {    
               e.printStackTrace();    
            }    
        }    
        return sb1.toString();    
    }


}
