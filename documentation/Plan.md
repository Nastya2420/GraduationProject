# План автоматизации дипломного проекта

## Перечень автоматизируемых сценариев

### Тестовые данные

Наш симулятор банковского сервиса имеет следующие данные карт для Payment и Credit gates:

4444 4444 4444 4441 - валидный номер карты с одобрением;

4444 4444 4444 4442 - валидный номер карты с отказом.

В качестве тестовых данных для оплаты по карте будем использовать следующее:

### Валидные данные:

Владелец: Ivan Ivanov
Срок действия банковской карты: 09/23
СVC-код: 000

### Невалидные данные:

Под невалидными данными будем подразумевать те символы, которые не должны находится в полях

В поле "Номер карты" не должно быть ничего кроме цифр;
В поле срока действия карты должны быть только цифры;
Поле "Владелец" должно принимать только буквы. (Считаем, что путешествие оформляет гражданин РФ)
CVC-код состоит только из цифр, другие символы запрещены к вводу.

## Чек-лист тестируемых сценариев:
1. Дебетовая карта с вводом валидных данных;

ОР: Уведомление об одобрении операции банком.

2. Выбор кредитной карты. Ввод валидных данных;

ОР: Уведомление об одобрении операции банком.

3. Выбор дебетовой карты. Ввод валидных данных с отказом;

ОР: Уведомление об отказе операции банком

4. Выбор кредитной карты. Ввод валидных денных с отказом;

ОР: Уведомление об отказе операции банком

5. Выбор дебетовой карты. Ввод невалидных данных;

ОР: Уведомление об отказе операции банком

6. Выбор кредитной карты. Ввод невалидных данных.

ОР: Уведомление об отказе операции банком

## Перечень используемых инструментов с обоснованием выбора

**JUnit 5** - Наиболее широко используемая среда тестирования для приложений Java;

**Docker** - Запуск необходимых приложений из контейнера без необходимости их установки на ОС;

**Selenide** - фреймворк для автоматизированного тестирования WEB-приложений на основе Selenium. Более удобный в использовании чем Selenium;

**GitHub** - крупнейший и бесплатный хостинг IT-проектов с возможностью без дополнительной платы внедрить CI.

**GIT** - это система контроля версий (VCS), которая позволяет отслеживать и фиксировать изменения в коде.

**Gradle** - система для автоматизации сборки приложений и сбора статистики об использовании программных библиотек, применяющая языки Groovy, Java, JavaScript, Kotlin и т. д.

**IntelliJ IDEA** - интегрированная среда разработки программного обеспечения для многих языков программирования, в частности Java, JavaScript, Python.

**Lombok** — это библиотека для сокращения кода в классах и расширения функциональности языка Java(перестать писать огромные простыни кода из гетеров, сеттеров, equals, hashcode и т.д).

**Allure** - фреймворк, предназначенный для создания визуально наглядной картины выполнения тестов.

## Перечень и описание возможных рисков при автоматизации

1. Выгода от автоматизации.
2. Использование двух разных БД
3. Тестовое окружение: Использование банковского симулятора
4. Неверная оценка трудозатрат

## Интервальная оценка с учётом рисков (в часах)

Автоматизация - 100 ч.
Отчетные документы по итогам тестирования - 5 ч.
Отчетные документы по итогам автоматизации - 5 ч.

## План сдачи работы

Сдача рабочих автотестов до 25 марта
Сдача отчетов по тестированию и автоматизации до 26 марта