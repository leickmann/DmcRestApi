package services;


import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.krysalis.barcode4j.BarcodeException;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.output.eps.EPSCanvasProvider;
import org.xml.sax.SAXException;

import javax.servlet.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class DmcGenerator {

    String cfgFilePath;
    BarcodeGenerator gen;
    Configuration cfg;
    DefaultConfigurationBuilder builder;
    ServletContext context;


    public DmcGenerator() {

        //Setzen des Config-Files in Anhangigkeit des tatsächlichen Pfades des Servlets

        String relativePath = "dmc-cfg.xml";
        String cfgFilePath = this.getClass().getResource("/../").getPath() + relativePath;

        //Barcode4J Konfigurieren
        builder = new DefaultConfigurationBuilder();

        try {
            cfg = builder.buildFromFile(new File(cfgFilePath));
            gen = BarcodeUtil.getInstance().createBarcodeGenerator(cfg);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BarcodeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /* Erzeugt ein DMC-PNG Temp-File
    * @return file-Objekt --> PNG-tmp
    * @params String content --> Text den der DMC beinhalten soll
    */
    public File getDmcAsFile(String content) {
        File file = null;
        try {

            file = File.createTempFile("dmc", ".png");
            OutputStream out = new FileOutputStream(file);
            BitmapCanvasProvider provider = new BitmapCanvasProvider(out, "image/x-png", 288, BufferedImage.TYPE_BYTE_GRAY, true, 0);
//            EPSCanvasProvider provider = new EPSCanvasProvider(out, 0);
            gen.generateBarcode(provider, content);
            provider.finish();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.deleteOnExit();
        }
        return file;
    }
}
