package com.example.cryptocurrency.model;

import com.example.cryptocurrency.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String symbol;
    private BigDecimal price_usd;

    public static UserDTO toModel(User user){
        UserDTO model = new UserDTO();
        model.setId(user.getId());
        model.setUsername(user.getUsername());
        model.setSymbol(user.getSymbol());
        model.setPrice_usd(user.getPrice().getPrice_usd());
        return model;
    }
}
