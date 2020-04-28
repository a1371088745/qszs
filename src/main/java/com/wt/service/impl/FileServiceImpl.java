package com.wt.service.impl;

import com.wt.entity.File;
import com.wt.mapper.FileMapper;
import com.wt.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;
    @Override
    public Integer UploadFile(String fileName) {
        return fileMapper.UploadFile(fileName);
    }

    @Override
    public List<File> readAllFile() {
        return fileMapper.readAllFile();
    }
}
