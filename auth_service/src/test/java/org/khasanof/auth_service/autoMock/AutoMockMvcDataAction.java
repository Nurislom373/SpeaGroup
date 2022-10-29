package org.khasanof.auth_service.autoMock;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.util.ExceptionCollector;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AutoMockMvcDataAction implements AutoMockMvcData {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public MvcResult obsessivePost(String path, Object value) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(value)))
                .andReturn();
    }

    @Override
    public MvcResult obsessivePost(String path, Object value, MediaType mediaType) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .contentType(mediaType)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(value)))
                .andReturn();
    }

    @Override
    public void obsessivePost(String path, Object value, ResultMatcher matcher) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(value)))
                .andExpect(matcher);
    }

    @Override
    public void obsessivePost(String path, Object value, MediaType mediaType, ResultMatcher matcher) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .contentType(mediaType)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(value)))
                .andExpect(matcher);
    }

    @Override
    public void obsessivePost(String path, Object value, ResultMatcher matcher, ResultHandler handler) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(value)))
                .andExpect(matcher)
                .andDo(handler);
    }

    @Override
    public void obsessivePost(String path, Object value, MediaType mediaType, ResultMatcher matcher, ResultHandler handler) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .contentType(mediaType)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(value)))
                .andExpect(matcher)
                .andDo(handler);
    }

    /*
    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(value)))
                .andDo(handler);
        matchersAction(resultActions, matchers);
     */

    @Override
    public void obsessivePost(String path, Object value, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(value)))
                .andDo(handler);
        matchersAction(resultActions, matchers);
    }

    @Override
    public void obsessivePost(String path, Object value, MediaType mediaType, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .contentType(mediaType)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(value)))
                .andDo(handler);
        matchersAction(resultActions, matchers);
    }

    @Override
    public MvcResult obsessiveDelete(String path) throws Exception {
        return obsessive(MethodType.DELETE, path,
                MediaType.APPLICATION_JSON, null, null, null, null);
    }

    @Override
    public MvcResult obsessiveDelete(String path, List<Object> variables) throws Exception {
        return obsessive(MethodType.DELETE, path,
                MediaType.APPLICATION_JSON, variables, null, null, null);
    }

    @Override
    public MvcResult obsessiveDelete(String path, List<Object> variables, LinkedMultiValueMap<String, String> params) throws Exception {
        return obsessive(MethodType.DELETE, path,
                MediaType.APPLICATION_JSON, variables, params, null, null);
    }

    @Override
    public MvcResult obsessiveDelete(String path, LinkedMultiValueMap<String, String> params) throws Exception {
        return obsessive(MethodType.DELETE, path,
                MediaType.APPLICATION_JSON, null, params, null, null);
    }

    @Override
    public void obsessiveDelete(String path, ResultMatcher matcher) throws Exception {
        obsessive(MethodType.DELETE, path,
                MediaType.APPLICATION_JSON, null, null, List.of(matcher), null);
    }

    @Override
    public void obsessiveDelete(String path, ResultMatcher matcher, ResultHandler handler) throws Exception {
        obsessive(MethodType.DELETE, path,
                MediaType.APPLICATION_JSON, null, null, List.of(matcher), handler);
    }

    @Override
    public void obsessiveDelete(String path, LinkedMultiValueMap<String, String> multiValueMap, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        obsessive(MethodType.DELETE, path,
                MediaType.APPLICATION_JSON, null, multiValueMap, matchers, handler);
    }

    @Override
    public void obsessiveDelete(String path, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        obsessive(MethodType.DELETE, path,
                MediaType.APPLICATION_JSON, null, null, matchers, handler);
    }

    @Override
    public void obsessiveDelete(String path, Object variable, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        obsessive(MethodType.DELETE, path,
                MediaType.APPLICATION_JSON, List.of(variable), null, matchers, handler);
    }

    @Override
    public void obsessiveDelete(String path, List<Object> variables, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        obsessive(MethodType.DELETE, path,
                MediaType.APPLICATION_JSON, variables, null, matchers, handler);
    }

    public enum MethodType {
        POST,
        DELETE,
        GET,
        PUT;
    }

    private MvcResult obsessive(MethodType type, String path, MediaType mediaType, List<Object> variables, LinkedMultiValueMap<String, String> params, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        ResultActions resultActions = obsessiveType(type, path, mediaType, variables, params);
        if (Objects.nonNull(handler)) {
            resultActions.andDo(handler);
        }
        if (Objects.nonNull(matchers)) {
            matchersAction(resultActions, matchers);
        }
        return resultActions.andReturn();
    }

    private ResultActions obsessiveType(MethodType type, String path, MediaType mediaType, List<Object> variables, LinkedMultiValueMap<String, String> params) throws Exception {
        if (Objects.isNull(params)) {
            return mockMvc.perform(getMethod(type, path, variables)
                    .contentType(mediaType));
        }
        return mockMvc.perform(getMethod(type, path, variables)
                .contentType(mediaType)
                .params(params));
    }

    private MockHttpServletRequestBuilder getMethod(MethodType type, String path, List<Object> variables) {
        if (Objects.isNull(variables)) {
            return switch (type) {
                case POST -> MockMvcRequestBuilders.post(path);
                case DELETE -> MockMvcRequestBuilders.delete(path);
                case GET -> MockMvcRequestBuilders.get(path);
                case PUT -> MockMvcRequestBuilders.put(path);
            };
        } else {
            return switch (type) {
                case POST -> MockMvcRequestBuilders.post(path);
                case DELETE -> MockMvcRequestBuilders.delete(path, variables);
                case GET -> MockMvcRequestBuilders.get(path, variables);
                case PUT -> MockMvcRequestBuilders.put(path, variables);
            };
        }
    }

    private void matchersAction(ResultActions actions, List<ResultMatcher> matchers) throws Exception {
        ExceptionCollector exceptionCollector = new ExceptionCollector();
        matchers.forEach(match -> {
            exceptionCollector.execute(() -> actions.andExpect(match));
        });
        exceptionCollector.assertEmpty();
    }
}
