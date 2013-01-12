package pl.healthinformator.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pl.healthinformator.model.HealthCareData;

/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the members
 * table.
 */

@RequestScoped
public class HealthInformationRESTServiceImpl implements
		HealthInformationRESTService {

	@Inject
	private EntityManager em;

	public HealthCareData lookupById(long id) {
		return em.find(HealthCareData.class, id);
	}

	public List<HealthCareData> lookupByInfo(String firstName, String lastName,
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

	public Long update(HealthCareData healthCareData) {
		healthCareData = em.merge(healthCareData);
		return healthCareData.getId();
	}

}
