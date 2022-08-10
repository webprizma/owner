Запуск тестов через консоль:
- с передачей properties локально:

```
gradle clean test -Dbrowser=CHROME -Dversion=103
```

- с передачей properties удаленно:

```
gradle clean test -Dbrowser=CHROME -Dversion=99 -DremoteUrl=https://user1:1234@selenoid.autotests.cloud/wd/hub/
gradle clean test -Dbrowser=CHROME -Dversion=100 -DremoteUrl=https://user1:1234@selenoid.autotests.cloud/wd/hub/
gradle clean test -Dbrowser=FIREFOX -Dversion=97 -DremoteUrl=https://user1:1234@selenoid.autotests.cloud/wd/hub/
gradle clean test -Dbrowser=FIREFOX -Dversion=98 -DremoteUrl=https://user1:1234@selenoid.autotests.cloud/wd/hub/
```

Запуск тестов через консоль с использованием готовых конфигураций:
- локально:

```
gradle clean test -Dhost=local/remote
```

- удаленно:

```
gradle clean test -Dhost=remote
```


