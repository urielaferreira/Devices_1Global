package com.uriel.Devices.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.uriel.Devices.model.Device;
import com.uriel.Devices.model.DeviceState;
import com.uriel.Devices.repository.DeviceRepository;

@SpringBootTest
class DeviceServiceTest
{

    @Autowired
    private DeviceService service;

    @Autowired
    private DeviceRepository repository;

    @Test
    void testCreateDevice( )
    {
        Device device = new Device( );
        device.setName( "SIM Card 1" );
        device.setBrand( "1Global" );
        device.setState( DeviceState.AVAILABLE );

        Device savedDevice = service.createDevice( device );

        Assertions.assertNotNull( savedDevice.getId( ) );
    }

    @Test
    void testUpdateDevice( )
    {
        Device device = new Device( );
        device.setName( "SIM Card 2" );
        device.setBrand( "Hired" );
        device.setState( DeviceState.AVAILABLE );

        Device savedDevice = service.createDevice( device );
        savedDevice.setName( "Uriel" );

        Device updatedDevice = service.updateDevice( savedDevice.getId( ), savedDevice );

        Assertions.assertEquals( "Uriel", updatedDevice.getName( ) );
    }

    @Test
    void testDeleteDevice( )
    {
        Device device = new Device( );
        device.setName( "SIM Card 4" );
        device.setBrand( "1Global" );
        device.setState( DeviceState.AVAILABLE );

        Device savedDevice = service.createDevice( device );
        service.deleteDevice( savedDevice.getId( ) );

        Assertions.assertTrue( repository.findById( savedDevice.getId( ) ).isEmpty( ) );
    }

    @Test
    void testPreventDeletionOfInUseDevice( )
    {
        Device device = new Device( );
        device.setName( "SIM Card 5" );
        device.setBrand( "Global" );
        device.setState( DeviceState.IN_USE );

        Device savedDevice = service.createDevice( device );

        Assertions.assertThrows( IllegalStateException.class, ( ) -> service.deleteDevice( savedDevice.getId( ) ) );
    }
}