package org.khasanof.auth_service.autoMock.MockMvc;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

public interface MockMvcPost {

    MvcResult obsessivePost(String path, Object value) throws Exception;

    MvcResult obsessivePost(String path, Object value, MediaType mediaType) throws Exception;

    void obsessivePost(String path, Object value, ResultMatcher matcher) throws Exception;

    void obsessivePost(String path, Object value, MediaType mediaType, ResultMatcher matcher) throws Exception;

    void obsessivePost(String path, Object value, ResultMatcher matcher, ResultHandler handler) throws Exception;

    void obsessivePost(String path, Object value, MediaType mediaType, ResultMatcher matcher, ResultHandler handler) throws Exception;

    void obsessivePost(String path, Object value, List<ResultMatcher> matchers, ResultHandler handler) throws Exception;

    void obsessivePost(String path, Object value, MediaType mediaType, List<ResultMatcher> matchers, ResultHandler handler) throws Exception;

}
