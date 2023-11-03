package com.dulana.pds.endpoints;


import com.dulana.pds.dto.PackageDetailDTO;
import com.dulana.pds.response.Response;
import com.dulana.pds.service.custom.PackageDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin
public class PackageDetailController {

    @GetMapping(path = "/demo")
    public String getHello(){
        return "Hello";
    }
    @Autowired
    private PackageDetailService packageDetailService;

    @PostMapping(path = "/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response savePackageDetail(@RequestBody PackageDetailDTO packageDetailDTO){
        return packageDetailService.save(packageDetailDTO);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response update(@RequestBody PackageDetailDTO packageDetailDTO) {
        return packageDetailService.update(packageDetailDTO);
    }

    @GetMapping(path = "/search", params = "packageDetailId", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response search(@RequestParam("packageDetailId") String packageDetailId) {
        return packageDetailService.search(packageDetailId);
    }

    @DeleteMapping(path = "/delete", params = "packageDetailId", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response delete(@RequestParam("packageDetailId") String packageDetailId) {
        return packageDetailService.delete(packageDetailId);
    }

    @GetMapping(path = "/fetchAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getAll() {
        return packageDetailService.getAll();
    }

}
