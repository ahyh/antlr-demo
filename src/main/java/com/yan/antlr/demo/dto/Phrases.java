package com.yan.antlr.demo.dto;

import lombok.Data;
import org.elasticsearch.index.query.BoolQueryBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
public class Phrases {

    /**
     * 0- keyword
     * 1- phrases, levels
     */
    private int type;

    /**
     * if type is 0, this field is the keyword
     * if type is 1, this field is empty
     */
    private String keyword;

    /**
     * this boolean value is for trim high light
     */
    private boolean match;

    /**
     * logics between phrases
     */
    private List<String> logics;

    private BoolQueryBuilder boolQueryBuilder;

    private List<Phrases> children;

    public void addChild( Phrases phrases) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(phrases);
    }

    public void addLogic(String logic) {
        if (this.logics == null) {
            this.logics = new ArrayList<>();
        }
        this.logics.add(logic);
    }

}
