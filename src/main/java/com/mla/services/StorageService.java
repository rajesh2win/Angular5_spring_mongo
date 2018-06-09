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
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.core.env.Environment;
        import org.springframework.beans.factory.annotation.Value;


@Service
public class StorageService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());


    private final Path rootLocation ;
    private final Path photosLocation ;
    private final Path videosLocation ;
    private final Path shareLocation;

    @Autowired
    public StorageService(@Value("${app.mla.darsi.images.common}") String imgCommon,
                          @Value("${app.mla.darsi.images.lib}") String imgLib,
                          @Value("${app.mla.darsi.videos.lib}") String vidLib,
                          @Value("${app.mla.common}") String shareLib) {
        this.rootLocation = Paths.get(imgCommon);
        this.photosLocation = Paths.get(imgLib);
        this.videosLocation = Paths.get(vidLib);
        this.shareLocation = Paths.get(shareLib);

    }

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
    public void deletePhoto(String file) {
        try {
            Files.delete(this.photosLocation.resolve(file));
        } catch (Exception e) {
            throw new RuntimeException("Image with the same name already exists !");
        }
    }
    public void deleteVideo(String file) {
        try {
            Files.delete(this.videosLocation.resolve(file));
        } catch (Exception e) {
            throw new RuntimeException("Video with the same name already exists !");
        }
    }
    public void storeAny(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.shareLocation.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Image with the same name already exists !");
        }
    }

    public void storeVideos(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.videosLocation.resolve(file.getOriginalFilename()));
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
    public Stream<Path> loadAnyAll() {
        try {
            return Files.walk(this.shareLocation, 1)
                    .filter(path -> !path.equals(this.shareLocation))
                    .filter(path -> !path.getFileName().toString().startsWith("."))
                    .map(path -> this.shareLocation.relativize(path));
        } catch (IOException e) {
            //throw new StorageException("Failed to read stored files", e);
        }
        return null;
    }

    public Stream<Path> loadAllVideos() {
        try {
            return Files.walk(this.videosLocation, 1)
                    .filter(path -> !path.equals(this.videosLocation))
                    .filter(path -> !path.getFileName().toString().startsWith("."))
                    .map(path -> this.videosLocation.relativize(path));
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

    public Resource loadAny(String filename) {
        try {
            Path file = shareLocation.resolve(filename);
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

    public Resource loadVideo(String filename) {
        try {
            Path file = videosLocation.resolve(filename);
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
