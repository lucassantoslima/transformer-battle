package com.aequilibrium.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aequilibrium.domain.AbstractTransformer;

@Repository
public interface TransformerRepository extends JpaRepository<AbstractTransformer, Integer> {

	public abstract Optional<AbstractTransformer> findByName( final String name ); 

}
