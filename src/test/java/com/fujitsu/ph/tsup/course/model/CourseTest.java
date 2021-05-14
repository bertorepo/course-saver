package com.fujitsu.ph.tsup.course.model;
/**
* <pre>
* The Unit Testing of course model
* <pre>
* @version 0.01
* @author st.diaz
* @author st.diaz
* @author D.escala
*/

//=======================================================
//$Id: PR04$
//Project Name: Training Sign Up
//Class Name: CourseTest.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+-----------------+---------------
//0.01    | 04/26/2021 | WS) St.Diaz      | New Creation
//0.02    | 05/10/2021 | WS) D.Escala     | Updated
//
//=======================================================
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class CourseTest {

	/**
     * <pre>
     * testBuilderOne with valid values
     * <pre>
     */
	@Test
	void testBuilderOne() {
		Course expected = createBuilderOne();
		
		assertNotNull(expected);
		assertEquals(expected.getName(), "Javascript");
		assertEquals(expected.getDetail(), "Details");
		assertEquals(expected.getIsMandatory(), "Yes");
		assertEquals(expected.getDeadline(), "Monthly");
		assertEquals(expected.getCourse_category_id(), 3);
	}
	
	/**
     * <pre>
     * testBuilderTwo with valid values
     * <pre>
     */
	@Test
	void testBuilderTwo() {
		Course expected = createBuilderTwo();
		
		assertNotNull(expected);
		assertEquals(expected.getName(), "Python");
		assertEquals(expected.getDetail(), "Details");
		assertEquals(expected.getIsMandatory(), "No");
		assertEquals(expected.getDeadline(), "-");
		assertEquals(expected.getCourse_category_id(), 4);
	}

	/**
	 * <pre>
	 * Creates a builder method instance for the first builder
	 * 
	 * <pre>
	 * 
	 */
	private Course createBuilderOne() {
		return new Course.Builder("Javascript", "Details", "Yes", "Monthly",3).build();
	}
	
	/**
	 * <pre>
	 * Creates a builder method instance for the second builder
	 * 
	 * <pre>
	 * 
	 */
	private Course createBuilderTwo() {
		return new Course.Builder("Python", "Details", "No", "-",4).build();
	}
}
