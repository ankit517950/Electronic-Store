package com.lcwd.electronic.store.dtos;

import com.lcwd.electronic.store.validate.ImageNameValid;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String userId;
    @Size(min=3,max=15,message = "Invalid name!!")
    private String name;
    /*@Email(message = "Invalid Email!!")*/
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Invalid user email !!")
    @NotBlank(message = "Invalid email")
    private String email;
    @NotBlank(message = "Password is required!!")
    private String password;
    @Size(min=4,max=6,message = "Invalid gender !!")
    private String gender;
    @NotBlank(message = "Please fill about field !!")
    private String about;
    @ImageNameValid
    private String imageName;
}
