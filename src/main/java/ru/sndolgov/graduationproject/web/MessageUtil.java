package ru.sndolgov.graduationproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import ru.sndolgov.graduationproject.util.exception.ApplicationException;

import java.text.MessageFormat;
import java.util.Locale;

@Component
public class MessageUtil {

    public String getMessage(String code, String args){
        return MessageFormat.format(code, args);
    }


    public String toString(String [] details){
        StringBuilder msg = new StringBuilder();
        for(String detail: details){
            msg.append(detail).append("\n");
        }
        return msg.toString();
    }
}
