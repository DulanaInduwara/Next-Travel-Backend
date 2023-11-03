package com.dulana.ps.endpoints;


import com.dulana.ps.dto.PackageDTO;
import com.dulana.ps.response.Response;
import com.dulana.ps.service.custom.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin
public class PackageController {

    @GetMapping(path = "/demo")
    public String getHello(){
        return "Hello";
    }
    @Autowired
    private PackageService packageService;

    @PostMapping(path = "/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response savePackage(@RequestBody PackageDTO packageDTO){
        return packageService.save(packageDTO);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response update(@RequestBody PackageDTO packageDTO) {
        return packageService.update(packageDTO);
    }

    @GetMapping(path = "/search", params = "packageId", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response search(@RequestParam("packageId") String packageId) {
        return packageService.search(packageId);
    }

    @DeleteMapping(path = "/delete", params = "packageId", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response delete(@RequestParam("packageId") String packageId) {
        return packageService.delete(packageId);
    }

    @GetMapping(path = "/fetchAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getAll() {
        return packageService.getAll();
    }

}
