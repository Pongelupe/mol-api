package br.com.mol.molapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import br.com.mol.molapi.entity.enums.State;
import lombok.Data;

@Entity
@Data
public class Address {

	@Id
	@Column(length = 36)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(length = 8)
	private String cep;

	@Column(length = 200)
	private String street;

	@Column(length = 50)
	private String neighborhood;

	@Column(length = 50)
	private String city;

	@Column(length = 3)
	private int number;

	@Enumerated(EnumType.STRING)
	@Column(length = 2)
	private State state;

	public String formattedAddress() {
		return street + " nº " + number + " - " + neighborhood + "\nCEP: " + cep + " - " + city + " - " + state;
	}

}
