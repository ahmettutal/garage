package com.tutalizm.garage.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table
@Entity
@EqualsAndHashCode(callSuper = true)
public class GarageModel extends AuditEntity {

    private String name;

}
