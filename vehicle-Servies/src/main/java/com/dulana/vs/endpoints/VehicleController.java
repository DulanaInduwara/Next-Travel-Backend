package com.dulana.vs.endpoints;


import com.dulana.vs.dto.VehicleDTO;
import com.dulana.vs.response.Response;
import com.dulana.vs.service.custom.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin
public class VehicleController {

    @GetMapping(path = "/demo")
    public String getHello(){
        return "Hello";
    }
    @Autowired
    private VehicleService vehicleService;

    @PostMapping(path = "/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response saveVehicle(@RequestBody VehicleDTO vehicleDTO){
        return vehicleService.save(vehicleDTO);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response update(@RequestBody VehicleDTO vehicleDTO) {
        return vehicleService.update(vehicleDTO);
    }

    @GetMapping(path = "/search", params = "vehicleId", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response search(@RequestParam("vehicleId") String vehicleId) {
        return vehicleService.search(vehicleId);
    }

    @DeleteMapping(path = "/delete", params = "vehicleId", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response delete(@RequestParam("vehicleId") String vehicleId) {
        return vehicleService.delete(vehicleId);
    }

    @GetMapping(path = "/fetchAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getAll() {
        return vehicleService.getAll();
    }

}
