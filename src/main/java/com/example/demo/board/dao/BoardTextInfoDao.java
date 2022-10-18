package com.example.demo.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.board.vo.BoardTextInfoVo;

@Mapper
public interface BoardTextInfoDao {
	public List<BoardTextInfoVo> BoardTextAllListDao();
	public List<BoardTextInfoVo> BoardTextTypeListDao(Map<String, String> map);
	
	public int BoardTextRecommendDao(Map<String, String> map);
	public int BoardTextPostDao(Map<String, String> map);
	public int BoardTextPutDao(Map<String, String> map);
	public int BoardTextDeleteDao(Map<String, String> map);
}
