package com.fujitsu.ph.tsup.domain.balanon;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.fujitsu.ph.tsup.domain.balanon.CourseScheduleDao;
import com.fujitsu.ph.tsup.domain.balanon.CourseSchedule;

public class CourseScheduleServiceImpl implements CourseScheduleService {
    
    private Logger logger = LoggerFactory.getLogger(CourseScheduleServiceImpl.class);
    
    @Autowired
    private CourseScheduleDao dao;

    @Override
    public void save(CourseSchedule Id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Set<CourseSchedule> findAll() {
        
        Set<CourseSchedule> b = dao.findAll();
        if (b.isEmpty()) {
            throw new IllegalArgumentException("Input to find");
        }
        return b;
       
    }

    @Override
    public CourseSchedule findById(Long Id) {
        CourseSchedule a = dao.findById(Id);
        if (a.getId() == 'A') {
            throw new IllegalArgumentException("Can't Find Course ID");
        }
        return a;
    }

}
