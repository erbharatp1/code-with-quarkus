package in.bharat.curd.mapper;

import in.bharat.curd.entity.Employee;
import in.bharat.curd.model.EmployeeRequest;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeMapper {

  public EmployeeRequest convertToRequest(Employee employee) {
    if (employee == null) {
      return null;
    }
    return EmployeeRequest.builder()
        .id(employee.getId())
        .firstName(employee.getFirstName())
        .lastName(employee.getLastName())
        .email(employee.getEmail())
        .mobileNo(employee.getMobileNo())
        .password(employee.getPassword())
        .build();
  }

  public void updateEmployeeFromRequest(Employee employee, EmployeeRequest request) {
    if (employee == null || request == null) {
      return;
    }
    employee.setFirstName(request.getFirstName());
    employee.setLastName(request.getLastName());
    employee.setEmail(request.getEmail());
    employee.setMobileNo(request.getMobileNo());
    employee.setPassword(request.getPassword());
  }
}
