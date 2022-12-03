package harloader.services;

import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.model.Har;
import harloader.DTO.HarMapper;
import harloader.DTO.MyMapper;
import harloader.models.HarArchive;
import harloader.models.Request;
import harloader.models.User;
import harloader.repositories.HarArchiveRepo;
import harloader.repositories.RequestsRepo;
import harloader.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HarArchiveService {
    private final HarArchiveRepo harRepo;
    private final UserRepo userRepo;
    private final RequestsRepo requestsRepo;
    private final HarMapper mapper;
    private final MyMapper myMapper;

    public Optional<HarArchive> getData(Long id){
        return harRepo.findById(id);
    }

    public HttpStatus loadHar(MultipartFile file) {
        try (InputStream is = file.getInputStream()){
            if (!file.isEmpty()) {
                HarReader harReader = new HarReader();
                Har h = harReader.readFromFile(multipartToFile(file, file.getName()));
                HarArchive savedHar = harRepo.saveAndFlush(mapper.HARfromRaw(h));
                parseHar("User1", h, savedHar);
                return HttpStatus.OK;
            }else{
                return HttpStatus.NO_CONTENT;
            }
        } catch (IOException | HarReaderException e) {
            throw new RuntimeException(e);
        }
    }

    private void parseHar(String name, Har h, HarArchive harArchive){
        User u = new User();
        u.setName(name);
        userRepo.saveAndFlush(u);
        List<Request> req = mapper.REQDTOtoEntity(myMapper.harToReq(h, u, harArchive));
        u.setRequests(req);
        userRepo.save(u);
    }

    private File multipartToFile(MultipartFile multipart, String fileName) throws IllegalStateException, IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+fileName);
        multipart.transferTo(convFile);
        return convFile;
    }
}
