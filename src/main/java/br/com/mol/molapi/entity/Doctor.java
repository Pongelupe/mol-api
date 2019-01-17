package br.com.mol.molapi.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import br.com.mol.molapi.entity.enums.State;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Doctor extends User {

	@Column(length = 50)
	private String field;

	@Column(length = 20, unique = true)
	private String rg;

	@Enumerated(EnumType.STRING)
	@Column(length = 2)
	private State state;

	@Column(length = 30, unique = true)
	private String crm;

	@Column
	@NotNull
	@Lob
	private byte[] digitalSignature;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "doctor", orphanRemoval = true)
	private Set<Prescription> precriptions;
}
