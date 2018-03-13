package ru.sndolgov.graduationproject.service.voice;

import ru.sndolgov.graduationproject.model.Voice;

import java.util.Date;
import java.util.List;

/**
 * Created by Сергей on 23.02.2018.
 */
public interface VoiceService {

    Voice getByDate(Date date, int userId);

    Voice creat(int menuId, int userId, Date date);

    void delete(int id);

}
