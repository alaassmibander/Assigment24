package com.example.assigment24.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddressDTO {

    private Integer teacherId;
    private Integer buildingNumber;
    private String street;
    private String area;

}
