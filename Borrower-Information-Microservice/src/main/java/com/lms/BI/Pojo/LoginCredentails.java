package com.lms.BI.Pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginCredentails {
    
    private String uname;
    private String password;
    private List<String> role;
}
