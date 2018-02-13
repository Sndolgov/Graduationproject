package ru.sndolgov.graduationproject.to;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import ru.sndolgov.graduationproject.View;
import ru.sndolgov.graduationproject.model.Menu;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Сергей on 07.02.2018.
 */
public class RestaurantTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 2, max = 100)
    @SafeHtml
    private String restaurantName;

    @NotBlank
    @Size(min = 2, max = 120)
    @SafeHtml
    private String menuDescription;

    @NotBlank
    @Size(min = 2, max = 120)
    @SafeHtml
    private String dishDescription;

    @Size(min = 1)
    @NotNull
    private  String dishPrice;

    @Range(min = 10, max = 30000)
    @NotNull
    private Integer  totalValue;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String restaurantName, String menuDescription, String dishDescription, String dishPrice, Integer totalValue) {
        super(id);
        this.restaurantName = restaurantName;
        this.menuDescription =menuDescription;
        this.dishDescription=dishDescription;
        this.dishPrice=dishPrice;
        this.totalValue=totalValue;
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

    public String getDishDescription() {
        return dishDescription;
    }

    public void setDishDescription(String dishDescription) {
        this.dishDescription = dishDescription;
    }

    public String getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(String dishPrice) {
        this.dishPrice = dishPrice;
    }

    public Integer getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Integer totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", restaurantName='" + restaurantName + '\'' +
                '}';
    }
}
