package com.durgesh.durgeag13_Lombok.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtResponse {

    private String jwtToken;
    private  String username;

}
