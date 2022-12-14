package org.khasanof.question_service.controller.question_report;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.khasanof.question_service.controller.AbstractController;
import org.khasanof.question_service.criteria.question_report.QuestionReportCriteria;
import org.khasanof.question_service.dto.question_report.QuestionReportCreateDTO;
import org.khasanof.question_service.dto.question_report.QuestionReportDetailDTO;
import org.khasanof.question_service.dto.question_report.QuestionReportGetDTO;
import org.khasanof.question_service.response.ApplicationError;
import org.khasanof.question_service.response.Data;
import org.khasanof.question_service.service.question_report.QuestionReportService;
import org.khasanof.question_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/26/2022
 * <br/>
 * Time: 7:13 PM
 * <br/>
 * Package: org.khasanof.question_service.controller.question_report
 */
@RestController
@RequestMapping(value = BaseUtils.PATH + "/question_report/*")
public class QuestionReportController extends AbstractController<QuestionReportService> {

    public QuestionReportController(QuestionReportService service) {
        super(service);
    }

    @Operation(
            summary = "Question Report Create API",
            description = "This api is used to report questions that users don't like or that are written incorrectly."
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully Created - Question Report",
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
                    description = "Question Report Not Found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationError.class))
                    }
            )
    })
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> create(@Valid @RequestBody QuestionReportCreateDTO dto) {
        service.create(dto);
        return new ResponseEntity<>(new Data<>("Successfully Created - Question Report"), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Question Report Delete API",
            description = "This api is used to delete a question. Only admins with question owner can delete this api."
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Successfully Deleted - Question Report",
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
                    description = "Question Report Not Found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationError.class))
                    }
            )
    })
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Question Report"), HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Question Report Get API",
            description = "This API is used to get a question report."
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully Received - Question Report",
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
                    description = "Question Report Not Found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationError.class))
                    }

            )
    })
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<QuestionReportGetDTO>> get(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }

    @Operation(
            summary = "Question Report Detail API",
            description = "This API is used to get detail a question report."
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully Received - Question Report",
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
                    description = "Question Report Not Found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationError.class))
                    }
            )
    })
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<QuestionReportDetailDTO>> detail(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.detail(id)), HttpStatus.OK);
    }

    @Operation(
            summary = "Question Report List API",
            description = "This API is used to get list a question report."
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully Received - Question Report",
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
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<Data<List<QuestionReportGetDTO>>> list(@Valid QuestionReportCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.list(criteria)), HttpStatus.OK);
    }

}
