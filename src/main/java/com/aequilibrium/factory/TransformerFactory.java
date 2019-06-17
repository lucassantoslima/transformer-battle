package com.aequilibrium.factory;

import com.aequilibrium.domain.AbstractTransformer;
import com.aequilibrium.dto.request.TransformerRequestDto;

public class TransformerFactory {
	
	public static AbstractTransformer getTransformer( final TransformerRequestDto dto ) {
		switch( dto.getTeam() ) {
			case AUTOBOT : 
				return dto.buildAutobot();
			case DECEPTICON :
				return dto.buildDecepticon(); 
			default :
				throw new RuntimeException("Type not found"); 
		}
	}

}
