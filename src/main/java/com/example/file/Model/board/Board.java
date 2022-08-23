package com.example.file.Model.board;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.Data;

@Data
public class Board {
    private long id;
    private String title;
    private String content;
    private Long hit;
    private String member_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime inputTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updateTime;

    public void addHit() {
        hit += 1;
    }
    public UpdataBoardForm toUpdateForm() {
        UpdataBoardForm updateBoardForm = new UpdataBoardForm();
        updateBoardForm.setId(id);
        updateBoardForm.setTitle(title);
        updateBoardForm.setContent(content);
        return updateBoardForm;
    }
}
