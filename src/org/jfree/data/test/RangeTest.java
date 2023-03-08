package org.jfree.data.test;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

public class RangeTest extends TestCase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	private Range rangeObjectUnderTest;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		rangeObjectUnderTest = new Range(-10, 10);
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public void testCentralValueShouldBeZero() {
		assertEquals("The central value of -1 and 1 is 0", 0, rangeObjectUnderTest.getCentralValue(), 0.000000001d);
	}
	//contains tests
	
	@Test
	public void testContainsReturnsFalseWhenValueBelowLower() {
		assertEquals("this value should not return true for the contains method", false, rangeObjectUnderTest.contains(-15));
	}

	@Test
	public void testContainsReturnsTrueWhenValueWithinRange() {
		assertEquals("this value should not return false for the contains method", true, rangeObjectUnderTest.contains(0));
	}

	@Test
	public void testContainsReturnsFalseWhenValueIsAboveHigherValue() {
		assertEquals("this value should not return true for the contains method", false, rangeObjectUnderTest.contains(15));
	}
	
	@Test
	public void testContainsThrowsNullPointerExceptionWhenInputValueIsNull() {
		try {
			rangeObjectUnderTest.contains((Double) null);
			fail("No exception thrown. The expected outcome was: a thrown exception of type: NullPointerException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(NullPointerException.class));
		}
	}

	
	@Test
	public void testContainsReturnsFalseWhenValueIsJustBelowLowerValue() {
		assertEquals("this value should not return true for the contains method", false, rangeObjectUnderTest.contains(-10.01));
	}

	@Test
	public void testContainsReturnsTrueWhenInputValueIsEqualToLowerValue() {
		assertEquals("this value should not return false for the contains method", true, rangeObjectUnderTest.contains(-10.0));
	}

	@Test
	public void testContainsReturnsTrueWhenValueIsEqualToUpperValue() {
		assertEquals("this value should not return false for the contains method", true, rangeObjectUnderTest.contains(10.0));
	}

	@Test
	public void testContainsReturnsFalseWhenValueIsJustAboveUpperValue() {
		assertEquals("this value should not return true for the contains method", false, rangeObjectUnderTest.contains(10.01));
	}
	
	// constrain tests
	
	@Test
	public void testConstrainReturnsLowerValueWhenInputLessThanLowerValue() {
		assertEquals("-10.0 should be returned from the constrains method", -10.0,
				rangeObjectUnderTest.constrain(-13.5), 0.000000001d);
	}

	@Test
	public void testConstrainReturnsInputValueWhenItIsWithinTHeRange() {
		assertEquals("4 should be returned from the constrains method", 4.0,
				rangeObjectUnderTest.constrain(4.0), 0.000000001d);
	}
	
	@Test
	public void testConstrainReturnsUpperValueWhenInputMoreThanHigherValue() {
		assertEquals("10 should be returned from the constrains method", 10,
				rangeObjectUnderTest.constrain(16.5), 0.000000001d);
	}
	

	@Test
	public void testConstrainThrowsNullPointerExceptionWhenInputIsNull() {
		try {
			rangeObjectUnderTest.constrain((Double) null);
			fail("No exception thrown‐Expected outcome was: a thrown exception of type: NullPointerException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(NullPointerException.class));
		}
	}
	
	
	@Test
	public void testConstrainValueJustBelowLowerValueShouldReturnInputValue() {
		assertEquals("-10 should be returned from the constrains method", -10,
				rangeObjectUnderTest.constrain(-10.01), 0.000000001d);
	}
	
	@Test
	public void testConstrainReturnsInputValueWhenInputValueIsLowerValue() {
		assertEquals("-10 should be returned from the constrains method", -10,
				rangeObjectUnderTest.constrain(-10), 0.000000001d);
	}
	
	@Test
	public void testConstrainReturnsUpperValueWhenInputValueIsUpperValue() {
		assertEquals("10 should be returned from the constrains method", 10,
				rangeObjectUnderTest.constrain(10), 0.000000001d);
	}
	
	@Test
	public void testConstrainReturnsUpperValueWhenInputValueJustAboveUpperValue() {
		assertEquals("10 should be returned from the constrains method", 10,
				rangeObjectUnderTest.constrain(10.01), 0.000000001d);
	}
	
	//getLength tests
	
	@Test
	public void testGetLengthReturns10WhenBothRangeValuesNegative() {
		rangeObjectUnderTest = new Range(-20, -10);
		assertEquals("getLength did not return 10", 10.0, rangeObjectUnderTest.getLength(), 0.000000001d);
	}
	@Test
	public void testGetLengthReturns10WhenBothRangeValuesPositive() {
		rangeObjectUnderTest = new Range(10, 20);
		assertEquals("getLength did not return 10", 10.0, rangeObjectUnderTest.getLength(), 0.000000001d);
	}
	@Test
	public void testGetLengthReturns30WhenOneValueNegativeAndOneValueIsPositive() {
		rangeObjectUnderTest = new Range(-10, 20);
		assertEquals("getLength did not return 30", 30.0, rangeObjectUnderTest.getLength(), 0.000000001d);
	}
	@Test
	public void testGetLengthReturns0WhenRangeValuesAreTheSameAndNegative() {
		rangeObjectUnderTest = new Range(-10, -10);
		assertEquals("getLength did not return 0", 0.0, rangeObjectUnderTest.getLength(), 0.000000001d);
	}
	@Test
	public void testGetLengthReturns0WhenRangeValuesAreTheSameAndPositive() {
		rangeObjectUnderTest = new Range(10, 10);
		assertEquals("getLength did not return 0", 0.0, rangeObjectUnderTest.getLength(), 0.000000001d);
	}

	@Test
	public void testGetLengthReturnsNullPointerExceptionWhenUpperValueIsNull() {
		try {
			rangeObjectUnderTest = new Range(10, (Double) null);
			rangeObjectUnderTest.getLength();
			fail("No exception thrown‐Expected outcome was: a thrown exception of type: NullPointerException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(NullPointerException.class));
		}
	}

	@Test
	public void testGetLengthReturnsNullPointerExceptionWhenLowerValueIsNull() {
		try {
			rangeObjectUnderTest = new Range((Double) null, 10);
			rangeObjectUnderTest.getLength();
			fail("No exception thrown‐Expected outcome was: a thrown exception of type: NullPointerException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(NullPointerException.class));
		}
	}

	@Test
	public void testGetLengthReturnsNullPointerExceptionWhenUpperAndLowerBothNull() {
		try {
			rangeObjectUnderTest = new Range((Double) null, (Double) null);
			rangeObjectUnderTest.getLength();
			fail("No exception thrown‐Expected outcome was: a thrown exception of type: NullPointerException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(NullPointerException.class));
		}
	}
	//intersects tests
	
	
	@Test
	public void testIntersectsLowerBelowRangeHigherAboveRange() {
		assertEquals("Intersects method should return true with these parameters", true, rangeObjectUnderTest.intersects(-20,20));
	}

	@Test
	public void testIntersectsLowerBelowRangeHigherBelowRange() {
		assertEquals("Intersects method should return false with these parameters", false, rangeObjectUnderTest.intersects(-20,-15));
	}
	
	@Test
	public void testIntersectsLowerBelowRangeHigherWithinRange() {
		assertEquals("Intersects method should return true with these parameters", true, rangeObjectUnderTest.intersects(-20,0));
	}
	
	@Test
	public void testIntersectsLowerwithinRangeHigherWithinRange() {
		assertEquals("Intersects method should return true with these parameters", true, rangeObjectUnderTest.intersects(-5,5));
	}
	
	@Test
	public void testIntersectsLowerwithinRangeHigherAboveRange() {
		assertEquals("Intersects method should return true with these parameters", true, rangeObjectUnderTest.intersects(0,20));
	}
	
	@Test
	public void testIntersectsLowerAboveRangeHigherAboveRange() {
		assertEquals("Intersects method should return false with these parameters", false, rangeObjectUnderTest.intersects(15,20));
	}
	public void testIntersectsThrowsNullPointerExceptionWhenInputValueIsNull() {
		try {
			rangeObjectUnderTest.intersects((Double) null,(Double) null);
			fail("No exception thrown. The expected outcome was: a thrown exception of type: NullPointerException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(NullPointerException.class));
		}
	}
	
	// getLowerBound tests
	@Test
	public void testGetLowerBoundReturnsLowerValueWhenLowerAndUpperBothNegative() {

		rangeObjectUnderTest = new Range(-20, -10);
		assertEquals("getLowerBound method did not return correct result", -20, rangeObjectUnderTest.getLowerBound(),0.000000001d);

	}
	
	@Test
	public void testGetLowerBoundReturnsLowerValueWhenLowerAndUpperBothPositive() {

		rangeObjectUnderTest = new Range(10, 20);
		assertEquals("getLowerBound method did not return correct result", 10, rangeObjectUnderTest.getLowerBound(),0.000000001d);

	}
	@Test
	public void testGetLowerBoundReturnsLowerValueWhenLowerNegativeAndUpperPositive() {

		rangeObjectUnderTest = new Range(-10, 20);
		assertEquals("getLowerBound method did not return correct result", -10, rangeObjectUnderTest.getLowerBound(),0.000000001d);

	}
	@Test
	public void testGetLowerBoundReturnsLowerValueWhenLowerAndUpperBothNegativeAndBothEqual() {

		rangeObjectUnderTest = new Range(-10, -10);
		assertEquals("getLowerBound method did not return correct result", -10, rangeObjectUnderTest.getLowerBound(),0.000000001d);

	}
	@Test
	public void testGetLowerBoundReturnsLowerValueWhenLowerAndUpperBothPositiveAndBothEqual() {

		rangeObjectUnderTest = new Range(10, 10);
		assertEquals("getLowerBound method did not return correct result", 10, rangeObjectUnderTest.getLowerBound(),0.000000001d);

	}
	@Test
	public void testGetLowerBoundThrowsNullPointerExceptionWhenLowerIsNegativeAndUpperIsNull() {
		try {
			rangeObjectUnderTest = new Range(-10, (Double) null);
			fail("No exception thrown-Expected outcome was: a thrown exception of type: NullPointerException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(NullPointerException.class));

		}

	}

	@Test
	public void testGetLowerBoundThrowsNullPointerExceptionWhenLowerIsNullUpperIsPositive() {

		try {
			rangeObjectUnderTest = new Range((Double) null, 10);
			fail("No exception thrown-Expected outcome was: a thrown exception of type: NullPointerException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(NullPointerException.class));

		}
	}

	@Test
	public void testGetLowerBoundhrowsNullPointerExceptionWhenUpperAndLowerIsNull() {

		rangeObjectUnderTest = null;
		try {
			rangeObjectUnderTest.getLowerBound();
			fail("No exception thrown-Expected outcome was: a thrown exception of type: NullPointerException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(NullPointerException.class));

		}
	}


}
