package org.khasanof.auth_service.autoMock.MockMvc;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

public interface MockMvcDelete {

    MvcResult obsessiveDelete(String path) throws Exception;

    MvcResult obsessiveDelete(String path, List<Object> variables) throws Exception;

    MvcResult obsessiveDelete(String path, List<Object> variables, LinkedMultiValueMap<String, String> params) throws Exception;

    MvcResult obsessiveDelete(String path, LinkedMultiValueMap<String, String> params) throws Exception;

    void obsessiveDelete(String path, ResultMatcher matcher) throws Exception;

    void obsessiveDelete(String path, ResultMatcher matcher, ResultHandler handler) throws Exception;

    void obsessiveDelete(String path, LinkedMultiValueMap<String, String> multiValueMap, List<ResultMatcher> matchers, ResultHandler handler) throws Exception;

    void obsessiveDelete(String path, List<ResultMatcher> matchers, ResultHandler handler) throws Exception;

    void obsessiveDelete(String path, Object variable, List<ResultMatcher> matchers, ResultHandler handler) throws Exception;

    void obsessiveDelete(String path, List<Object> variables, List<ResultMatcher> matchers, ResultHandler handler) throws Exception;


}
