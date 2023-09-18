package com.sonrisa.timeappointmentapp.entity.repository;

import com.sonrisa.timeappointmentapp.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentEntityRepository extends JpaRepository<AppointmentEntity, Long> {

    @Query("SELECT a FROM AppointmentEntity a WHERE (:fromDate BETWEEN a.from AND a.to) OR (:toDate BETWEEN a.from AND a.to)")
    List<AppointmentEntity> findAppointmentEntitiesBetweenFromAndTo(@Param("fromDate") LocalDateTime fromDate,@Param("toDate") LocalDateTime toDate);
}
