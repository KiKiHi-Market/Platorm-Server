package org.jiyoung.kikihi.platform.adapter.out.redis.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ProductOptionRedisRepository extends CrudRepository<ProductOptionRedisHash, Long> {

    List<ProductOptionRedisHash> findByProductId(Long productId);

}
