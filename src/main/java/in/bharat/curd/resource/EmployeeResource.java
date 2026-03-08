package in.bharat.curd.resource;

import in.bharat.curd.entity.Employee;
import in.bharat.curd.mapper.EmployeeMapper;
import in.bharat.curd.model.EmployeeRequest;
import in.bharat.curd.model.MessageResponse;
import in.bharat.curd.repository.EmployeeRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
@RequiredArgsConstructor
public class EmployeeResource {

  @Inject
  EmployeeRepository employeeRepository;
   @Inject EmployeeMapper employeeMapper;

  @GET
  @Path("/")
  public List<EmployeeRequest> getAllEmployees() {
    log.info("Fetching all employees");
    return employeeRepository.listAll().stream()
        .map(employeeMapper::convertToRequest)
        .collect(Collectors.toList());
  }

  @POST
  @Path("/create")
  @Transactional
  public Response createEmployee(EmployeeRequest request) {
    log.info("Creating employee: {}", request.getEmail());
    Employee employee = new Employee();
    employeeMapper.updateEmployeeFromRequest(employee, request);
    employeeRepository.persist(employee);
    return Response.status(Response.Status.CREATED)
        .entity(new MessageResponse("Employee created successfully"))
        .build();
  }

  @PUT
  @Path("/update/{id}")
  @Transactional
  public Response updateEmployee(@PathParam("id") Long id, EmployeeRequest request) {
    log.info("Updating employee with id: {}", id);
    Employee employee = employeeRepository.findById(id);
    if (employee != null) {
      employeeMapper.updateEmployeeFromRequest(employee, request);
      return Response.ok(new MessageResponse("Employee updated successfully")).build();
    }
    log.warn("Employee not found with id: {}", id);
    return Response.status(Response.Status.NOT_FOUND)
        .entity(new MessageResponse("Employee not found"))
        .build();
  }

  @DELETE
  @Path("/delete/{id}")
  @Transactional
  public Response deleteEmployee(@PathParam("id") Long id) {
    log.info("Deleting employee with id: {}", id);
    boolean deleted = employeeRepository.deleteById(id);
    if (deleted) {
      return Response.ok(new MessageResponse("Employee deleted successfully")).build();
    }
    log.warn("Employee not found with id: {}", id);
    return Response.status(Response.Status.NOT_FOUND)
        .entity(new MessageResponse("Employee not found"))
        .build();
  }
}
