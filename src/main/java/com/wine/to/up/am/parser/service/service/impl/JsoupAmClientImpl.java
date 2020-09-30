package com.wine.to.up.am.parser.service.service.impl;

import com.wine.to.up.am.parser.service.service.AmClient;
import com.wine.to.up.am.parser.service.service.AmParserService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : SSyrova
 * @since : 29.09.2020, вт
 **/
@Component
@Slf4j
public class JsoupAmClientImpl implements AmClient {

    @Value(value = "${am.site.base-url}")
    private String baseUrl;

    @Value(value = "${am.site.user-agent}")
    private String userAgent;

    @Value(value = "${am.site.referrer}")
    private String referrer;

    @Override
    public Document getPage(Long page) {
        return getPage(baseUrl + "?page=" + page);
    }

    private Document getPage(String url) {
        try {
            return Jsoup
                    .connect(url)
                    .userAgent(userAgent)
                    .referrer(referrer)
                    .get();
        } catch (IOException e) {
            log.error("Cannot get document by '{}' url with exception: {}", url, e.getMessage());
            return null;
        }
    }

    @Override
    public Document getMainPage() {
        return getPage(baseUrl);
    }
}
