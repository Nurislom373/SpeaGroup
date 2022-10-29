package org.khasanof.auth_service;

import org.springframework.stereotype.Component;
import org.springframework.test.util.ExceptionCollector;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

@Component
public class AutoMockMvcAction implements AutoMockMvc {

    @Override
    public MvcResult obsessiveGet(MockMvc mockMvc, String path) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andReturn();
    }

    @Override
    public MvcResult obsessiveGet(MockMvc mockMvc, String path, List<Object> variables) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(path, variables))
                .andReturn();
    }

    @Override
    public void obsessiveGet(MockMvc mockMvc, String path, ResultMatcher matcher) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andExpect(matcher);
    }

    @Override
    public void obsessiveGet(MockMvc mockMvc, String path, ResultMatcher matcher, ResultHandler handler) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andExpect(matcher)
                .andDo(handler);
    }

    @Override
    public void obsessiveGet(MockMvc mockMvc, String path, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andDo(handler);
        matchersAction(resultActions, matchers);
    }

    @Override
    public void obsessiveGet(MockMvc mockMvc, String path, Object variable, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path, variable))
                .andDo(handler);
        matchersAction(resultActions, matchers);
    }

    @Override
    public void obsessiveGet(MockMvc mockMvc, String path, List<Object> variables, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
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
