# QA-java-diplom-3

Тестирование UI веб-приложения Stellar Burgers (https://stellarburgers.nomoreparties.site).

Проект выполнен с помощью следующих технологий:
Java 11
JUnit 4.13.2
Maven 4.0.0
Hamcrest 2.2
Rest-assured 5.1.1

Запуск автотестов:
```
mvn clean test
```

Формирование allure-отчета:
```
mvn allure:serve
```


Для запуска в Яндекс.Браузере:

```
mvn clean test -D browser=yandex
```

