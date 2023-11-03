package com.dulana.us.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAuthDTO implements Serializable,SuperDTO {
    private String userId;
    private String userName;
    private String pw;
    //    @Enumerated(EnumType.STRING)
    private String role;
    private String userNIC;
    private String userAddress;
    private String userDOB;
    private String userPhone;
    private String userEmail;
    private String gender;
    private String userNICimageLocation;
    private String userImageLocation;
    private boolean isValidated;
}

