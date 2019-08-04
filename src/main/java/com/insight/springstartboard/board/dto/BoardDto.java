package com.insight.springstartboard.board.dto;

import lombok.Data;

@Data
public class BoardDto {
    // Lombok으로 getter/setter, toString, hashCode, equals 생성
    private int boardIdx;

    private String title;

    private String contents;

    private int hitCnt;

    private String creatorId;

    private String createdDatetime;

    private String updaterId;

    private String updatedDatetime;
}
