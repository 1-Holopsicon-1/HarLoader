package harloader.DTO;

import de.sstoehr.harreader.model.Har;
import harloader.models.HarArchive;
import harloader.models.Request;
import harloader.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HarMapper {
    InputHarArchiveDTO HARtoDTO(HarArchive harArchive);
    HarArchive ARCHIVEtoModel(InputHarArchiveDTO inputHarArchiveDTO);
    @Mapping(target = "data", source = "log")
    @Mapping(target = "browserData", source = "log.browser.name")
    @Mapping(target = "archiveVer", source = "log.version")
    HarArchive HARfromRaw(Har har);

    User USERDTOtoEntity(UserDTO userDTO);

    List<Request> REQDTOtoEntity(List<RequestDTO> requestDTO);

}
