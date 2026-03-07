package in.bharat.curd.repository;


import in.bharat.curd.entity.Country;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CountryRepository implements PanacheRepository<Country> {
    
    public List<Country> findAllActive() {
        return find("active = true order by name").list();
    }
    
    public Country findByCode(String code) {
        return find("code", code.toUpperCase()).firstResult();
    }
}