package beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TopLevelProduct extends Product{
    private List<SubProduct> subProducts;
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    private List<Link> links;

    public TopLevelProduct(){
        super();
        subProducts = new ArrayList();
        links = new ArrayList<Link>();
    }

    public TopLevelProduct(int productId, String productName, Long dunsNr) {
        super(productId, productName, dunsNr);
        subProducts = new ArrayList();
        links = new ArrayList<Link>();
    }

    public List<SubProduct> getSubProducts() {
        return subProducts;
    }

    public void setSubProducts(List<SubProduct> subProducts) {
        this.subProducts = subProducts;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addSubProduct(SubProduct subProduct) {
        this.subProducts.add(subProduct);
    }
}
