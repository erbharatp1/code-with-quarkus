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
  @CacheName("country-cache") // Inject the specific cache
  Cache cache;

  public void refreshCountryCache() {
    // Invalidate all entries in the cache
    cache.invalidateAll().await().indefinitely();
  }

  public void updateCacheSettings() {
    // If you need to dynamically change cache settings
    if (cache instanceof CaffeineCache) {
      CaffeineCache caffeineCache = (CaffeineCache) cache;
      // Change expiration to 12 hours
      caffeineCache.setExpireAfterWrite(Duration.ofHours(12));
    }
  }
}
