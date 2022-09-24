package org.khasanof.upload_service.upload;

import org.khasanof.upload_service.utils.BaseUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/upload/*")
public class UploadController {
}
