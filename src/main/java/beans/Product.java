package beans;

import java.util.ArrayList;

public abstract class Product {

    private Integer productId;
    private String productName;
    private Long dunsNr;
    private String serialNr;

    public Product() {

    }

    public Product(int productId, String productName, Long dunsNr) {
        this.productId = productId;
        this.productName = productName;
        this.dunsNr = dunsNr;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getDunsNr() {
        return dunsNr;
    }

    public void setDunsNr(long dunsNr) {
        this.dunsNr = dunsNr;
    }

    public void setDunsNr(Long dunsNr) {
        this.dunsNr = dunsNr;
    }

    public String getSerialNr() {
        return serialNr;
    }

    public void setSerialNr(String serialNr) {
        this.serialNr = serialNr;
    }
}
