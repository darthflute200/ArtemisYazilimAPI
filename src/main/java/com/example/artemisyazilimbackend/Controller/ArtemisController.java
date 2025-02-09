package com.example.artemisyazilimbackend.Controller;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.artemisyazilimbackend.Models.BlogEntity;
import com.example.artemisyazilimbackend.Repository.BlogRepo;
import com.example.artemisyazilimbackend.Service.BlogService;
@RestController
public class ArtemisController {
    private static final Logger log = LoggerFactory.getLogger(ArtemisController.class);
    @Autowired
    private BlogRepo blogRepo;

    @Autowired
    private BlogService blogservice;

    @GetMapping("/")
    public String home(){
        return "Başarılı";
    }
    @PostMapping("/api/createblog")
    public ResponseEntity<Map<String , String>> createBlog(@RequestBody BlogEntity blog) {
        try {
            log.debug(blog + "Body Burada");
            if (blog.getBase64Image() != null && !blog.getBase64Image().isEmpty()) {
                String imagePath = blogservice.saveBase64Image(blog.getBase64Image());
                blog.setImagePath(imagePath);
                blog.setBase64Image(null); 
            }
            else{
                return ResponseEntity.badRequest().body(Map.of("error", "Resim Dosyası Bulunamadı"));
            }

            blogRepo.save(blog);

            return ResponseEntity.ok(Map.of("message", "Başarılı"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error" , "Hata: " + e));
        }
    }
    @GetMapping("/api/allblogs")
    public ResponseEntity<?> getallblogs(){
        try{
        List<BlogEntity> bloglist = blogRepo.findAll();
        return ResponseEntity.ok().body(bloglist);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("error:" + " " + e);
        }
    }
    
}
