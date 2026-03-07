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
    EmployeeRequest request = new EmployeeRequest();
    request.setId(employee.getId());
    request.setFirstName(employee.getFirstName());
    request.setLastName(employee.getLastName());
    request.setEmail(employee.getEmail());
    request.setMobileNo(employee.getMobileNo());
    request.setPassword(employee.getPassword());
    return request;
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
