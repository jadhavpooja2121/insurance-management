package com.insurance.usermanagementservice.controllers;

import java.util.concurrent.CompletableFuture;
import org.apache.tomcat.util.codec.binary.StringUtils;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import com.insurance.usermanagementservice.constants.Constants;
import com.insurance.usermanagementservice.models.Client;
import com.insurance.usermanagementservice.responseDOs.MessageResponseDO;
import com.insurance.usermanagementservice.services.ClientService;
import com.insurance.usermanagementservice.validators.RequestValidator;

@RestController
public class ClientController {
  private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

  @Autowired
  private ClientService clientService;

  @PostMapping(value = "/api/clients", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> saveClient(@RequestBody Client client) {
    DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<ResponseEntity<?>>();
    try {
      CompletableFuture<ResponseEntity<?>> completableFuture =
          new CompletableFuture<ResponseEntity<?>>();
      MessageResponseDO errorResponse = RequestValidator.isClientRequestValid(client);
      if (errorResponse != null) {
        completableFuture.complete(ResponseEntity.ok(errorResponse));
      }
      clientService.save(client, completableFuture);
      completableFuture.thenAccept(result -> deferredResult.setResult(result));
    } catch (Exception e) {
      logger.info("Exception occured while processing the request due to: {}", e.getMessage());
    }
    return deferredResult;
  }

  @GetMapping(value = "/api/clients/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> getClient(@PathVariable Integer id) {
    DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<ResponseEntity<?>>();
    try {
      CompletableFuture<ResponseEntity<?>> completableFuture =
          new CompletableFuture<ResponseEntity<?>>();
      Boolean isValidId = RequestValidator.isValidId(id);
      if (isValidId != null) {
        completableFuture.complete(ResponseEntity
            .ok(new MessageResponseDO(Constants.INVALID_ID, Constants.INVALID_ID_MESSAGE)));
      }
      clientService.getClient(id, completableFuture);
      completableFuture.thenAccept(result -> deferredResult.setResult(result));
    } catch (Exception e) {
      logger.info("Exception occured while processing the request due to: {}", e.getMessage());
    }
    return deferredResult;
  }
  
  @GetMapping(value = "/api/clients", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> getClients() {
    DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<ResponseEntity<?>>();
    try {
      CompletableFuture<ResponseEntity<?>> completableFuture =
          new CompletableFuture<ResponseEntity<?>>();
      clientService.getClients(completableFuture);
      completableFuture.thenAccept(result -> deferredResult.setResult(result));
    } catch (Exception e) {
      logger.info("Exception occured while processing the request due to: {}", e.getMessage());
    }
    return deferredResult;
  }
  
  @PutMapping(value = "/api/clients/{id}", produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> updateClient(@PathVariable Integer id, @RequestBody Client client) {
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
      MessageResponseDO errorResponse = RequestValidator.isClientRequestValid(client);
      if (errorResponse != null) {
        completableFuture.complete(ResponseEntity.ok(errorResponse));
        return deferredResult;
      }
      clientService.updateClient(id, client, completableFuture);
      completableFuture.thenAccept(result -> deferredResult.setResult(result));
    } catch (Exception e) {
      logger.info("Exception occured while processing the request due to: {}", e.getMessage());
    }
    return deferredResult;
  }
  
  @DeleteMapping(value = "/api/clients/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> deleteClient(@PathVariable Integer id) {
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
      clientService.deleteClient(id, completableFuture);
      completableFuture.thenAccept(result -> deferredResult.setResult(result));
    } catch (Exception e) {
      logger.info("Exception occured while processing the request due to: {}", e.getMessage());
    }
    return deferredResult;
  }
  
}
