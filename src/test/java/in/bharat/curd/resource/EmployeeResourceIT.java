package in.bharat.curd.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import in.bharat.curd.model.EmployeeRequest;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@QuarkusIntegrationTest
class EmployeeResourceIT extends EmployeeResourceTest {

  @Test
  @Order(7)
  void testCreateAnotherEmployee() {
    EmployeeRequest request = new EmployeeRequest();
    request.setFirstName("Jane");
    request.setLastName("Smith");
    request.setEmail("jane.smith@example.com");
    request.setMobileNo("0987654321");
    request.setPassword("password123");

    given()
        .contentType(ContentType.JSON)
        .body(request)
        .when()
        .post("/employee/create")
        .then()
        .statusCode(201)
        .body("message", is("Employee created successfully"));
  }
}
