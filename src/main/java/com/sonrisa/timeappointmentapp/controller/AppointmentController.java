package com.sonrisa.timeappointmentapp.controller;

import com.sonrisa.timeappointmentapp.controller.json.AppointmentInput;
import com.sonrisa.timeappointmentapp.entity.AppointmentEntity;
import com.sonrisa.timeappointmentapp.service.AppointmentService;
import com.sonrisa.timeappointmentapp.service.exception.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<AppointmentEntity>> getAllAppointments() {
        List<AppointmentEntity> allAppointments = this.appointmentService.getAllEntities();

        if(allAppointments.isEmpty()) {
            return ResponseEntity.status(200).body(null);
        }
        return ResponseEntity.status(200).body(allAppointments);
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<AppointmentEntity> addNewAppointment(@RequestBody AppointmentInput input) throws ReservationException {
        LocalDateTime from = LocalDateTime.parse(input.getFrom());
        LocalDateTime to = LocalDateTime.parse(input.getTo());
        String name = input.getName();

        AppointmentEntity newEntity = this.appointmentService.reservation(name,from, to);

        return ResponseEntity.status(200).body(newEntity);
    }

    @DeleteMapping(value = "/delete" ,produces = "application/json")
    public ResponseEntity<Void> removeAllReservations() {
        this.appointmentService.cleanUp();
        return ResponseEntity.status(200).build();
    }

}
