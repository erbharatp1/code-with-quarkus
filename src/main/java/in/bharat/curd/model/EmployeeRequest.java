package in.bharat.curd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EmployeeRequest {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String mobileNo;
  private String password;
}
