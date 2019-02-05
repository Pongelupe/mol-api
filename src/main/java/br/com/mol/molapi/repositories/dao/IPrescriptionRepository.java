package br.com.mol.molapi.repositories.dao;

import java.util.List;

import br.com.mol.molapi.dtos.prescription.PrescriptionPartialDTO;

public interface IPrescriptionRepository {

	List<PrescriptionPartialDTO> findPrescriptionsPartialByPatientId(String patientId);

}
