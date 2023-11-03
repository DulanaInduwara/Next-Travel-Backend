package com.dulana.hs.service;


import com.dulana.hs.dto.HotelDTO;
import com.dulana.hs.response.Response;

public interface SuperService<T extends HotelDTO,ID >{
    Response save(T t);

    Response update(T t);

    Response delete(ID id);

    Response search(ID id);

    Response getAll();

    Response createAndSendResponse(int statusCode, String message, Object data);
}
