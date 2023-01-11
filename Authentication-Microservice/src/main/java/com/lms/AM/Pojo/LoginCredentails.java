package com.lms.AM.Pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginCredentails {
    
    private String uname;
    private String password;
    private List<String> role;
}
