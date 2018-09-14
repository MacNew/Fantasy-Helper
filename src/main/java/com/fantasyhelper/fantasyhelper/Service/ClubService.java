package com.fantasyhelper.fantasyhelper.Service;

import com.fantasyhelper.fantasyhelper.Exception.MyCustomException;
import com.fantasyhelper.fantasyhelper.modle.ClubName;
import com.fantasyhelper.fantasyhelper.config.FileStorageProperties;
import com.fantasyhelper.fantasyhelper.repository.ClubsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class ClubService {
    @Autowired
    ClubsRepository clubsRepository;
    private  Path fileSotageLocation = null;
    @Autowired
    public ClubService(FileStorageProperties fileStorageProperties) throws MyCustomException{
        this.fileSotageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileSotageLocation);
        } catch (Exception ex) {
            throw new MyCustomException("Could not Create the directory where the uploaded files will be stored  "+ex.getMessage());
        }
    }

    public List<ClubName> getCurrentSeasonClubs() {
        return  clubsRepository.findAll();
    }
    public String addClubs(ClubName clubname, MultipartFile file) throws MyCustomException {
        //Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new MyCustomException("Sorry! File name contains invalid path Sequence");
            }
            // Copy file to the target location (Replacing existing file with the same name )
            Path targetLocation = this.fileSotageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            clubname.setFiledownloadLink(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("fantasyhelper/api/downloadFile/")
                    .path(fileName)
                    .toUriString());
            clubname.setFilepath(this.fileSotageLocation.resolve(fileName).normalize().toString());
            clubname.setFileName(fileName);
            ClubName instance = clubsRepository.save(clubname);
            if (instance != null) {
                return fileName; //String.valueOf(this.fileSotageLocation.resolve(fileName).normalize());
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new MyCustomException(clubname.getClubName() + " alrady exists");
        }
    }

    public Resource loadFileAsResource(String fileName) throws MyCustomException {
        try {
            Path filePath = this.fileSotageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyCustomException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyCustomException("File not found " + fileName);
        }
    }

    public ClubName getClub(int club_id)  {
        return clubsRepository.findById(club_id);
    }

    public ClubName getfileName(int clubId) {
        return clubsRepository.findById(clubId);
    }
}
