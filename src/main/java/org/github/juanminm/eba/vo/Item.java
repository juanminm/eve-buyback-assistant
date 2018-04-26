
package org.github.juanminm.eba.vo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("prices")
    @Expose
    private Prices prices;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("typeID")
    @Expose
    private Integer typeID;
    @SerializedName("typeName")
    @Expose
    private String typeName;
    @SerializedName("typeVolume")
    @Expose
    private Double typeVolume;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Prices getPrices() {
        return prices;
    }

    public void setPrices(Prices prices) {
        this.prices = prices;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Double getTypeVolume() {
        return typeVolume;
    }

    public void setTypeVolume(Double typeVolume) {
        this.typeVolume = typeVolume;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("meta", meta)
                .append("name", name).append("prices", prices)
                .append("quantity", quantity).append("typeID", typeID)
                .append("typeName", typeName).append("typeVolume", typeVolume)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(quantity).append(meta).append(name)
                .append(typeName).append(typeID).append(prices)
                .append(typeVolume).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Item) == false) {
            return false;
        }
        Item rhs = ((Item) other);
        return new EqualsBuilder().append(quantity, rhs.quantity)
                .append(meta, rhs.meta).append(name, rhs.name)
                .append(typeName, rhs.typeName).append(typeID, rhs.typeID)
                .append(prices, rhs.prices).append(typeVolume, rhs.typeVolume)
                .isEquals();
    }

}
