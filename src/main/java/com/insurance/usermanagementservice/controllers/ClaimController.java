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
import com.insurance.usermanagementservice.models.Claim;
import com.insurance.usermanagementservice.responseDOs.MessageResponseDO;
import com.insurance.usermanagementservice.services.ClaimService;
import com.insurance.usermanagementservice.validators.RequestValidator;

@RestController
public class ClaimController {
  private static final Logger logger = LoggerFactory.getLogger(ClaimController.class);

  @Autowired
  private ClaimService claimService;

  @PostMapping(value = "/api/claims", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> savePolicy(@RequestBody Claim claim) {
    DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<ResponseEntity<?>>();
    try {
      CompletableFuture<ResponseEntity<?>> completableFuture =
          new CompletableFuture<ResponseEntity<?>>();
      MessageResponseDO errorResponse = RequestValidator.isClaimRequestValid(claim);
      if (errorResponse != null) {
        completableFuture.complete(ResponseEntity.ok(errorResponse));
      }
      claimService.save(claim, completableFuture);
      completableFuture.thenAccept(result -> deferredResult.setResult(result));
    } catch (Exception e) {
      logger.info("Exception occured while processing the request due to: {}", e.getMessage());
    }
    return deferredResult;
  }

  @GetMapping(value = "/api/claims/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> getclaim(@PathVariable Integer id) {
    DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<ResponseEntity<?>>();
    try {
      CompletableFuture<ResponseEntity<?>> completableFuture =
          new CompletableFuture<ResponseEntity<?>>();
      Boolean isValidId = RequestValidator.isValidId(id);
      if (isValidId != null) {
        completableFuture.complete(ResponseEntity
            .ok(new MessageResponseDO(Constants.INVALID_ID, Constants.INVALID_ID_MESSAGE)));
      }
      claimService.getClaim(id, completableFuture);
      completableFuture.thenAccept(result -> deferredResult.setResult(result));
    } catch (Exception e) {
      logger.info("Exception occured while processing the request due to: {}", e.getMessage());
    }
    return deferredResult;
  }

  @GetMapping(value = "/api/claims", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> getclaims() {
    DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<ResponseEntity<?>>();
    try {
      CompletableFuture<ResponseEntity<?>> completableFuture =
          new CompletableFuture<ResponseEntity<?>>();
      claimService.getClaims(completableFuture);
      completableFuture.thenAccept(result -> deferredResult.setResult(result));
    } catch (Exception e) {
      logger.info("Exception occured while processing the request due to: {}", e.getMessage());
    }
    return deferredResult;
  }

  @PutMapping(value = "/api/claims/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> updateclaim(@PathVariable Integer id,
      @RequestBody Claim claim) {
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
      MessageResponseDO errorResponse = RequestValidator.isClaimRequestValid(claim);
      if (errorResponse != null) {
        completableFuture.complete(ResponseEntity.ok(errorResponse));
        return deferredResult;
      }
      claimService.updateClaim(id, claim, completableFuture);
      completableFuture.thenAccept(result -> deferredResult.setResult(result));
    } catch (Exception e) {
      logger.info("Exception occured while processing the request due to: {}", e.getMessage());
    }
    return deferredResult;
  }

  @DeleteMapping(value = "/api/claims/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> deleteclaim(@PathVariable Integer id) {
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
      claimService.deleteClaim(id, completableFuture);
      completableFuture.thenAccept(result -> deferredResult.setResult(result));
    } catch (Exception e) {
      logger.info("Exception occured while processing the request due to: {}", e.getMessage());
    }
    return deferredResult;
  }


}
