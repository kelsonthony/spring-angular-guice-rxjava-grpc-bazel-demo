package com.kelsonthony.costumer.api.assembler;

import com.kelsonthony.costumer.api.v1.controller.CustomerController;
import com.kelsonthony.costumer.api.links.CustomerLinks;
import com.kelsonthony.costumer.api.model.CustomerModel;
import com.kelsonthony.costumer.domain.entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CustomerModelAssembler
        extends RepresentationModelAssemblerSupport<CustomerEntity, CustomerModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerLinks customerLinks;

    public CustomerModelAssembler() {
        super(CustomerController.class, CustomerModel.class);
    }

    @Override
    public CustomerModel toModel(CustomerEntity client) {
        CustomerModel customerModel = createModelWithId(client.getId(), client);

        modelMapper.map(client, customerModel);

        customerModel.add(customerLinks.linktoCustomers("clients"));

        return customerModel;
    }
}
