package com.dulana.pds.service.custom.Impl;

import com.dulana.pds.dto.PackageDetailDTO;
import com.dulana.pds.entity.PackageDetail;
import com.dulana.pds.repo.PackageDetailRepo;
import com.dulana.pds.response.Response;
import com.dulana.pds.service.custom.PackageDetailService;
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
public class PackageDetailServiceImpl implements PackageDetailService {
    @Autowired
    private Response response;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PackageDetailRepo packageDetailRepo;


    @Override
    @PostMapping(path = "save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response save(PackageDetailDTO packageDetailDTO) {
        if (search(packageDetailDTO.getPackageDetailId()).getData() == null) {
            packageDetailRepo.save(modelMapper.map(packageDetailDTO, PackageDetail.class));
            return createAndSendResponse(HttpStatus.OK.value(), "PackageDetail Successfully saved!", null);
        }
        throw new RuntimeException("PackageDetail already exists!");
    }

    @Override
    public Response update(PackageDetailDTO packageDetailDTO) {
        if (search(packageDetailDTO.getPackageDetailId()).getData() != null) {
            packageDetailRepo.save(modelMapper.map(packageDetailDTO, PackageDetail.class));
            return createAndSendResponse(HttpStatus.OK.value(), "PackageDetail Successfully updated!", null);
        }
        throw new RuntimeException("PackageDetail does not exists!");
    }

    @Override
    public Response delete(String s) {
        if (search(s).getData() != null) {
            packageDetailRepo.deleteById(s);
            return createAndSendResponse(HttpStatus.OK.value(), "PackageDetail Successfully deleted!", null);
        }
        throw new RuntimeException("PackageDetail does not exists!");
    }

    @Override
    public Response search(String s) {
        Optional<PackageDetail> packageDetail = packageDetailRepo.findById(s);
        if (packageDetail.isPresent()) {
            return createAndSendResponse(HttpStatus.FOUND.value(), "PackageDetail Successfully retrieved!", modelMapper.map(packageDetail.get(), PackageDetailDTO.class));
        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "PackageDetail does not exists!", null);
    }

    @Override
    public Response getAll() {
        List<PackageDetail> packageDetails = packageDetailRepo.findAll();
        if (!packageDetails.isEmpty()) {
            ArrayList<PackageDetailDTO> packageDetails_dtos = new ArrayList<>();
            packageDetails.forEach((packageDetailss) -> {
                packageDetails_dtos.add(modelMapper.map(packageDetailss, PackageDetailDTO.class));
            });
            return createAndSendResponse(HttpStatus.FOUND.value(), "PackageDetails Successfully retrieved!", packageDetails_dtos);
        }
        throw new RuntimeException("No PackageDetails found in the database!");
    }

    @Override
    public Response createAndSendResponse(int statusCode, String message, Object data) {
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
