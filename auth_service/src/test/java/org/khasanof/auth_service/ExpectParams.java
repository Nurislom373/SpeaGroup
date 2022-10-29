package org.khasanof.auth_service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ExpectParams {
    private List<ResultMatcher> matchers;
    private List<ResultHandler> handlers;
}
