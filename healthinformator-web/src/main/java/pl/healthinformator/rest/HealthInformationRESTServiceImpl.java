package pl.healthinformator.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import pl.healthinformator.model.HealthCareData;
import pl.healthinformator.service.HealthCareService;

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
	private HealthCareService healthCareService;

	public HealthCareData lookupById(long id) {
		return healthCareService.lookupById(id);
	}

	public List<HealthCareData> lookupByInfo(String firstName, String lastName,
			String pesel) {
			return healthCareService.clientHealthCareInformations(firstName, lastName, pesel);
	}

	public HealthCareData update(HealthCareData healthCareData) {
		healthCareData = healthCareService.save(healthCareData);
		return healthCareData;
	}

}
