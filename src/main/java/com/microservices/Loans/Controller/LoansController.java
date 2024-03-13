package com.microservices.Loans.Controller;

import com.microservices.Loans.Constants.LoansConstants;
import com.microservices.Loans.DTO.ErrorResponseDTO;
import com.microservices.Loans.DTO.LoansDTO;
import com.microservices.Loans.DTO.ResponseDTO;
import com.microservices.Loans.Service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Tag(
        name = "CRUD REST APIs for Loans in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE loan details"
)
@RestController
@Validated
@RequestMapping(path = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class LoansController {
    @Autowired
    private ILoansService iLoansService;
    @Operation(
            summary = "Create Rest API for Loans controller",
            description = "Rest API to create new Loans EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Http Status Created"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema=@Schema(
                                    implementation = ErrorResponseDTO.class
                            )
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createLoans(@RequestParam
                                                       @Pattern(regexp = "(^$|[0-9]{10})",message = "mobile number should be 10 digits")
                                                       String mobileNumber){
            iLoansService.createLoans(mobileNumber);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(LoansConstants.STATUS_201,LoansConstants.MESSAGE_201));
    }
    @Operation(
            summary = "Fetch Loan Details REST API",
            description = "REST API to fetch loan details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @GetMapping("/fetch")
    public ResponseEntity<LoansDTO> fetchLoanDetails(@RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{10})",message = "mobile number should be 10 digits")
                                                         String mobileNumber){
        LoansDTO loansDTO=iLoansService.fetchLoanDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(loansDTO);

    }
    @Operation(
            summary = "Update Loan Details REST API",
            description = "REST API to update loan details based on a loan number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateLoans(@RequestBody @Valid LoansDTO loansDTO){
        boolean isUpdated=iLoansService.updateLoans(loansDTO);
        if(isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_UPDATE));
        }
    }
    @Operation(
            summary = "Delete Loan Details REST API",
            description = "REST API to delete Loan details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteLoans(@RequestParam
                                                       @Pattern(regexp = "(^$|[0-9]{10})",message = "mobile number should be 10 digits")
                                                       String mobileNumber){
        boolean isDeleted=iLoansService.deleteLoans(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_DELETE));
        }
    }
}
