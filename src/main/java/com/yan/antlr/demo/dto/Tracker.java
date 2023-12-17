package com.yan.antlr.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tracker {

    @JsonProperty("keywords")
    private List<String> keywords;

    @JsonProperty("has_duplicate_keyword")
    private boolean hasDuplicateKeyword;

    @JsonProperty("has_different_logic_in_same_paren")
    private boolean hasDifferentLogicInSameParen;

    @JsonProperty("phrases")
    private Phrases phrases;
}
