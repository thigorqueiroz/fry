package com.thigorqueiroz.fry.domain.model.common;

import org.springframework.data.domain.AbstractAggregateRoot;

public abstract class AgregateRoot<T> extends AbstractAggregateRoot<AgregateRoot<T>> implements DomainEntity {

}
