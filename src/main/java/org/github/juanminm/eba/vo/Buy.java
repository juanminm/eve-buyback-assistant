
package org.github.juanminm.eba.vo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Buy {

    @SerializedName("avg")
    @Expose
    private Double avg;
    @SerializedName("max")
    @Expose
    private Double max;
    @SerializedName("median")
    @Expose
    private Double median;
    @SerializedName("min")
    @Expose
    private Double min;
    @SerializedName("order_count")
    @Expose
    private Integer orderCount;
    @SerializedName("percentile")
    @Expose
    private Double percentile;
    @SerializedName("stddev")
    @Expose
    private Double stddev;
    @SerializedName("volume")
    @Expose
    private Long volume;

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMedian() {
        return median;
    }

    public void setMedian(Double median) {
        this.median = median;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Double getPercentile() {
        return percentile;
    }

    public void setPercentile(Double percentile) {
        this.percentile = percentile;
    }

    public Double getStddev() {
        return stddev;
    }

    public void setStddev(Double stddev) {
        this.stddev = stddev;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("avg", avg).append("max", max).append("median", median).append("min", min).append("orderCount", orderCount).append("percentile", percentile).append("stddev", stddev).append("volume", volume).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(volume).append(avg).append(min).append(percentile).append(median).append(max).append(orderCount).append(stddev).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Buy) == false) {
            return false;
        }
        Buy rhs = ((Buy) other);
        return new EqualsBuilder().append(volume, rhs.volume).append(avg, rhs.avg).append(min, rhs.min).append(percentile, rhs.percentile).append(median, rhs.median).append(max, rhs.max).append(orderCount, rhs.orderCount).append(stddev, rhs.stddev).isEquals();
    }

}
