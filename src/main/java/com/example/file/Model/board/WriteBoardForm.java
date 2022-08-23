package com.example.file.Model.board;


import javax.validation.constraints.NotBlank;


import lombok.Data;

@Data
public class WriteBoardForm {
		@NotBlank
        private String title;
		@NotBlank
        private String content;
		
		public Board toBoard() {
			Board board = new Board();
			board.setTitle(title);
			board.setContent(content);
			return board;
		}
}
