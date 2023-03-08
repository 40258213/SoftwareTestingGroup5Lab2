package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jfree.date.DateUtilities;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private Values2D values2D;
	private Values2D column2D;
	private Values2D column2DWithNulls;
	private Values2D column2DEmpty;
	private Values2D row2D;
	private Values2D row2DWithNulls;
	private Values2D row2DEmpty;
	private double[] testArray;
	private Number[] numberArray;
	private double[] emptyTestArray;
	private Number[] emptyNumberArray;
	private double[][] testArray2D;
	private Number[][] numberArray2D;
	private double[][] emptyTestArray2D;
	private Number[][] emptyNumberArray2D;
	private KeyedValues keyedValues;
	private KeyedValues keyedValuesWithNegatives;
	@Before
	public void setUp() throws Exception {
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		values2D = testValues;
		testValues.addValue(1, 0, 0);
		testValues.addValue(4, 1, 0);
		testValues.addValue(6, 2, 0);
		
		DefaultKeyedValues testValues2 = new DefaultKeyedValues();
		keyedValues = testValues2;
		testValues2.addValue(0, (Number) 4);
		testValues2.addValue(1, (Number) 8);
		testValues2.addValue(2, (Number) 10);
		
		DefaultKeyedValues testValues3 = new DefaultKeyedValues();
		keyedValuesWithNegatives = testValues3;
		testValues3.addValue(0, (Number) (-3.0));
		testValues3.addValue(1, (Number) 4);
		testValues3.addValue(2, (Number) 5);
		
		DefaultKeyedValues2D columnTest = new DefaultKeyedValues2D();
		column2D = columnTest;
		columnTest.addValue(1, 0, 0);
		columnTest.addValue(5, 1, 0);
		columnTest.addValue(9, 2, 0);
		
		columnTest.addValue(3, 0, 1);
		columnTest.addValue(7, 1, 1);
		columnTest.addValue(11, 2, 1);
		
		DefaultKeyedValues2D columnTest2 = new DefaultKeyedValues2D();
		column2DWithNulls = columnTest2;
		columnTest2.addValue(1, 0, 0);
		columnTest2.addValue(null, 1, 0);
		columnTest2.addValue(2, 2, 0);
		
		columnTest2.addValue(null, 0, 1);
		columnTest2.addValue(3, 1, 1);
		columnTest2.addValue(4, 2, 1);
		
		DefaultKeyedValues2D columnTest3 = new DefaultKeyedValues2D();
		column2DEmpty = columnTest3;
		
		DefaultKeyedValues2D rowTest1 = new DefaultKeyedValues2D();
		row2D = rowTest1;
		rowTest1.addValue(2, 0, 0);
		rowTest1.addValue(3, 1, 0);

		rowTest1.addValue(4, 0, 1);
		rowTest1.addValue(6, 1, 1);

		rowTest1.addValue(8, 0, 2);
		rowTest1.addValue(9, 1, 2);
		
		DefaultKeyedValues2D rowTest2 = new DefaultKeyedValues2D();
		row2DWithNulls = rowTest2;
		rowTest2.addValue(1, 0, 0);
		rowTest2.addValue(null, 1, 0);
		rowTest2.addValue(3, 2, 0);
		
		rowTest2.addValue(2, 0, 1);
		rowTest2.addValue(3, 1, 1);
		rowTest2.addValue(5, 2, 1);

		rowTest2.addValue(2, 0, 2);
		rowTest2.addValue(4, 1, 2);
		rowTest2.addValue(7, 2, 2);
		
		DefaultKeyedValues2D rowTest3 = new DefaultKeyedValues2D();
		row2DEmpty = rowTest3;
		
		testArray = new double[] {2.0, -6.0, 11.0, 7.0, 9.0};
		numberArray = new Number[5];
		
		emptyTestArray = new double[2];
		numberArray = new Number[2];
		
		testArray2D = new double[][] {{1.0, 3.0}, {3.4, 2.0}, {9.1, 1.0} };
		numberArray2D = new Number[3][2];
	}

	@After
	public void tearDown() throws Exception {
		values2D = null;
		column2D = null;
		column2DWithNulls = null;
		column2DEmpty = null;
		row2D = null;
		row2DWithNulls = null;
		row2DEmpty = null;
		testArray = null;
		numberArray = null;
	}

	@Test
	public void testValidDataAndColumnTotal() {
		assertEquals("Wrong sum returned. It should be 11.0", 11.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.000000001d);
	}
	
	@Test
	public void testGetCumulativePercentages() {
		// setup
		DefaultKeyedValues keyvalues = new DefaultKeyedValues();
		keyvalues.addValue((Comparable) 0.0, 6.0);
		keyvalues.addValue((Comparable) 1.0, 11.0);
		keyvalues.addValue((Comparable) 2.0, 3.0);
		
		KeyedValues object_under_test = DataUtilities.getCumulativePercentages((KeyedValues) keyvalues);
		
		assertEquals((double) object_under_test.getValue(2), 1.0, 0.000000001d);
	}
	

	/**
	 * CalculateColumnTotal Test Case 1
	 */
	@Test
	public void testValidDataAndColumForCalculateColumnTotal() {
		assertEquals("Wrong sum returned. It should be 21.0", 21.0, DataUtilities.calculateColumnTotal(column2D, 1), 0.000000001d);
	}
	
	/**
	 * CalculateColumnTotal Test Case 2
	 */
	@Test
	public void testNegativeColumnForCalculateColumnTotal() {
		try {
			assertEquals("Invalid input should return 0", 0.0, DataUtilities.calculateColumnTotal(column2D, -1), 0.000000001d);
		} catch (Exception e) {
			fail("Exception: " + e.getMessage() + "\nShould return 0");
		}
	}
	
	/**
	 * CalculateColumnTotal Test Case 3
	 */
	@Test
	public void  testValidDataWithInvalidColumnForCalculateColumnTotal() {
		try {
			assertEquals("Method expected to return 0 when provided a column which does not exist", 0.0, DataUtilities.calculateColumnTotal(column2D, 7), 0.000000001d);
		} catch(Exception e) {
			fail("Exception for invalid column input should return 0: " + e.getMessage());
		}
	}
	
	/**
	 * CalculateColumnTotal Test Case 4
	 */
	@Test
	public void testNullDataAndPositiveColumnForCalculateColumnTotal() {
		try {
			DataUtilities.calculateColumnTotal(null, 2);
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown. Exception: " + e.getMessage(), e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	/**
	 * CalculateColumnTotal Test Case 5
	 */
	@Test
	public void testNullDataAndNegativeColumnForCalculateColumnTotal() {
		try {
			DataUtilities.calculateColumnTotal(null,-1);
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown. Exception: " + e.getMessage(), e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	/**
	 * CalculateColumnTotal Test Case 6
	 */
	@Test
	public void  testEmptyDataAndNegativeColumnForCalculateColumnTotal() {
		try {
			assertEquals("Expected return of 0 with empty data input", 0.0, DataUtilities.calculateColumnTotal(column2DEmpty, -1), 0.000000001d);
		} catch (Exception e) {
			fail("Exception: " + e.getMessage());
		}
	}
	
	/**
	 * CalculateColumnTotal Test Case 7
	 */
	@Test
	public void  testEmptyDataAndPositiveColumnForCalculateColumnTotal() {
		try {
			assertEquals("Expected return of 0 with empty data input", 0.0, DataUtilities.calculateColumnTotal(column2DEmpty, 1), 0.000000001d);
		} catch (Exception e) {
			fail("Exception: " + e.getMessage());
		}
	}
	
	/**
	 * CalculateColumnTotal Test Case 8
	 */
	@Test
	public void testValidAndNullValuesinDataWithPositiveColumnForCalculateColumnTotal() {
		try {
			assertEquals("Expected return of 0 as data contains null values", 0.0, DataUtilities.calculateColumnTotal(column2DWithNulls, 1), 0.000000001d);
		} catch(Exception e) {
			fail("Exception: " + e.getMessage());
		}
	}
	
	/**
	 * CalculateColumnTotal Test Case 9
	 */
	@Test
	public void testValidAndNullValuesInDataWithNegativeColumnForCalculateColumnTotal() {
		try {
			assertEquals("Expected return of 0 as data contains null values and column is invalid", 0.0, DataUtilities.calculateColumnTotal(column2DWithNulls, -1), 0.000000001d);
		} catch(Exception e) {
			fail("Exception: " + e.getMessage());
		}
	}
	
	/**
	 * CalculateRowTotal Test Case 1
	 */
	@Test
	public void testValidDataAndRowForCalculateRowTotal() {
		assertEquals("Wrong sum returned. It should be 18.0", 18.0, DataUtilities.calculateRowTotal(row2D, 1), 0.000000001d);
	}
	
	/**
	 * CalculateRowTotal Test Case 2
	 */
	@Test
	public void testNegativeRowForCalculateRowTotal() {
		try {
			assertEquals("Invalid input should return 0", 0.0, DataUtilities.calculateRowTotal(row2D, -1), 0.000000001d);
		} catch (Exception e) {
			fail("Exception: " + e.getMessage() + "\nShould return 0");
		}
	}
	
	/**
	 * CalculateRowTotal Test Case 3
	 */
	@Test
	public void  testValidDataWithInvalidRowForCalculateRowTotal() {
		try {
			assertEquals("Method expected to return 0 when provided a column which does not exist", 0.0, DataUtilities.calculateRowTotal(column2D, 7), 0.000000001d);
		} catch(Exception e) {
			fail("Exception for invalid column input should return 0: " + e.getMessage());
		}
	}
	
	/**
	 * CalculateRowTotal Test Case 4
	 */
	@Test
	public void testNullDataAndPositiveRowForCalculateRowTotal() {
		try {
			DataUtilities.calculateRowTotal(null, 2);
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown. Exception : " + e.getMessage(), e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	/**
	 * CalculateRowTotal Test Case 5
	 */
	@Test
	public void testNullDataAndNegativeRowForCalculateRowotal() {
		try {
			DataUtilities.calculateRowTotal(null,-1);
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	/**
	 * CalculateRowTotal Test Case 6
	 */
	@Test
	public void  testEmptyDataAndNegativeRowForCalculateRowTotal() {
		try {
			assertEquals("Expected return of 0 with empty data input", 0.0, DataUtilities.calculateRowTotal(row2DEmpty, -1), 0.000000001d);
		} catch (Exception e) {
			fail("Exception: " + e.getMessage());
		}
	}
	
	/**
	 * CalculateRowTotal Test Case 7
	 */
	@Test
	public void  testEmptyDataAndPositiveRowForCalculateRowTotal() {
		try {
			assertEquals("Expected return of 0 with empty data input", 0.0, DataUtilities.calculateRowTotal(row2DEmpty, 1), 0.000000001d);
		} catch (Exception e) {
			fail("Exception: " + e.getMessage());
		}
	}
	
	/**
	 * CalculateRowTotal Test Case 8
	 */
	@Test
	public void testValidAndNullValuesinDataWithPositiveRowForCalculateRowTotal() {
		try {
			assertEquals("Expected return of 7", 7.0, DataUtilities.calculateRowTotal(row2DWithNulls, 1), 0.000000001d);
		} catch(Exception e) {
			fail("Exception: " + e.getMessage());
		}
	}
	
	/**
	 * CalculateRowTotal Test Case 9
	 */
	@Test
	public void testValidAndNullValuesInDataWithNegativeRowForCalculateRowTotal() {
		try {
			assertEquals("Expected return of 0 as row is invalid", 0.0, DataUtilities.calculateRowTotal(row2DWithNulls, -1), 0.000000001d);
		} catch(Exception e) {
				fail("Exception: " + e.getMessage());
		}
	}
	
	/**
	 * CalculateRowTotal Test Case 10
	 */
	@Test
	public void testValidValuesinDataWithPositiveRowForCalculateRowTotal() {
		try {
			assertEquals("Expected return of 15", 15.0, DataUtilities.calculateRowTotal(row2DWithNulls, 2), 0.000000001d);
		} catch(Exception e) {
			fail("Exception: " + e.getMessage());
		}
	}
	
	/**
	 * CreateNumberArray Test Case 1
	 */
	@Test
	public void testValidDoubleArrayForCreateNumberArray() {
		numberArray = DataUtilities.createNumberArray(testArray);
		assertEquals("Incorrect value in number array", testArray[0], numberArray[0]);
		assertEquals("Incorrect value in number array", testArray[1], numberArray[1]);
		assertEquals("Incorrect value in number array", testArray[2], numberArray[2]);
		assertEquals("Incorrect value in number array", testArray[3], numberArray[3]);
		assertEquals("Incorrect value in number array", testArray[4], numberArray[4]);
	}
	
	/**
	 * CreateNumberArray Test Case 2
	 */
	@Test
	public void testNullDataNumberArray() {
		try {
			DataUtilities.createNumberArray(null);
			fail();
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	/**
	 * CreateNumberArray Test Case 3
	 */
	@Test
	public void testEmptyArrayCreateNumberArray() {
		try {
			emptyNumberArray = DataUtilities.createNumberArray(emptyTestArray);
		} catch(Exception e) {
			fail("Unable to create empty number array");
		}
	}
	
	/**
	 * CreateNumberArray2D Test Case 1
	 */
	@Test
	public void testValidInputForCreateNumberArray2D() {

		numberArray2D = DataUtilities.createNumberArray2D(testArray2D);
		assertEquals("Incorrect value in number array", testArray2D[0][0], numberArray2D[0][0]);;
		assertEquals("Incorrect value in number array", testArray2D[0][1], numberArray2D[0][1]);;
		assertEquals("Incorrect value in number array", testArray2D[1][0], numberArray2D[1][0]);;
		assertEquals("Incorrect value in number array", testArray2D[1][1], numberArray2D[1][1]);;
		assertEquals("Incorrect value in number array", testArray2D[2][0], numberArray2D[2][0]);;
		assertEquals("Incorrect value in number array", testArray2D[2][1], numberArray2D[2][1]);
	}
	
	/**
	 * CreateNumberArray2D Test Case 2
	 */
	@Test
	public void testNullNumberArray2D() {
		try {
			DataUtilities.createNumberArray2D(null);
			fail();
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	/**
	 * CreateNumberArray2D Test Case 3
	 */
	@Test
	public void testEmptyDataInputForCreateNumberArray2D() {
		try {
			emptyNumberArray2D = DataUtilities.createNumberArray2D(emptyTestArray2D);
		} catch(Exception e) {
			fail("Unable to create empty number array2D, exception: " + e.getMessage());
		}
	}
	
	
	/**
	 * getCumulativePercentages Test Case 1
	 */
	@Test
	public void testValidCumulativePercentages() {

		keyedValues = DataUtilities.getCumulativePercentages(keyedValues);
	
		assertEquals("Incorrect cumulative percentage", 0.181, keyedValues.getValue(0));
		assertEquals("Incorrect cumulative percentage", 0.545, keyedValues.getValue(1));
		assertEquals("Incorrect cumulative percentage", 1.0, keyedValues.getValue(2));
	}	
	
	/**
	 * getCumulativePercentages Test Case 2
	 */
	@Test
	public void testNullCumulativePercentages() {
		try {
			DataUtilities.createNumberArray2D(null);
			fail();
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	/**
	 * getCumulativePercentages Test Case 3
	 */
	@Test
	public void testNegativeValuesForCumulativePercentages() {

		keyedValuesWithNegatives = DataUtilities.getCumulativePercentages(keyedValuesWithNegatives);
	
		try {
			Number index0 = keyedValuesWithNegatives.getValue(0);
			Number index1 = keyedValuesWithNegatives.getValue(1);
			Number index2 = keyedValuesWithNegatives.getValue(2);
			fail("A calculation has been completed when it should be impossible");
		} catch(Exception e) {
		}
	}
	
	/**
	 * getCumulativePercentages Test Case 4
	 */
	@Test
	public void testEmptyCumulativePercentages() {
		double [][] array = new double[0][0];
		try {
			DataUtilities.createNumberArray2D(array);
		} catch (Exception e) {
			fail("Exception occurred unexpectedly: " + e.getMessage());
		}
	}

	
}
