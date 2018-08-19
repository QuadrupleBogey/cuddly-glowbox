package com.mortonstudios.app.processing;

import com.mortonstudios.app.utils.ImageBase64Helpers;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;

import java.io.File;

/**
 * Created by Cameron on 17/08/2018.
 */
public class ImageProcessing {

    public String processImage(String image) throws Exception {
        ITesseract instance = new Tesseract();

        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        instance.setDatapath(tessDataFolder.getAbsolutePath());

        return instance.doOCR(ImageBase64Helpers.decodeToImage(image));
    }

}
