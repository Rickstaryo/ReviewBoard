package com.example.file.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.file.Model.board.Board;

@Mapper
public interface BoardMapper {

	public List<Board> findAllBoards();

	public int saveBoard(Board board);

	public Board findBoardById(long id);

	public int updateBoard(Board findBoard);

	public int removeBoard(long id);



}
