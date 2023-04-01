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
import com.insurance.usermanagementservice.models.Claim;
import com.insurance.usermanagementservice.repositories.ClaimRepository;
import com.insurance.usermanagementservice.responseDOs.MessageResponseDO;
import com.insurance.usermanagementservice.responseDOs.SuccessResponseDO;

@Service
public class ClaimService {
  private static final Logger logger = LoggerFactory.getLogger(ClaimService.class);

  @Autowired
  private ClaimRepository claimRepository;

  @Async
  public void save(Claim claim, CompletableFuture<ResponseEntity<?>> completableFuture) {
    claimRepository.save(claim);
    completableFuture.complete(
        ResponseEntity.ok(new MessageResponseDO(HttpStatus.OK.value(), Constants.SUCCESS_MESSAGE)));
  }

  @Async
  public void getClaim(Integer id, CompletableFuture<ResponseEntity<?>> completableFuture) {
    try {
      Claim claim = claimRepository.findById(id)
          .orElseThrow(() -> new ResourceAccessException("Requested resource is not found"));
      completableFuture
          .complete(ResponseEntity.ok(new SuccessResponseDO<>(HttpStatus.OK.value(), claim)));
    } catch (Exception e) {
      if (e instanceof ResourceAccessException) {
        completableFuture
            .complete(ResponseEntity.ok(new MessageResponseDO(Constants.RESOURCE_NOT_FOUND_CODE,
                Constants.RESOURCE_NOT_FOUND)));
      }
    }
  }

  public void getClaims(CompletableFuture<ResponseEntity<?>> completableFuture) {
    try {
      Iterable<Claim> claims = claimRepository.findAll();
      completableFuture
          .complete(ResponseEntity.ok(new SuccessResponseDO<>(HttpStatus.OK.value(), claims)));
    } catch (Exception e) {
      if (e instanceof ResourceAccessException) {
        completableFuture
            .complete(ResponseEntity.ok(new MessageResponseDO(Constants.RESOURCE_NOT_FOUND_CODE,
                Constants.RESOURCE_NOT_FOUND)));
      }
    }
  }

  @Async
  public void updateClaim(Integer id, Claim claim,
      CompletableFuture<ResponseEntity<?>> completableFuture) {
    try {
      Claim existingClaim = claimRepository.findById(id)
          .orElseThrow(() -> new ResourceAccessException("Requested resource is not found"));
      if (existingClaim != null) {
        existingClaim.setDescription(claim.getDescription());
        existingClaim.setInsurancePolicy(claim.getInsurancePolicy());
        existingClaim.setClaimDate(claim.getClaimDate());
        existingClaim.setClaimStatus(claim.getClaimStatus());
        Claim updatedClaim = claimRepository.save(existingClaim);
        completableFuture.complete(
            ResponseEntity.ok(new SuccessResponseDO<>(HttpStatus.OK.value(), updatedClaim)));
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
  public void deleteClaim(Integer id, CompletableFuture<ResponseEntity<?>> completableFuture) {
    try {
      Claim existingClaim = claimRepository.findById(id)
          .orElseThrow(() -> new ResourceAccessException("Requested resource is not found"));
      if (existingClaim != null) {
        claimRepository.deleteById(id);
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
