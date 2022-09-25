package org.khasanof.upload_service.upload;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class LocalFileService {

    private final String PATH = "C:\\Nurislom\\Java\\SpeaGroup\\SpeaGroup\\upload_service\\src\\main\\resources\\static\\img\\";

    public String writeFile(MultipartFile file) {
        try {
            String org_name = file.getOriginalFilename();
            String extension = StringUtils.getFilenameExtension(org_name);
            String generatedName = System.currentTimeMillis() + "." + extension;
            Files.copy(file.getInputStream(), Paths.get(PATH, generatedName), StandardCopyOption.REPLACE_EXISTING);
            return PATH + generatedName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}


