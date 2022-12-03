package harloader.controllers;

import harloader.DTO.HarMapper;
import harloader.repositories.HarArchiveRepo;
import harloader.services.HarArchiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class HarArchiveController {

    private final HarMapper harMapper;
    private final HarArchiveRepo repo;
    private final HarArchiveService service;

    @PostMapping(path = "/load", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public HttpStatus uploadArchive(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        return service.loadHar(file);
    }


}
