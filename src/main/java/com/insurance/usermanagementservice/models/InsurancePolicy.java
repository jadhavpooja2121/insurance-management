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
@Table(name = "insurance_policies")
public class InsurancePolicy {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String type;
  private Double amount;
  private Double premium;
  private Timestamp startDate;
  private Timestamp endDate;
  @OneToOne
  @JoinColumn(name = "client_id", referencedColumnName = "id")
  private Client client;
}
