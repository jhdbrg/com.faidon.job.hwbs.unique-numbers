## Тестовое задание на вакансию **[HWBS Java Developer](https://moikrug.ru/vacancies/1000005277)**

### Описание ###

Нужно создать веб приложение, используя [стандартный GWT](http://www.gwtproject.org), любую технологию для доступа к базе, любую технологию для клиент-серверного взаимодействия, [HSQLDB](hsqldb.org) и [Maven](https://maven.apache.org).

Основная задача приложения - генерация 1.000.000 случайных строк и их сохранение в таблицу базы данных. Таблица базы данных состоит из двух колонок. Первая колонка - уникальные случайные строки в формате NNNNN, где N - 0..9. Вторая - количество повторов этой строки при генерации. Из ограничений первой колонки следует, что всего таблица может содержать максимум 100.000 записей. 

Приложение состоит из одной веб страницы. На странице есть три элемента:

* Таблица со строками и их количеством из базы данных. 
    * Таблица всегда отображает актуальное состояние базы. Например, если идет процесс генерации, то в таблице показывается часть записей, и это количество со временем растет.
* Кнопка управлением генерацией строк. 
    * Строки генерятся случайным образом. Если оказывается, что такая строка уже есть в базе, то во второй колонке увеличивается счетчик.
    * В зависимости от состояния таблицы в базе и статуса генерации в текущей сессии, кнопка имеет разный функционал и наименование. Если база пустая и процесс генерации в данной сессии не запущен - начинает создание миллиона записей и их сохранение. Если в текущей сессии уже идет процесс генерации - останавливает этот процесс. Если в базе есть записи, а процесс генерации не запущен - удаляет все записи из базы и начинает генерацию новых записей. 
    * Генерация может идти в нескольких пользовательских сессиях, например, из нескольких окон браузера. Явно запрещать параллельную генерацию нельзя.
    * Все сессии работают с одной и той же таблицей, с одними и теми же данными. Какого-либо разделения области видимости нет.
    * Генерация останавливается или по достижению 1.000.000 сгенерированных строк, или когда в таблице становится 100.000 записей, или когда удаляется пользовательская сессия.
* Надпись с актуальным количеством записей в таблице базы данных. 
    * Во время пере-/генерации значение меняется в реальном времени

В решении нужно сделать упор на:

* Правильную работу с соединениями, транзакциями и их уровнями изоляции;
* Корректную работу с приложением из нескольких окон браузера.
* Обновление в реальном времени данных на странице: таблица и надпись с количеством записей.

### Решение ###

* Для начала работы над решением нужно: **создать** `private reposritory`, сделав [fork](https://confluence.atlassian.com/bitbucket/forking-a-repository-221449527.html) этого проекта в свой [Bitbucket аккаунт](https://bitbucket.org/account/signup), **дать права** на чтение пользователю [ismagilov](https://bitbucket.org/ismagilov).
* После выполнения задания нужно отправить на почту ilya@faidon.com письмо с темой "HWBS Java Developer" и ссылкой на свой репозиторий с кодом решения.

### Указания ###

* Проект запускается из командной строки: `mvn gwt:run`. В решении запуск должен остаться таким же.
* Если проект уже запущен, то изменения в клиентском коде появляются после обновления страницы в браузере. Для появления серверных изменений нужно делать `Restart Server` в закладке `Jetty` диалога  `GWT Development Mode`.
* В проекте есть файл `V1__create.sql`. Файл содержит начальную схему базы данных. Она создается автоматически при запуске проекта через [Flyway](http://flywaydb.org).
* Файлы базы создаются в директории: `target/unique-numbers-1.0-SNAPSHOT/WEB-INF/db`.
* Если нужно внести изменения в схему или настройки базы, то это можно делать путем редактирования V1__create.sql (перед этим придется вручную удалить уже созданную базу).
* URL соединения к базе можно брать из класса [DatabaseContextListener](https://bitbucket.org/singulator/com.faidon.job.hwbs.unique-numbers/src/master/src/main/java/com/faidon/job/hwbs/un/server/DatabaseContextListener.java?at=master&fileviewer=file-view-default).

##Удачи!##