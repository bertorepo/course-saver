/**
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.category.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.course.category.dao.CourseCategoryManagementDao;
import com.fujitsu.ph.tsup.course.category.model.CourseCategory;

/**
 * @author a.batongbaca
 * @version creation: 0.01 Date: 2021-02-08
 *
 */
@Service
public class CourseCategoryManagementServiceImpl  implements CourseCategoryManagementService{

    @Autowired
    CourseCategoryManagementDao courseCategoryManagementDao;
    
    @Override
    public Set<CourseCategory> findAllCourseCategory() {

        return courseCategoryManagementDao.findAllCourseCategory();
    }

    @Override
    public Set<CourseCategory> findCourseCategoryByName(String name) {

        Set<CourseCategory> courseCategoryFormList = courseCategoryManagementDao.findCourseCategoryByName(name);
        
        try {
            
            if(courseCategoryFormList == null || courseCategoryFormList.isEmpty()) {
                
                return courseCategoryManagementDao.findAllCourseCategory();
                
            } else {
                
                return courseCategoryFormList;
                
            }
            
        } catch(Exception ex) {
            
            ex.printStackTrace();
            
        }
        
         return courseCategoryFormList;
        
    
    }
}
