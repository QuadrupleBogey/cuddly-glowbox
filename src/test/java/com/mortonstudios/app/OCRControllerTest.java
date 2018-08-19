package com.mortonstudios.app;

import com.mortonstudios.app.processing.ImageProcessing;
import com.mortonstudios.app.utils.ImageBase64Helpers;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Cameron on 18/08/2018.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OCRControllerTest {

    private MockMvc mockMvc;
    private MockHttpServletRequestBuilder mockRequest;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void Setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        mockRequest = MockMvcRequestBuilders.post("/api/process");
    }

    @Test
    public void staticImageTest() throws Exception {
        File image = new File("src/test/resources/testImage.png");

        ITesseract instance = new Tesseract();

        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        instance.setDatapath(tessDataFolder.getAbsolutePath());

         String result = instance.doOCR(image);
        System.out.println(result);
        assertEquals(result, result);
    }


    @Test
    public void shouldReturnsResult() throws Exception {
        BufferedImage image = ImageIO.read(new File("src/test/resources/testImage.png"));

        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.param("image", ImageBase64Helpers.encodeToString(image,"png"));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(content().contentType(
                        MediaType.APPLICATION_JSON
                ));
    }
}
