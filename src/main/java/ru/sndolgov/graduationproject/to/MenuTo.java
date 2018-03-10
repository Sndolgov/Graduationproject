package ru.sndolgov.graduationproject.to;

import org.hibernate.validator.constraints.SafeHtml;
import ru.sndolgov.graduationproject.View;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class MenuTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank
    @Size(min = 2, max = 100)
    @SafeHtml(groups = {View.Web.class})
    protected String description;

    @NotNull
    private LocalDate date;


    public MenuTo(Integer id, String description, LocalDate date) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MenuTo{" +
                ", description='" + description + '\'' +
                '}';
    }
}
