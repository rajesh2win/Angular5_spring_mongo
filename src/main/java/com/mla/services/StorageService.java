package com.mla.services;

/**
 * Created by rnallamothu on 4/20/18.
 */

        import java.io.IOException;
        import java.net.MalformedURLException;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.nio.file.Paths;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.core.io.Resource;
        import org.springframework.core.io.UrlResource;
        import org.springframework.stereotype.Service;
        import org.springframework.util.FileSystemUtils;
        import org.springframework.web.multipart.MultipartFile;
        import java.util.stream.Stream;
@Service
public class StorageService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path rootLocation = Paths.get("/opt/");
    private final Path photosLocation = Paths.get("/opt/images");


    public void store(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Image with the same name already exists !");
        }
    }

    public void storePhotos(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.photosLocation.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Image with the same name already exists !");
        }
    }
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.photosLocation, 1)
                    .filter(path -> !path.equals(this.photosLocation))
                    .filter(path -> !path.getFileName().toString().startsWith("."))
                    .map(path -> this.photosLocation.relativize(path));
        } catch (IOException e) {
            //throw new StorageException("Failed to read stored files", e);
        }
        return null;
    }
    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }
    public Resource loadPhoto(String filename) {
        try {
            Path file = photosLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }
}
