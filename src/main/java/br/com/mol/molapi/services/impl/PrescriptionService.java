package br.com.mol.molapi.services.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mol.molapi.dtos.prescription.PrescriptionDTO;
import br.com.mol.molapi.entity.Doctor;
import br.com.mol.molapi.entity.Medicine;
import br.com.mol.molapi.entity.Patient;
import br.com.mol.molapi.entity.Prescription;
import br.com.mol.molapi.entity.PrescriptionItem;
import br.com.mol.molapi.exceptions.GenericIdException;
import br.com.mol.molapi.repositories.MedicineRepository;
import br.com.mol.molapi.repositories.PrescriptionRepository;
import br.com.mol.molapi.repositories.dao.PrescriptionItemRepository;
import br.com.mol.molapi.services.IMedicineService;
import br.com.mol.molapi.services.IPatientService;

@Service
public class PrescriptionService {

	@Autowired
	private PrescriptionRepository prescriptionRepository;

	@Autowired
	private PrescriptionItemRepository prescriptionItemRepository;

	@Autowired
	private MedicineRepository medicineRepository;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private IPatientService pacientService;

	@Autowired
	private IMedicineService medicineService;

	@Autowired
	private ObjectMapper mapper;

	public String create(PrescriptionDTO prescriptionDTO) throws GenericIdException {
		if (prescriptionDTO.getId() != null && existsById(prescriptionDTO.getId())) {
			throw new GenericIdException("Prescription with id: " + prescriptionDTO.getId() + "already exists.");
		}

		Prescription prescription = mapper.convertValue(prescriptionDTO, Prescription.class);
		prescription.setDoctor(doctorService.findById(prescription.getDoctorId()));
		Patient patient = insertRetrivePatient(prescriptionDTO);
		prescription.setPatient(patient);
		prescription
				.setShelfLife(Date.from(LocalDate.now().plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		persistItensPrescription(prescription.getPrescriptionItems());

		return prescriptionRepository.save(prescription).getId();
	}

	private Patient insertRetrivePatient(PrescriptionDTO prescriptionDTO) {
		if ((prescriptionDTO.getPatientId() != null && !pacientService.existsById(prescriptionDTO.getPatientId()))
				|| prescriptionDTO.getPatient() != null) {
			return pacientService.registerPatient(prescriptionDTO.getPatient());
		} else {
			return pacientService.findById(prescriptionDTO.getPatientId()).orElseThrow(EntityNotFoundException::new);
		}

	}

	private void persistItensPrescription(Set<PrescriptionItem> prescriptonItems) {
		if (CollectionUtils.isNotEmpty(prescriptonItems)) {
			for (PrescriptionItem item : prescriptonItems) {
				if (StringUtils.isBlank(item.getMedicineId()) && item.getMedicine() != null
						&& StringUtils.isBlank(item.getMedicine().getId())) {
					item.setMedicineId(insertMedicine(item.getMedicine()));
				} else {
					item.setMedicine(medicineRepository.findById(item.getMedicineId()).orElse(null));
				}
				item.setId(prescriptionItemRepository.save(item).getId());
			}
		}
	}

	private String insertMedicine(Medicine medicine) {
		return medicineService.insertMedicine(medicine);
	}

	public PrescriptionDTO update(PrescriptionDTO prescriptionDTO) {
		if (!existsById(prescriptionDTO.getId()))
			throw new EntityNotFoundException("Prescription not found");

		Doctor doctor = findDoctor(prescriptionDTO.getDoctorId());

		Prescription prescription = mapper.convertValue(prescriptionDTO, Prescription.class);

		prescription.setDoctor(doctor);

		prescription = prescriptionRepository.save(prescription);

		return mapper.convertValue(prescription, PrescriptionDTO.class);
	}

	private Doctor findDoctor(String doctorId) {
		if (!doctorService.existsById(doctorId))
			throw new EntityNotFoundException("Doctor not found");

		return doctorService.findById(doctorId);
	}

	public PrescriptionDTO findById(String id) {
		Prescription prescription = prescriptionRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Prescription not found"));

		return mapper.convertValue(prescription, PrescriptionDTO.class);
	}

	public List<PrescriptionDTO> findByPatient(String patientId) {
		List<Prescription> prescriptions = prescriptionRepository.findByPatientId(patientId);
		return prescriptions.stream().map(p -> mapper.convertValue(p, PrescriptionDTO.class))
				.collect(Collectors.toList());
	}

	public Boolean existsById(String id) {
		return prescriptionRepository.existsById(id);
	}
}
