package com.insurance.usermanagementservice.services;

import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import com.insurance.usermanagementservice.constants.Constants;
import com.insurance.usermanagementservice.models.InsurancePolicy;
import com.insurance.usermanagementservice.repositories.InsurancePolicyRepository;
import com.insurance.usermanagementservice.responseDOs.MessageResponseDO;
import com.insurance.usermanagementservice.responseDOs.SuccessResponseDO;

@Service
public class InsurancePolicyService {
  private static final Logger logger = LoggerFactory.getLogger(InsurancePolicyService.class);
  
  @Autowired
  private InsurancePolicyRepository insurancePolicyRepository;
  
  @Async
  public void save(InsurancePolicy insurancePolicy, CompletableFuture<ResponseEntity<?>> completableFuture) {
    insurancePolicyRepository.save(insurancePolicy);
    completableFuture.complete(
        ResponseEntity.ok(new MessageResponseDO(HttpStatus.OK.value(), Constants.SUCCESS_MESSAGE)));
  }

  @Async
  public void getInsurancePolicy(Integer id, CompletableFuture<ResponseEntity<?>> completableFuture) {
    try {
      InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(id)
          .orElseThrow(() -> new ResourceAccessException("Requested resource is not found"));
      completableFuture
          .complete(ResponseEntity.ok(new SuccessResponseDO<>(HttpStatus.OK.value(), insurancePolicy)));
    } catch (Exception e) {
      if (e instanceof ResourceAccessException) {
        completableFuture
            .complete(ResponseEntity.ok(new MessageResponseDO(Constants.RESOURCE_NOT_FOUND_CODE,
                Constants.RESOURCE_NOT_FOUND)));
      }
    }
  }

  public void getInsurancePolicies(CompletableFuture<ResponseEntity<?>> completableFuture) {
    try {
      Iterable<InsurancePolicy> insurancePolicys = insurancePolicyRepository.findAll();
      completableFuture
          .complete(ResponseEntity.ok(new SuccessResponseDO<>(HttpStatus.OK.value(), insurancePolicys)));
    } catch (Exception e) {
      if (e instanceof ResourceAccessException) {
        completableFuture
            .complete(ResponseEntity.ok(new MessageResponseDO(Constants.RESOURCE_NOT_FOUND_CODE,
                Constants.RESOURCE_NOT_FOUND)));
      }
    }
  }

  @Async
  public void updateInsurancePolicy(Integer id, InsurancePolicy insurancePolicy,
      CompletableFuture<ResponseEntity<?>> completableFuture) {
    try {
      InsurancePolicy existingInsurancePolicy = insurancePolicyRepository.findById(id)
          .orElseThrow(() -> new ResourceAccessException("Requested resource is not found"));
      if (existingInsurancePolicy != null) {
        existingInsurancePolicy.setType(insurancePolicy.getType());
        existingInsurancePolicy.setAmount(insurancePolicy.getAmount());
        existingInsurancePolicy.setPremium(insurancePolicy.getPremium());
        existingInsurancePolicy.setStartDate(insurancePolicy.getStartDate());
        existingInsurancePolicy.setEndDate(insurancePolicy.getEndDate());
        InsurancePolicy updatedinsurancePolicy = insurancePolicyRepository.save(existingInsurancePolicy);
        completableFuture.complete(
            ResponseEntity.ok(new SuccessResponseDO<>(HttpStatus.OK.value(), updatedinsurancePolicy)));
      }
    } catch (Exception e) {
      if (e instanceof ResourceAccessException) {
        completableFuture
            .complete(ResponseEntity.ok(new MessageResponseDO(Constants.RESOURCE_NOT_FOUND_CODE,
                Constants.RESOURCE_NOT_FOUND)));
      }
    }
  }

  @Async
  public void deleteInsurancePolicy(Integer id, CompletableFuture<ResponseEntity<?>> completableFuture) {
    try {
      InsurancePolicy existinginsurancePolicy = insurancePolicyRepository.findById(id)
          .orElseThrow(() -> new ResourceAccessException("Requested resource is not found"));
      if (existinginsurancePolicy != null) {
        insurancePolicyRepository.deleteById(id);
        completableFuture.complete(ResponseEntity
            .ok(new MessageResponseDO(HttpStatus.OK.value(), Constants.DELETION_SUCCESS)));
      }
    } catch (Exception e) {
      if (e instanceof IllegalArgumentException) {
        completableFuture
            .complete(ResponseEntity.ok(new MessageResponseDO(Constants.RESOURCE_NOT_FOUND_CODE,
                Constants.RESOURCE_NOT_FOUND)));
      }
    }
  }
}
