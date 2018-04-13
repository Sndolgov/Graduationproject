package ru.sndolgov.graduationproject.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.sndolgov.graduationproject.util.ValidationUtil;
import ru.sndolgov.graduationproject.util.exception.ApplicationException;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Arrays;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @Autowired
    private MessageUtil messageUtil;

    @ExceptionHandler(ApplicationException.class)
    public ModelAndView applicationErrorHandler(HttpServletRequest req, ApplicationException appEx) throws Exception {
        System.out.println(appEx.getMsgCode());
        System.out.println(Arrays.toString(appEx.getArgs()));

        String[] details = Arrays.stream(appEx.getArgs())
                .map(args -> messageUtil.getMessage(appEx.getMsgCode(), args))
                .toArray(String[]::new);


        return getView(req, appEx, details);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        return getView(req, e, null);
    }

    public ModelAndView getView(HttpServletRequest req, Exception e, String [] msg) throws Exception {
        Throwable rootCause = ValidationUtil.getRootCause(e);
        log.error("Exception at request " + req.getRequestURL(), rootCause);
        ModelAndView mav = new ModelAndView("exception/exception");
        mav.addObject("exception", rootCause);
        mav.addObject("message", msg != null ? messageUtil.toString(msg) : rootCause.toString());
        return mav;
    }
}
