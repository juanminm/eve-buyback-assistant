
package org.github.juanminm.eba.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Appraisal {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("created")
    @Expose
    private Integer created;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("market_name")
    @Expose
    private String marketName;
    @SerializedName("totals")
    @Expose
    private Totals totals;
    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<Item>();
    @SerializedName("raw")
    @Expose
    private String raw;
    @SerializedName("parser_lines")
    @Expose
    private ParserLines parserLines;
    @SerializedName("unparsed")
    @Expose
    private Map<String, String> unparsed;
    @SerializedName("private")
    @Expose
    private Boolean _private;
    @SerializedName("price_percentage")
    @Expose
    private Integer pricePercentage;
    @SerializedName("live")
    @Expose
    private Boolean live;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public Totals getTotals() {
        return totals;
    }

    public void setTotals(Totals totals) {
        this.totals = totals;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public ParserLines getParserLines() {
        return parserLines;
    }

    public void setParserLines(ParserLines parserLines) {
        this.parserLines = parserLines;
    }

    public Map<String, String> getUnparsed() {
        return unparsed;
    }

    public void setUnparsed(Map<String, String> unparsed) {
        this.unparsed = unparsed;
    }

    public Boolean getPrivate() {
        return _private;
    }

    public void setPrivate(Boolean _private) {
        this._private = _private;
    }

    public Integer getPricePercentage() {
        return pricePercentage;
    }

    public void setPricePercentage(Integer pricePercentage) {
        this.pricePercentage = pricePercentage;
    }

    public Boolean getLive() {
        return live;
    }

    public void setLive(Boolean live) {
        this.live = live;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id)
                .append("created", created).append("kind", kind)
                .append("marketName", marketName).append("totals", totals)
                .append("items", items).append("raw", raw)
                .append("parserLines", parserLines).append("unparsed", unparsed)
                .append("_private", _private)
                .append("pricePercentage", pricePercentage).append("live", live)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(parserLines).append(created)
                .append(kind).append(unparsed).append(raw).append(totals)
                .append(pricePercentage).append(marketName).append(_private)
                .append(id).append(items).append(live).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Appraisal) == false) {
            return false;
        }
        Appraisal rhs = ((Appraisal) other);
        return new EqualsBuilder().append(parserLines, rhs.parserLines)
                .append(created, rhs.created).append(kind, rhs.kind)
                .append(unparsed, rhs.unparsed).append(raw, rhs.raw)
                .append(totals, rhs.totals)
                .append(pricePercentage, rhs.pricePercentage)
                .append(marketName, rhs.marketName)
                .append(_private, rhs._private).append(id, rhs.id)
                .append(items, rhs.items).append(live, rhs.live).isEquals();
    }

}
