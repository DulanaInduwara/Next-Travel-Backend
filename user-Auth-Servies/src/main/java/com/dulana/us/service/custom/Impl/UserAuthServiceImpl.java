package com.dulana.us.service.custom.Impl;

import com.dulana.us.dto.UserAuthDTO;
import com.dulana.us.entity.UserAuth;
import com.dulana.us.repo.UserAuthRepo;
import com.dulana.us.response.Response;
import com.dulana.us.service.custom.UserAuthService;
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
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    private Response response;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserAuthRepo userAuthRepo;


    @Override
    @PostMapping(path = "save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response save(UserAuthDTO userAuthDTO) {
        if (search(userAuthDTO.getUserId()).getData() == null) {
            userAuthRepo.save(modelMapper.map(userAuthDTO, UserAuth.class));
            return createAndSendResponse(HttpStatus.OK.value(), "UserAuth Successfully saved!", null);
        }
        throw new RuntimeException("UserAuth already exists!");
    }

    @Override
    public Response update(UserAuthDTO userAuthDTO) {
        if (search(userAuthDTO.getUserId()).getData() != null) {
            userAuthRepo.save(modelMapper.map(userAuthDTO,UserAuth.class));
            return createAndSendResponse(HttpStatus.OK.value(), "UserAuth Successfully updated!", null);
        }
        throw new RuntimeException("UserAuth does not exists!");
    }

    @Override
    public Response delete(String s) {
        if (search(s).getData() != null) {
            userAuthRepo.deleteById(s);
            return createAndSendResponse(HttpStatus.OK.value(), "UserAuth Successfully deleted!", null);
        }
        throw new RuntimeException("UserAuth does not exists!");
    }

    @Override
    public Response search(String s) {
        Optional<UserAuth> userAuth = userAuthRepo.findById(s);
        if (userAuth.isPresent()) {
            return createAndSendResponse(HttpStatus.FOUND.value(), "UserAuth Successfully retrieved!", modelMapper.map(userAuth.get(), UserAuthDTO.class));
        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "UserAuth does not exists!", null);
    }

    @Override
    public Response getAll() {
        List<UserAuth> userAuths = userAuthRepo.findAll();
        if (!userAuths.isEmpty()) {
            ArrayList<UserAuthDTO> userAuths_dtos = new ArrayList<>();
            userAuths.forEach((userAuthss) -> {
                userAuths_dtos.add(modelMapper.map(userAuthss, UserAuthDTO.class));
            });
            return createAndSendResponse(HttpStatus.FOUND.value(), "UserAuths Successfully retrieved!", userAuths_dtos);
        }
        throw new RuntimeException("No UserAuths found in the database!");
    }

    @Override
    public Response createAndSendResponse(int statusCode, String message, Object data) {
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
