package br.com.mol.molapi.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.mol.molapi.entity.enums.MedicineFrequencyPerDay;
import br.com.mol.molapi.utils.ReportableJson;
import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PrescriptionItem implements ReportableJson {

	@Id
	@Column(length = 36)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private Double quantity;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "medicine_id", updatable = false, nullable = true)
	private Medicine medicine;

	@Column(name = "medicine_id", updatable = false, insertable = false)
	private String medicineId;

	@Column(length = 200)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, nullable = true)
	private Prescription prescription;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 17)
	@NotNull
	private MedicineFrequencyPerDay medicineFrequencyPerDay;
	
	@Column
	@NotNull
	private int daysToTake;

	@Override
	public Map<String, Object> toMap() {
		HashMap<String, Object> props = new HashMap<>();
		Medicine medicineItem = getMedicine();
		props.put("name", medicineItem.getComercialName());
		props.put("measureType", medicineItem.getMeasureType());
		props.put("quantity", quantity);
		props.put("description", description);
		props.put("pharmaceuticalForm", medicineItem.getPharmaceuticalForm());
		props.put("email", "melao");
		return props;
	}
}
