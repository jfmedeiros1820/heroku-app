package com.javaee.tests.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.javaee.tests.api.v1.mapper.CustomerMapper;
import com.javaee.tests.api.v1.model.CustomerDTO;
import com.javaee.tests.controllers.v1.CustomerController;
import com.javaee.tests.domain.Customer;
import com.javaee.tests.exceptions.ResourceNotFoundException;
import com.javaee.tests.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	private CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(custumer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(custumer);
                    customerDTO.setUrl(CustomerController.CUSTOMER_URL + "/" + custumer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(String id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        return saveAndReturnDTO(customerMapper.customerDTOToCustomer(customerDTO));
    }

    @Override
    public CustomerDTO saveCustomerByDTO(String id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);

        returnDto.setUrl(CustomerController.CUSTOMER_URL + "/" + savedCustomer.getId());

        return returnDto;
    }

    @Override
    public CustomerDTO patchCustomer(String id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {

            if(customerDTO.getFirstName() != null){
                customer.setFirstName(customerDTO.getFirstName());
            }

            if(customerDTO.getLastName() != null){
                customer.setLastName(customerDTO.getLastName());
            }

            return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
        }).orElseThrow(ResourceNotFoundException::new); //todo implement better exception handling;
    }

    @Override
    public void deleteCustomerById(String id) {
        customerRepository.deleteById(id);
    }
}
