[![Codacy Badge](https://api.codacy.com/project/badge/Grade/33064c36334d461a8bcd6a59f8bc0d1d)](https://www.codacy.com/app/Sndolgov/graduationproject?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Sndolgov/graduationproject&amp;utm_campaign=Badge_Grade)
[![Dependency Status](https://www.versioneye.com/user/projects/5acf76020fb24f3a0d6b49c7/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/5acf76020fb24f3a0d6b49c7)

Java Enterprise Online Project 
===============================
Полнофункциональное приложение Spring/JPA Enterprise c авторизацией и правами доступа на основе ролей с использованием наиболее популярных инструментов и технологий Java: Maven, Spring MVC, Security, Bootstrap (css,js), datatables, jQuery + plugins, Java 8 Stream and Time API и хранением в базе данных Postgresql.

![project structure](https://raw.githubusercontent.com/Sndolgov/graduationproject/master/technologies.jpg)

Система голосования, для того чтобы выбрать в каком ресторане взять обед.

2 типа пользователей: администратор и постоянные пользователи
Администратор может добавить ресторан и его обеденное меню дня (обычно 2-5 блюд, только c названием и ценой)
Меню меняется каждый день (администраторы делают обновления)
Пользователи могут проголосовать, в каком ресторане они хотят пообедать.
Пользователь может отдать только один голос.
Если пользователь снова проголосовал в тот же день:
Если до 11:00 мы предполагаем, что он передумал.
Если это после 11:00, то уже слишком поздно, голосование не может быть изменено
Каждый ресторан предлагает новое меню каждый день.