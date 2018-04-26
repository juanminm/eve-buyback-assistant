
package org.github.juanminm.eba.vo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Totals {

    @SerializedName("buy")
    @Expose
    private Double buy;
    @SerializedName("sell")
    @Expose
    private Double sell;
    @SerializedName("volume")
    @Expose
    private Double volume;

    public Double getBuy() {
        return buy;
    }

    public void setBuy(Double buy) {
        this.buy = buy;
    }

    public Double getSell() {
        return sell;
    }

    public void setSell(Double sell) {
        this.sell = sell;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("buy", buy).append("sell", sell)
                .append("volume", volume).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(volume).append(buy).append(sell)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Totals) == false) {
            return false;
        }
        Totals rhs = ((Totals) other);
        return new EqualsBuilder().append(volume, rhs.volume)
                .append(buy, rhs.buy).append(sell, rhs.sell).isEquals();
    }

}
