package ssafy.modo.jamkkaebi.domain.vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssafy.modo.jamkkaebi.domain.vehicle.entity.Vehicle;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Boolean existsByVehicleNumber(String vehicleNumber);

    @Query("""
            SELECT v FROM Vehicle v
            WHERE v.driver.id = :driverId
            """)
    Optional<Vehicle> findByDriverId(Long driverId);

    Boolean existsByDriverId(Long driverId);

    @Query("""
            SELECT v FROM Vehicle v WHERE v.id = :vehicleId AND v.driver IS NULL
            """)
    Optional<Vehicle> findAvailableById(Long vehicleId);

    @Query("""
            SELECT CASE WHEN COUNT(v) > 0 THEN TRUE ELSE FALSE END
            FROM Vehicle v
            JOIN ManagerAndDriver md ON md.driver = v.driver
            WHERE v.id = :vehicleId AND md.manager.id = :managerId
            """)
    Boolean isVehicleMappedToDriverOfManager(Long vehicleId, Long managerId);

    @Query("""
            SELECT v FROM Vehicle v
            JOIN Device d on d.vehicle = v
            WHERE d.uuid = :uuid
            """)
    Optional<Vehicle> findByDeviceUuid(String uuid);
}
