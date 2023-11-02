package com.example.coffee2.reponsitory;

import com.example.coffee2.entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface EquipmentRespository extends JpaRepository<EquipmentEntity, Long> {
    @Query(
            value = "SELECT * FROM equipment",
            nativeQuery = true
    )
    List<EquipmentEntity> findAllEquipment();

    List<String> findAllByName(@RequestParam String name);

    List<String> findByName(@RequestBody String name);


}
