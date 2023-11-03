package com.dulana.pds.service;


import com.dulana.pds.dto.PackageDetailDTO;
import com.dulana.pds.response.Response;

public interface SuperService<T extends PackageDetailDTO,ID >{
    Response save(T t);

    Response update(T t);

    Response delete(ID id);

    Response search(ID id);

    Response getAll();

    Response createAndSendResponse(int statusCode, String message, Object data);
}
