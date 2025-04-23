package com.blog.payload;

import lombok.Builder;

import lombok.Data;


@Data
@Builder
public class JwtResponse {
    private String token;
    private String username;
	
	

    // No need for manual getters, setters, constructors, or toString()
    // Lombok's @Data and @Builder will handle these for you
}
