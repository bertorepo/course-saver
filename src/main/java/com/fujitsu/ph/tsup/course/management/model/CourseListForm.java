/* Author : Angara, Mary Rose */
package com.fujitsu.ph.tsup.course.management.model;

import java.util.Set;
import javax.validation.constraints.Size;

public class CourseListForm {

    private Set<CourseNames> CNs;
    @Size(min = 1, max = 50)
    private String Search;

    public String getSearch() {
        return Search;
    }

    public void setSearch(String Search) {
        this.Search = Search;
    }

    public Set<CourseNames> getCNs() {
        return CNs;
    }

    public void setCNs(Set<CourseNames> CNs) {
        this.CNs = CNs;
    }

    @Override
    public String toString() {
        return "CourseListForm [CNs=" + CNs + "]";
    }

}