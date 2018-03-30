package ru.sndolgov.graduationproject.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.SafeHtml;
import ru.sndolgov.graduationproject.View;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.Cache;


/**
 * Created by Сергей on 07.02.2018.
 */
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id","date"}, name = "restaurant_date_idx")})
public class Menu extends AbstractBaseEntity{

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    @SafeHtml(groups = {View.Web.class})
    private String description;

    @Column(name = "date", nullable = false)
    @NotNull
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private Restaurant restaurant;


    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name="menuconsist",
            joinColumns={@JoinColumn(name ="menu_id", referencedColumnName ="id")},
            inverseJoinColumns={@JoinColumn(name ="dish_id", referencedColumnName ="id")})
    @OrderBy("id ASC")
    private List<Dish> dishes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    @OrderBy("user_id ASC")
    private List<Voice> voices;

    public Menu(){}

    public Menu (String description, Date date){
        this(null, description, date);
    }

    public Menu(Integer id, String description, Date date) {
        super(id);
        this.description = description;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<Voice> getVoices() {
        return voices;
    }

    public void setVoices(List<Voice> voices) {
        this.voices = voices;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
