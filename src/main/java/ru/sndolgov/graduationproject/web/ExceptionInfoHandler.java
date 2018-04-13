package ru.sndolgov.graduationproject.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.sndolgov.graduationproject.util.ValidationUtil;
import ru.sndolgov.graduationproject.util.exception.ApplicationException;
import ru.sndolgov.graduationproject.util.exception.ErrorInfo;


import javax.servlet.http.HttpServletRequest;
import java.util.*;


@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class ExceptionInfoHandler {
    private static Logger log = LoggerFactory.getLogger(ExceptionInfoHandler.class);

    public static final String EXCEPTION_DUPLICATE_EMAIL = "Пользователь с такой почтой уже есть в приложении";
    public static final String EXCEPTION_DUPLICATE_VOICE = "Вы уже проголосовали сегодня";
    public static final String EXCEPTION_DUPLICATE_NAME = "Ресторан с таким именем уже есть в приложении";
    public static final String EXCEPTION_DUPLICATE_DATE = "Меню с такой датой уже есть у ресторана";
    public static final String EXCEPTION_DUPLICATE_DESCRIPTION = "У ресторана уже есть блюдо с таким названием";



    private static final Map<String, String> CONSTRAINS_I18N_MAP = Collections.unmodifiableMap(
            new HashMap<String, String>() {
                {
                    put("users_unique_email_idx", EXCEPTION_DUPLICATE_EMAIL);
                    put("date_user_idx", EXCEPTION_DUPLICATE_VOICE);
                    put("restaurant_unique_name_idx", EXCEPTION_DUPLICATE_NAME);
                    put("restaurant_date_idx", EXCEPTION_DUPLICATE_DATE);
                    put("restaurant_description_idx", EXCEPTION_DUPLICATE_DESCRIPTION);
                }
            });

    @Autowired
    private MessageUtil messageUtil;

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorInfo> applicationError(HttpServletRequest req, ApplicationException appEx) {
        String[] details = Arrays.stream(appEx.getArgs())
                .map(arg -> messageUtil.getMessage(appEx.getMsgCode()+" {0}", arg))
                .toArray(String[]::new);
        ErrorInfo errorInfo = logAndGetErrorInfo(req, appEx, false, details);
        return new ResponseEntity<>(errorInfo, appEx.getHttpStatus());
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        String rootMsg = ValidationUtil.getRootCause(e).getMessage();
        if (rootMsg != null) {
            String lowerCaseMsg = rootMsg.toLowerCase();
            Optional<Map.Entry<String, String>> entry = CONSTRAINS_I18N_MAP.entrySet().stream()
                    .filter(it -> lowerCaseMsg.contains(it.getKey()))
                    .findAny();
            if (entry.isPresent()) {
                return logAndGetErrorInfo(req, e, false, entry.get().getValue());
            }
        }
        return logAndGetErrorInfo(req, e, true);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)  // 422
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public ErrorInfo bindValidationError(HttpServletRequest req, Exception e) {
        BindingResult result = e instanceof BindException ?
                ((BindException) e).getBindingResult() : ((MethodArgumentNotValidException) e).getBindingResult();

        String[] details = result.getFieldErrors().stream()
                .map(fe -> messageUtil.getMessage(fe.getField()+" {0}", fe.getDefaultMessage()))
                .toArray(String[]::new);

        return logAndGetErrorInfo(req, e, false, details);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true);
    }

    private ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e, boolean logException, String... details) {
        Throwable rootCause = ValidationUtil.getRootCause(e);
        if (logException) {
            log.error(" at request " + req.getRequestURL(), rootCause);
        } else {
            log.warn("{} at request  {}: {}", req.getRequestURL(), rootCause.toString());
        }
        return new ErrorInfo(req.getRequestURL(),
                details.length != 0 ? details : new String[]{rootCause.toString()});
    }
}