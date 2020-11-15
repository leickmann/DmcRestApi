package services;

import beans.SubProduct;
import beans.TopLevelProduct;

import java.text.DecimalFormat;

public class StringGenerator {

    private final String messageHeader = "[)>";
    private final String formatHeader = "06";

    private final char groupSeparator = 0x1D;
    private final char recordSeparator = 0x1E;
    private final char endOfTransmission = 0x04;

    private final String diSerialNr = groupSeparator + "S";
    private final String diDunsNr = groupSeparator + "13V";
    private final String diMaterialNr = groupSeparator + "P";



    public String generateLabelString(TopLevelProduct product) {

        return  messageHeader +
                recordSeparator +
                formatHeader +
                diLoopTopLevelProduct(product) +
                diDunsNr +
                product.getDunsNr() +
                diMaterialNr +
                product.getProductId() +
                diSerialNr +
                product.getSerialNr() +
                subProductsString(product) +
                recordSeparator +
                endOfTransmission;

    }

    private String diLoopTopLevelProduct(TopLevelProduct product) {
        if(product.getSubProducts() == null || product.getSubProducts().isEmpty()) {
            return "";
        }
        else {
            return groupSeparator + "F01001F";
        }
    }

    private String diLoopSubProduct(int counter, int size) {
        DecimalFormat df = new DecimalFormat("00");
        int nextElement = 0;


        if((size - counter) < 0) {
            nextElement = 0;
        }
        else {
            nextElement = 1;
        }

        return "F" +
                df.format(counter) +
                "01" +
                nextElement +
                "F";

    }

    private String subProductsString(TopLevelProduct product) {
        String subProductsString = "";
        int counter = 2;
        int subProductsSize = product.getSubProducts().size();
        DecimalFormat df = new DecimalFormat("00");

        if(product.getSubProducts() != null && !product.getSubProducts().isEmpty()) {

            for (SubProduct subProduct : product.getSubProducts()) {
                subProductsString = subProductsString +
                                diLoopSubProduct(counter, subProductsSize) +
                                diDunsNr +
                                subProduct.getDunsNr() +
                                diMaterialNr +
                                subProduct.getProductId() +
                                diSerialNr +
                                subProduct.getSerialNr()
                ;
                counter++;
            }
        }
        return subProductsString;
    }

}
