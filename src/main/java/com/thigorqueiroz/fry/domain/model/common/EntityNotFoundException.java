package com.thigorqueiroz.fry.domain.model.common;

import java.util.UUID;

public class EntityNotFoundException extends BusinessException{
    public EntityNotFoundException(String entity) {
        super(entity + " not found", null);
    }

    public EntityNotFoundException(String entity, UUID id) {
        super(entity + "with id"+ id +" not found" , null);
    }
}
