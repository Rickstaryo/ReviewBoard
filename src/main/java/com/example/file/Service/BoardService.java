package com.example.file.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.file.Model.board.Board;
import com.example.file.repository.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardMapper boardMapper;
	
	public List<Board> findAllBoards() {
		return boardMapper.findAllBoards() ;
	}
	
}
