package ru.sndolgov.graduationproject.service.user;

import ru.sndolgov.graduationproject.model.User;
import ru.sndolgov.graduationproject.to.UserTo;
import ru.sndolgov.graduationproject.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user);

    void update(UserTo user);

    List<User> getAll();

    void enable(int id, boolean enable);
}