package com.dulana.vs.service.custom.Impl;

import com.dulana.vs.dto.VehicleDTO;
import com.dulana.vs.entity.Vehicle;
import com.dulana.vs.repo.VehicleRepo;
import com.dulana.vs.response.Response;
import com.dulana.vs.service.custom.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private Response response;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VehicleRepo vehicleRepo;


    @Override
    @PostMapping(path = "save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response save(VehicleDTO vehicleDTO) {
        if (search(vehicleDTO.getVehicleID()).getData() == null) {
            vehicleRepo.save(modelMapper.map(vehicleDTO, Vehicle.class));
            return createAndSendResponse(HttpStatus.OK.value(), "Vehicle Successfully saved!", null);
        }
        throw new RuntimeException("Vehicle already exists!");
    }

    @Override
    public Response update(VehicleDTO vehicleDTO) {
        if (search(vehicleDTO.getVehicleID()).getData() == null) {
            vehicleRepo.save(modelMapper.map(vehicleDTO, Vehicle.class));
            return createAndSendResponse(HttpStatus.OK.value(), "Vehicle Successfully updated!", null);
        }
        throw new RuntimeException("Vehicle does not exists!");
    }

    @Override
    public Response delete(String s) {
        if (search(s).getData() != null) {
            vehicleRepo.deleteById(s);
            return createAndSendResponse(HttpStatus.OK.value(), "Vehicle Successfully deleted!", null);
        }
        throw new RuntimeException("Vehicle does not exists!");
    }

    @Override
    public Response search(String s) {
        Optional<Vehicle> vehicle = vehicleRepo.findById(s);
        if (vehicle.isPresent()) {
            return createAndSendResponse(HttpStatus.FOUND.value(), "Vehicle Successfully retrieved!", modelMapper.map(vehicle.get(), VehicleDTO.class));
        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Vehicle does not exists!", null);
    }

    @Override
    public Response getAll() {
        List<Vehicle> vehicles = vehicleRepo.findAll();
        if (!vehicles.isEmpty()) {
            ArrayList<VehicleDTO> vehicles_dtos = new ArrayList<>();
            vehicles.forEach((vehicless) -> {
                vehicles_dtos.add(modelMapper.map(vehicless, VehicleDTO.class));
            });
            return createAndSendResponse(HttpStatus.FOUND.value(), "Vehicles Successfully retrieved!", vehicles_dtos);
        }
        throw new RuntimeException("No Vehicles found in the database!");
    }

    @Override
    public Response createAndSendResponse(int statusCode, String message, Object data) {
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
