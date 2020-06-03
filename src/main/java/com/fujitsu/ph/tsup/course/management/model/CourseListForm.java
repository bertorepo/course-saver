/* Author : Angara, Mary Rose */
package com.fujitsu.ph.tsup.course.management.model;

import java.util.Set;

import javax.validation.constraints.Size;

public class CourseListForm {

    private Set<CourseNames> cns;
    @Size(min = 1, max = 50)
    private String Search;

    public String getSearch() {
        return Search;
    }

    public void setSearch(String Search) {
        this.Search = Search;
    }

    public Set<CourseNames> getCns() {
        return cns;
    }

    public void setCns(Set<CourseNames> cns) {
        this.cns = cns;
    }

    @Override
    public String toString() {
        return "CourseListForm [cns=" + cns + "]";
    }
}
