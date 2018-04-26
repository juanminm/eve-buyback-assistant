
package org.github.juanminm.eba.vo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Evepraisal {

    @SerializedName("appraisal")
    @Expose
    private Appraisal appraisal;

    public Appraisal getAppraisal() {
        return appraisal;
    }

    public void setAppraisal(Appraisal appraisal) {
        this.appraisal = appraisal;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("appraisal", appraisal)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(appraisal).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Evepraisal) == false) {
            return false;
        }
        Evepraisal rhs = ((Evepraisal) other);
        return new EqualsBuilder().append(appraisal, rhs.appraisal).isEquals();
    }

}
