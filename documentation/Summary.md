## Отчёт о проведённой автоматизации

### 1. Что было запланировано и что было сделано

В результате работы над дипломным проектом были выполнены задачи, а именно:

Автоматизированы позитивные и негативные тестовые сценарии покупки тура через UI ("Оплата по карте" и "Кредит по данным карты");
Протестирована работа сервиса на двух СУБД (MySQL и PostgreSQL), а так же параметризован запуск SUT и тестов для указанных БД;
Сгенерирован отчет о проведенном тестировании в Allure Report.
### 2. Причины, по которым что-то не было сделано

Не была выполнена дополнительно интеграция с системой CI по причине нехватки времени.

### 3. Полученные проблемы

В процессе автоматизации тестирования были выявлены следующие проблемы:

В следствие отсутствия уникальных идентификаторов для элементов страниц (id) были сложности с выбором правильных селекторов;
Проверка правильности работы автоматизированных тестов вручную и исключение «ложных» падений по причине наличия значительного числа дефектов в сервисе потребовали дополнительного времени для анализа;
Отсутствие подробной документации к сервису;
Встретились проблемы с правильным подключением к базе данных в MySQL и PostgreSQL, а именно получением данных из таблиц в процессе автоматизации;
### 4. Общий итог по времени

Итого запланировано: 100 часов

Итого потрачено: 125 часов