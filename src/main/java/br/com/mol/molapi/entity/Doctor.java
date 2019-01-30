package br.com.mol.molapi.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.mol.molapi.utils.ReportableJson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Doctor extends User implements ReportableJson {

	@Column(length = 50)
	private String field;

	@Column(length = 20, unique = true)
	private String rg;

	@Column(length = 30, unique = true)
	private String crm;

	@Column
	@NotNull
	@Lob
	private byte[] digitalSignature;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "doctor", orphanRemoval = true)
	private Set<Prescription> precriptions;

	@Override
	public Map<String, Object> toMap() {
		HashMap<String, Object> props = new HashMap<>();
		props.put("email", getEmail());
		props.put("phone", getPhone());
		props.put("name", getName());
		props.put("crm", getCrm());
		props.put("address", getAddress().formattedAddress());		
		props.put("city", getAddress().getCity());
		return props;
	}
}
