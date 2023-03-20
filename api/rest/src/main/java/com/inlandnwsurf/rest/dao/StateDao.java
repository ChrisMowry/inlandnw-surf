package com.inlandnwsurf.rest.dao;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inlandnwsurf.rest.model.location.State;

@EnableScan
@Repository
public interface StateDao extends CrudRepository< State, String >{
	Optional<State> findByName(String name);
}
