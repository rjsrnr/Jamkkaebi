package ssafy.modo.jamkkaebi.domain.manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ssafy.modo.jamkkaebi.common.ApiResponse;
import ssafy.modo.jamkkaebi.domain.delivery.dto.response.DeliveryDetailResponseDto;
import ssafy.modo.jamkkaebi.domain.manager.dto.request.VehicleMapRequestDto;
import ssafy.modo.jamkkaebi.domain.manager.dto.response.DriversResponseDto;
import ssafy.modo.jamkkaebi.domain.manager.dto.response.ManageConnectResponseDto;
import ssafy.modo.jamkkaebi.domain.manager.dto.response.ReportResponseDto;
import ssafy.modo.jamkkaebi.domain.manager.dto.response.VehicleMapResponseDto;
import ssafy.modo.jamkkaebi.domain.manager.entity.DriversType;
import ssafy.modo.jamkkaebi.domain.manager.service.ManagerReadService;
import ssafy.modo.jamkkaebi.domain.manager.service.ManagerWriteService;

@RestController
@RequestMapping("/api/v1/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerWriteService managerWriteService;
    private final ManagerReadService managerReadService;

    @GetMapping(path = "/drivers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<DriversResponseDto> getDrivers(@RequestParam String type) {

        DriversResponseDto responseDto;

        if (type.toUpperCase().equals(DriversType.MANAGED.toString())) {
            responseDto = managerReadService.getManagedDrivers();
        } else if (type.toUpperCase().equals(DriversType.UNMANAGED.toString())) {
            responseDto = managerReadService.getUnmanagedDrivers();
        } else {
            throw new IllegalArgumentException("Invalid driver type: " + type);
        }

        return ApiResponse.success(responseDto);
    }

    @PostMapping(path = "/driver/connect/{driver_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<ManageConnectResponseDto> connectDriver(@PathVariable("driver_id") Integer driverId) {
        return ApiResponse.success(managerWriteService.connectDriver(driverId));
    }

    @PostMapping(path = "/vehicle/map",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<VehicleMapResponseDto> mapVehicleToDriver(@RequestBody VehicleMapRequestDto dto) {
        return ApiResponse.success(managerWriteService.mapVehicleToDriver(dto));
    }

    @GetMapping(path = "/driver/delivery", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<DeliveryDetailResponseDto> getCurrentVehicleInfo(
            @Valid @RequestParam("driver_id") Long driverId) {
        return ApiResponse.success(managerReadService.getDriverDeliveryInfo(driverId));
    }

    @GetMapping(path = "/report", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<ReportResponseDto> getDriverReport(@Valid @RequestParam("driver_id") Long driverId) {
        return ApiResponse.success(managerReadService.getDriverReport(driverId));
    }
}