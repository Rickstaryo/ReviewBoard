package com.example.file.Model.board;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdataBoardForm {
    private long id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    public Board toBoard() {
        Board board = new Board();
        board.setId(id);
        board.setTitle(title);
        board.setContent(content);
        return board;
    }
}
