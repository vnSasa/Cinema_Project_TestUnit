package _1._Unit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;

public class CinemaTest {
	private static Cinema cinema;
	private static Movie movie1;
	private static Movie movie2;
	private static Seance seance1;
	private static Seance seance2;
	private static Seance wrongSeance;
	private static Time time1;
	private static Time time2;
	private static String day1;
	private static String day2;

	@Rule
	public TestWatcher testWatcher = new TestWatcher() {
		protected void failed(Throwable e, org.junit.runner.Description description) {
			System.out.println("FAILED--> " + description.getMethodName());
		};

		protected void succeeded(org.junit.runner.Description description) {
			System.out.println("SUCCEED--> " + description.getMethodName());
		};
	};

	@BeforeClass
	public static void beforeTest() {
		cinema = new Cinema(Time.timeFromString("10:00"), Time.timeFromString("22:00"));
		movie1 = new Movie("Avengers: Infinity War", new Time(2, 29));
		movie2 = new Movie("Avengers: Endgame", new Time(3, 02));
		seance1 = new Seance(movie1, new Time(10, 0));
		seance2 = new Seance(movie2, new Time(12, 30));
		wrongSeance = new Seance(movie2, new Time(9, 0));
		time1 = Time.timeFromString("14:00");
		time2 = Time.timeFromString("16:00");
		day1 = "MONDAY";
		day2 = "SUNDAY";

	}

	@AfterClass
	public static void afterTest() {
		cinema = null;
		movie1 = null;
		movie2 = null;
		seance1 = null;
		seance2 = null;
		wrongSeance = null;
		time1 = null;
		time2 = null;
		day1 = null;
		day2 = null;

	}

	@Test
	public void checkSeanceBoundsShouldBeTrue() {
		boolean isTrue = cinema.checkSeanceBounds(seance1);
		Assert.assertTrue(isTrue);
	}

	@Test
	public void checkSeanceBoundsShouldBeFalse() {
		boolean isTrue = cinema.checkSeanceBounds(wrongSeance);
		Assert.assertFalse(isTrue);
	}

	@Test
	public void addMovieShouldBeTrue() {
		boolean isTrue = cinema.addMovie(movie1, time1, time2);
		Assert.assertTrue(isTrue);
	}

	@Test
	public void addSeanceShouldBeTrue1() {
		boolean isTrue = cinema.addSeance(seance1, day1);
		Assert.assertTrue(isTrue);
	}

	@Test
	public void addSeanceShouldBeTrue2() {
		boolean isTrue = cinema.addSeance(seance2, day2);
		Assert.assertTrue(isTrue);
	}

	@Test
	public void removeMovieShouldBeTrue() {
		boolean isTrue = cinema.removeMovie(movie1);
		Assert.assertTrue(isTrue);
	}

	@Test
	public void removeMovieShouldBeFalse() {
		cinema.getMoviesLibrary().add(movie1);
		System.out.println(cinema.getMoviesLibrary().toString());
		boolean isTrue = cinema.removeMovie(movie2);
		Assert.assertFalse(isTrue);
	}

	@Test
	public void removeSeanceByDayShouldBeTrue() {
		cinema.addSeance(seance1, day1);
		boolean isTrue = cinema.removeSeanceByDay(seance1, day1);
		Assert.assertTrue(isTrue);
	}

	@Test
	public void removeAllSeancesByMovieBeTrue() {
		cinema.addSeance(seance1, day1);
		boolean isTrue = cinema.removeAllSeancesByMovie("Avengers: Infinity War");
		Assert.assertTrue(isTrue);
	}

	@Test
	public void removeAllSeancesByMovieBeFalse() {
		cinema.addSeance(seance1, day1);
		boolean isTrue = cinema.removeAllSeancesByMovie("Avengers: Endgame");
		Assert.assertFalse(isTrue);
	}

}
