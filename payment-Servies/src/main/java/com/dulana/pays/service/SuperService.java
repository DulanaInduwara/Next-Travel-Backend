package com.dulana.pays.service;


import com.dulana.pays.dto.PaymentDTO;
import com.dulana.pays.response.Response;

public interface SuperService<T extends PaymentDTO,ID >{
    Response save(T t);

    Response update(T t);

    Response delete(ID id);

    Response search(ID id);

    Response getAll();

    Response createAndSendResponse(int statusCode, String message, Object data);
}
