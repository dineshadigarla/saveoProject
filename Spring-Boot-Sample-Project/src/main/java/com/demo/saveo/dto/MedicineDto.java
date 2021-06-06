package com.demo.saveo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineDto {

    @JsonProperty("c_unique_id")
    private String cUniqueId;

    @JsonProperty("n_quantity")
    private String n_quantity;

    @JsonProperty("c_name")
    private String cName;

}
