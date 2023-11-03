package com.dulana.pays.endpoints;


import com.dulana.pays.dto.PaymentDTO;
import com.dulana.pays.response.Response;
import com.dulana.pays.service.custom.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin
public class PaymentController {

    @GetMapping(path = "/demo")
    public String getHello(){
        return "Hello";
    }
    @Autowired
    private PaymentService paymentService;

    @PostMapping(path = "/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response savePayment(@RequestBody PaymentDTO paymentDTO){
        return paymentService.save(paymentDTO);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response update(@RequestBody PaymentDTO paymentDTO) {
        return paymentService.update(paymentDTO);
    }

    @GetMapping(path = "/search", params = "paymentId", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response search(@RequestParam("paymentId") String paymentId) {
        return paymentService.search(paymentId);
    }

    @DeleteMapping(path = "/delete", params = "paymentId", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response delete(@RequestParam("paymentId") String paymentId) {
        return paymentService.delete(paymentId);
    }

    @GetMapping(path = "/fetchAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getAll() {
        return paymentService.getAll();
    }

}
