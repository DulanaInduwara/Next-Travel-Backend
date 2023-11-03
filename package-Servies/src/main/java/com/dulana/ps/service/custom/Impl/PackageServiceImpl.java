package com.dulana.ps.service.custom.Impl;

import com.dulana.ps.dto.PackageDTO;
import com.dulana.ps.entity.Package;
import com.dulana.ps.repo.PackageRepo;
import com.dulana.ps.response.Response;
import com.dulana.ps.service.custom.PackageService;
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
public class PackageServiceImpl implements PackageService {
    @Autowired
    private Response response;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PackageRepo packageRepo;


    @Override
    @PostMapping(path = "save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response save(PackageDTO packageDTO) {
        if (search(packageDTO.getPackageId()).getData() == null) {
            packageRepo.save(modelMapper.map(packageDTO, Package.class));
            return createAndSendResponse(HttpStatus.OK.value(), "Package Successfully saved!", null);
        }
        throw new RuntimeException("Package already exists!");
    }

    @Override
    public Response update(PackageDTO packageDTO) {
        if (search(packageDTO.getPackageId()).getData() != null) {
            packageRepo.save(modelMapper.map(packageDTO, Package.class));
            return createAndSendResponse(HttpStatus.OK.value(), "package Successfully updated!", null);
        }
        throw new RuntimeException("Package does not exists!");
    }

    @Override
    public Response delete(String s) {
        if (search(s).getData() != null) {
            packageRepo.deleteById(s);
            return createAndSendResponse(HttpStatus.OK.value(), "Package Successfully deleted!", null);
        }
        throw new RuntimeException("Package does not exists!");
    }

    @Override
    public Response search(String s) {
        Optional<Package> pack = packageRepo.findById(s);
        if (pack.isPresent()) {
            return createAndSendResponse(HttpStatus.FOUND.value(), "Package Successfully retrieved!", modelMapper.map(pack.get(), PackageDTO.class));
        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Package does not exist!", null);
    }


    @Override
    public Response getAll() {
        List<Package> packages = packageRepo.findAll();
        if (!packages.isEmpty()) {
            ArrayList<PackageDTO> packages_dtos = new ArrayList<>();
            packages.forEach((packagess) -> {
                packages_dtos.add(modelMapper.map(packagess, PackageDTO.class));
            });
            return createAndSendResponse(HttpStatus.FOUND.value(), "Packages Successfully retrieved!", packages_dtos);
        }
        throw new RuntimeException("No Packages found in the database!");
    }

    @Override
    public Response createAndSendResponse(int statusCode, String message, Object data) {
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
