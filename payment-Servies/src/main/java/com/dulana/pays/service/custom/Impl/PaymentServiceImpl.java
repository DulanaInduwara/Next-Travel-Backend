package com.dulana.pays.service.custom.Impl;

import com.dulana.pays.dto.PaymentDTO;
import com.dulana.pays.entity.Payment;
import com.dulana.pays.repo.PaymentRepo;
import com.dulana.pays.response.Response;
import com.dulana.pays.service.custom.PaymentService;
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
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private Response response;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PaymentRepo paymentRepo;


    @Override
    @PostMapping(path = "save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response save(PaymentDTO payemntDTO) {
        if (search(payemntDTO.getPaymentId()).getData() == null) {
            paymentRepo.save(modelMapper.map(payemntDTO,Payment.class));
            return createAndSendResponse(HttpStatus.OK.value(), "Payemnt Successfully saved!", null);
        }
        throw new RuntimeException("Payemnt already exists!");
    }

    @Override
    public Response update(PaymentDTO payemntDTO) {
        if (search(payemntDTO.getPaymentId()).getData() != null) {
            paymentRepo.save(modelMapper.map(payemntDTO, Payment.class));
            return createAndSendResponse(HttpStatus.OK.value(), "Payemnt Successfully updated!", null);
        }
        throw new RuntimeException("Payemnt does not exists!");
    }

    @Override
    public Response delete(String s) {
        if (search(s).getData() != null) {
            paymentRepo.deleteById(s);
            return createAndSendResponse(HttpStatus.OK.value(), "Payemnt Successfully deleted!", null);
        }
        throw new RuntimeException("Payemnt does not exists!");
    }

    @Override
    public Response search(String s) {
        Optional<Payment> payemnt = paymentRepo.findById(s);
        if (payemnt.isPresent()) {
            return createAndSendResponse(HttpStatus.FOUND.value(), "Payemnt Successfully retrieved!", modelMapper.map(payemnt.get(), PaymentDTO.class));
        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Payemnt does not exists!", null);
    }

    @Override
    public Response getAll() {
        List<Payment> payemnts = paymentRepo.findAll();
        if (!payemnts.isEmpty()) {
            ArrayList<PaymentDTO> payemnts_dtos = new ArrayList<>();
            payemnts.forEach((payemntss) -> {
                payemnts_dtos.add(modelMapper.map(payemntss, PaymentDTO.class));
            });
            return createAndSendResponse(HttpStatus.FOUND.value(), "Payemnts Successfully retrieved!", payemnts_dtos);
        }
        throw new RuntimeException("No Payemnts found in the database!");
    }

    @Override
    public Response createAndSendResponse(int statusCode, String message, Object data) {
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
