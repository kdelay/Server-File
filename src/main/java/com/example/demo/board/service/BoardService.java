package com.example.demo.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.board.vo.BoardCommentVo;
import com.example.demo.board.vo.BoardImgInfoVo;
import com.example.demo.board.vo.BoardRecommendVo;
import com.example.demo.board.vo.BoardScrapVo;
import com.example.demo.board.vo.BoardTextInfoVo;
import com.example.demo.board.vo.ConnectBoardImgVo;

public interface BoardService {

	
	// 게시판 테이블 요청
	// 테이블 조회 요청
	public List<BoardTextInfoVo> BoardTextAllList();
	public List<BoardTextInfoVo> BoardTextTypeList(String boardType);
	
	//추천수 
	public int BoardTextRecommend(String boardId);
	
	// 게시판 작성, 수정, 삭제
	public String BoardTextPost(String token, BoardTextInfoVo BoardTextInfoVo);
	public int BoardTextPut(BoardTextInfoVo BoardTextInfoVo, String boardId);
	public int BoardTextDelete(String boardId);
	
	
	/////////////////////////////////////////////////////////////
	//이미지 업로드 관련
	//이미지 정보 작성
	public List<String> BoardImgInfoUpload(MultipartFile file[]);
	// 특정 이미지 정보 조회
	public List<BoardImgInfoVo> BoardImgInfoSearch(String fileUniqueName);
	
	/////////////////////////////////////////////////////////////
	// 이미지 연결 테이블
	// 작성, 삭제 관련
	public int ConnectBoardImgPost(ConnectBoardImgVo ConnectBoardImgVo);
	public int ConnectBoardImgDelete(String boardId);
	
	//게시판 타입에 따른 올 조회
	public List<ConnectBoardImgVo> ConnectBoardImgTypeList(String boardType);
	//게시그 아이디로 조회
	public List<ConnectBoardImgVo> ConnectBoardImgBoardIdList(String boardId);
	
	
	/////////////////////////////////////////////////////////////
	// 댓글 관련
	// 댓글 작성, 수정, 삭제 
	public int BoardCommentPost(String token, BoardCommentVo BoardCommentVo);
	public int BoardCommentPut(BoardCommentVo BoardCommentVo, String commentId);
	public int BoardCommentDelete(String commentId, String commentLayer);
	public int BoardCommentDeletelayer2(String commentId);//하위레이어 전부 삭제
	
	//boardid 마다 댓글 갯수
	// all조회로 어느정도 가능한 동작일지도?
	//public int BoardCommentCountDao(Map<String, String> map);
	
	//boardid의 댓글, 대댓글 몰 리스트
	public List<BoardTextInfoVo> BoardCommentAllList();
	
	//개시글 아이디로 연관 댓글 검색
	public List<BoardTextInfoVo> BoardCommentBoardIdList(String boardId);
	
	//boardid 댓글 리스트
	public List<BoardTextInfoVo> BoardCommentLayer1List(Map<String, String> map);

	//boardid 대댓글 리스트
	public List<BoardTextInfoVo> BoardCommentLayer2List(Map<String, String> map);
	
	
	
	
	/////////////////////////////////////////////////////////////
	// 추천 관련
	// 작성 삭제
	public int BoardRecommendPost(String token, BoardRecommendVo BoardRecommendVo);
	public int BoardRecommendDelete(String token, String boardId);
	
	
	// 글아디디랑 유저 아이디 동시 검색 할듯
	public List<BoardRecommendVo> BoardRecommendSerch(String token);
	
	/////////////////////////////////////////////////////////////
	// 스크랩 관련
	// 작성 삭제
	public int BoardScrapPost(String token, BoardScrapVo BoardScrapVo);
	public int BoardScrapDelete(String token, String boardId);
	
	
	// 유저 아이디로 글 아이디 가져옴
	public List<BoardScrapVo> BoardScrapUserIdList(String token, String boardId);

}
