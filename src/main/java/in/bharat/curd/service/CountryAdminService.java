package in.bharat.curd.service;

import io.quarkus.cache.Cache;
import io.quarkus.cache.CacheName;
import io.quarkus.cache.CaffeineCache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.Duration;

@ApplicationScoped
public class CountryAdminService {

  @Inject
  @CacheName("country-cache") 
  Cache cache;
  
  public void refreshCountryCache() {
    cache.invalidateAll().await()
      .indefinitely();
  }

  public void updateCacheSettings() {
    if (cache instanceof CaffeineCache) {
      CaffeineCache caffeineCache = (CaffeineCache) cache;
      caffeineCache.setExpireAfterWrite(Duration.ofHours(12));
    }
  }
}
