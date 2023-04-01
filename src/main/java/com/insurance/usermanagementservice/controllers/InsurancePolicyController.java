package com.insurance.usermanagementservice.controllers;

import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import com.insurance.usermanagementservice.constants.Constants;
import com.insurance.usermanagementservice.models.InsurancePolicy;
import com.insurance.usermanagementservice.responseDOs.MessageResponseDO;
import com.insurance.usermanagementservice.services.InsurancePolicyService;
import com.insurance.usermanagementservice.validators.RequestValidator;

@RestController
public class InsurancePolicyController {
  private static final Logger logger = LoggerFactory.getLogger(InsurancePolicyController.class);
  
  @Autowired
  private InsurancePolicyService insurancePolicyService;
  
  @PostMapping(value = "/api/policies", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> savePolicy(@RequestBody InsurancePolicy InsurancePolicy) {
    DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<ResponseEntity<?>>();
    try {
      CompletableFuture<ResponseEntity<?>> completableFuture =
          new CompletableFuture<ResponseEntity<?>>();
      MessageResponseDO errorResponse = RequestValidator.isInsurancePolicyRequestValid(InsurancePolicy);
      if (errorResponse != null) {
        completableFuture.complete(ResponseEntity.ok(errorResponse));
      }
      insurancePolicyService.save(InsurancePolicy, completableFuture);
      completableFuture.thenAccept(result -> deferredResult.setResult(result));
    } catch (Exception e) {
      logger.info("Exception occured while processing the request due to: {}", e.getMessage());
    }
    return deferredResult;
  }

  @GetMapping(value = "/api/policies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> getInsurancePolicy(@PathVariable Integer id) {
    DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<ResponseEntity<?>>();
    try {
      CompletableFuture<ResponseEntity<?>> completableFuture =
          new CompletableFuture<ResponseEntity<?>>();
      Boolean isValidId = RequestValidator.isValidId(id);
      if (isValidId != null) {
        completableFuture.complete(ResponseEntity
            .ok(new MessageResponseDO(Constants.INVALID_ID, Constants.INVALID_ID_MESSAGE)));
      }
      insurancePolicyService.getInsurancePolicy(id, completableFuture);
      completableFuture.thenAccept(result -> deferredResult.setResult(result));
    } catch (Exception e) {
      logger.info("Exception occured while processing the request due to: {}", e.getMessage());
    }
    return deferredResult;
  }
  
  @GetMapping(value = "/api/policies", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> getpolicies() {
    DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<ResponseEntity<?>>();
    try {
      CompletableFuture<ResponseEntity<?>> completableFuture =
          new CompletableFuture<ResponseEntity<?>>();
      insurancePolicyService.getInsurancePolicies(completableFuture);
      completableFuture.thenAccept(result -> deferredResult.setResult(result));
    } catch (Exception e) {
      logger.info("Exception occured while processing the request due to: {}", e.getMessage());
    }
    return deferredResult;
  }
  
  @PutMapping(value = "/api/policies/{id}", produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> updateInsurancePolicy(@PathVariable Integer id, @RequestBody InsurancePolicy InsurancePolicy) {
    DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<ResponseEntity<?>>();
    try {
      CompletableFuture<ResponseEntity<?>> completableFuture =
          new CompletableFuture<ResponseEntity<?>>();
      Boolean isValidId = RequestValidator.isValidId(id);
      if (isValidId != null) {
        completableFuture.complete(ResponseEntity
            .ok(new MessageResponseDO(Constants.INVALID_ID, Constants.INVALID_ID_MESSAGE)));
        return deferredResult;
      }
      MessageResponseDO errorResponse = RequestValidator.isInsurancePolicyRequestValid(InsurancePolicy);
      if (errorResponse != null) {
        completableFuture.complete(ResponseEntity.ok(errorResponse));
        return deferredResult;
      }
      insurancePolicyService.updateInsurancePolicy(id, InsurancePolicy, completableFuture);
      completableFuture.thenAccept(result -> deferredResult.setResult(result));
    } catch (Exception e) {
      logger.info("Exception occured while processing the request due to: {}", e.getMessage());
    }
    return deferredResult;
  }
  
  @DeleteMapping(value = "/api/policies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> deleteInsurancePolicy(@PathVariable Integer id) {
    DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<ResponseEntity<?>>();
    try {
      CompletableFuture<ResponseEntity<?>> completableFuture =
          new CompletableFuture<ResponseEntity<?>>();
      Boolean isValidId = RequestValidator.isValidId(id);
      if (isValidId != null) {
        completableFuture.complete(ResponseEntity
            .ok(new MessageResponseDO(Constants.INVALID_ID, Constants.INVALID_ID_MESSAGE)));
        return deferredResult;
      }
      insurancePolicyService.deleteInsurancePolicy(id, completableFuture);
      completableFuture.thenAccept(result -> deferredResult.setResult(result));
    } catch (Exception e) {
      logger.info("Exception occured while processing the request due to: {}", e.getMessage());
    }
    return deferredResult;
  }
  

}
