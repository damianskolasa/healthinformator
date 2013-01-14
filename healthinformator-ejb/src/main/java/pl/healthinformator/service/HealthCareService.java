package pl.healthinformator.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;


import pl.healthinformator.model.HealthCareData;

@Stateless
public class HealthCareService {

	@Inject
	private EntityManager em;
	
	public HealthCareData lookupById(long id) {
		return em.find(HealthCareData.class, id);
	}
	
	public List<HealthCareData> clientHealthCareInformations(String firstName, String lastName,
			String pesel) {

		List<HealthCareData> healthCareDatas = em
				.createQuery(
						"select hcd from HealthCareData hcd where hcd.firstName = :firstName and hcd.lastName = :lastName and hcd.pesel = :pesel",
						HealthCareData.class)
				.setParameter("firstName", firstName)
				.setParameter("lastName", lastName)
				.setParameter("pesel", pesel).getResultList();

		return healthCareDatas;
	
	}

	public HealthCareData save(HealthCareData healthCareData) {
		return em.merge(healthCareData);
	}
	
}
