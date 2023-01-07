package com.inlandnwsurf.rest.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.inlandnwsurf.rest.model.State;

@EnableScan
@Repository
public interface StateRepository extends PagingAndSortingRepository< State, String >{}
