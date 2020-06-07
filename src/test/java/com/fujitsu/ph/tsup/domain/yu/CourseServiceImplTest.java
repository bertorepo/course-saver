package com.fujitsu.ph.tsup.domain.yu;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

import com.fujitsu.ph.tsup.domain.yu.CourseService;
import com.fujitsu.ph.tsup.domain.yu.CourseDao;
import com.fujitsu.ph.tsup.domain.yu.Course;

@RunWith(SpringRunner.class)
public class CourseServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @TestConfiguration
    static class CourseServiceImplTestContextConfiguration {

        @Bean
        CourseService courseService() {
            return new CourseServiceImpl();
        }
    }

    @Autowired
    private CourseService service;

        @MockBean
        private CourseDao courseDao;

    @Test
    public void testSave() {
        Course c = new Course.Builder((long) 1, "SpringBoot").build();
        service.save(c);
        assertEquals(c.getId(), new Long(1));
        assertEquals(c.getName(), "SpringBoot");
    }

    @Test
    public void testSaveError() {
        Course c = new Course.Builder((long) 2, "SpringBootMVC").build();
        service.save(c);
        assertEquals(c.getId(), new Long(2));
        assertEquals(c.getName(), "SpringBootMVC");
    }

    @Test
    public void testFindById() {
        when(courseDao.findById(anyLong())).thenReturn(createCourseFindById());
        Course c = service.findById((long) 1);
        assertEquals(c.getId(), new Long(1));
    }

    private Course createCourseFindById() {
        
        return new Course.Builder(new Long(1), "SpringBoot").build();
    }

    @Test
    public void testFindByIdError() {
        when(courseDao.findById(anyLong()))
                .thenReturn(createCourseFindByIdError());
        Course c = service.findById((long) 2);
        assertEquals(c.getId(), new Long(2));
    }

    private Course createCourseFindByIdError() {
        
        return new Course.Builder(new Long(2), "SpringBootMVC").build();
    }
}
