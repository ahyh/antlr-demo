package com.yan.antlr.demo.service;

import com.yan.antlr.demo.dto.Phrases;
import com.yan.antlr.demo.dto.User;
import com.yan.antlr.demo.dto.UserDto;
import com.yan.antlr.demo.utils.JacksonUtil;
import com.yan.antlr.demo.utils.TrackerUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private static final String INDEX_NAME = "test_user";

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public boolean insert(User user) {
        IndexRequest indexRequest = new IndexRequest(INDEX_NAME);
        indexRequest.id(String.valueOf(user.getId()));
        indexRequest.source(JacksonUtil.toJson(user), XContentType.JSON);
        indexRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);
        try {
            IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            return response != null && response.status().getStatus() < 300;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            DeleteRequest deleteRequest = new DeleteRequest();
            deleteRequest.index(INDEX_NAME);
            deleteRequest.id(String.valueOf(id));
            deleteRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);
            DeleteResponse response = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            return response != null;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserById(int id) {
        try {
            SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
            searchRequest.searchType(SearchType.DEFAULT);

            TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("id", id);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(termQueryBuilder);
            searchRequest.source(searchSourceBuilder);

            SearchResponse response = restHighLevelClient.search(searchRequest, getRequestOptions());
            if (response == null) {
                return null;
            }
            SearchHit hit = response.getHits().getHits()[0];
            String sourceAsString = hit.getSourceAsString();
            return JacksonUtil.fromJson(sourceAsString, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> listAll() {
        try {
            SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
            searchRequest.searchType(SearchType.DEFAULT);
            SearchResponse response = restHighLevelClient.search(searchRequest, getRequestOptions());
            if (response == null) {
                return null;
            }
            SearchHit[] hits = response.getHits().getHits();
            List<User> list = new ArrayList<>();
            for (SearchHit hit:hits) {
                String sourceAsString = hit.getSourceAsString();
                list.add(JacksonUtil.fromJson(sourceAsString, User.class)) ;
            }
            return list;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据单个keyword匹配，带高亮keyword
     */
    @Override
    public List<UserDto> listByKeyword(String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return null;
        }
        try {
            SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
            searchRequest.searchType(SearchType.DEFAULT);

            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("desc");
            highlightBuilder.preTags("<em>");
            highlightBuilder.postTags("</em>");

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.should(QueryBuilders.matchPhraseQuery("desc", keyword));
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

    @Override
    public List<User> listByOr(List<String> keywords, List<String> ignoreKeywords) {
        if (CollectionUtils.isEmpty(keywords)) {
            return null;
        }
        try {
            SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
            searchRequest.searchType(SearchType.DEFAULT);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            for (String keyword : keywords) {
                boolQueryBuilder.should(QueryBuilders.matchPhraseQuery("desc", keyword));
            }
            if (CollectionUtils.isNotEmpty(ignoreKeywords)) {
                BoolQueryBuilder mustNotBoolQueryBuilder = QueryBuilders.boolQuery();
                for (String ignoreKeyword : ignoreKeywords) {
                    mustNotBoolQueryBuilder.should(QueryBuilders.matchPhraseQuery("desc", ignoreKeyword));
                }
                mustNotBoolQueryBuilder.minimumShouldMatch(ignoreKeywords.size());
                boolQueryBuilder.mustNot(mustNotBoolQueryBuilder);
            }
            boolQueryBuilder.minimumShouldMatch(1);
            searchSourceBuilder.query(boolQueryBuilder);
            searchRequest.source(searchSourceBuilder);

            SearchResponse response = restHighLevelClient.search(searchRequest, getRequestOptions());
            if (response == null) {
                return null;
            }
            SearchHit[] hits = response.getHits().getHits();
            if (hits == null || hits.length == 0) {
                return null;
            }
            List<User> list = new ArrayList<>();
            for (SearchHit hit : hits) {
                list.add(JacksonUtil.fromJson(hit.getSourceAsString(), User.class));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> listByAnd(List<String> keywords, List<String> ignoreKeywords) {
        if (CollectionUtils.isEmpty(keywords)) {
            return null;
        }
        try {
            SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
            searchRequest.searchType(SearchType.DEFAULT);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            for (String keyword : keywords) {
                boolQueryBuilder.should(QueryBuilders.matchPhraseQuery("desc", keyword));
            }
            if (CollectionUtils.isNotEmpty(ignoreKeywords)) {
                BoolQueryBuilder mustNotBoolQueryBuilder = QueryBuilders.boolQuery();
                for (String ignoreKeyword : ignoreKeywords) {
                    mustNotBoolQueryBuilder.should(QueryBuilders.matchPhraseQuery("desc", ignoreKeyword));
                }
                mustNotBoolQueryBuilder.minimumShouldMatch(ignoreKeywords.size());
                boolQueryBuilder.mustNot(mustNotBoolQueryBuilder);
            }
            boolQueryBuilder.minimumShouldMatch(keywords.size());
            searchSourceBuilder.query(boolQueryBuilder);
            searchRequest.source(searchSourceBuilder);

            SearchResponse response = restHighLevelClient.search(searchRequest, getRequestOptions());
            if (response == null) {
                return null;
            }
            SearchHit[] hits = response.getHits().getHits();
            if (hits == null || hits.length == 0) {
                return null;
            }
            List<User> list = new ArrayList<>();
            for (SearchHit hit : hits) {
                list.add(JacksonUtil.fromJson(hit.getSourceAsString(), User.class));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> listByInput(String input) {
        if (StringUtils.isBlank(input)) {
            return null;
        }
        try {
            Phrases phrases = TrackerUtil.parse(input);
            TrackerUtil.parsePhrase(phrases);
            TrackerUtil.parseQueryBuilder(phrases);
            BoolQueryBuilder boolQueryBuilder = phrases.getBoolQueryBuilder();

            SearchRequest searchRequest = new SearchRequest(INDEX_NAME );
            searchRequest.searchType(SearchType.DEFAULT);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(boolQueryBuilder);
            searchRequest.source(searchSourceBuilder);

            SearchResponse response = restHighLevelClient.search(searchRequest, getRequestOptions());

            if (response == null) {
                return null;
            }
            SearchHit[] hits = response.getHits().getHits();
            if (hits == null || hits.length == 0) {
                return null;
            }
            List<User> list = new ArrayList<>();
            for (SearchHit hit : hits) {
                list.add(JacksonUtil.fromJson(hit.getSourceAsString(), User.class));
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
