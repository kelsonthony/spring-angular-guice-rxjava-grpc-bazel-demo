package com.kelsonthony.costumer.api.disassembler;

import com.kelsonthony.costumer.api.input.CustomerInput;
import com.kelsonthony.costumer.domain.entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public CustomerEntity toDomainObject(CustomerInput customerInput) {
        return modelMapper.map(customerInput, CustomerEntity.class);
    }

    public void copyToDomainObject(CustomerInput customerInput, CustomerEntity customerEntity) {
        modelMapper.map(customerInput, customerEntity);
    }
}
