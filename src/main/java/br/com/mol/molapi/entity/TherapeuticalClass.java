package br.com.mol.molapi.entity;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class TherapeuticalClass extends DescriptionEntity {

}
