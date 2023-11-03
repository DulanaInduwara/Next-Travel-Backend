package com.dulana.hs.endpoints;


import com.dulana.hs.dto.HotelDTO;
import com.dulana.hs.response.Response;
import com.dulana.hs.service.custom.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin
public class HotelController {

    @GetMapping(path = "/demo")
    public String getHello(){
        return "Hello";
    }
    @Autowired
    private HotelService hotelService;

    @PostMapping(path = "/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response savehotel(@RequestBody HotelDTO hotelDTO){
        return hotelService.save(hotelDTO);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response update(@RequestBody HotelDTO hotelDTO) {
        return hotelService.update(hotelDTO);
    }

    @GetMapping(path = "/search", params = "hotelId", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response search(@RequestParam("hotelId") String hotelId) {
        return hotelService.search(hotelId);
    }

    @DeleteMapping(path = "/delete", params = "hotelId", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response delete(@RequestParam("hotelId") String hotelId) {
        return hotelService.delete(hotelId);
    }

    @GetMapping(path = "/fetchAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getAll() {
        return hotelService.getAll();
    }

}
