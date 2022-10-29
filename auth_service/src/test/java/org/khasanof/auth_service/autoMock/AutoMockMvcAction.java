package org.khasanof.auth_service.autoMock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.util.ExceptionCollector;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

@Service
public class AutoMockMvcAction implements AutoMockMvc {

    @Autowired
    private MockMvc mockMvc;

    @Override
    public MvcResult obsessiveGet(String path) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andReturn();
    }

    @Override
    public MvcResult obsessiveGet(String path, List<Object> variables) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(path, variables))
                .andReturn();
    }

    @Override
    public MvcResult obsessiveGet(String path, List<Object> variables, LinkedMultiValueMap<String, String> params) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(path, variables)
                        .params(params))
                .andReturn();
    }

    @Override
    public MvcResult obsessiveGet(String path, LinkedMultiValueMap<String, String> multiValueMap) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(path)
                        .params(multiValueMap))
                .andReturn();
    }

    @Override
    public void obsessiveGet(String path, ResultMatcher matcher) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andExpect(matcher);
    }

    @Override
    public void obsessiveGet(String path, ResultMatcher matcher, ResultHandler handler) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andExpect(matcher)
                .andDo(handler);
    }

    @Override
    public void obsessiveGet(String path, LinkedMultiValueMap<String, String> multiValueMap, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path).params(multiValueMap))
                .andDo(handler);
        matchersAction(resultActions, matchers);
    }

    @Override
    public void obsessiveGet(String path, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andDo(handler);
        matchersAction(resultActions, matchers);
    }

    @Override
    public void obsessiveGet(String path, Object variable, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path, variable))
                .andDo(handler);
        matchersAction(resultActions, matchers);
    }

    @Override
    public void obsessiveGet(String path, List<Object> variables, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path, variables))
                .andDo(handler);
        matchersAction(resultActions, matchers);
    }

    private void matchersAction(ResultActions actions, List<ResultMatcher> matchers) throws Exception {
        ExceptionCollector exceptionCollector = new ExceptionCollector();
        matchers.forEach(match -> {
            exceptionCollector.execute(() -> actions.andExpect(match));
        });
        exceptionCollector.assertEmpty();
    }


}
