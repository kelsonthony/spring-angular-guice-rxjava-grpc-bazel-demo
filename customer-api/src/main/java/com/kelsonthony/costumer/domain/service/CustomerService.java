package com.kelsonthony.costumer.domain.service;

import com.kelsonthony.costumer.domain.exception.CustomerNotFoundException;
import com.kelsonthony.costumer.domain.repository.CustomerRepository;
import com.kelsonthony.costumer.domain.entity.CustomerEntity;
import com.kelsonthony.costumer.domain.exception.EntityInUseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    private static final String MSG_CUSTOMER_IN_USE = "Cliente de código %d não pode ser removido, pois está em uso";

    @Autowired
    private CustomerRepository customerRepository;


    public CustomerEntity searchOrFail(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(
                () -> new CustomerNotFoundException(customerId));
    }

    @Transactional
    public CustomerEntity saveClient(CustomerEntity customerEntity) {
        return customerRepository.save(customerEntity);
    }

    @Transactional
    public void deleteClient(Long customerId) {
        try {
            customerRepository.deleteById(customerId);
            customerRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw  new CustomerNotFoundException(customerId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(MSG_CUSTOMER_IN_USE, customerId));
        }
    }
}
