package com.example.demo.security.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.security.vo.UserVo;
@Mapper
public interface UserDao {
	public UserVo UserSearchDao(Map<String, String> map);
	public int UsernameSearchDao(Map<String, String> map);
	public int UserEmailSearchDao(Map<String, String> map);
	public int UserNicknameSearchDao(Map<String, String> map);
	public int UserSignUpDao(Map<String, String> map);
}
