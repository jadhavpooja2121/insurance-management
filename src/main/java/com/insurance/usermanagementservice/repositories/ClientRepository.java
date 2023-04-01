package com.insurance.usermanagementservice.repositories;

import org.springframework.data.repository.CrudRepository;
import com.insurance.usermanagementservice.models.Client;

public interface ClientRepository extends CrudRepository<Client, Integer> {

}
