package br.com.mol.molapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.mol.molapi.entity.enums.State;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Doctor extends User {

	@Column(length = 50)
	private String field;

	@Column(length = 20)
	private String rg;

	@Enumerated(EnumType.STRING)
	@Column(length = 2)
	private State state;

	@Column(length = 30)
	private String crm;

	@Column
	private String crmFile;

}
