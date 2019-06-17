package com.aequilibrium.exception;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
public class ErrorMessage  implements Serializable {

	  private static final long serialVersionUID = -40089374255050241L;
	  
	  private int status;
	  
	  private String reasonPhrase;
	  
	  private String message;
	  
	  private List<ErrorMessageDetails> constraints;

}
