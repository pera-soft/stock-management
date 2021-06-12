# Stock Management :moneybag:

``` 
  The application consists of 3 parts. Category, Subcategory and Product. 
* Each category has a name and one or more subcategories.
* Each subcategory has a name, category, and one or more products.
* Each product has a name, price, stock quantity and a subcategory.
```

### Used Technologies :rocket:
* Java 11
* Spring Boot
* Maven
* Hibernate
* Redis
* Docker
* Swagger
* H2

### Build & Run

#### Docker

``` docker-compose up -d --build ```

#### Backend

``` mvn clean install ```

``` mvn --projects your-service spring-boot:run ```

### License

Distributed under the MIT License. See [LICENSE](LICENSE) for more information.
