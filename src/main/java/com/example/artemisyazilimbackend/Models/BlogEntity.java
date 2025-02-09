package com.example.artemisyazilimbackend.Models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "blogs")
@Getter
@Setter
@NoArgsConstructor
public class BlogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String Title;

    @Column(nullable = false)
    private String Subtitle;
    

    @Column()
    private String imagePath;

    @Column(nullable = false)
    private String Text;


    @Column(columnDefinition = "TEXT") 
    private String base64Image;
    
    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT now()")
    private LocalDateTime createdAt;
}
