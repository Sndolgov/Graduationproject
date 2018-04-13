package ru.sndolgov.graduationproject.web;


import org.springframework.stereotype.Component;
import java.text.MessageFormat;

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
