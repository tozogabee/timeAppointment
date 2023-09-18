package com.sonrisa.timeappointmentapp.service;

import com.sonrisa.timeappointmentapp.entity.AppointmentEntity;
import com.sonrisa.timeappointmentapp.entity.repository.AppointmentEntityRepository;
import com.sonrisa.timeappointmentapp.service.exception.ReservationException;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

    @Mock
    private AppointmentEntityRepository appointmentEntityRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @DisplayName("One person reservation SUCCESS")
    @Test
    public void onePersonReservationSuccess() throws ReservationException {
        //Given
        AppointmentEntity entity = new AppointmentEntity();
        entity.setId(1L);
        entity.setName("Gabor");
        entity.setFrom(LocalDateTime.of(2023,9,18,9,00));
        entity.setTo(LocalDateTime.of(2023,9,18,10,00));

        //When
        lenient().when(this.appointmentEntityRepository.save(entity)).thenReturn(entity);

        //Then
        AppointmentEntity savedEntity = this.appointmentService.reservation("tozo",entity.getFrom(),entity.getTo());
        assertTrue(savedEntity!=null);
        assertEquals("tozo",savedEntity.getName());
        assertEquals(LocalDateTime.of(2023,9,18,9,00),savedEntity.getFrom());
        assertEquals(LocalDateTime.of(2023,9,18,10,00),savedEntity.getTo());
    }

    @DisplayName("One person reservation not in 30 mins")
    @Test
    public void reservationNotIn30MinsFromInterval() {
        AppointmentEntity newEntity = new AppointmentEntity();
        newEntity.setName("Istvan");
        newEntity.setId(1L);
        newEntity.setFrom(LocalDateTime.of(2023,9,18,9,45));
        newEntity.setTo(LocalDateTime.of(2023,9,18,10,00));

        Exception exception = assertThrows(ReservationException.class,
                () -> this.appointmentService.reservation(newEntity.getName(),newEntity.getFrom(), newEntity.getTo())
        );

        String expectedMessage = "You can reserve only in 30 mins intervals";

        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @DisplayName("One person reservation not in 30 mins")
    @Test
    public void reservationNotIn30MinsToInterval() {
        AppointmentEntity newEntity = new AppointmentEntity();
        newEntity.setName("Istvan");
        newEntity.setId(1L);
        newEntity.setFrom(LocalDateTime.of(2023,9,18,9,30));
        newEntity.setTo(LocalDateTime.of(2023,9,18,10,10));

        Exception exception = assertThrows(ReservationException.class,
                () -> this.appointmentService.reservation(newEntity.getName(),newEntity.getFrom(), newEntity.getTo())
        );

        String expectedMessage = "You can reserve only in 30 mins intervals";

        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @DisplayName("From reservation time before 9")
    @Test
    public void testReservationFromBeforeNine() {
        AppointmentEntity newEntity = new AppointmentEntity();
        newEntity.setName("Istvan");
        newEntity.setId(1L);
        newEntity.setFrom(LocalDateTime.of(2023,9,18,8,00));
        newEntity.setTo(LocalDateTime.of(2023,9,18,10,10));

        Exception exception = assertThrows(ReservationException.class,
                () -> this.appointmentService.reservation(newEntity.getName(),newEntity.getFrom(), newEntity.getTo())
        );

        String expectecMessage = "You can not reserve before 9";

        assertTrue(exception.getMessage().contains(expectecMessage));
    }

    @DisplayName("From reservation time after 17")
    @Test
    public void testReservationFromAfterFive() {
        AppointmentEntity newEntity = new AppointmentEntity();
        newEntity.setName("Istvan");
        newEntity.setId(1L);
        newEntity.setFrom(LocalDateTime.of(2023,9,18,16,00));
        newEntity.setTo(LocalDateTime.of(2023,9,18,18,00));

        Exception exception = assertThrows(ReservationException.class,
                () -> this.appointmentService.reservation(newEntity.getName(),newEntity.getFrom(), newEntity.getTo())
        );

        String expectecMessage = "You can not reserve after 17";

        assertTrue(exception.getMessage().contains(expectecMessage));
    }

    @DisplayName("Duration more than 3 hours")
    @Test
    public void testReservationDurationMoreThanThreeHours() {
        AppointmentEntity newEntity = new AppointmentEntity();
        newEntity.setName("Istvan");
        newEntity.setId(1L);
        newEntity.setFrom(LocalDateTime.of(2023,9,18,13,30));
        newEntity.setTo(LocalDateTime.of(2023,9,18,17,00));

        Exception exception = assertThrows(ReservationException.class,
                () -> this.appointmentService.reservation(newEntity.getName(),newEntity.getFrom(), newEntity.getTo())
        );

        String expectecMessage = "You can not reserve more than 3 hours";

        assertTrue(exception.getMessage().contains(expectecMessage));
    }

    @DisplayName("Reservation overlapping negative test")
    @Test
    public void testOverlapping() {
        AppointmentEntity newEntity = new AppointmentEntity();
        newEntity.setName("Istvan");
        newEntity.setId(1L);
        newEntity.setFrom(LocalDateTime.of(2023,9,18,10,30));
        newEntity.setTo(LocalDateTime.of(2023,9,18,11,00));

        AppointmentEntity entityInDb = entityInDB();

        lenient().when(this.appointmentEntityRepository.findAppointmentEntitiesBetweenFromAndTo(newEntity.getFrom(),newEntity.getTo())).thenReturn(List.of(entityInDb));


        Exception exception = assertThrows(ReservationException.class,
                () -> this.appointmentService.reservation(newEntity.getName(),newEntity.getFrom(), newEntity.getTo())
        );

        String expectecMessage = "The timetable has already reserved between "+newEntity.getFrom()+" and "+newEntity.getTo();

        assertTrue(exception.getMessage().contains(expectecMessage));
    }



    private AppointmentEntity entityInDB() {
        AppointmentEntity entity = new AppointmentEntity();
        entity.setId(1L);
        entity.setName("tozo");
        entity.setFrom(LocalDateTime.of(2023,9,18,10,00));
        entity.setTo(LocalDateTime.of(2023,9,18,11,30));
        return entity;
    }

    
    @After("cleanUp")
    public void cleanUp() {
        this.appointmentService.cleanUp();
    }

}
