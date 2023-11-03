package com.dulana.us.endpoints;


import com.dulana.us.dto.UserAuthDTO;
import com.dulana.us.response.Response;
import com.dulana.us.service.custom.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
public class UserAuthController {

    @GetMapping(path = "/demo")
    public String getHello(){
        return "Hello";
    }
    @Autowired
    private UserAuthService userAuthService;

    @PostMapping(path = "/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response saveUserAuth(@RequestBody UserAuthDTO userAuthDTO){
        return userAuthService.save(userAuthDTO);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response update(@RequestBody UserAuthDTO userAuthDTO) {
        return userAuthService.update(userAuthDTO);
    }

    @GetMapping(path = "/search", params = "userAuthId", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response search(@RequestParam("userAuthId") String userAuthId) {
        return userAuthService.search(userAuthId);
    }

    @DeleteMapping(path = "/delete", params = "userAuthId", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response delete(@RequestParam("userAuthId") String userAuthId) {
        return userAuthService.delete(userAuthId);
    }

    @GetMapping(path = "/fetchAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getAll() {
        return userAuthService.getAll();
    }

}
