package org.khasanof.auth_service;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

public interface AutoMockMvc {

    MvcResult obsessiveGet(MockMvc mockMvc, String path) throws Exception;

    MvcResult obsessiveGet(MockMvc mockMvc, String path, List<Object> variables) throws Exception;

    void obsessiveGet(MockMvc mockMvc, String path, ResultMatcher matcher) throws Exception;

    void obsessiveGet(MockMvc mockMvc, String path, ResultMatcher matcher, ResultHandler handler) throws Exception;

    void obsessiveGet(MockMvc mockMvc, String path, List<ResultMatcher> matchers, ResultHandler handler) throws Exception;

    void obsessiveGet(MockMvc mockMvc, String path, Object variable, List<ResultMatcher> matchers, ResultHandler handler) throws Exception;

    void obsessiveGet(MockMvc mockMvc, String path, List<Object> variables, List<ResultMatcher> matchers, ResultHandler handler) throws Exception;

}
