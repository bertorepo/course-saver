/* Created by: Jeamel de Guzman */
package com.fujitsu.ph.tsup.venue.management.model;

import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class VenueListForm {

    private Set<VenueNames> vns;
    @Size(min = 1, max = 50)
    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Set<VenueNames> getVns() {
        return vns;
    }

    public void setVns(Set<VenueNames> vns) {
        this.vns = vns;
    }

    @Override
    public String toString() {
        return "VenueListForm [vns=" + vns + "]";
    }

}
