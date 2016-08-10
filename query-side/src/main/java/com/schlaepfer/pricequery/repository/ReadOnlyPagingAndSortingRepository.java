package com.schlaepfer.pricequery.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.schlaepfer.pricequery.domain.Price;

@NoRepositoryBean
public interface ReadOnlyPagingAndSortingRepository extends PagingAndSortingRepository<Price, String> {

	@Override
    @SuppressWarnings("unchecked")
    @RestResource(exported = false)//true means the capability will be offered
    Price save(Price entity);

    @Override
    @RestResource(exported = false)//false restricts the capability
    void delete(String aLong);

    @Override
    @RestResource(exported = false)
    void delete(Price entity);
	
}
