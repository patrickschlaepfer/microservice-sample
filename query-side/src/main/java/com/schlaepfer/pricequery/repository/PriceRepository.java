package com.schlaepfer.pricequery.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@SuppressWarnings("unchecked")
@RepositoryRestResource(collectionResourceRel = "prices", path = "prices")
public interface  PriceRepository  extends ReadOnlyPagingAndSortingRepository {

}
