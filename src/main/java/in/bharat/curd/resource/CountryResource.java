package in.bharat.curd.resource;

import in.bharat.curd.service.CountryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/countries")
@Produces(MediaType.APPLICATION_JSON)
public class CountryResource {

  @Inject CountryService countryService;

  @GET
  public List<CountryService.CountryDTO> getCountries() {
    log.info("Get all countries");
    return countryService.getAllCountries();
  }
}
