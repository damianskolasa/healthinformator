package kolasa.wojcik.healthinformator.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import kolasa.wojcik.healthinformator.model.HealthCareData;

/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the members
 * table.
 */
@Path("/healthinformations")
@RequestScoped
public class HealthInformationRESTServiceImpl {

	@Inject
	private EntityManager em;

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("text/xml")
	public HealthCareData lookupById(@PathParam("id") long id) {
		return em.find(HealthCareData.class, id);
	}

	@GET
	@Path("/health")
	@Produces("text/xml")
	public List<HealthCareData> lookupByInfo(
			@QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName,
			@QueryParam("pesel") String pesel) {

		List<HealthCareData> healthCareDatas = em
				.createQuery(
						"select hcd from HealthCareData hcd where hcd.firstName = :firstName and hcd.lastName = :lastName and hcd.pesel = :pesel",
						HealthCareData.class)
				.setParameter("firstName", firstName)
				.setParameter("lastName", lastName)
				.setParameter("pesel", pesel).getResultList();

		return healthCareDatas;
	}

}
