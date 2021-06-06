package com.demo.saveo.dto;

import com.demo.saveo.model.Medicine;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MedicinesDto {

    @JsonProperty("medicines")
    private List<Medicine> medicines;
}
