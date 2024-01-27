package com.lcwd.electronic.store.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private String categoryId;
    @NotBlank(message = "title is required")
    @Size(min=4,message = "title is greater or equal 4 !!")
    private String title;
    @NotBlank(message = "Description Required !!")
    private String description;
    private String coverImage;
}
