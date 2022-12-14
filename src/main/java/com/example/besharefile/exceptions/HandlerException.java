package com.example.besharefile.exceptions;

import com.example.besharefile.basess.BaseResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseResponseDto> MyException(RuntimeException e) {
        if (e instanceof BadRequestException) {
            return new ResponseEntity<>(BaseResponseDto.error(e.getMessage(), 400), HttpStatus.BAD_REQUEST);
        } else if (e instanceof UnauthorizedException) {
            return new ResponseEntity<>(BaseResponseDto.error(e.getMessage(), 401), HttpStatus.UNAUTHORIZED);
        } else if (e instanceof ForbiddenException) {
            return new ResponseEntity<>(BaseResponseDto.error(e.getMessage(), 403), HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity<>(BaseResponseDto.error(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
