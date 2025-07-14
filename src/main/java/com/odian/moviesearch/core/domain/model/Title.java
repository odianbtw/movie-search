package com.odian.moviesearch.core.domain.model;


import lombok.Data;

@Data
public abstract class Title {
    private final Id titleId;
    private String title;
    private TitleInfo titleInfo;
    private Float score;

    public Title(Id titleId, String title, TitleInfo titleInfo, Float score) {
        if (score < 0.0f) throw new IllegalArgumentException("Movie score must greater than 0");
        this.titleId = titleId;
        this.title = title;
        this.titleInfo = titleInfo;
        this.score = score;
    }
}
