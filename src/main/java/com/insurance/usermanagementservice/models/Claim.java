package com.insurance.usermanagementservice.models;

import java.sql.Timestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "claims")
public class Claim {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String description;
  private Timestamp claimDate;
  private String claimStatus;
  @OneToOne
  @JoinColumn(name = "policy_number", referencedColumnName = "id")
  private InsurancePolicy insurancePolicy;
}
