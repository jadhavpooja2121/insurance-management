package com.insurance.usermanagementservice.repositories;

import org.springframework.data.repository.CrudRepository;
import com.insurance.usermanagementservice.models.Claim;

public interface ClaimRepository extends CrudRepository<Claim, Integer> {

}
