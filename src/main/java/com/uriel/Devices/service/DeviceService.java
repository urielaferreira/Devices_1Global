package com.uriel.Devices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uriel.Devices.model.Device;
import com.uriel.Devices.model.DeviceState;
import com.uriel.Devices.repository.DeviceRepository;
import java.util.List;

@Service
public class DeviceService
{
    @Autowired
    private DeviceRepository repository;

    public Device createDevice( Device device )
    {
        device.setCreationTime( java.time.LocalDateTime.now( ) );
        return repository.save( device );
    }

    public Device updateDevice( Long id, Device newDevice )
    {
        Device device = repository.findById( id ).orElseThrow( ( ) -> new RuntimeException( "Device not found" ) );

        if( device.getState( ) == DeviceState.IN_USE )
        {
            throw new IllegalStateException( "Cannot update name or brand when device is in use." );
        }

        if( newDevice.getName( ) != null )
        {
            device.setName( newDevice.getName( ) );
        }
        if( newDevice.getBrand( ) != null )
        {
            device.setBrand( newDevice.getBrand( ) );
        }

        return repository.save( device );
    }

    public void deleteDevice( Long id )
    {
        Device device = repository.findById( id ).orElseThrow( ( ) -> new RuntimeException( "Device not found" ) );

        if( device.getState( ) == DeviceState.IN_USE )
        {
            throw new IllegalStateException( "Cannot delete a device that is in use." );
        }

        repository.deleteById( id );
    }

    public List<Device> getAllDevices( )
    {
        return repository.findAll( );
    }

    public Device getDeviceById( Long id )
    {
        return repository.findById( id ).orElseThrow( ( ) -> new RuntimeException( "Device not found" ) );
    }

    public List<Device> getDevicesByBrand( String brand )
    {
        return repository.findByBrand( brand );
    }

    public List<Device> getDevicesByState( DeviceState state )
    {
        return repository.findByState( state );
    }
}
