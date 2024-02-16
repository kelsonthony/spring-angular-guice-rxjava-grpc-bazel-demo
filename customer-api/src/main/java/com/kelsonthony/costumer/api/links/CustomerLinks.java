package com.kelsonthony.costumer.api.links;

import com.kelsonthony.costumer.api.v1.controller.CustomerController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CustomerLinks {

    public Link linktoCustomers(String rel) {
        return WebMvcLinkBuilder.linkTo(CustomerController.class).withRel(rel);
    }

    public Link linktoCustomers() {
        return linktoCustomers(IanaLinkRelations.SELF.value());
    }
}
