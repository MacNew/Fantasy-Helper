package com.fantasyhelper.fantasyhelper.resources;
import com.fantasyhelper.fantasyhelper.Exception.MyCustomException;
import com.fantasyhelper.fantasyhelper.Service.ClubService;
import com.fantasyhelper.fantasyhelper.Service.PlayerService;
import com.fantasyhelper.fantasyhelper.config.RoleService;
import com.fantasyhelper.fantasyhelper.modle.ClubName;
import com.fantasyhelper.fantasyhelper.modle.PlayerList;
import com.fantasyhelper.fantasyhelper.modle.UploadFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.core.io.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("fantasyhelper/api/")
public class Controller {
    // private static final Logger logger = (Logger) LoggerFactory.getLogger(Controller.class);
    @Autowired
    ClubService clubService;
    @Autowired
    PlayerService playerService;
    @Autowired
    RoleService roleService;
    @GetMapping("currentseason/get/clubs")
    public ResponseEntity getAll(@AuthenticationPrincipal final UserDetails userDetails) {
        if (roleService.isUserOrAdmin(userDetails)) {
            return new ResponseEntity(clubService.getCurrentSeasonClubs(), HttpStatus.OK);
        } else {
            return new ResponseEntity("You are not an User", HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "insert/clubs", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @ResponseBody
    public ResponseEntity addClubs(@RequestPart("clubName")  String club, @RequestPart("isPlaying") String isPlaying,
                                   @AuthenticationPrincipal final UserDetails userDetails,
                                   @RequestPart("file")  MultipartFile file) throws MyCustomException {
        if (roleService.isAdmin(userDetails)) {
            ClubName clubName = new ClubName();
            clubName.setClubName(club);
            clubName.setCurrentSeasonPlaying(Boolean.parseBoolean(isPlaying));
            String fileName = clubService.addClubs(clubName, file);
            if( fileName != null) {
                String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("fantasyhelper/api/downloadFile/")
                        .path(fileName)
                        .toUriString();
                return new ResponseEntity(new UploadFileResponse(fileName,
                        fileDownloadUrl,file.getContentType(),file.getSize(),club,isPlaying), HttpStatus.OK);
            }
        }else {
            throw new MyCustomException("Sorry you Are not an Admin");
        }
        return new ResponseEntity("Error",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/downloadFile/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws MyCustomException {
        Resource resource = clubService.loadFileAsResource(fileName);
        String contentType = null;
        try {

        } catch (Exception ex) {

        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("insert/player")
    public ResponseEntity addPlayers(@RequestBody PlayerList player,
                                     @AuthenticationPrincipal final UserDetails userDetails) throws MyCustomException {
        if (roleService.isAdmin(userDetails)) {
            if (playerService.addPlayer(player)) {
                return new ResponseEntity(player, HttpStatus.OK);
            }
        }
        throw new MyCustomException("Sorry you Aere not an Admin");
    }

    @GetMapping("get/player/{club_id}")
    public ResponseEntity getPlayersList(@AuthenticationPrincipal final  UserDetails userDetails, @PathVariable("club_id") Integer clubId) throws MyCustomException{
        if (roleService.isUserOrAdmin(userDetails)) {
            return new ResponseEntity(playerService.getPlayerList(clubId),HttpStatus.OK);
        } else {
            return new ResponseEntity("Sorry you are not an User", HttpStatus.OK);
        }
    }

    @GetMapping("admin")
    public ResponseEntity isAdmin(@AuthenticationPrincipal final  UserDetails userDetails) {
        if (roleService.isAdmin(userDetails)) {
            return new ResponseEntity("Yes you are Admin", HttpStatus.OK);
        }
        return new ResponseEntity("Sorry you are not a admin", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("useroradmin")
    public ResponseEntity isuserOrAdmin(@AuthenticationPrincipal final  UserDetails userDetails) {
        if (roleService.isUserOrAdmin(userDetails)) {
            return new ResponseEntity("Yes you are either user or Admin", HttpStatus.OK);
        }
        return new ResponseEntity("Sorry you are not a user or Admin", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("user")
    public ResponseEntity isUser(@AuthenticationPrincipal final  UserDetails userDetails) {
        if (roleService.isUser(userDetails)) {
            return new ResponseEntity("yes you are user", HttpStatus.OK);
        }
        return new ResponseEntity("Sorry you are not a user ", HttpStatus.UNAUTHORIZED);
    }

}