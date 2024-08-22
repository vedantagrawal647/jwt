package com.durgesh.durgeag13_Lombok.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtRequest {

    private String username;
    private  String password;

}
