package com.mla.controllers;

/**
 * Created by rnallamothu on 4/20/18.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.mla.services.StorageService;

@Controller
@CrossOrigin(origins = {"http://localhost:3200", "http://35.200.168.104:8080", "http://localhost:4200"})
public class UploadController {

    @Autowired
    StorageService storageService;

    List<String> files = new ArrayList<String>();

    @RequestMapping(method=RequestMethod.POST, value="/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.store(file);
            files.add(file.getOriginalFilename());

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @RequestMapping(method=RequestMethod.POST, value="/uploadPhotos")
    public ResponseEntity<String> handlePhotoUpload(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.storePhotos(file);
            files.add(file.getOriginalFilename());

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/deletePhotoCommon/{filename:.+}")
    public ResponseEntity<String> handleDeletePhotoCommon(@PathVariable("filename") String filename){
        String message = "";
        try {
            storageService.deletePhotoCommon(filename);
            files.remove(filename);

            message = "You successfully deleted " + filename + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to delete " + filename + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
    @RequestMapping(method=RequestMethod.DELETE, value="/deletePhoto/{filename:.+}")
    public ResponseEntity<String> handleDeletePhoto(@PathVariable("filename") String filename){
        String message = "";
        try {
            storageService.deletePhoto(filename);
            files.remove(filename);

            message = "You successfully deleted " + filename + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to delete " + filename + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
    @RequestMapping(method=RequestMethod.DELETE, value="/deleteVideo/{filename:.+}")
    public ResponseEntity<String> handleDeleteVideo(@PathVariable("filename") String filename){
        String message = "";
        try {
            storageService.deleteVideo(filename);
            files.remove(filename);

            message = "You successfully deleted " + filename + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to delete " + filename + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
    @RequestMapping(method=RequestMethod.POST, value="/uploadAny")
    public ResponseEntity<String> handleAnyUpload(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.storeAny(file);
            files.add(file.getOriginalFilename());

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @RequestMapping(method=RequestMethod.POST, value="/uploadVideos")
    public ResponseEntity<String> handleVideoUpload(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.storeVideos(file);
            files.add(file.getOriginalFilename());

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @GetMapping("/getallfiles/{filename:.+}")
    public ResponseEntity<List<String>> getListFiles(@PathVariable String filename) {
        files.clear();
        files.add(filename);
        List<String> fileNames = files
                .stream().map(fileName -> MvcUriComponentsBuilder
                        .fromMethodName(UploadController.class, "getFile", fileName).build().toString())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(fileNames);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @GetMapping("/getallphotos")
    public ResponseEntity<List<String>> getListFiles() {
        List<String> urls = storageService
                .loadAll()
                    .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(UploadController.class, "getPhoto", path.getFileName().toString())
                                .build().encode().toString())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(urls);
    }
    @GetMapping("/getAny")
    public ResponseEntity<List<String>> getAny() {
        List<String> urls = storageService
                .loadAnyAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(UploadController.class, "getAny", path.getFileName().toString())
                                .build().encode().toString())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(urls);
    }

    @GetMapping("/getallvideos")
    public ResponseEntity<List<String>> getVideoFiles() {
        List<String> urls = storageService
                .loadAllVideos()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(UploadController.class, "getVideo", path.getFileName().toString())
                                .build().encode().toString())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(urls);
    }

    @GetMapping("/photos/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getPhoto(@PathVariable String filename) {
        Resource file = storageService.loadPhoto(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @GetMapping("/videos/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getVideo(@PathVariable String filename) {
        Resource file = storageService.loadVideo(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
    @GetMapping("/any/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getAny(@PathVariable String filename) {
        Resource file = storageService.loadAny(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
