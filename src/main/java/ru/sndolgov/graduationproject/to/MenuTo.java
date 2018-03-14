package ru.sndolgov.graduationproject.to;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;
import ru.sndolgov.graduationproject.model.Dish;
import ru.sndolgov.graduationproject.util.DateUtil;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class MenuTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;


    @Size(min = 2, max = 100)
    private Integer restaurantId;

    @NotBlank
    @Size(min = 2, max = 100)
    @SafeHtml
    private String restaurantName;

    @NotBlank
    @Size(min = 2, max = 120)
    @SafeHtml
    private String menuDescription;


    private List<Dish> dishes;


    @NotNull
    @DateTimeFormat(pattern = DateUtil.DATE_PATTERN)
    private LocalDate date;


    public MenuTo() {
    }

    public MenuTo(Integer id, Integer restaurantId, String restaurantName, String menuDescription, List<Dish> dishes, LocalDate date) {
        super(id);
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.menuDescription =menuDescription;
        this.dishes=dishes;
        this.date=date;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MenuTo{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                ", menuDescription='" + menuDescription + '\'' +
                ", dishes=" + dishes +
                ", date=" + date +
                '}';
    }
}
