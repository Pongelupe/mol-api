package br.com.mol.molapi.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Patient extends User {
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "patient", orphanRemoval = true)
	private Set<Contraindication> contraindications;
}
