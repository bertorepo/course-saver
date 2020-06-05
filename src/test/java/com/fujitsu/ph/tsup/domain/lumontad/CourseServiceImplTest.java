//
//package com.fujitsu.ph.tsup.domain.lumontad;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.dao.DataRetrievalFailureException;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ExtendWith(SpringExtension.class)
//public class CourseServiceImplTest {
//
//    @TestConfiguration
//    static class TestContextConfiguration {
//
//        @Bean
//        CourseService courseService() {
//            /*
//             * returns the implementation that will be injected in the service
//             */
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
//    void testSave() {
//        when(courseDao.save(any(Course.class))).thenReturn(createId());
//
//        Course course = new Course.Builder(1L, "BS-ICT").build();
//
//        Long id = courseService.save(course);
//
//        assertEquals(1L, id);
//    }
//
//    @Test
//    void testFindById() {
//        Course expected = createCourse();
//        when(courseDao.findById(any(Long.class))).thenReturn(expected);
//
//        Course course = courseService.findById(1L);
//
//        assertEquals(expected.getId(), course.getId());
//        assertEquals(expected.getName(), course.getName());
//
//    }
//
//    @Test
//    void testFindById_NotFound() {
//        when(courseDao.findById(any(Long.class))).thenThrow(new DataRetrievalFailureException("error"));
//
//        Exception exception = assertThrows(CourseException.class, () -> {
//            courseService.findById(1L);
//        });
//
//        String expectedMessage = "Course not found";
//        String actualMessage = exception.getMessage();
//        assertFalse(actualMessage.contains(expectedMessage));
//    }
//
//    private Course createCourse() {
//        return new Course.Builder(1L, "BS-ICT").build();
//    }
//
//    private Long createId() {
//        return 1L;
//    }
//
//}
