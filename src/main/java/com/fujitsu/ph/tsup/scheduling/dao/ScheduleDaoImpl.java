package com.fujitsu.ph.tsup.scheduling.dao;

import java.time.ZonedDateTime;
import java.util.Set;

import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.VenueForm;

public class ScheduleDaoImpl implements ScheduleDao{
    
    

    @Override
    public Set<CourseSchedule> findAllScheduledCourses(ZonedDateTime scheduledStartDateTime,
            ZonedDateTime scheduledEndDateTime) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<CourseForm> findAllCourses() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<InstructorForm> findAllInstructors() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<VenueForm> findAllVenues() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void saveCourseSchedule(CourseSchedule courseSchedule) {
        // TODO Auto-generated method stub
        
    }

}
