package beans;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class Supplier {


    private Integer supplierId;
    private String supplierName;
    private String DunsNr;
    @JsonIgnore
    private List<TopLevelProduct> products;
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    private List<Link> links;

    public String getDunsNr() {
        return DunsNr;
    }

    public void setDunsNr(String dunsNr) {
        DunsNr = dunsNr;
    }

    public List<TopLevelProduct> getProducts() {
        return products;
    }

    public void setProducts(List<TopLevelProduct> products) {
        this.products = products;
    }

    public Supplier(Integer supplierId, String supplierName) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        products = new ArrayList<TopLevelProduct>();
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addProduct(TopLevelProduct product) {
        this.getProducts().add(product);
    }
}
