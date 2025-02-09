package com.example.artemisyazilimbackend.Service;
import org.springframework.stereotype.Service;
import java.nio.file.*;
import java.util.Base64;
import java.util.UUID;
import java.io.File;
import java.io.IOException;

@Service
public class BlogService {

    private static final String UPLOAD_DIR = "uploads/";

    public String saveBase64Image(String base64Image) throws IOException {
        if (base64Image == null || base64Image.isEmpty()) {
            throw new IllegalArgumentException("Base64 image is empty!");
        }

        // Base64 başlığını temizle
        String[] parts = base64Image.split(",");
        String imageData = parts.length > 1 ? parts[1] : parts[0];

        byte[] imageBytes = Base64.getDecoder().decode(imageData);
        String fileName = UUID.randomUUID() + ".png";

        // Eğer uploads klasörü yoksa oluştur
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Dosyayı kaydet
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        Files.write(filePath, imageBytes);

        return filePath.toAbsolutePath().toString();
    }
}

