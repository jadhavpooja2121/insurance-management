package com.insurance.usermanagementservice.validators;

import com.insurance.usermanagementservice.constants.Constants;
import com.insurance.usermanagementservice.models.Claim;
import com.insurance.usermanagementservice.models.Client;
import com.insurance.usermanagementservice.models.InsurancePolicy;
import com.insurance.usermanagementservice.responseDOs.MessageResponseDO;

public class RequestValidator {

  public static MessageResponseDO isClientRequestValid(Client client) {
    if (client == null || client.getName() == null || client.getDob() == null
        || client.getAddress() == null || client.getContactNumber() == null) {
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

  public static MessageResponseDO isInsurancePolicyRequestValid(InsurancePolicy insurancePolicy) {
    if (insurancePolicy == null || insurancePolicy.getType() == null
        || insurancePolicy.getAmount() == null || insurancePolicy.getStartDate() == null
        || insurancePolicy.getClient() == null) {
      return new MessageResponseDO(Constants.INVALID_CLIENT_DATA, Constants.INVALID_POLICY_DATA);
    }
    return null;
  }

  public static MessageResponseDO isClaimRequestValid(Claim claim) {
    if (claim == null || claim.getClaimDate() == null || claim.getClaimStatus() == null
        || claim.getDescription() == null || claim.getInsurancePolicy() == null) {
      return new MessageResponseDO(Constants.INVALID_CLAIM_DATA_CODE, Constants.INVALID_CLAIM_DATA);
    }
    return null;
  }
}
