package org.khasanof.question_service.controller.question_category;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.khasanof.question_service.controller.AbstractController;
import org.khasanof.question_service.criteria.question_category.QuestionCategoryCriteria;
import org.khasanof.question_service.dto.question_category.*;
import org.khasanof.question_service.response.ApplicationError;
import org.khasanof.question_service.response.Data;
import org.khasanof.question_service.service.question_category.QuestionCategoryService;
import org.khasanof.question_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/7/2022
 * <br/>
 * Time: 11:08 PM
 * <br/>
 * Package: org.khasanof.question_service.controller.question_category
 */
@RestController
@RequestMapping(value = BaseUtils.PATH + "/question_category/*")
public class QuestionCategoryController extends AbstractController<QuestionCategoryService> {

    public QuestionCategoryController(QuestionCategoryService service) {
        super(service);
    }


    @Operation(
            summary = "Question Category Create API",
            description = "This API is used to add a category to a question."
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully Added Category - Question Category",
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
                    description = "Question Category Not Found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationError.class))
                    }
            )
    })
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> create(@Valid @RequestBody QuestionCategoryCreateDTO dto) {
        service.create(dto);
        return new ResponseEntity<>(new Data<>("Successfully Created - Question Category"), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Question Category Add Category API",
            description = "This API is used to add a category to a question."
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully Added Category - Question Category",
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
                    description = "Question Category Not Found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationError.class))
                    }
            )
    })
    @RequestMapping(value = "addCategory", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> addCategory(@Valid @RequestBody QuestionCategoryAddDTO dto) {
        service.addCategory(dto);
        return new ResponseEntity<>(new Data<>("Successfully Added Category - Question Category"), HttpStatus.OK);
    }

    @Operation(
            summary = "Question Category Delete Category API",
            description = "This API is used to delete a question category."
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully Deleted Category - Question Category",
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
                    description = "Question Category Not Found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationError.class))
                    }
            )
    })
    @RequestMapping(value = "deleteCategory", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> deleteCategory(@Valid @RequestBody QuestionCategoryDeleteDTO dto) {
        service.deleteCategory(dto);
        return new ResponseEntity<>(new Data<>("Successfully Deleted Category - Question Category"), HttpStatus.OK);
    }

    @Operation(
            summary = "Question Category Get API",
            description = "This API is used to get a question category."
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully Received - Question Category",
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
                    description = "Question Category Not Found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationError.class))
                    }
            )
    })
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<QuestionCategoryGetDTO>> get(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }

    @Operation(
            summary = "Question Category Detail API",
            description = "This API is used to get detail a question category."
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully Received - Question Category",
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
                    description = "Question Category Not Found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationError.class))
                    }
            )
    })
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<QuestionCategoryDetailDTO>> detail(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.detail(id)), HttpStatus.OK);
    }

    @Operation(
            summary = "Question Category List API",
            description = "This API is used to get list a question category."
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully Received - Question Category",
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
    public ResponseEntity<Data<List<QuestionCategoryGetDTO>>> list(@Valid QuestionCategoryCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.list(criteria)), HttpStatus.OK);
    }
}
