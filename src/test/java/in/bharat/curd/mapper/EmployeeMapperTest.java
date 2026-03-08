package in.bharat.curd.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import in.bharat.curd.entity.Employee;
import in.bharat.curd.model.EmployeeRequest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
class EmployeeMapperTest {

  @Inject EmployeeMapper employeeMapper;

  @Test
  void testConvertToRequest() {
    Employee employee = new Employee();
    employee.setId(1L);
    employee.setFirstName("John");
    employee.setLastName("Doe");
    employee.setEmail("john.doe@example.com");
    employee.setMobileNo("1234567890");
    employee.setPassword("password");

    EmployeeRequest request = employeeMapper.convertToRequest(employee);

    assertEquals(employee.getId(), request.getId());
    assertEquals(employee.getFirstName(), request.getFirstName());
    assertEquals(employee.getLastName(), request.getLastName());
    assertEquals(employee.getEmail(), request.getEmail());
    assertEquals(employee.getMobileNo(), request.getMobileNo());
    assertEquals(employee.getPassword(), request.getPassword());
  }

  @Test
  void testUpdateEmployeeFromRequest() {
    EmployeeRequest request = new EmployeeRequest();
    request.setFirstName("Jane");
    request.setLastName("Smith");
    request.setEmail("jane.smith@example.com");
    request.setMobileNo("0987654321");
    request.setPassword("newpassword");

    Employee employee = new Employee();
    employeeMapper.updateEmployeeFromRequest(employee, request);

    assertEquals(request.getFirstName(), employee.getFirstName());
    assertEquals(request.getLastName(), employee.getLastName());
    assertEquals(request.getEmail(), employee.getEmail());
    assertEquals(request.getMobileNo(), employee.getMobileNo());
    assertEquals(request.getPassword(), employee.getPassword());
  }
}
