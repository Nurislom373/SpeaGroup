package org.khasanof.auth_service.autoMock;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

public interface AutoMockMvc {

    MvcResult obsessiveGet(String path) throws Exception;

    MvcResult obsessiveGet(String path, List<Object> variables) throws Exception;

    MvcResult obsessiveGet(String path, List<Object> variables, LinkedMultiValueMap<String, String> params) throws Exception;

    MvcResult obsessiveGet(String path, LinkedMultiValueMap<String, String> params) throws Exception;

    void obsessiveGet(String path, ResultMatcher matcher) throws Exception;

    void obsessiveGet(String path, ResultMatcher matcher, ResultHandler handler) throws Exception;

    void obsessiveGet(String path, LinkedMultiValueMap<String, String> multiValueMap, List<ResultMatcher> matchers, ResultHandler handler) throws Exception;

    void obsessiveGet(String path, List<ResultMatcher> matchers, ResultHandler handler) throws Exception;

    void obsessiveGet(String path, Object variable, List<ResultMatcher> matchers, ResultHandler handler) throws Exception;

    void obsessiveGet(String path, List<Object> variables, List<ResultMatcher> matchers, ResultHandler handler) throws Exception;

}
