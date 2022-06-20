package com.migros.couriertracking.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Courier {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double lat;
    private Double lng;

}
