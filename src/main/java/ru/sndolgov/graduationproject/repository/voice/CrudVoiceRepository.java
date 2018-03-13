package ru.sndolgov.graduationproject.repository.voice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.sndolgov.graduationproject.model.Voice;

import java.util.Date;
import java.util.List;

/**
 * Created by Сергей on 19.02.2018.
 */
@Transactional(readOnly = true)
public interface CrudVoiceRepository extends JpaRepository<Voice, Integer> {




/*
    @Query("SELECT v FROM Voice v WHERE v.menu.id=:menuId AND v.restaurant.id=:restaurantId")
    List<Voice> getAll(@Param("menuId") int id, @Param("restaurantId") int restaurantId);
*/

    @Query("SELECT v FROM Voice v WHERE v.date=:date AND v.user.id=:userId")
    Voice getByDate(@Param("date") Date date , @Param("userId") int userId);


    @Override
    Voice save(Voice voice);

    @Modifying
    @Query("DELETE FROM Voice v WHERE v.id=:id")
    int delete(@Param("id") int id);
}
