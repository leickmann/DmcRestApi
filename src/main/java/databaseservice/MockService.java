package databaseservice;

import beans.SubProduct;
import beans.TopLevelProduct;
import beans.Supplier;

import java.util.ArrayList;
import java.util.List;

public class MockService {

    private static MockService instance;
    private List<Supplier> suppliers = new ArrayList<Supplier>();

    private MockService() {
        Supplier supplier1 = new Supplier(1111, "Bosch");
        Supplier supplier2 = new Supplier(2222, "MAN");

        TopLevelProduct prod1 = new TopLevelProduct(1234, "Fliewatüt", 9467094988L);
        TopLevelProduct prod2 = new TopLevelProduct(2345, "Motor", 2937445532L);
        TopLevelProduct prod3 = new TopLevelProduct(3456, "Achse", 744664555L);
        SubProduct subProd1 = new SubProduct(1111, "Filter", 765592566L);
        SubProduct subProd2 = new SubProduct(2222, "Hebel", 917752772L);

        prod1.setSerialNr("P001");
        prod2.setSerialNr("P001");
        prod3.setSerialNr("P111");
        subProd1.setSerialNr("S2222");
        subProd2.setSerialNr("S3333");

        prod1.addSubProduct(subProd1);
        prod1.addSubProduct(subProd2);
        supplier1.addProduct(prod1);
        supplier1.addProduct(prod2);
        supplier2.addProduct(prod3);
        suppliers.add(supplier1);
        suppliers.add(supplier2);
    }

    public static MockService getInstance() {
        if (MockService.instance == null) {
            MockService.instance = new MockService();
        }
        return MockService.instance;
    }

    public Supplier findSupplier(int id) {
        Supplier sup = null;
        for(Supplier s : suppliers) {
            if(s.getSupplierId() == id) {
               sup = s;
            }
        }
        return sup;
    }

    public List<Supplier> getAllSuppliers() {
        return this.suppliers;
    }

    public List<TopLevelProduct> getAllProductsOfSupplier(int supplierId) {
        Supplier sup = null;
        for(Supplier s : suppliers) {
            if(s.getSupplierId() == supplierId) {
                sup = s;
            }
        }
        return sup.getProducts();

    }

    public TopLevelProduct findProduct(int supplierId, int productId) {

        for (Supplier s : suppliers) {
            if (s.getSupplierId() == supplierId) {
                for (TopLevelProduct p : s.getProducts()) {
                    if (p.getProductId() == productId) {
                        return p;
                    }
                }
            }
        }
        return null;
    }

}
