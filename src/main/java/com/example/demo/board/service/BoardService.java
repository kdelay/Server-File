package com.example.demo.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.board.vo.BoardCommentLayer1InfoVo;
import com.example.demo.board.vo.BoardCommentVo;
import com.example.demo.board.vo.BoardImgInfoVo;
import com.example.demo.board.vo.BoardScrapBoardInfoVo;
import com.example.demo.board.vo.BoardScrapVo;
import com.example.demo.board.vo.BoardTextInfoResultVo;
import com.example.demo.board.vo.BoardTextInfoVo;
import com.example.demo.board.vo.ConnectBoardImgVo;

public interface BoardService {

	
	// 게시판 테이블 요청
	// 테이블 조회 요청
	public List<BoardTextInfoVo> BoardTextAllList();
	public List<BoardTextInfoResultVo> BoardTextTypeList(String boardType);
	
	//추천수 
	public int BoardTextRecommendUp(String boardId);
	public int BoardTextRecommendDawn(String boardId);
	
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
	public List<String> ConnectBoardImgBoardIdList(String boardId);
	
	
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
	public List<BoardCommentVo> BoardCommentAllList();
	
	//개시글 아이디로 연관 댓글 검색
	public List<BoardCommentVo> BoardCommentBoardIdList(String boardId);
	
	//모바일용 조회 레이어1 댓글 정보와 그 댓글의 레이어2 댓글(대댓글) 수를 포함하여 리턴
	public List<BoardCommentLayer1InfoVo> BoardCommentLayer1List(String boardId);

	//모바일용 조회 댓글에 딸린 레이어2 정보(대댓글) 조회
	public List<BoardCommentVo> BoardCommentLayer2List(String commentParentsId);
	
	
	
	
	/////////////////////////////////////////////////////////////
	// 추천 관련
	// 작성 삭제
	public int BoardRecommendPost(String token, String boardId, String boardType);
	public int BoardRecommendDelete(String token, String boardId);
	
	
	// 추천 했는지 정보 확인 // 글아디디랑 유저 아이디 동시 검색 할듯
	public int BoardRecommendSerch(String token, String boardid);
	
	//카운트 검색
	public int BoardRecommendCountSerch(String boardid);
	/////////////////////////////////////////////////////////////
	// 스크랩 관련
	// 작성 삭제
	public int BoardScrapPost(String token, BoardScrapVo BoardScrapVo);
	public int BoardScrapDelete(String token, BoardScrapVo BoardScrapVo);
	
	
	// 유저 아이디로 글 아이디 가져옴
	public List<BoardScrapVo> BoardScrapUserIdList(String token);
	// 유저 아이디로 검색 후 타입 구분
	public List<BoardScrapVo> BoardScrapBoardTypeList(String token, String boardType);
	// 유저 아이디 검색 후 글 정보 추가 데이터
	public List<BoardScrapBoardInfoVo> BoardScrapBoardInfoList(String token);
	

}
