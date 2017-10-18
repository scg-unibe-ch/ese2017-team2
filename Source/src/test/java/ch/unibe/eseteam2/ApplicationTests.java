package ch.unibe.eseteam2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ch.unibe.eseteam2.controller.TripViewController;
import ch.unibe.eseteam2.controller.driver.DriverListController;
import ch.unibe.eseteam2.controller.planner.PlannerListController;
import ch.unibe.eseteam2.controller.planner.TripCreateController;
import ch.unibe.eseteam2.controller.planner.TripEditController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private DriverListController driverListController;

	@Autowired
	private PlannerListController plannerListController;

	@Autowired
	private TripCreateController tripCreateController;

	@Autowired
	private TripEditController tripEditController;

	@Autowired
	private TripViewController tripViewController;

	@Test
	public void contextLoadsDriverList() throws Exception {
		assertThat(driverListController).isNotNull();
	}

	@Test
	public void contextLoadsPlannerList() throws Exception {
		assertThat(plannerListController).isNotNull();
	}

	@Test
	public void contexLoadsTripCreate() throws Exception {
		assertThat(tripCreateController).isNotNull();
	}

	@Test
	public void contexLoadsTripEdit() throws Exception {
		assertThat(tripEditController).isNotNull();
	}

	@Test
	public void contexLoadsTripView() throws Exception {
		assertThat(tripViewController).isNotNull();
	}

	

}
