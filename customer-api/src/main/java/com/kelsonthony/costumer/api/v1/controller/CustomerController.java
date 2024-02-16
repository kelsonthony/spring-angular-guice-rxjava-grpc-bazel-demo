package com.kelsonthony.costumer.api.v1.controller;


import com.kelsonthony.costumer.api.assembler.CustomerModelAssembler;
import com.kelsonthony.costumer.api.input.CustomerInput;
import com.kelsonthony.costumer.api.disassembler.CustomerInputDisassembler;
import com.kelsonthony.costumer.api.model.CustomerModel;
import com.kelsonthony.costumer.domain.entity.CustomerEntity;
import com.kelsonthony.costumer.domain.repository.CustomerRepository;
import com.kelsonthony.costumer.domain.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/v1/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PagedResourcesAssembler<CustomerEntity> pagedResourcesAssembler;

    @Autowired
    private CustomerInputDisassembler customerInputDisassembler;

    @Autowired
    private CustomerModelAssembler customerModelAssembler;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public PagedModel<CustomerModel> listClient(@PageableDefault(size = 10)Pageable pageable) {
        Page<CustomerEntity> clientPage = customerRepository.findAll(pageable);

        PagedModel<CustomerModel> clientPageModel = pagedResourcesAssembler
                .toModel(clientPage, customerModelAssembler);

        return clientPageModel;
    }

    @GetMapping("/{clientId}")
    public CustomerModel getClientId(@PathVariable Long clientId) {
        CustomerEntity client = customerService.searchOrFail(clientId);

        return customerModelAssembler.toModel(client);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerModel createClient(@RequestBody @Valid CustomerInput customerInput) {

        CustomerEntity customerEntity = customerInputDisassembler.toDomainObject(customerInput);

        customerEntity = customerService.saveClient(customerEntity);

        return customerModelAssembler.toModel(customerEntity);
    }

    @PutMapping("/{clientId}")
    public CustomerModel updateClient(@PathVariable Long clientId,
                                      @RequestBody @Valid CustomerInput customerInput) {

        CustomerEntity customerEntity = customerService.searchOrFail(clientId);

        customerInputDisassembler.copyToDomainObject(customerInput, customerEntity);

        customerEntity = customerService.saveClient(customerEntity);

        return customerModelAssembler.toModel(customerEntity);
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeClient(@PathVariable Long clientId) {
        customerService.deleteClient(clientId);
    }
}
