package com.example.proptitendcoursepractice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String name;
    private String username;
    private String password;
    private String roles = "ROLE_USER";
}
