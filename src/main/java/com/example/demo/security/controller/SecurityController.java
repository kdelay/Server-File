package com.example.demo.security.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.jwt.util.JwtUtil;
import com.example.demo.security.service.UserService;
import com.example.demo.security.vo.AuthRequestVo;
import com.example.demo.security.vo.UserVo;

@RestController
public class SecurityController {
	@Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService UserService;
 
    @GetMapping("/api/v1/sample")
    public String sample(HttpServletRequest httpServletRequest,
            Model model)
    {
        return "list1";       
    }
//    @PostMapping("/authenticate")
//    public String generateToken(@RequestBody AuthRequestVo authRequest) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
//            );
//        } catch (Exception ex) {
////            throw new Exception("inavalid username/password");
//        	ex.printStackTrace();
//        }
//        System.out.println("authRequest.getUserName() : "+authRequest.getUsername());
//        return "jwtUtil.generateToken(authRequest.getUsername())";
//    }
    
    @PostMapping("/api/v1/signup")
    public String signup(@RequestBody UserVo request) {
    	int result = UserService.UserSignUp(request);
    	String resstr = "";
    	if(result == 1) {
    		resstr = "ok";
    	}else {
    		resstr = "fail";
    	}
    	return resstr;
    }
    
    @PostMapping("/api/v1/login")
    public String login(@RequestBody AuthRequestVo authRequest) {
    	String AccessToken = UserService.logintoken(authRequest);
    	System.out.println("*******************ch  " + AccessToken);
    	return AccessToken;
    }
    
    @PostMapping("/api/v1/signup/idch")
    public String signupidch(@RequestBody AuthRequestVo authRequest) {
    	int result = UserService.UserSignUp_idserch(authRequest);
    	String resstr = "";
    	if(result == 1) {
    		resstr = "fail";
    	}else {
    		resstr = "ok";
    	}
    	return resstr;
    }
    
    
    @PostMapping("/api/v1/signup/emailch")
    public String signupemailch(@RequestBody UserVo authRequest) {
    	int result = UserService.UserSignUp_emailserch(authRequest);
    	String resstr = "";
    	if(result == 1) {
    		resstr = "fail";
    	}else {
    		resstr = "ok";
    	}
    	return resstr;
    }
    
    
    
    @PostMapping("/api/v1/signup/nickch")
    public String signupnickch(@RequestBody UserVo authRequest) {
    	int result = UserService.UserSignUp_nicknameserch(authRequest);
    	String resstr = "";
    	if(result == 1) {
    		resstr = "fail";
    	}else {
    		resstr = "ok";
    	}
    	return resstr;
    }
    
}
