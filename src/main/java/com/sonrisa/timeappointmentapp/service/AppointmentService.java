package com.sonrisa.timeappointmentapp.service;

import com.sonrisa.timeappointmentapp.entity.AppointmentEntity;
import com.sonrisa.timeappointmentapp.entity.repository.AppointmentEntityRepository;
import com.sonrisa.timeappointmentapp.service.exception.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentEntityRepository appointmentEntityRepository;

    public List<AppointmentEntity> betweenTwoDates(LocalDateTime from, LocalDateTime to) {
        return this.appointmentEntityRepository.findAppointmentEntitiesBetweenFromAndTo(from,to);
    }

    public AppointmentEntity reservation(String name,LocalDateTime from, LocalDateTime to) throws ReservationException {

        if(from.getHour() < 9) {
            throw new ReservationException("You can not reserve before 9");
        }

        if(to.getHour() > 17) {
            throw new ReservationException("You can not reserve after 17");
        }

        if(to.getHour() < from.getHour()) {
            throw new ReservationException("reservation issue to < from");
        }

        if((from.getYear() == to.getYear() && from.getMonth() == to.getMonth() && from.getDayOfMonth() == to.getDayOfMonth()) && (from.getMinute() % 30 != 0 || to.getMinute() % 30 != 0)) {
            throw new ReservationException("You can reserve only in 30 mins intervals");
        }

        int duration = to.getHour() - from.getHour();

        if(duration > 3) {
            throw new ReservationException("You can not reserve more than 3 hours");
        }

        List<AppointmentEntity> existingEntity = betweenTwoDates(from,to);

        if(!existingEntity.isEmpty()) {
            throw new ReservationException("The timetable has already reserved between "+from+" and "+to);
        }

        AppointmentEntity newAppointment = new AppointmentEntity();
        newAppointment.setName(name);
        newAppointment.setFrom(from);
        newAppointment.setTo(to);

        this.appointmentEntityRepository.save(newAppointment);
        return newAppointment;
    }

    public List<AppointmentEntity> getAllEntities() {
        return appointmentEntityRepository.findAll();
    }

    public void cleanUp() {
        this.appointmentEntityRepository.deleteAll();
    }

}
