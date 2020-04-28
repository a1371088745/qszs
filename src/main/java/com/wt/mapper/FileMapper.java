package com.wt.mapper;

import com.wt.entity.File;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileMapper {
    public Integer UploadFile(@Param("fileName") String fileName);

    public List<File> readAllFile();
}
