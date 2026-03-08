package in.bharat.curd.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Table(name = "countries")
@Cacheable
public class Country extends PanacheEntity {

  @Column(unique = true, nullable = false, length = 2)
  public String code;

  @Column(nullable = false)
  public String name;

  @Column(length = 3)
  public String phoneCode;

  @Column(nullable = false)
  public boolean active = true;

  public static List<Country> listAllActive() {
    log.info("Getting all active countries");
    return list("active", true);
  }
}
