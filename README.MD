Проект автоматизации тестирования веб-приложения, взаимодействующего с СУБД и API Банка. Описание задания можно найти здесь - https://github.com/netology-code/qa-diploma

Описание тестовых сценариев, запланированных для автоматизации, можно посмотреть здесь - https://github.com/Skitovich/qa-diplom/blob/master/Plan.md

Перед запуском автотестов необходимо убедиться, что на компьютере, где они будут запускаться, установлены Docker Desktop, IntelliJ IDEA и Java 11.

Как запустить автотесты
Открыть в IntelliJ IDEA терминал(Terminal) и запустить контейнеры командой docker-compose up

После того как контейнеры запустятся, в соседней вкладке терминала запустить приложение командой:

вариант 1 (с подключением к MySQL) java -jar aqa-shop.jar -P:jdbc.url=jdbc:mysql://localhost:3306/app -P:jdbc.user=app -P:jdbc.password=pass

вариант 2 (с подключением к PostgreSQL) java -jar aqa-shop.jar -P:jdbc.url=jdbc:mysql://localhost:5432/postgres -P:jdbc.user=postgres -P:jdbc.password=mysecretpassword

После того как приложение запустится, в следующей соседней вкладке терминала запустить тесты командой:

вариант 1 (с подключением к MySQL) gradlew test -Ddatabase.url=jdbc:mysql://localhost:3306/app -Ddatabase.name=app -Ddatabase.password=pass

вариант 2 (с подключением к PostgreSQL) gradlew test -Ddatabase.url=jdbc:postgresql://localhost:5432/postgres -Ddatabase.name=postgres -Ddatabase.password=mysecretpassword
