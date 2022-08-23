package com.example.file.Controller;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.file.Model.Member;
import com.example.file.Model.board.Board;
import com.example.file.Model.board.UpdataBoardForm;
import com.example.file.Model.board.WriteBoardForm;
import com.example.file.Service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping(path="/board")

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {
	private final BoardService boardService;
	
	
	// FindAllBoard
	@GetMapping(value="/list")
	public String bulletinBoard(Model model) {
		List<Board> boards =boardService.findAllBoards();
		log.info("Board: {}",boards);
		model.addAttribute("boards", boards);
		return "/board/list";
	}
	
	// Inserting board
	@GetMapping(value="/write")
	public String CallwriteBoard(Model model) {
		model.addAttribute("writeBoardForm",new WriteBoardForm());
		return "/board/writeForm";
	}
	
    @PostMapping(value = "/write")
    public String write(@Validated @ModelAttribute("writeBoardForm") WriteBoardForm writeBoardForm, BindingResult bindingResult, @SessionAttribute("loginMember") Member loginMember) {
        log.info("writeBoardForm : {}", writeBoardForm);

        if (bindingResult.hasErrors()) {
            return "board/writeForm";
        }
        Board board = writeBoardForm.toBoard();
        board.setMember_id(loginMember.getId());
        boardService.saveBoard(board);
        return "redirect:/board/list";
    }
	
    // 
    @GetMapping(value="/read/{id}")
    public String readBoard(@PathVariable long id,
    		Model model) {
    	 model.addAttribute("board",boardService.readBoard(id));

    	return "board/read";
    }
    
    // call updateBoardForm
    @GetMapping(value = "/update/{id}")
    public String updateForm(@PathVariable long id
    		, Model model, @SessionAttribute("loginMember") Member loginMember) {
        // 게시물 검색
        Board findBoard = boardService.findBoardById(id);
        // 게시글 작성자가 맞는지 확인
        if (!loginMember.getId().equals(findBoard.getMember_id())) {
            log.info("작성자와 로그인한 사용자가 다름!!");
            return "redirect:/board/list";
        }

        model.addAttribute("updateBoardForm", findBoard.toUpdateForm());
        return "board/updateForm";
    }

    // 게시물 수정
    @PostMapping(value = "/update")
    public String update(@Validated @ModelAttribute("updateBoardForm") UpdataBoardForm updateBoardForm, BindingResult bindingResult, @SessionAttribute("loginMember") Member loginMember) {
        log.info("updateBoardForm : {}", updateBoardForm);

        if (bindingResult.hasErrors()) {
            return "board/updateForm";
        }

        Board findBoard = boardService.findBoardById(updateBoardForm.getId());
        // 게시글 작성자가 맞는지 확인
        if (!loginMember.getId().equals(findBoard.getMember_id())) {
            log.info("작성자와 로그인한 사용자가 다름!!");
            return "redirect:/board/list";
        }
        findBoard.setTitle(updateBoardForm.getTitle());
        findBoard.setContent(updateBoardForm.getContent());
        findBoard.setUpdateTime(LocalDateTime.now());
        boardService.updateBoard(findBoard);
        return "redirect:/board/list";
    }
	
    // Board Delete 
    @GetMapping(value = "remove/{id}")
    public String remove(@PathVariable long id, @SessionAttribute("loginMember") Member loginMember) {
        log.info("id : {}", id);
        Board findBoard = boardService.findBoardById(id);
         // 게시글 작성자가 맞는지 확인
         if (!loginMember.getId().equals(findBoard.getMember_id())) {
            log.info("작성자와 로그인한 사용자가 다름!!");
            return "redirect:/board/list";
        }
        boardService.removeBoard(id);
        return "redirect:/board/list";
    }
    
    
}
