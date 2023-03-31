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
import com.insurance.usermanagementservice.models.Client;
import com.insurance.usermanagementservice.repositories.ClientRepository;
import com.insurance.usermanagementservice.responseDOs.MessageResponseDO;
import com.insurance.usermanagementservice.responseDOs.SuccessResponseDO;

@Service
public class ClientService {
  private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

  @Autowired
  private ClientRepository clientRepository;

  @Async
  public void save(Client client, CompletableFuture<ResponseEntity<?>> completableFuture) {
    clientRepository.save(client);
    completableFuture.complete(
        ResponseEntity.ok(new MessageResponseDO(HttpStatus.OK.value(), Constants.SUCCESS_MESSAGE)));
  }

  public void getClient(Integer id, CompletableFuture<ResponseEntity<?>> completableFuture) {
    try {
      Client client = clientRepository.findById(id)
          .orElseThrow(() -> new ResourceAccessException("Requested resource is not found"));
      completableFuture
          .complete(ResponseEntity.ok(new SuccessResponseDO<>(HttpStatus.OK.value(), client)));
    } catch (Exception e) {
      if (e instanceof ResourceAccessException) {
        completableFuture
            .complete(ResponseEntity.ok(new MessageResponseDO(Constants.RESOURCE_NOT_FOUND_CODE,
                Constants.RESOURCE_NOT_FOUND)));
      }
    }
  }


}
