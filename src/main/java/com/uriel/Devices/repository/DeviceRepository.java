package com.uriel.Devices.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.uriel.Devices.model.Device;
import com.uriel.Devices.model.DeviceState;

public interface DeviceRepository extends JpaRepository<Device, Long>
{
    List<Device> findByBrand( String brand );
    List<Device> findByState( DeviceState state );
}
