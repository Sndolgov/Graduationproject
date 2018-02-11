package ru.sndolgov.graduationproject.to;

import org.hibernate.validator.constraints.SafeHtml;
import ru.sndolgov.graduationproject.model.Menu;

import javax.validation.constraints.NotBlank;
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

    private String menuDescription;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String restaurantName, String menuDescription) {
        super(id);
        this.restaurantName = restaurantName;
        this.menuDescription =menuDescription;
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

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", restaurantName='" + restaurantName + '\'' +
                '}';
    }
}
