package in.bharat.curd.mapper;

import static org.junit.jupiter.api.Assertions.*;

import in.bharat.curd.entity.Employee;
import in.bharat.curd.model.EmployeeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeMapperTest {

  private EmployeeMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new EmployeeMapper();
  }

  @Test
  void testConvertToRequest() {
    Employee employee = new Employee();
    employee.setId(1L);
    employee.setFirstName("John");
    employee.setLastName("Doe");
    employee.setEmail("john.doe@example.com");
    employee.setMobileNo("1234567890");
    employee.setPassword("password");

    EmployeeRequest request = mapper.convertToRequest(employee);

    assertNotNull(request);
    assertEquals(employee.getId(), request.getId());
    assertEquals(employee.getFirstName(), request.getFirstName());
    assertEquals(employee.getLastName(), request.getLastName());
    assertEquals(employee.getEmail(), request.getEmail());
    assertEquals(employee.getMobileNo(), request.getMobileNo());
    assertEquals(employee.getPassword(), request.getPassword());
  }

  @Test
  void testConvertToRequest_Null() {
    assertNull(mapper.convertToRequest(null));
  }

  @Test
  void testUpdateEmployeeFromRequest() {
    Employee employee = new Employee();
    EmployeeRequest request = new EmployeeRequest();
    request.setFirstName("Jane");
    request.setLastName("Smith");
    request.setEmail("jane.smith@example.com");
    request.setMobileNo("0987654321");
    request.setPassword("newpassword");

    mapper.updateEmployeeFromRequest(employee, request);

    assertEquals(request.getFirstName(), employee.getFirstName());
    assertEquals(request.getLastName(), employee.getLastName());
    assertEquals(request.getEmail(), employee.getEmail());
    assertEquals(request.getMobileNo(), employee.getMobileNo());
    assertEquals(request.getPassword(), employee.getPassword());
  }

  @Test
  void testUpdateEmployeeFromRequest_Null() {
    Employee employee = new Employee();
    mapper.updateEmployeeFromRequest(employee, null);
    assertNull(employee.getFirstName());

    mapper.updateEmployeeFromRequest(null, new EmployeeRequest());
    // Should not throw exception
  }
}
