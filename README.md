# TietoEvry Homework

## Run Spring Boot application
To run the Spring Boot application, execute the following command:
```
mvn spring-boot:run
```
The application runs on port 8080 by default, but you can modify the port in the `application.properties` file.

## Description
The application implements an API endpoint at `GET /api/hike/items`. 
This endpoint calculates the items needed for a hike, including the number of nights to stay, the required meals, and the end date of the hike. 
The endpoint accepts two required query parameters for calculations:
- kilometers: the distance of the hike in kilometers.
- startDate: the start date of the hike in `YYYY-MM-DD` format.

## Example 
The application includes a Swagger UI that can be accessed at http://localhost:8080/swagger-ui/index.html.

You can also call the endpoint using any API platform that supports endpoint calls, such as Postman. Here is an example curl command:
```
curl -X 'GET' \
  'http://localhost:8080/api/hike/items?kilometers=100&startDate=2023-11-30' \
  -H 'accept: */*'
```
