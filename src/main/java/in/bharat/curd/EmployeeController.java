package in.bharat.curd;

import in.bharat.curd.entity.Employee;
import in.bharat.curd.model.EmployeeRequest;
import in.bharat.curd.repository.EmployeeRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.stream.Collectors;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeController {

    @Inject
    EmployeeRepository employeeRepository;

    @GET
    @Path("/")
    public List<EmployeeRequest> getAllEmployees() {
        return employeeRepository.listAll().stream()
                .map(this::convertToRequest)
                .collect(Collectors.toList());
    }

    @POST
    @Path("/create")
    @Transactional
    public String createEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        updateEmployeeFromRequest(employee, request);
        employeeRepository.persist(employee);
        return "Employee created successfully";
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public String updateEmployee(@PathParam("id") Long id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id);
        if (employee != null) {
            updateEmployeeFromRequest(employee, request);
            return "Employee updated successfully";
        }
        return "Employee not found";
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public String deleteEmployee(@PathParam("id") Long id) {
        boolean deleted = employeeRepository.deleteById(id);
        if (deleted) {
            return "Employee deleted successfully";
        }
        return "Employee not found";
    }

    private EmployeeRequest convertToRequest(Employee employee) {
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName(employee.getFirstName());
        request.setLastName(employee.getLastName());
        request.setEmail(employee.getEmail());
        request.setMobileNo(employee.getMobileNo());
        request.setPassword(employee.getPassword());
        return request;
    }

    private void updateEmployeeFromRequest(Employee employee, EmployeeRequest request) {
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setMobileNo(request.getMobileNo());
        employee.setPassword(request.getPassword());
    }
}
