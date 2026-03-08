package in.bharat.curd.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import in.bharat.curd.model.EmployeeRequest;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeResourceTest {

  private static Long employeeId;

  @Test
  @Order(1)
  void testCreateEmployee() {
    EmployeeRequest request = new EmployeeRequest();
    request.setFirstName("John");
    request.setLastName("Doe");
    request.setEmail("john.doe@example.com");
    request.setMobileNo("1234567890");
    request.setPassword("password");

    given()
        .contentType(ContentType.JSON)
        .body(request)
        .when()
        .post("/employee/create")
        .then()
        .statusCode(201)
        .body("message", is("Employee created successfully"));
  }

  @Test
  @Order(2)
  void testGetAllEmployees() {
    EmployeeRequest[] employees =
        given()
            .when()
            .get("/employee")
            .then()
            .statusCode(200)
            .extract()
            .as(EmployeeRequest[].class);

    Assertions.assertTrue(employees.length > 0);
    employeeId = employees[0].getId();
    Assertions.assertNotNull(employeeId);
  }

  @Test
  @Order(3)
  void testUpdateEmployee() {
    EmployeeRequest request = new EmployeeRequest();
    request.setFirstName("John Updated");
    request.setLastName("Doe");
    request.setEmail("john.doe@example.com");
    request.setMobileNo("1234567890");
    request.setPassword("password");

    given()
        .contentType(ContentType.JSON)
        .body(request)
        .pathParam("id", employeeId)
        .when()
        .put("/employee/update/{id}")
        .then()
        .statusCode(200)
        .body("message", is("Employee updated successfully"));
  }

  @Test
  @Order(4)
  void testDeleteEmployee() {
    given()
        .pathParam("id", employeeId)
        .when()
        .delete("/employee/delete/{id}")
        .then()
        .statusCode(200)
        .body("message", is("Employee deleted successfully"));
  }

  @Test
  @Order(5)
  void testUpdateEmployeeNotFound() {
    EmployeeRequest request = new EmployeeRequest();
    request.setFirstName("Ghost");

    given()
        .contentType(ContentType.JSON)
        .body(request)
        .pathParam("id", 999999)
        .when()
        .put("/employee/update/{id}")
        .then()
        .statusCode(404)
        .body("message", is("Employee not found"));
  }

  @Test
  @Order(6)
  void testDeleteEmployeeNotFound() {
    given()
        .pathParam("id", 999999)
        .when()
        .delete("/employee/delete/{id}")
        .then()
        .statusCode(404)
        .body("message", is("Employee not found"));
  }
}
