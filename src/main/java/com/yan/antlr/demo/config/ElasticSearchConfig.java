package com.yan.antlr.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Configuration
public class ElasticSearchConfig {

    @Value("${es.host:127.0.0.1:9200}")
    private String esHost;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestClientBuilder restClientBuilder = getRestClientBuilder();
        return new RestHighLevelClient(restClientBuilder);
    }

    public RestClientBuilder getRestClientBuilder() {
        RestClientBuilder builder = RestClient.builder(HttpHost.create(esHost));
        builder.setHttpClientConfigCallback(httpAsyncClientBuilder -> {
            httpAsyncClientBuilder.setMaxConnTotal(15000);
            httpAsyncClientBuilder.setMaxConnPerRoute(15000);
            return httpAsyncClientBuilder;
        });

        builder.setRequestConfigCallback(x -> x.setConnectTimeout(100)
                .setSocketTimeout(100));

        return builder;
    }

}
