package com.fujitsu.ph.tsup.domain.iwarat;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

@JdbcTest
@ActiveProfiles({ "postgres-test-iwarat" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CourseScheduleDaoImplTest {

    @Autowired
    private CourseScheduleDao courseScheduleDao;

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        public CourseScheduleDao courseScheduleDao() {
            return new CourseScheduleDaoImpl();
        }
    }

    @Test
    void test() {
        CourseSchedule courseSchedule1st = new CourseSchedule.Builder(1L ,8L, 9L, 10L, 5, 99999, "A").build();
        courseScheduleDao.save(courseSchedule1st);
        Long id1 = courseScheduleDao.GeneratedKeyHolderId();
        System.out.println("ID:" + id1);
       
        CourseSchedule datababseCourseSched1st = courseScheduleDao.findById(id1);
        assertEquals(8L, datababseCourseSched1st.getCourseId());
        assertEquals(9L, datababseCourseSched1st.getInstructorId());
        assertEquals(10L, datababseCourseSched1st.getVenueId());
        assertEquals(5, datababseCourseSched1st.getMinRequired());
        assertEquals(99999, datababseCourseSched1st.getMaxAllowed());
        assertEquals("A", datababseCourseSched1st.getStatus());

        CourseSchedule courseSchedule2nd = new CourseSchedule.Builder(12L, 19L, 10L, 13, 28, "B").build();
        courseScheduleDao.save(courseSchedule2nd);
        Long id2 = courseScheduleDao.GeneratedKeyHolderId();
        System.out.println("ID:" + id2);
        
        CourseSchedule datababseCourseSched2nd = courseScheduleDao.findById(id2);
        assertEquals(12L, datababseCourseSched2nd.getCourseId());
        assertEquals(19L, datababseCourseSched2nd.getInstructorId());
        assertEquals(10L, datababseCourseSched2nd.getVenueId());
        assertEquals(13, datababseCourseSched2nd.getMinRequired());
        assertEquals(28, datababseCourseSched2nd.getMaxAllowed());
        assertEquals("B", datababseCourseSched2nd.getStatus());

        CourseSchedule courseSchedule3rd = new CourseSchedule.Builder(1L, 4L, 3L, 10, 13, "C").build();
        courseScheduleDao.save(courseSchedule3rd);
        Long id3 = courseScheduleDao.GeneratedKeyHolderId();
        System.out.println("ID:" + id3);
        
        CourseSchedule datababseCourseSched3rd = courseScheduleDao.findById(id3);
        assertEquals(1L, datababseCourseSched3rd.getCourseId());
        assertEquals(4L, datababseCourseSched3rd.getInstructorId());
        assertEquals(3L, datababseCourseSched3rd.getVenueId());
        assertEquals(10, datababseCourseSched3rd.getMinRequired());
        assertEquals(13, datababseCourseSched3rd.getMaxAllowed());
        assertEquals("C", datababseCourseSched3rd.getStatus());
        
        Set<CourseSchedule> courseScheduleSet = courseScheduleDao.findAll();
        assertNotNull(courseScheduleSet.size());
    }

    @Test
    void Test_NotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            courseScheduleDao.findById(7L);
        });
    }

}
