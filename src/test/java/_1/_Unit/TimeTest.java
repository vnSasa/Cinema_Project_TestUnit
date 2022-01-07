package _1._Unit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;
import org.junit.*;
import org.junit.rules.TestWatcher;

public class TimeTest {
	private static Time time;
	private static Time plusTime1;
	private static Time plusTime2;
	private static Time plusTime3;
	private static Time sumTime1;
	private static Time sumTime2;
	private static Time sumTime3;

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

		time = new Time(15, 25);
		plusTime1 = new Time(1, 25);
		plusTime2 = new Time(1, 50);
		plusTime3 = new Time(12, 50);
		sumTime1 = new Time(16, 50);
		sumTime2 = new Time(17, 15);
		sumTime3 = new Time(4, 15);
	}

	@AfterClass
	public static void afterTest() {
		time = null;
		plusTime1 = null;
		plusTime2 = null;
		plusTime3 = null;
		sumTime1 = null;
		sumTime2 = null;
		sumTime3 = null;
	}

	@Test
	public void plusTimeShouldOutputCorrectSumTime1() {
		Time sumTime = time.plusTime(time, plusTime1);
		System.out.println("result " + sumTime.toString());
		System.out.println("expected " + sumTime1.toString());
		Assert.assertEquals(sumTime1, sumTime);
	}

	@Test
	public void plusTimeShouldOutputCorrectSumTime2() {
		Time sumTime = time.plusTime(time, plusTime2);
		System.out.println("result " + sumTime.toString());
		System.out.println("expected " + sumTime2.toString());
		Assert.assertEquals(sumTime2, sumTime);

	}

	@Test
	public void plusTimeShouldOutputCorrectSumTime3() {
		Time sumTime = time.plusTime(time, plusTime3);
		System.out.println("result " + sumTime.toString());
		System.out.println("expected " + sumTime3.toString());
		Assert.assertEquals(sumTime3, sumTime);

	}

}
