package com.yan.antlr.demo.service;

import com.yan.antlr.demo.dto.Phrases;
import com.yan.antlr.demo.dto.UserDto;
import com.yan.antlr.demo.utils.JacksonUtil;
import com.yan.antlr.demo.utils.TrackerUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserTrackerServiceImpl implements UserTrackerService {

    private static final String INDEX_NAME = "test_user";

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @Override
    public List<UserDto> listUserByTracker(String input) {
        if (StringUtils.isBlank(input)) {
            return null;
        }
        try {
            Phrases phrases = TrackerUtil.parse(input);
            TrackerUtil.parsePhrase(phrases);
            TrackerUtil.parseQueryBuilder(phrases);
            BoolQueryBuilder boolQueryBuilder = phrases.getBoolQueryBuilder();

            // hight light keyword
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("desc");
            highlightBuilder.preTags("<em>");
            highlightBuilder.postTags("</em>");
            highlightBuilder.highlightQuery(boolQueryBuilder);

            SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
            searchRequest.searchType(SearchType.DEFAULT);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(boolQueryBuilder).highlighter(highlightBuilder);

            searchRequest.source(searchSourceBuilder);
            SearchResponse response = restHighLevelClient.search(searchRequest, getRequestOptions());

            if (response == null) {
                return null;
            }
            SearchHit[] hits = response.getHits().getHits();
            if (hits == null || hits.length == 0) {
                return null;
            }
            List<UserDto> list = new ArrayList<>();
            for (SearchHit hit : hits) {
                UserDto userDto = JacksonUtil.fromJson(hit.getSourceAsString(), UserDto.class);
                Text[] descs = hit.getHighlightFields().get("desc").getFragments();
                List<String> highLightDescs = new ArrayList<>();
                for (Text text:descs) {
                    highLightDescs.add(text.toString());
                }
                userDto.setHighLightDescs(highLightDescs);
                list.add(userDto);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public RequestOptions getRequestOptions() {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        builder.addHeader("business", INDEX_NAME);
        return builder.build();
    }
}
