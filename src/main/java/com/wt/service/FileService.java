package com.wt.service;

import com.wt.entity.File;

import java.util.List;

public interface FileService {
    public Integer UploadFile(String fileName);

    public List<File> readAllFile();
}
