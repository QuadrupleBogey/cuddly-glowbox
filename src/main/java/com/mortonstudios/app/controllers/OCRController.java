package com.mortonstudios.app.controllers;

import com.mortonstudios.app.processing.ImageProcessing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Cameron on 17/08/2018.
 */

@RestController
public class OCRController {

    @RequestMapping(value = "/api/processing", method = RequestMethod.POST)
    public ResponseEntity processing(@RequestParam(value="image") String image){
        ImageProcessing process = new ImageProcessing();
        try {
            String data = process.processImage(image);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
