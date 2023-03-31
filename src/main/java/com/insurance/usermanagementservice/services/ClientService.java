package com.insurance.usermanagementservice.services;

import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.insurance.usermanagementservice.constants.Constants;
import com.insurance.usermanagementservice.models.Client;
import com.insurance.usermanagementservice.repositories.ClientRepository;
import com.insurance.usermanagementservice.responseDOs.MessageResponseDO;

@Service
public class ClientService {
  private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

  @Autowired
  private ClientRepository clientRepository;

  @Async
  public void save(Client client, CompletableFuture<ResponseEntity<?>> completableFuture) {
    clientRepository.save(client);
    completableFuture.complete(
        ResponseEntity.ok(new MessageResponseDO(HttpStatus.OK.value(), Constants.successMessage)));
  }

}
