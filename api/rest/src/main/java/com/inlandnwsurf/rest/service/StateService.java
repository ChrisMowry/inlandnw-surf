package com.inlandnwsurf.rest.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.inlandnwsurf.rest.exception.EntityNotFoundException;
import com.inlandnwsurf.rest.model.location.State;
import com.inlandnwsurf.rest.model.request.StateCreateRequest;
import com.inlandnwsurf.rest.repository.StateRepository;


@Service
public class StateService {
	
	private final StateRepository stateRepository;
	
	@Autowired
	StateService(StateRepository stateRepository){
		this.stateRepository = stateRepository;
	}
	
	public Iterable<State> getStates(){
		return stateRepository.findAll();
	}
	
	public State getState( String stateId ) throws EntityNotFoundException{
		Optional<State> state = stateRepository.findById(stateId);
		if( state.isPresent() ) {
			return state.get();
		} 
		String errorMessage = String.format( "No state with id: %s", stateId );
		throw new EntityNotFoundException( errorMessage );
	}
	
	public State createState( StateCreateRequest request ) {
        State state = new State();
        BeanUtils.copyProperties( request, state );
        return stateRepository.save( state );
	}
	
	public void deleteState( String stateId ) {
		stateRepository.deleteById( stateId );
	}
}
