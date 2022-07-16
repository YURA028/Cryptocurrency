package com.example.cryptocurrency.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class CryptoNameDTO {
    private String name;
}
