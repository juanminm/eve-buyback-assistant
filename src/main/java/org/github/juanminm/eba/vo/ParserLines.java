
package org.github.juanminm.eba.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParserLines {

    @SerializedName("cargo_scan")
    @Expose
    private List<Integer> cargoScan = new ArrayList<Integer>();
    @SerializedName("listing")
    @Expose
    private List<Integer> listing = new ArrayList<Integer>();

    public List<Integer> getCargoScan() {
        return cargoScan;
    }

    public void setCargoScan(List<Integer> cargoScan) {
        this.cargoScan = cargoScan;
    }

    public List<Integer> getListing() {
        return listing;
    }

    public void setListing(List<Integer> listing) {
        this.listing = listing;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("cargoScan", cargoScan)
                .append("listing", listing).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cargoScan).append(listing)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ParserLines) == false) {
            return false;
        }
        ParserLines rhs = ((ParserLines) other);
        return new EqualsBuilder().append(cargoScan, rhs.cargoScan)
                .append(listing, rhs.listing).isEquals();
    }

}
