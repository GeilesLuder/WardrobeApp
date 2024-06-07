package com.example.wardrobeapp.service;

import com.example.wardrobeapp.model.Picture;
import com.example.wardrobeapp.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;


@Service
public class PictureService{

    @Autowired
    private PictureRepository pictureRepository;

    public Picture save(Picture pic){

        return pictureRepository.save(pic);
    }

    public Boolean delete (Long picId){
        try{
             pictureRepository.deleteById(picId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
