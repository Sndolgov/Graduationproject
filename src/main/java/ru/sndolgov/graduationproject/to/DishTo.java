package ru.sndolgov.graduationproject.to;


import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import ru.sndolgov.graduationproject.View;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Сергей on 17.03.2018.
 */
public class DishTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Size(min = 2, max = 100)
    private Integer menuId;

    @NotBlank
    @Size(min = 2, max = 120)
    @SafeHtml(groups = {View.Web.class})
    private String description;


    @Range(min = 10, max = 10000)
    @NotNull
    private Integer price;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean inMenu;

    public DishTo() {
    }

    public DishTo(Integer id, Integer menuId, String description, Integer price, boolean inMenu) {
        super(id);
        this.menuId = menuId;
        this.description = description;
        this.price = price;
        this.inMenu = inMenu;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public boolean isInMenu() {
        return inMenu;
    }

    public void setInMenu(boolean inMenu) {
        this.inMenu = inMenu;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "id=" + id +
                ", menuId=" + menuId +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", inMenu=" + inMenu +
                '}';
    }
}
