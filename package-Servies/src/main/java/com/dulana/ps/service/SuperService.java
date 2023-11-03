package com.dulana.ps.service;


import com.dulana.ps.dto.PackageDTO;
import com.dulana.ps.response.Response;

public interface SuperService<T extends PackageDTO,ID >{
    Response save(T t);

    Response update(T t);

    Response delete(ID id);

    Response search(ID id);

    Response getAll();

    Response createAndSendResponse(int statusCode, String message, Object data);
}
