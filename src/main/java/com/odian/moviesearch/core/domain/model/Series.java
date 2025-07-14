package com.odian.moviesearch.core.domain.model;

import java.util.Set;

public class Series extends Title {

    private Set <Season> seasons;

    public Series(Id titleId, String title, TitleInfo titleInfo, Float score) {
        super(titleId, title, titleInfo, score);
    }
}
