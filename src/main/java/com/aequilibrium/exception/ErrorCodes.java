package com.aequilibrium.exception;

public interface ErrorCodes {

  String REQUIRED_FIELD_NOT_INFORMED = "requiredField.not.informed";

  String VALIDATE_FIELD_MAX_LENGTH = "validateField.max.length";

  String VALIDATE_FIELD_MIN_LENGTH = "validateField.min.length";

  String VALIDATE_FIELD_INVALID_RANGE = "validateField.invalid.length";

  String VALIDATE_RECORD_ALREADY_REGISTERED = "validateRecord.already.registered";

  String VALIDATE_FIELD_INVALID_PATTERN = "validateField.invalid.pattern";
  
  String VALIDATE_FIELD_INVALID_INPUT = "validateField.invalid.input";
  

 //Others 
  
  String VALIDATE_RECORD_DUPLICATE_ITEM = "validate.duplicate.item";
  
  String VALIDATE_NOT_FOUND_ITEM = "validate.not.found.item";
  
  String VALIDATE_TEAM_NOT_INFORMED = "validate.team.not.informed";
  
  String MESSAGE_FILL_FIELDS_BELOW = "message.fill.fields.below";
  
  String VALIDATE_GAME_END_COMPETITORS_DESTROYED = "validate.game.end.competitors.destroyed";
}
