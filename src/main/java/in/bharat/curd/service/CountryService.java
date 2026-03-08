package in.bharat.curd.service;

import in.bharat.curd.entity.Country;
import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CountryService {

  @CacheResult(cacheName = "country-cache")
  public List<CountryDTO> getAllCountries() {
    List<Country> countries = Country.listAllActive();
    return countries.stream().map(this::toDTO).collect(Collectors.toList());
  }

  private CountryDTO toDTO(Country country) {
    return new CountryDTO(country.code, country.name);
  }

  @Transactional
  @CacheInvalidate(cacheName = "country-cache")
  public Country addCountry(String code, String name) {
    Country country = new Country();
    country.code = code.toUpperCase();
    country.name = name;
    country.persist();
    return country;
  }

  public static class CountryDTO {
    private String code;
    private String name;

    public CountryDTO(String code, String name) {
      this.code = code;
      this.name = name;
    }
  }
}
