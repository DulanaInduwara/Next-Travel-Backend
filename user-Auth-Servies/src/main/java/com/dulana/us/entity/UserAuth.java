package com.dulana.us.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAuth {

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
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
