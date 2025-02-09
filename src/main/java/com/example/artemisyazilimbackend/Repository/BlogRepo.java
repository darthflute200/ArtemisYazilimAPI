package com.example.artemisyazilimbackend.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.artemisyazilimbackend.Models.BlogEntity;

@Repository
public interface BlogRepo extends JpaRepository<BlogEntity , Long >  {
    
}
