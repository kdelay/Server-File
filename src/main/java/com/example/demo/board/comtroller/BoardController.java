package com.example.demo.board.comtroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.board.service.BoardService;
import com.example.demo.board.vo.BoardCommentVo;
import com.example.demo.board.vo.BoardTextInfoVo;
import com.example.demo.board.vo.ConnectBoardImgVo;

@RestController
public class BoardController {
	@Autowired
	BoardService BoardService;
	
	//게시판 관련*********************************************************************************************************
	@GetMapping("/api/v1/boardlist")
	public List<BoardTextInfoVo> boardlist(HttpServletRequest httpServletRequest){
    	List<BoardTextInfoVo> list1 = BoardService.BoardTextAllList();
        return list1;       
    }
	@GetMapping("/api/v1/boardlist/{boardtype}")
	public List<BoardTextInfoVo> boardlist(@PathVariable(name="boardtype") String boardtype){
    	List<BoardTextInfoVo> list1 = BoardService.BoardTextTypeList(boardtype);
        return list1;       
    }
	
	@PostMapping("/api/v1/board")
	public String TestPost(HttpServletRequest httpServletRequest, @RequestBody BoardTextInfoVo request, Model model) {
		String authorizationHeader = httpServletRequest.getHeader("Authorization");
		String token = authorizationHeader.substring(7);
		
		String resultboardId = BoardService.BoardTextPost(token, request);
		
		return resultboardId;
	}
	//이미지 관련********************************************************************************************************
	@PostMapping("/api/v1/boardimgupload")
	  public List<String> boardimgupload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file[]) {
		List<String> result = BoardService.BoardImgInfoUpload(file);
		return result;
	  }
	
	//이미지 연결 테이블 관련***********************************************************************************************
	 @PostMapping("/api/v1/boardimgconnect")
	  public String boardimgconnect(HttpServletRequest httpServletRequest, @RequestBody ConnectBoardImgVo request) {
			
		int result = BoardService.ConnectBoardImgPost(request);
		if(result == 1) {
			return "ok";
		}else {
			return "fail";
		}
		
	  }
	//댓글 관련*********************************************************************************************************
		@PostMapping("/api/v1/comment")
		public String comment(HttpServletRequest httpServletRequest, @RequestBody BoardCommentVo request, Model model) {
			String authorizationHeader = httpServletRequest.getHeader("Authorization");
			String token = authorizationHeader.substring(7);
			
			int result = BoardService.BoardCommentPost(token, request);
			
			if(result == 1) {
				return "ok";
			}else {
				return "fail";
			}
		}
		
		@GetMapping("/api/v1/commentlist/{boardid}")
		public List<BoardTextInfoVo> commentlist(@PathVariable(name="boardid") String boardid){
	    	List<BoardTextInfoVo> list1 = BoardService.BoardCommentBoardIdList(boardid);
	        return list1;       
	    }
		
	//추천 관련*********************************************************************************************************
	//스크랩 관련********************************************************************************************************
}
