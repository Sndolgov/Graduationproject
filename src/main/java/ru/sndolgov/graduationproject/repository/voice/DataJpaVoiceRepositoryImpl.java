package ru.sndolgov.graduationproject.repository.voice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.model.User;
import ru.sndolgov.graduationproject.model.Voice;
import ru.sndolgov.graduationproject.repository.menu.CrudMenuRepository;
import ru.sndolgov.graduationproject.repository.user.CrudUserRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by Сергей on 19.02.2018.
 */

@Repository
public class DataJpaVoiceRepositoryImpl {

    @Autowired
    CrudVoiceRepository voiceRepository;

    @Autowired
    CrudMenuRepository menuRepository;

    @Autowired
    CrudUserRepository userRepository;


    public Voice getByDate(Date date, int userId) {
        return voiceRepository.getByDate(date, userId);
    }


    @Transactional
    public Voice save(int menuId, int userId, Date date) {
        Menu menu = menuRepository.getWithRestaurant(menuId);
        User user = userRepository.getOne(userId);
        Voice newVoice = new Voice(null, menu, menu.getRestaurant(), user, new Date());
        return voiceRepository.save(newVoice);
    }

    @Transactional
    public boolean delete(int id) {
        return voiceRepository.delete(id) != 0;
    }
}
