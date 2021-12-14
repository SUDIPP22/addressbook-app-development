package com.bridgelabz.exception.global;


import com.bridgelabz.dto.ResponseDTO;
import com.bridgelabz.exception.custom.AddressBookCustomException;
import com.bridgelabz.exception.custom.AddressBookNotFoundException;
import com.bridgelabz.exception.custom.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Purpose : This class is major class for handling the all exception which can be thrown
 * while the application is running in the server side, as this is the global exception handler
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-13
 */
@ControllerAdvice
public class AddressBookGlobalExceptionHandler {

    /**
     * Purpose : This method is created for handle the global exception which can occur while running the application
     *
     * @param exception : this parameter will be the exception which will going to help
     *                  for responding back if exception occurs
     * @return : the new response entity which will holds the response DTO
     * that consists the exception message and Http status
     */
    @ExceptionHandler({AddressBookCustomException.class})
    public ResponseEntity<ResponseDTO> handleEmployeeCustomException(AddressBookCustomException exception) {
        ResponseDTO responseDto = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return new ResponseEntity<>(responseDto, responseDto.getHttpStatus());
    }

    /**
     * Purpose : This method is created for handle the global exception which can occur while running the application
     *
     * @param exception : this parameter will be the exception which will going to help
     *                  for responding back if exception occurs
     * @return : the new response entity which will holds the response DTO
     * that consists the exception message and Http status
     */
    @ExceptionHandler({AddressBookNotFoundException.class})
    public ResponseEntity<ResponseDTO> handleEmployeeNotFoundException(AddressBookNotFoundException exception) {
        ResponseDTO responseDto = new ResponseDTO(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(responseDto, responseDto.getHttpStatus());
    }

    /**
     * Purpose : This method is created for handle the global exception which can occur while running the application
     *
     * @param exception : this parameter will be the exception which will going to help
     *                  for responding back if exception occurs
     * @return : the new response entity which will holds the response DTO
     * that consists the exception message and Http status
     */
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ResponseDTO> handleBadRequestException(BadRequestException exception) {
        ResponseDTO responseDto = new ResponseDTO(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(responseDto, responseDto.getHttpStatus());
    }
}
