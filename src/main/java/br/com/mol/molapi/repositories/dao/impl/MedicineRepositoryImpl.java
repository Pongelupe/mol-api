package br.com.mol.molapi.repositories.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.com.mol.molapi.dtos.medicine.AutoCompleteDTO;
import br.com.mol.molapi.entity.Medicine;
import br.com.mol.molapi.repositories.dao.IMedicineRepository;

@Repository
public class MedicineRepositoryImpl implements IMedicineRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<AutoCompleteDTO> searchQueryForAutoComplete(String query) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<AutoCompleteDTO> cq = cb.createQuery(AutoCompleteDTO.class);
		Root<Medicine> from = cq.from(Medicine.class);

		cq.select(cb.construct(AutoCompleteDTO.class, from.get("id"), from.get("comercialName")))
				.where(cb.like(from.get("comercialName"), '%' + query + '%'));

		return em.createQuery(cq).setMaxResults(20).getResultList();
	}

}
