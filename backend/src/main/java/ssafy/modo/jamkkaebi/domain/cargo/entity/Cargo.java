package ssafy.modo.jamkkaebi.domain.cargo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ssafy.modo.jamkkaebi.common.entity.BaseEntity;
import ssafy.modo.jamkkaebi.domain.delivery.entity.Delivery;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cargo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "cargo")
    private Delivery delivery;

    @NotNull
    private String cargoInfo;

    @NotNull
    private String origin;

    @NotNull
    private String originLat;

    @NotNull
    private String originLon;

    @NotNull
    private String destination;

    @NotNull
    private String destinationLat;

    @NotNull
    private String destinationLon;

    @NotNull
    private LocalDateTime dueDate;

    @NotNull
    private Integer distance;

    private String routeId;

    public void updateRouteId(String routeId) {
        this.routeId = routeId;
    }
}
