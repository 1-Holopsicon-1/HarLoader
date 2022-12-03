package harloader.DTO;

import de.sstoehr.harreader.model.HarLog;

public record InputHarArchiveDTO(HarLog data,
                                 String browserData,
                                 String archiveVer) {}

