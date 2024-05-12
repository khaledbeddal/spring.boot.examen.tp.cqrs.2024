package com.example.msqueryjoin.projection;


import com.example.coreapi.events.*;
import com.example.msqueryjoin.entities.StatusTable;
import com.example.msqueryjoin.repository.StatusTableRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class StatusProjection {

    @Autowired
    private StatusTableRepository statusTableRepository;


    @EventHandler
    public void addHotel(HotelAddedEvent hotelAddedEvent){
        StatusTable statusTable = new StatusTable();
        statusTable.setIdH(hotelAddedEvent.getIdH());
        statusTable.setIdCh("");
        statusTable.setNbReservations(0);
        statusTableRepository.save(statusTable);
    }

    @EventHandler
    public void addChambre(ChambreAddedEvent chambreAddedEvent){
        statusTableRepository.save(new StatusTable(chambreAddedEvent.getIdCh(),chambreAddedEvent.getIdH(),0));
    }

    @EventHandler
    public void deleteChambre(ChambreRemovedEvent chambreRemovedEvent){
        statusTableRepository.deleteById(chambreRemovedEvent.getIdCh());
    }

    @EventHandler
    public void addReservation(ReservationAddedEvent reservationAddedEvent){

        StatusTable statusTable = statusTableRepository.findById(reservationAddedEvent.getIdCh()).get();
        statusTable.setNbReservations(statusTable.getNbReservations()+1);
        statusTableRepository.save(statusTable);
    }

    @EventHandler
    public void deleteReservation(ReservationRemovedEvent reservationRemovedEvent){

        StatusTable statusTable = statusTableRepository.findById(reservationRemovedEvent.getIdCh()).get();
        statusTable.setNbReservations(statusTable.getNbReservations()-1);
        statusTableRepository.save(statusTable);
    }

}
