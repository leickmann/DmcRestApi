import beans.SubProduct;
import beans.TopLevelProduct;
import org.junit.jupiter.api.Test;
import services.LabelGeneratorRessource;
import services.StringGenerator;

public class LebelStringGenTest {

    @Test
    public void labelStringGenTest() {

        TopLevelProduct prod1 = new TopLevelProduct(1234, "Fliewatüt", 9467094988L);
        SubProduct subProd1 = new SubProduct(1111, "Filter", 765592566L);
        SubProduct subProd2 = new SubProduct(2222, "Hebel", 917752772L);

        prod1.setSerialNr("001");
        subProd1.setSerialNr("2222");
        subProd2.setSerialNr("3333");

//        prod1.addSubProduct(subProd1);
//        prod1.addSubProduct(subProd2);

        StringGenerator sg = new StringGenerator();

        String string = sg.generateLabelString(prod1);

        System.out.println(string);
    }
}
