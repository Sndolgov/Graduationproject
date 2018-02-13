package ru.sndolgov.graduationproject.repository.dish;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sndolgov.graduationproject.model.Menu;

/**
 * Created by Сергей on 12.02.2018.
 */
public interface CrudDishRepository extends JpaRepository<Menu, Integer> {

}
