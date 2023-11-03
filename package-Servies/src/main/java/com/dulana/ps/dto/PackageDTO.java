package com.dulana.ps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackageDTO implements Serializable,SuperDTO {
    private String packageId;
    private String packageCategory;
    private String vehicleCategory;
    private String hotelCategory;

    @ElementCollection
    private List<String> hotelsList;

    @ElementCollection
    private List<String> vehicleList;
}
