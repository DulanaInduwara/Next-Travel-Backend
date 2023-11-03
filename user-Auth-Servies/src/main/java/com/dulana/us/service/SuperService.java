package com.dulana.us.service;


import com.dulana.us.dto.UserAuthDTO;
import com.dulana.us.response.Response;

public interface SuperService<T extends UserAuthDTO,ID >{
    Response save(T t);

    Response update(T t);

    Response delete(ID id);

    Response search(ID id);

    Response getAll();

    Response createAndSendResponse(int statusCode, String message, Object data);
}
