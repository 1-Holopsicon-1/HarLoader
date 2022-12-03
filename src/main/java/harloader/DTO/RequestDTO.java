package harloader.DTO;


import de.sstoehr.harreader.model.HttpMethod;
import harloader.models.HarArchive;
import harloader.models.User;

import java.util.Map;

public record RequestDTO(String url,
                         String body,
                         Map<String, String> headers,
                         Map<String, String> params,
                         HttpMethod method,
                         User user,
                         HarArchive harArchive) {}
