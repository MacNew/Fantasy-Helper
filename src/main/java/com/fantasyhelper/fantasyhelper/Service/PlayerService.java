package com.fantasyhelper.fantasyhelper.Service;

import com.fantasyhelper.fantasyhelper.Exception.MyCustomException;
import com.fantasyhelper.fantasyhelper.config.FileStorageProperties;
import com.fantasyhelper.fantasyhelper.modle.PlayerList;
import com.fantasyhelper.fantasyhelper.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepository;
    private  Path fileSotageLocation = null;
    @Autowired
    public PlayerService(FileStorageProperties fileStorageProperties) throws MyCustomException{
        this.fileSotageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileSotageLocation);
        } catch (Exception ex) {
            throw new MyCustomException("Could not Create the directory where the uploaded files will be stored  "+ex.getMessage());
        }
    }

    public String addPlayer(PlayerList player, MultipartFile file) throws MyCustomException {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("...")) {
                throw new MyCustomException("Sorry! File name contains invalid path Sequence");
            }
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileSotageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            player.setFileDownloadLink(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("fantasyhelper/api/downloadFile/")
                    .path(fileName)
                    .toUriString());
            player.setFilePath(this.fileSotageLocation.resolve(fileName).normalize().toString());
            player.setFileName(fileName);
            PlayerList playerList = playerRepository.save(player);
            if (playerList != null) {
                return fileName;
            } else {
                return null;
            }
        }catch (Exception e) {
           throw new MyCustomException(player.getPlayerName()+" alrady Exists");
        }
    }

    public List<PlayerList> getPlayerList(Integer clubId)
    {
        return playerRepository.findByClubid(clubId);
    }


    public List<PlayerList> getForwardList(String position) {
        return playerRepository.findByPlayerPosition(position);
    }
}
