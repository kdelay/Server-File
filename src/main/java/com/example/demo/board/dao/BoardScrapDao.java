package com.example.demo.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.board.vo.BoardScrapVo;

@Mapper
public interface BoardScrapDao {
	public int BoardScrapPostDao(Map<String, String> map);
	public int BoardScrapDeleteDao(Map<String, String> map);
	
	
	// 유저 아이디로 글 아이디 가져옴
	public List<BoardScrapVo> BoardScrapUserIdListDao(Map<String, String> map);
}
