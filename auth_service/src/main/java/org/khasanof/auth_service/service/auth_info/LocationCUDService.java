package org.khasanof.auth_service.service.auth_info;

import org.khasanof.auth_service.dto.location.LocationCreateDTO;
import org.khasanof.auth_service.dto.location.LocationUpdateDTO;

public interface LocationCUDService {
    void addLocation(LocationCreateDTO dto);

    void updateLocation(LocationUpdateDTO dto);

    void deleteLocation(String infoId);
}
