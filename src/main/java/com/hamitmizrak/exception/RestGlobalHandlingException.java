package com.hamitmizrak.exception;

import com.hamitmizrak.error.ApiResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Exception Spring tarafından yakalanması için kullanıyoruz.
@RestControllerAdvice
public class RestGlobalHandlingException {

    //application.properties veri al

    // HamitMizrakException
    @ExceptionHandler({HamitMizrakException.class})
    public ApiResult adviceHamitMizrakException() {
        ApiResult  apiResult = ApiResult.builder()
                .error("Genel hata")
                .message("Rest Global Handling Exception")
                .path("/api")
                .status(44)
                .build();
        return apiResult;
    }

    // status codes: 404
    // ResourceNotFoundException
    @ExceptionHandler({ResourceNotFoundException.class})
    public ApiResult adviceResourceNotFoundException() {
        ApiResult  apiResult = ApiResult.builder()
                .error("Bulunamadı")
                .message("404 Hatası")
                .path("/api")
                .status(404)
                .build();
        return apiResult;
    }

    // status codes: 401
    @ExceptionHandler({ResourceAuthorizedException.class})
    public ApiResult adviceResourceAuthorizedException() {
        ApiResult  apiResult = ApiResult.builder()
                .error("Yetkisiz Giriş")
                .message("401 Hatası")
                .path("/api")
                .status(401)
                .build();
        return apiResult;
    }

    // status codes: 400
    @ExceptionHandler({ResourceBadRequestException.class})
    public ApiResult adviceResourceBadRequestException() {
        ApiResult  apiResult = ApiResult.builder()
                .error("Kötü istek")
                .message("400 Hatası")
                .path("/api")
                .status(400)
                .build();
        return apiResult;
    }

    // status codes:  201
    @ExceptionHandler({ResourceCreatedException.class})
    public ApiResult adviceResourceCreatedException() {
        ApiResult  apiResult = ApiResult.builder()
                .error("Oluşturuldu")
                .message("201")
                .path("/api")
                .status(201)
                .build();
        return apiResult;
    }
}
