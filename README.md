# Stock Management :bar_chart:

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/c0f9589d4e3c4804aa192cdf6bc2c3a9)](https://app.codacy.com/gh/oguzhancevik/stock-management?utm_source=github.com&utm_medium=referral&utm_content=oguzhancevik/stock-management&utm_campaign=Badge_Grade_Settings)

``` 
  The application consists of 3 parts. Category, Subcategory and Product. 
* Each category has a name and one or more subcategories.
* Each subcategory has a name, category, and one or more products.
* Each product has a name, price, stock quantity and a subcategory.
* If requests are received at the same time for the only product left in stock, only one request get successful response.
```

### Tech Stack & Patterns :dart:
* Java 11
* Spring Boot
* Maven
* Hibernate
* Mapstruct  
* SLF4J
* Junit
* Redis
* Docker
* Swagger
* H2
* Factory Pattern
* CQRS Pattern

### Prerequisites :warning:
| Tech   | Version |
|--------|---------|
| Java   | 11.0.11 |
| Maven  | 3.8.1   |
| Docker | 20.10.6 |
| Redis  | 3.2.100 |

### Build & Run :rocket:

###### Docker

``` docker-compose up -d --build ```

###### Backend

``` mvn clean install -Dspring.profiles.active=local ```

``` mvn --projects io.github.oguzhancevik:stock-management spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local" ```

### Design :art:
![DesignPhoto](static/chart.png)

### APIs :memo:
| API                                              | Method | Description                                       |
|--------------------------------------------------|--------|---------------------------------------------------|
| /api/categories                                  | GET    | Returns categories                                |
| /api/categories/{categoryId}                     | GET    | Returns a specific category                       |
| /api/categories                                  | POST   | Creates a new category                            |
| /api/categories/{categoryId}                     | PUT    | Updates a specific category                       |
| /api/categories/{categoryId}                     | DELETE | Deletes a specific category                       |
| /api/subcategories                               | GET    | Returns sub categories                            |
| /api/subcategories/{subCategoryId}               | GET    | Returns a specific sub category                   |
| /api/subcategories                               | POST   | Creates a new sub category                        |
| /api/subcategories/{subCategoryId}               | PUT    | Updates a specific sub category                   |
| /api/subcategories/{subCategoryId}               | DELETE | Deletes a specific sub category                   |
| /api/products                                    | GET    | Returns products                                  |
| /api/products/{productId}                        | GET    | Returns a specific product                        |
| /api/products/search/category/{categoryId}       | GET    | Returns products by category id                   |
| /api/products/search/subcategory/{subCategoryId} | GET    | Returns products by sub category id               |
| /api/products/search/name/{productName}          | GET    | Returns products by product name                  |
| /api/products/search/price/?min=200&max=500      | GET    | Returns products by price interval                |
| /api/shopping-cart                               | POST   | Returns successful / unsuccessful purchase result |


### Postman Collection :pushpin:
[Click Here](static/stock-management.postman_collection.json)

### Swagger UI :gift:
http://localhost:8080/swagger-ui/

### License :key:
Distributed under the MIT License. See [LICENSE](LICENSE) for more information.
