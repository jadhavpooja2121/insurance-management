package com.insurance.usermanagementservice.repositories;

import org.springframework.data.repository.CrudRepository;
import com.insurance.usermanagementservice.models.InsurancePolicy;

public interface InsurancePolicyRepository extends CrudRepository<InsurancePolicy, Integer> {

}
