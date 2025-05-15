package com.uriel.Devices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uriel.Devices.model.Device;
import com.uriel.Devices.model.DeviceState;
import com.uriel.Devices.service.DeviceService;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController
{

    @Autowired
    private DeviceService service;

    @PostMapping
    public ResponseEntity<Device> createDevice( @RequestBody Device device )
    {
        return ResponseEntity.ok( service.createDevice( device ) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice( @PathVariable Long id, @RequestBody Device newDevice )
    {
        return ResponseEntity.ok( service.updateDevice( id, newDevice ) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById( @PathVariable Long id )
    {
        return ResponseEntity.ok( service.getDeviceById( id ) );
    }

    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices( )
    {
        return ResponseEntity.ok( service.getAllDevices( ) );
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Device>> getDevicesByBrand( @PathVariable String brand )
    {
        return ResponseEntity.ok( service.getDevicesByBrand( brand ) );
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<Device>> getDevicesByState( @PathVariable DeviceState state )
    {
        return ResponseEntity.ok( service.getDevicesByState( state ) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice( @PathVariable Long id )
    {
        service.deleteDevice( id );
        return ResponseEntity.noContent( ).build( );
    }
}
