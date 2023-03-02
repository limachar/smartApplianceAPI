package com.example.smartcontroller.repository;

import com.example.smartcontroller.model.Appliance;
import com.example.smartcontroller.model.Dishwasher;
import com.example.smartcontroller.model.Oven;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ApplianceRepo extends JpaRepository<Appliance, Long> {
    @Query(value = "SELECT * FROM smarterdb.appliance AS ap WHERE ap.id=:id", nativeQuery = true)
    public Optional<Dishwasher> getDishwasherById(@Param("id") Long id);
   // @Query("FROM appliance AS ap WHERE ap.id=:id")
   // public Optional<Oven> getOvenById(@Param("id") Long id);

    Optional<Appliance> findById(Long id);
}
