package org.khasanof.question_service.controller.question_view;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.khasanof.question_service.controller.AbstractController;
import org.khasanof.question_service.criteria.question_view.QuestionViewCriteria;
import org.khasanof.question_service.dto.question_view.QuestionViewCreateDTO;
import org.khasanof.question_service.dto.question_view.QuestionViewDetailDTO;
import org.khasanof.question_service.dto.question_view.QuestionViewGetDTO;
import org.khasanof.question_service.response.ApplicationError;
import org.khasanof.question_service.response.Data;
import org.khasanof.question_service.service.question_view.QuestionViewService;
import org.khasanof.question_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/1/2023
 * <br/>
 * Time: 7:54 PM
 * <br/>
 * Package: org.khasanof.question_service.controller.quesion_view
 */
@RestController
@RequestMapping(value = BaseUtils.PATH + "/question_view/*")
public class QuestionViewController extends AbstractController<QuestionViewService> {

    public QuestionViewController(QuestionViewService service) {
        super(service);
    }

    @Operation(
            summary = "Question View Create API",
            description = "This API is used to create row from table to query view. If it is created, we can use it to update it."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully Created - Question View",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Data.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid Validation Exception",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationError.class))
                    }
            )
    })
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> create(@Valid @RequestBody QuestionViewCreateDTO dto) {
        service.create(dto);
        return new ResponseEntity<>(new Data<>("Successfully Created - Question View"), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Question View Delete API",
            description = "This API is used to delete a row belonging to a view from the question view table."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Successfully Deleted - Question View",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Data.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid Validation Exception",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationError.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Question View Not Found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationError.class))
                    }
            )
    })
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Question View"), HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Question View Get API",
            description = "This API is used to get a row belonging to a view from the question view table."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully Get - Question View",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Data.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid Validation Exception",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationError.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Question View Not Found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationError.class))
                    }
            )
    })
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<QuestionViewGetDTO>> get(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }

    @Operation(
            summary = "Question View Detail API",
            description = "We can get this API question view detail."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully Get - Question View",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Data.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid Validation Exception",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationError.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Question View Not Found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationError.class))
                    }
            )
    })
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<QuestionViewDetailDTO>> detail(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.detail(id)), HttpStatus.OK);
    }

    @Operation(
            summary = "Question View List API",
            description = "This API is used to get a list of question views."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully Get - Question View",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Data.class))
                    }
            )
    })
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<Data<List<QuestionViewGetDTO>>> list(@Valid QuestionViewCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.list(criteria)), HttpStatus.OK);
    }
}
