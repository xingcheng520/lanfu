package com.shengtian.lanfu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shengtian.lanfu.model.Notice;
import com.shengtian.lanfu.service.NoticeService;

@RestController
@RequestMapping("v1/notice")
public class NoticeController {
	@Autowired
	private NoticeService  noticeService;
	/*
	 *获取首页公告消息
	 */
	@RequestMapping(value="/getNoticeMessage",method=RequestMethod.GET)
	public Map<String,Object>  getNoticeMessage(){
		Map<String,Object> result =new HashMap<String, Object>();
		try {
			List<Notice> notice=noticeService.selectlistNotice();
			result.put("flag", 1);
			result.put("notice", notice);
			result.put("message", "获取成功");
		} catch (Exception e) {
			result.put("flag", 2);
			result.put("message", "获取失败");
		}
		
		return result;
	}

}
