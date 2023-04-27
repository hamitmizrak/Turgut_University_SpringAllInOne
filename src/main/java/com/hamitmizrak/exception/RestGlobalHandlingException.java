package com.hamitmizrak.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Exception Spring tarafından yakalanması için kullanıyoruz.
@RestControllerAdvice
public class RestGlobalHandlingException {

    //application.properties veri al

    // HamitMizrakException
    @ExceptionHandler({HamitMizrakException.class})
    public String adviceHamitMizrakException() {
        return "Hamit Mızrak Exception";
    }

    // status codes: 404
    // ResourceNotFoundException
    @ExceptionHandler({ResourceNotFoundException.class})
    public String adviceResourceNotFoundException() {
        return "HM 404 Hatası ";
    }

    // status codes: 401
    @ExceptionHandler({ResourceAuthorizedException.class})
    public String adviceResourceAuthorizedException() {
        return "HM Yetkisiz Giriş";
    }

    // status codes: 400
    @ExceptionHandler({ResourceBadRequestException.class})
    public String adviceResourceBadRequestException() {
        return "HM Kötü istek";
    }

    // status codes:  201
    @ExceptionHandler({ResourceCreatedException.class})
    public String adviceResourceCreatedException() {
        return "HM Oluşturuldu";
    }
}
