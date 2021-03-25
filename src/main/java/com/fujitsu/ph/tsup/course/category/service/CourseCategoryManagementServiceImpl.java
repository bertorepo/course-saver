/**
 *  Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.category.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.course.category.dao.CourseCategoryManagementDao;
import com.fujitsu.ph.tsup.course.category.model.CourseCategory;

//=======================================================
//$Id: PR10$
//Project Name: Training Sign Up
//System Name : Course Category Management Process
//Class Name: CourseCategoryManagementServiceImpl.java
//
//<<Modification History>>
//Version | Date       | Updated by          | Content
//--------+------------+---------------------+---------------
//0.01    | 02/08/2020 | WS) A.Batongbacal   | New Creation
//0.02    | 02/15/2020 | WS) A.Batongbacal   | Update
//0.03    | 02/15/2020 | WS) J.Zamora        | Update
//0.04    | 02/15/2020 | WS) G.Cabiling      | Update
//0.05    | 02/24/2020 | WS) Z.DeGuia        | Update
//=======================================================
/**
 * <pre>
 * The implementation of course category management service
 * 
 * <pre>
 * 
 * @version 0.05
 * @author a.batongbaca
 * @author j.zamora
 * @author g.cabiling
 * @author z.deguia
 *
 */
@Service
public class CourseCategoryManagementServiceImpl implements CourseCategoryManagementService {

    private static String EXISTING = "EXISTING";
    private static String SPECIAL = "SPECIAL";
    private static String NO_CHANGE = "NO_CHANGE";
    private static String NOT_EXISTING = "NOT_EXISTING";
    private static String SUCCESSFUL = "SUCCESSFUL";
    
    @Autowired
    private CourseCategoryManagementDao courseCategoryManagementDao;

    @Override
    public String updateCourseCategory(CourseCategory courseCategory) {
        
        CourseCategory courseCategoryForm = courseCategoryManagementDao
                .findCourseCategoryById(courseCategory.getId());

        if (courseCategoryForm == null) {
            return NOT_EXISTING;
        }
        if (courseCategoryForm.getCategory().toLowerCase().equals(courseCategory.getCategory().trim().toLowerCase())
                && courseCategoryForm.getDetail().toLowerCase()
                        .equals(courseCategory.getDetail().trim().toLowerCase())) {
            return NO_CHANGE;
        }        
        if(checkForSpecialCharacter(courseCategory.getCategory().toLowerCase()) || checkForSpecialCharacter(courseCategory.getDetail().toLowerCase())) {
            return SPECIAL;
        }

        Set<CourseCategory> courseCategorySet = courseCategoryManagementDao
                .findCourseCategoryByName(courseCategory.getCategory());

        if (!courseCategorySet.isEmpty()) {
            List<CourseCategory> listOfCourseCategory = courseCategorySet.stream()
                    .collect(Collectors.toList());
            for (CourseCategory category : listOfCourseCategory) {
                if (!category.getId().equals(courseCategory.getId()) && category.getCategory().toLowerCase()
                        .equals(courseCategory.getCategory().toLowerCase())) {
                    return EXISTING;
                }
            }
        }
        this.courseCategoryManagementDao.updateCourseCategory(courseCategory);
        return SUCCESSFUL;
        
    }

    // Creates course category
    @Override
    public String createCourseCategory(CourseCategory courseCategory) {
        if(checkForSpecialCharacter(courseCategory.getCategory().toLowerCase())) {
            return SPECIAL;
        }
        CourseCategory categoryDetails = new CourseCategory.Builder(courseCategory.getCategory(), courseCategory.getDetail())
                .build();
        Set<CourseCategory> categorySize = courseCategoryManagementDao
                .findCourseCategoryByName(courseCategory.getCategory());
        List<CourseCategory> listOfCourseCat = categorySize.stream().collect(Collectors.toList());
        for (CourseCategory cat : listOfCourseCat) {
            if (cat.getCategory().toLowerCase().equals(courseCategory.getCategory().toLowerCase())) {
                return EXISTING;
            }
        }
        courseCategoryManagementDao.createCourseCategory(categoryDetails);
        return SUCCESSFUL;
    }

    @Override
    public Set<CourseCategory> findAllCourseCategory() {

        return courseCategoryManagementDao.findAllCourseCategory();
    }

    @Override
    public Set<CourseCategory> findCourseCategoryByName(String name) {

        return courseCategoryManagementDao.findCourseCategoryByName(name);
    }

    @Override
    public boolean deleteCourseCategoryById(Long id) {
        CourseCategory courseCategoryResult = courseCategoryManagementDao.findCourseCategoryById(id);
        if (courseCategoryResult != null) {
            courseCategoryManagementDao.deleteCourseCategoryById(id);
            return true;
        } else {
            return false;
        }        
    }

    @Override
    public CourseCategory findCourseCategoryById(Long id) {
        CourseCategory courseCategoryResult = courseCategoryManagementDao.findCourseCategoryById(id);
        return courseCategoryResult;
    }
    
    private boolean checkForSpecialCharacter(String input) {
        
        String specialCharactersString = "*/\"'\\:<>?|"; 
        for (int i=0; i < input.length() ; i++)
        {
            char ch = input.charAt(i);
            if(specialCharactersString.contains(Character.toString(ch))) {
                return true;
            }
        }
        return false;
    }
}
