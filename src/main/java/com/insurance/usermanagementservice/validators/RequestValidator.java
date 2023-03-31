package com.insurance.usermanagementservice.validators;

import com.insurance.usermanagementservice.constants.Constants;
import com.insurance.usermanagementservice.models.Client;
import com.insurance.usermanagementservice.responseDOs.MessageResponseDO;

public class RequestValidator {

  public static MessageResponseDO isSaveClientRequestValid(Client client) {
    if (client == null || client.getName() == null || client.getDob() == null) {
      return new MessageResponseDO(Constants.invalidClientData, Constants.invalidClientDataMessage);
    }
    return null;
  }
}
