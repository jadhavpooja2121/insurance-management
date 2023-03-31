package com.insurance.usermanagementservice.validators;

import com.insurance.usermanagementservice.constants.Constants;
import com.insurance.usermanagementservice.models.Client;
import com.insurance.usermanagementservice.responseDOs.MessageResponseDO;

public class RequestValidator {

  public static MessageResponseDO isSaveClientRequestValid(Client client) {
    if (client == null || client.getName() == null || client.getDob() == null) {
      return new MessageResponseDO(Constants.INVALID_CLIENT_DATA,
          Constants.INVALID_CLIENT_DATA_MESSAGE);
    }
    return null;
  }

  public static Boolean isValidId(Integer id) {
    if (id == null || id < 0) {
      return false;
    }
    return null;
  }
}
