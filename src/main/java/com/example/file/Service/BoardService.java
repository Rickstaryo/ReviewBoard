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

	public void saveBoard(Board board) {
	 boardMapper.saveBoard(board);
	}

	public Board findBoardById(long id) {
		return boardMapper.findBoardById(id);
	}

	public Board readBoard(long id) {
		Board findBoard = boardMapper.findBoardById(id);
		findBoard.addHit();
		boardMapper.updateBoard(findBoard);
		return findBoard;
	}
	
    // 게시물 수정
    public void updateBoard(Board board) {
        boardMapper.updateBoard(board);
    }


	public void removeBoard(long id) {
		 boardMapper.removeBoard(id);
		
	}

	


	
}
