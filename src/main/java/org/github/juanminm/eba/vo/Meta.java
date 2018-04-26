
package org.github.juanminm.eba.vo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta {

    @SerializedName("bpc")
    @Expose
    private Boolean bpc;

    public Boolean getBpc() {
        return bpc;
    }

    public void setBpc(Boolean bpc) {
        this.bpc = bpc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("bpc", bpc).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(bpc).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Meta) == false) {
            return false;
        }
        Meta rhs = ((Meta) other);
        return new EqualsBuilder().append(bpc, rhs.bpc).isEquals();
    }

}
