package com.lcwd.electronic.store.services.impl;

import com.lcwd.electronic.store.exception.BadAPIRequest;
import com.lcwd.electronic.store.services.FileService;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {
        //Ex=abc.png
        String origionalfilename=file.getOriginalFilename();
        logger.info("FileBName : {}",origionalfilename);
        String filename = UUID.randomUUID().toString();
        String extension = origionalfilename.substring(origionalfilename.lastIndexOf("."));
        String fileNameWithExtension = filename+extension;
        String fullPathWithFileName = path + fileNameWithExtension;
        if (extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg")
        ||extension.equalsIgnoreCase(".jpeg"))
        {
         //File Save
            File folder = new File(path);
            if (!folder.exists())
            {
                //Create the folder
                folder.mkdirs();
            }
            //Upload
            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
            return fileNameWithExtension;
        }
        else
        {
            throw new BadAPIRequest("File with this"+extension+"Not allowed !!");
        }

    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        String fullPath = path+File.separator+name;
        InputStream inputStreams = new FileInputStream(fullPath);

        return inputStreams;
    }
}
