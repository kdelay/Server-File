package com.example.demo.security.service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.security.dao.UserDao;
import com.example.demo.security.jwt.util.JwtUtil;
import com.example.demo.security.vo.AuthRequestVo;
import com.example.demo.security.vo.UserVo;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	UserDao dao;
	@Autowired
	private JwtUtil jwtUtil;
  
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("username", username);
        UserVo user = dao.UserSearchDao(paramMap);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
    
    public String logintoken(AuthRequestVo authrequestvo) {
    	Map<String, String> userMap = new HashMap<String, String>();
    	userMap.put("username", authrequestvo.getUsername());
    	System.out.println("***** username : "+authrequestvo.getUsername());
    	
    	UserVo result = dao.UserSearchDao(userMap);
    	
    	JSONObject userjsonObject = new JSONObject();
    	
    	BCryptPasswordEncoder Encoder = new BCryptPasswordEncoder();
    	if(result == null) {
    		String iderr = "아이디가 없습니다";
    		return iderr;
    	}
    	else if( ! Encoder.matches(authrequestvo.getPassword(), result.getPassword())) {
    		System.out.println("***** password : false");
    		String pwerr = "비밀번호가 일치하지 않습니다";
    		return pwerr;
    	}else {
    		System.out.println("***** password : true");
    		userjsonObject.put("username", result.getUsername());
        	userjsonObject.put("userRole", result.getUserRole());
        	userjsonObject.put("userNickName", result.getUserNickName());
        	userjsonObject.put("userGender", result.getUserGender());
        	String userinfo = userjsonObject.toJSONString();
        	String usertoken = jwtUtil.generateToken(result, result.getUsername());
        	return usertoken;
    	}
    	
//    	userjsonObject.put("Id", result.getId());
    	
    	
    	
//    	JSONParser parser = new JSONParser();
//    	JSONObject info = new JSONObject();
//		try {
//			info = (JSONObject)parser.parse( userinfo );
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
//		System.out.println("***** info : "+info.get("userNickName"));
    	
//    	String usertoken = jwtUtil.generateToken(userinfo);
    }
    
    public int UserSignUp(UserVo request) {
    	Map<String, String> userMap = new HashMap<String,String>();
    	userMap.put("username", request.getUsername());
    	//패스워드 암화화 
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	String resultpassword = encoder.encode(request.getPassword());
    	userMap.put("password", resultpassword);
    	
    	//user, admin 구분 예정
    	userMap.put("userRole", "user");
    	userMap.put("userEmail", request.getUserEmail());
    	//유저 특수정보
    	userMap.put("userNickName", request.getUserNickName());
    	userMap.put("userGender", request.getUserGender());
    	userMap.put("userDOB", request.getUserDOB());
    	
    	
    	ZoneOffset seoulZoneOffset = ZoneOffset.of("+09:00");
		String currentout = ZonedDateTime.now(seoulZoneOffset).toString();
    	//가입일, 비밀 번호 수정일
    	userMap.put("createAT", currentout.substring(0, 19));
    	userMap.put("updateAT", currentout.substring(0, 19));
    	
    	
    	int result = dao.UserSignUpDao(userMap);
    	return result;
    }
}