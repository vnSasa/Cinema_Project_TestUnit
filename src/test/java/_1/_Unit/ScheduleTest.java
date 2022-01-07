package _1._Unit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;

public class ScheduleTest {
	private Schedule schedule;
	private Movie movie1;
	private Movie movie2;
	private Seance seance1;
	private Seance seance2;

	@Rule
	public TestWatcher testWatcher = new TestWatcher() {
		protected void failed(Throwable e, org.junit.runner.Description description) {
			System.out.println("FAILED--> " + description.getMethodName());
		};

		protected void succeeded(org.junit.runner.Description description) {
			System.out.println("SUCCEED--> " + description.getMethodName());
		};
	};

	@Before
	public void beforeTest() {
		schedule = new Schedule();
		movie1 = new Movie("Avengers: Infinity War", new Time(2, 29));
		movie2 = new Movie("Avengers: Endgame", new Time(3, 02));
		seance1 = new Seance(movie1, new Time(10, 0));
		seance2 = new Seance(movie2, new Time(12, 30));

	}

	@After
	public void afterTest() {
		schedule = null;
		movie1 = null;
		movie2 = null;
		seance1 = null;
		seance2 = null;

	}

	@Test
	public void addSeanceShouldOutputTrue() {
		boolean addSeance = schedule.addSeance(seance1);
		Assert.assertTrue(addSeance);
	}

	@Test
	public void removeSeanceShouldOutputTrue() {
		schedule.addSeance(seance1);
		boolean removeSeance = schedule.removeSeance(seance1);
		Assert.assertTrue(removeSeance);
	}

	@Test
	public void removeSeanceShouldOutputFalse() {
		schedule.addSeance(seance1);
		boolean removeSeance = schedule.removeSeance(seance2);
		Assert.assertFalse(removeSeance);
	}

}
