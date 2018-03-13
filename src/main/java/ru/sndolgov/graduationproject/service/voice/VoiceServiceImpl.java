package ru.sndolgov.graduationproject.service.voice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.sndolgov.graduationproject.model.Voice;
import ru.sndolgov.graduationproject.repository.voice.DataJpaVoiceRepositoryImpl;
import ru.sndolgov.graduationproject.util.exception.NotFoundException;

import java.util.Date;
import java.util.List;

import static ru.sndolgov.graduationproject.util.ValidationUtil.checkNotFound;
import static ru.sndolgov.graduationproject.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Сергей on 23.02.2018.
 */

@Service
public class VoiceServiceImpl implements VoiceService {

    @Autowired
    DataJpaVoiceRepositoryImpl repository;

    @Override
    public Voice getByDate(Date date, int userId) throws NotFoundException{
        Assert.notNull(date, "date must not be null");
        return checkNotFound(repository.getByDate(date, userId), "date="+date+" userId="+userId);
    }


    @Override
    public Voice creat(int menuId, int userId, Date date) {
        return repository.save(menuId, userId, date);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

}
