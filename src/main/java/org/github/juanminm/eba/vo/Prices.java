
package org.github.juanminm.eba.vo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prices {

    @SerializedName("all")
    @Expose
    private All all;
    @SerializedName("buy")
    @Expose
    private Buy buy;
    @SerializedName("sell")
    @Expose
    private Sell sell;
    @SerializedName("strategy")
    @Expose
    private String strategy;
    @SerializedName("updated")
    @Expose
    private String updated;

    public All getAll() {
        return all;
    }

    public void setAll(All all) {
        this.all = all;
    }

    public Buy getBuy() {
        return buy;
    }

    public void setBuy(Buy buy) {
        this.buy = buy;
    }

    public Sell getSell() {
        return sell;
    }

    public void setSell(Sell sell) {
        this.sell = sell;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("all", all).append("buy", buy)
                .append("sell", sell).append("strategy", strategy)
                .append("updated", updated).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(all).append(strategy)
                .append(updated).append(buy).append(sell).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Prices) == false) {
            return false;
        }
        Prices rhs = ((Prices) other);
        return new EqualsBuilder().append(all, rhs.all)
                .append(strategy, rhs.strategy).append(updated, rhs.updated)
                .append(buy, rhs.buy).append(sell, rhs.sell).isEquals();
    }

}
