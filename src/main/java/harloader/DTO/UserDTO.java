package harloader.DTO;

import harloader.models.Request;

import java.util.List;

public record UserDTO(String name,
                      List<Request> requests) {}
