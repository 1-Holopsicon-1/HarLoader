package harloader.DTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.sstoehr.harreader.model.*;
import harloader.models.HarArchive;
import harloader.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class MyMapper {
    Map<String, String> headers(List<HarHeader> headers) {
        Map<String, String> mapObj = null;
        for (HarHeader h : headers) {
            ObjectMapper mapObject = new ObjectMapper();
            mapObj = mapObject.convertValue(h, Map.class);
        }
        return mapObj;
    }

    Map<String, String> param(List<HarQueryParam> params) {
        Map<String, String> mapObj = null;
        for (HarQueryParam h : params) {
            ObjectMapper mapObject = new ObjectMapper();
            mapObj = mapObject.convertValue(h, Map.class);
        }
        return mapObj;
    }

    RequestDTO harReqToReq(HarRequest harRequest, User user, HarArchive harArchive){
        return new RequestDTO(harRequest.getUrl(),
                harRequest.getPostData().toString(),
                headers(harRequest.getHeaders()),
                param(harRequest.getQueryString()),
                harRequest.getMethod(),
                user,
                harArchive);
    }
    public List<RequestDTO> harToReq(Har har, User user, HarArchive harArchive){
        List<RequestDTO> output = new ArrayList<>();
        for (HarEntry h : har.getLog().getEntries()){
            output.add(harReqToReq(h.getRequest(), user, harArchive));
        }
        return output;
    }
}
