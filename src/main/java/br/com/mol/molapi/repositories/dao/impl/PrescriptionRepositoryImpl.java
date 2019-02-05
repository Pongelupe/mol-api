package br.com.mol.molapi.repositories.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.com.mol.molapi.dtos.prescription.PrescriptionPartialDTO;
import br.com.mol.molapi.entity.Prescription;
import br.com.mol.molapi.repositories.dao.IPrescriptionRepository;

@Repository
public class PrescriptionRepositoryImpl implements IPrescriptionRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<PrescriptionPartialDTO> findPrescriptionsPartialByPatientId(String patientId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PrescriptionPartialDTO> cq = cb.createQuery(PrescriptionPartialDTO.class);
		Root<Prescription> from = cq.from(Prescription.class);

		cq.select(cb.construct(PrescriptionPartialDTO.class, 
				from.get("id"), 
				from.get("observation"), 
				from.get("shelfLife"),
				from.get("createdAt"),
				from.get("doctorId"),
				from.get("patientId"),
				from.get("prescriptionType"))
				)
			.where(cb.equal(from.get("patientId"), patientId));

		return em.createQuery(cq).getResultList();
	}

}
