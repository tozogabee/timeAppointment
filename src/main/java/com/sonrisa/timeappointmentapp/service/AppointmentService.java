package com.sonrisa.timeappointmentapp.service;

import com.sonrisa.timeappointmentapp.entity.AppointmentEntity;
import com.sonrisa.timeappointmentapp.entity.repository.AppointmentEntityRepository;
import com.sonrisa.timeappointmentapp.service.exception.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

        if((to.isAfter(LocalDateTime.of(to.getYear(),to.getMonth(),to.getDayOfMonth(),17,00))) || from.isAfter(LocalDateTime.of(to.getYear(),to.getMonth(),to.getDayOfMonth(),17,00))) {
            throw new ReservationException("You can not reserve after 17");
        }

        if(from.isAfter(to)) {
            throw new ReservationException("reservation issue where the from is bigger than to interval");
        }

        if((from.getYear() == to.getYear() && from.getMonth() == to.getMonth() && from.getDayOfMonth() == to.getDayOfMonth()) && (from.getMinute() % 30 != 0 || to.getMinute() % 30 != 0)) {
            throw new ReservationException("You can reserve only in 30 mins intervals");
        }

        int durationHour = Math.abs(to.getHour() - from.getHour());
        double durationMin = Math.abs(to.getMinute() - from.getMinute()) / 60.0;
        double duration = 0;

        if(from.getMinute() > to.getMinute()) {
            duration = durationHour - durationMin;
        } else if(from.getMinute() < to.getMinute()) {
            duration = durationHour + durationMin;
        }

        if(duration > 3.0) {
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

    public void removeReservationById(long id) {
        this.appointmentEntityRepository.deleteById(id);
    }

}
