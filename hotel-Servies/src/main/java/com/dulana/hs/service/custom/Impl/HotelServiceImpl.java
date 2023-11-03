package com.dulana.hs.service.custom.Impl;

import com.dulana.hs.dto.HotelDTO;
import com.dulana.hs.entity.Hotel;
import com.dulana.hs.repo.HotelRepo;
import com.dulana.hs.response.Response;
import com.dulana.hs.service.custom.HotelService;
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
public class HotelServiceImpl implements HotelService {
    @Autowired
    private Response response;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HotelRepo hotelRepo;


    @Override
    @PostMapping(path = "save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response save(HotelDTO hotelDTO) {
        if (search(hotelDTO.getHotelID()).getData() == null) {
            hotelRepo.save(modelMapper.map(hotelDTO, Hotel.class));
            return createAndSendResponse(HttpStatus.OK.value(), "Hotel Successfully saved!", null);
        }
        throw new RuntimeException("Hotel already exists!");
    }

    @Override
    public Response update(HotelDTO hotelDTO) {
        if (search(hotelDTO.getHotelID()).getData() != null) {
            hotelRepo.save(modelMapper.map(hotelDTO, Hotel.class));
            return createAndSendResponse(HttpStatus.OK.value(), "Hotel Successfully updated!", null);
        }
        throw new RuntimeException("Hotel does not exists!");
    }

    @Override
    public Response delete(String s) {
        if (search(s).getData() != null) {
            hotelRepo.deleteById(s);
            return createAndSendResponse(HttpStatus.OK.value(), "Hotel Successfully deleted!", null);
        }
        throw new RuntimeException("Hotel does not exists!");
    }

    @Override
    public Response search(String s) {
        Optional<Hotel> hotel = hotelRepo.findById(s);
        if (hotel.isPresent()) {
            return createAndSendResponse(HttpStatus.FOUND.value(), "Hotel Successfully retrieved!", modelMapper.map(hotel.get(), HotelDTO.class));
        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Hotel does not exists!", null);
    }

    @Override
    public Response getAll() {
        List<Hotel> hotels = hotelRepo.findAll();
        if (!hotels.isEmpty()) {
            ArrayList<HotelDTO> hotels_dtos = new ArrayList<>();
            hotels.forEach((hotelss) -> {
                hotels_dtos.add(modelMapper.map(hotelss, HotelDTO.class));
            });
            return createAndSendResponse(HttpStatus.FOUND.value(), "Hotels Successfully retrieved!", hotels_dtos);
        }
        throw new RuntimeException("No Hotels found in the database!");
    }

    @Override
    public Response createAndSendResponse(int statusCode, String message, Object data) {
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
