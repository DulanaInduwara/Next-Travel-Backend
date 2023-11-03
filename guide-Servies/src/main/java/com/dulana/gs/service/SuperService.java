package com.dulana.gs.service;


import com.dulana.gs.dto.GuideDTO;
import com.dulana.gs.response.Response;

public interface SuperService<T extends GuideDTO,ID >{
    Response save(T t);

    Response update(T t);

    Response delete(ID id);

    Response search(ID id);

    Response getAll();

    Response createAndSendResponse(int statusCode, String message, Object data);
}
