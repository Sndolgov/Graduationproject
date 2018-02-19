package ru.sndolgov.graduationproject.repository.menu;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.model.Voice;

import java.util.Date;
import java.util.List;

/**
 * Created by Сергей on 07.02.2018.
 */
@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Override
    Menu save(Menu menu);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId ORDER BY m.date DESC")
    List<Menu> getAll(@Param("restaurantId") int restaurantId);

    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m JOIN FETCH m.restaurant WHERE m.id = ?1 and m.restaurant.id = ?2")
    Menu getWithRestaurant(int id, int restaurantId);

    @EntityGraph(attributePaths = {"restaurant", "dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m WHERE m.date=?1 ORDER BY m.restaurant.name ASC")
    List<Menu> getAllTodayWithRestaraunt(Date date);

    @EntityGraph(attributePaths = {"voices"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m WHERE m.id = ?1 and m.restaurant.id = ?2")
    Menu getWithVoices(int id, int restaurantId);
}
