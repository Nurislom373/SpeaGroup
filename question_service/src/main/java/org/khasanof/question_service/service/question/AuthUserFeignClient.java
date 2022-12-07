package org.khasanof.question_service.service.question;

import org.khasanof.question_service.dto.auth_user.AuthUserGetDTO;
import org.khasanof.question_service.response.Data;
import org.khasanof.question_service.utils.BaseUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/6/2022
 * <br/>
 * Time: 8:44 PM
 * <br/>
 * Package: org.khasanof.question_service.service.question
 */
@FeignClient(name = "AuthUserFeignClient", url = "http://localhost:8800", path = BaseUtils.PATH + "/auth_user")
public interface AuthUserFeignClient {

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Data<AuthUserGetDTO> get(@PathVariable String id) throws Exception;

}
