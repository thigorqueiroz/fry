package com.thigorqueiroz.fry.domain.model.common;

public class EntityNotFoundException extends BusinessException{
    public EntityNotFoundException(String entity) {
        super(entity + "not found", null);
    }
}
