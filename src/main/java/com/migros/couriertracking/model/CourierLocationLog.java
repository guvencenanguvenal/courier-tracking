package com.migros.couriertracking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CourierLocationLog {

    @Id
    @GeneratedValue
    private Long id;
    private Long courierId;
    private Double lat;
    private Double lng;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date logDate;

}
