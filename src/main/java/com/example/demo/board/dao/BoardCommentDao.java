package com.example.demo.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.board.vo.BoardTextInfoVo;

@Mapper
public interface BoardCommentDao {
	public int BoardCommentPostDao(Map<String, String> map);
	public int BoardCommentPutDao(Map<String, String> map);
	public int BoardCommentDeleteDao(Map<String, String> map);
	public int BoardCommentDeletelayer2Dao(Map<String, String> map);
	//boardid 마다 댓글 갯수
	//public int BoardCommentCountDao(Map<String, String> map);
	
	//boardid의 댓글, 대댓글 몰 리스트
	public List<BoardTextInfoVo> BoardCommentAllListDao();
	
	//BoardCommentBoardIdListDao
	public List<BoardTextInfoVo> BoardCommentBoardIdListDao(Map<String, String> map);
	
	//boardid 댓글 리스트
	public List<BoardTextInfoVo> BoardCommentLayer1ListDao(Map<String, String> map);

	//boardid 대댓글 리스트
	public List<BoardTextInfoVo> BoardCommentLayer2ListDao(Map<String, String> map);

}
