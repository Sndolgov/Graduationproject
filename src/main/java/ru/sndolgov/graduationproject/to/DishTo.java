package ru.sndolgov.graduationproject.to;


import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import ru.sndolgov.graduationproject.View;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Сергей on 17.03.2018.
 */
public class DishTo extends BaseTo {

    private Integer parentId;

    @NotBlank
    @Size(min = 2, max = 120)
    @SafeHtml(groups = {View.Web.class})
    private String description;


    @Range(min = 10, max = 10000)
    @NotNull
    private Integer price;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled;

    public DishTo() {
    }

    public DishTo(Integer id, Integer parentId, String description, Integer price, boolean enabled) {
        super(id);
        this.parentId = parentId;
        this.description = description;
        this.price = price;
        this.enabled = enabled;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", enabled=" + enabled +
                '}';
    }
}
