package com.example.mshotelcommand.Aggregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.EntityId;

import jakarta.persistence.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class ChambreAggregate {


    @Id
    private String idCh;
    private String type;
    private Integer etage;
    @ManyToOne
    @TargetAggregateIdentifier
    private HotelAggregate hotel;

}
