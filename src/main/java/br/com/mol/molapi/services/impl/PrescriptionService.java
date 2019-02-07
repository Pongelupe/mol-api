package br.com.mol.molapi.services.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mol.molapi.dtos.prescription.PrescriptionDTO;
import br.com.mol.molapi.dtos.prescription.PrescriptionDetailedDTO;
import br.com.mol.molapi.dtos.prescription.PrescriptionPartialDTO;
import br.com.mol.molapi.entity.Doctor;
import br.com.mol.molapi.entity.Medicine;
import br.com.mol.molapi.entity.Patient;
import br.com.mol.molapi.entity.Prescription;
import br.com.mol.molapi.entity.PrescriptionItem;
import br.com.mol.molapi.enums.Reports;
import br.com.mol.molapi.exceptions.GenericIdException;
import br.com.mol.molapi.payloads.ReportPayload;
import br.com.mol.molapi.repositories.MedicineRepository;
import br.com.mol.molapi.repositories.PrescriptionItemRepository;
import br.com.mol.molapi.repositories.PrescriptionRepository;
import br.com.mol.molapi.services.IMedicineService;
import br.com.mol.molapi.services.IPatientService;
import br.com.mol.molapi.services.IReportService;
import net.sf.jasperreports.engine.JRException;

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

	@Autowired
	private IReportService reportService;

	public String create(PrescriptionDTO prescriptionDTO)
			throws GenericIdException, JRException, JsonProcessingException {
		if (prescriptionDTO.getId() != null && existsById(prescriptionDTO.getId())) {
			throw new GenericIdException("Prescription with id: " + prescriptionDTO.getId() + "already exists.");
		}

		Prescription prescription = mapper.convertValue(prescriptionDTO, Prescription.class);
		Doctor doctor = doctorService.findById(prescription.getDoctorId());
		prescription.setDoctor(doctor);
		Patient patient = insertRetrivePatient(prescriptionDTO);
		prescription.setPatient(patient);
		prescription.setCreatedAt(new Date());
		prescription
				.setShelfLife(Date.from(LocalDate.now().plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		persistItensPrescription(prescription.getPrescriptionItems());

		prescription.setPrescriptionFile(reportService
				.generateReport(generateReportPayload(doctor, patient, prescription.getPrescriptionItems())));

		return prescriptionRepository.save(prescription).getId();
	}

	private ReportPayload generateReportPayload(Doctor doctor, Patient patient, Set<PrescriptionItem> itens)
			throws JsonProcessingException {
		HashMap<String, Object> jsonReport = new HashMap<>();
		jsonReport.put("doctor", doctor.toMap());
		jsonReport.put("patient", patient.toMap());
		jsonReport.put("medicines", getItensReport(itens));

		return new ReportPayload(mapper.writeValueAsString(jsonReport), Reports.DEFAULT);
	}

	private List<Map<String, Object>> getItensReport(Set<PrescriptionItem> itens) {
		return itens.stream().map(PrescriptionItem::toMap).collect(Collectors.toList());
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
					item.setMedicine(insertMedicine(item.getMedicine()));
				} else {
					item.setMedicine(medicineRepository.findById(item.getMedicineId()).orElse(null));
				}
				item.setId(prescriptionItemRepository.save(item).getId());
			}
		}
	}

	private Medicine insertMedicine(Medicine medicine) {
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

	public PrescriptionDetailedDTO findById(String id) {
		Prescription prescription = prescriptionRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Prescription not found"));

		PrescriptionDetailedDTO prescriptionDTO = mapper.convertValue(prescription, PrescriptionDetailedDTO.class);
		prescriptionDTO.setPrescriptionBase64(Base64.encodeBase64String(prescription.getPrescriptionFile()));
		prescriptionDTO.setDoctor(doctorService.findByIdDTO(prescription.getDoctorId()));
		return prescriptionDTO;
	}

	public List<PrescriptionPartialDTO> findByPatient(String patientId) {
		return prescriptionRepository.findPrescriptionsPartialByPatientId(patientId);
	}

	public Boolean existsById(String id) {
		return prescriptionRepository.existsById(id);
	}
}
