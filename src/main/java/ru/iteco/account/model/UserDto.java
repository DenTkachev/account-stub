package ru.iteco.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
public class UserDto {

    private Integer id;
    private String name;
    @Email
    private String email;
    private AddressDto address;

}
