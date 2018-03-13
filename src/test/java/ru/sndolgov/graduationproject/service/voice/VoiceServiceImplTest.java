package ru.sndolgov.graduationproject.service.voice;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import ru.sndolgov.graduationproject.MenuTestData;
import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.model.Voice;
import ru.sndolgov.graduationproject.service.AbstractServiceTest;
import ru.sndolgov.graduationproject.util.DateUtil;
import ru.sndolgov.graduationproject.util.exception.NotFoundException;

import static org.junit.Assert.*;
import static ru.sndolgov.graduationproject.MenuTestData.MENU1;
import static ru.sndolgov.graduationproject.MenuTestData.MENU1_ID;
import static ru.sndolgov.graduationproject.MenuTestData.MENU6_ID;
import static ru.sndolgov.graduationproject.UserTestData.ADMIN_ID;
import static ru.sndolgov.graduationproject.UserTestData.USER_ID;
import static ru.sndolgov.graduationproject.VoiceTestData.*;

/**
 * Created by Сергей on 23.02.2018.
 */
public class VoiceServiceImplTest extends AbstractServiceTest {
    @Autowired
    VoiceService service;

    @Test
    public void get() throws Exception {
        Voice voice = service.getByDate(DateUtil.of(2017, 12, 26), USER_ID);
        assertMatch(voice, VOICE1);
        MenuTestData.assertMatch(voice.getMenu(), MENU1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.getByDate(DateUtil.getDateToday(), ADMIN_ID);

    }

    @Test
    public void save() throws Exception {
        Voice created = getCreated();
        Voice voice = service.creat(MENU6_ID, USER_ID, DateUtil.getDateToday());
        created.setId(voice.getId());
        assertMatch(voice, created);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void saveError() throws Exception {
        service.creat(MENU1_ID, USER_ID, DateUtil.getDateToday());
        service.creat(MENU1_ID, USER_ID, DateUtil.getDateToday());
    }

}