//
//package com.fujitsu.ph.tsup.domain.lumontad;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//public class CourseServiceImplTest {
//
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    @TestConfiguration
//    static class CourseServiceImplestContextConfiguration {
//
//        @Bean
//        CourseService courseService() {
//            return new CourseServiceImpl();
//        }
//
//    }
//
//    @Autowired
//    private CourseService courseService;
//
//    @MockBean
//    private CourseDao courseDao;
//
//    @Test
//    public void testFindCourse() {
//        when(courseDao.findByid((long) 2)).thenReturn(createCourse());
//        Course c = courseService.findByid((long) 2);
//        assertEquals(c.getId(), new Long(2));
//    }
//
//    @Test
//    public void testFindCourse_Unequal() {
//        thrown.expect(IllegalArgumentException.class);
//        thrown.expectMessage("Course ID not Equal");
//        when(courseDao.findByid((long) 2)).thenReturn(createCourse_Unequal());
//
//        courseService.findByid((long) 2);
//    }
//
//    private Course createCourse() {
//        return null;
//    }
//
//    private Course createCourse_Unequal() {
//        return null;
//    }
//
//}
