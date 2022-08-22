package com.example.file.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.file.Model.board.Board;
import com.example.file.Service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping(value="/board")

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {
	private final BoardService boardService;
	
	@GetMapping(value="/list")
	public String bulletinBoard(Model model) {
		List<Board> boards =boardService.findAllBoards();
		log.info("Board: {}",boards);
		model.addAttribute("boards", boards);
		return "/board/list";
	}
}
