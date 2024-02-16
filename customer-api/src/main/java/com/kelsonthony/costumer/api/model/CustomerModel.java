package com.kelsonthony.costumer.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;


@Relation(collectionRelation = "customers")
@Setter
@Getter
public class CustomerModel extends RepresentationModel<CustomerModel> {

    private Long id;
    private String name;
    private BigDecimal payment;
}
