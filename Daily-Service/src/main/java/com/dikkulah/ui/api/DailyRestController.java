package com.dikkulah.ui.api;

import com.dikkulah.business.dto.DailyDto;
import com.dikkulah.business.services.DailyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/daily")
@CrossOrigin
public class DailyRestController {

    private final DailyService dailyService;

    @GetMapping
    public ResponseEntity<List<DailyDto>> getUserDaily(@RequestHeader("GatewayToken") String gatewayToken) {
        return ResponseEntity.ok().body(dailyService.listUserDaily(gatewayToken));
    }

    @PostMapping
    public ResponseEntity<DailyDto> createDaily(
            @Valid @RequestBody DailyDto dailyDto,
            @RequestHeader("GatewayToken") String gatewayToken) {

        return ResponseEntity.ok().body(dailyService.createDaily(dailyDto, gatewayToken));
    }

    @GetMapping("{id}")
    public ResponseEntity<DailyDto> findDailyById(
            @PathVariable(name = "id") String id,
            @RequestHeader("GatewayToken") String gatewayToken) {
        return ResponseEntity.ok(dailyService.findDaily(id, gatewayToken));
    }

    @PutMapping("{id}")
    public ResponseEntity<DailyDto> updateDailyById(
            @PathVariable(name = "id") String id,
            @Valid @RequestBody DailyDto dailyDto,
            @RequestHeader("GatewayToken") String gatewayToken) {


        return ResponseEntity.ok(dailyService.updateDaily(id, dailyDto, gatewayToken));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDailyById(
            @PathVariable(name = "id") String id,
            @RequestHeader("GatewayToken") String gatewayToken) {
        return ResponseEntity.ok().body(dailyService.deleteDaily(id, gatewayToken));
    }

}
