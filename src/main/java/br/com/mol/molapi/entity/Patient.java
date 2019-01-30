package br.com.mol.molapi.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import br.com.mol.molapi.utils.ReportableJson;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Patient extends User implements ReportableJson {

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "patient", orphanRemoval = true)
	private Set<Contraindication> contraindications;

	public Patient(User user) {
		super(user);
	}

	public Map<String, Object> toMap() {
		HashMap<String, Object> props = new HashMap<>();
		props.put("name", getName());
		return props;
	}
}
