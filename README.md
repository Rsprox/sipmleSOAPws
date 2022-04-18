# sipmleSOAPws
**Простой SOAP сервис на spring boot.**

**Все зависимости можно увидеть в pom.xml**

**Что он делает:**
* принимает SOAP запрос
* валидирует
* сохраняет в SQLite3 БД (файлик)
* генерирует SOAP ответ

**Используется:**

  * Java 8
  * Maven

**- Библиотеки:**

    * spring-boot-starter-web-services
    * spring-boot-devtools
    * wsdl4j
    * spring-boot-starter-test
    * sqlite-jdbc

- Плагины для Maven:
    * spring-boot-maven-plugin
    * jaxb2-maven-plugin <-- генерирует классы по xsd-схеме