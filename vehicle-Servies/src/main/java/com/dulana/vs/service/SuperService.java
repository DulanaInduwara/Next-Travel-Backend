package com.dulana.vs.service;


import com.dulana.vs.dto.VehicleDTO;
import com.dulana.vs.response.Response;

public interface SuperService<T extends VehicleDTO,ID >{
    Response save(T t);

    Response update(T t);

    Response delete(ID id);

    Response search(ID id);

    Response getAll();

    Response createAndSendResponse(int statusCode, String message, Object data);
}
