package com.dikkulah.ui.api;

import com.dikkulah.business.services.DailyService;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/gateway/daily")
public class DailyController {
    private final DailyService dailyService;

    @PostMapping
    public JsonElement saveDaily(@RequestBody JsonElement jsonElement, @RequestHeader("Authorization") String token) {
        return dailyService.dailySave(jsonElement, token);
    }

    @GetMapping
    public ResponseEntity<List<JsonElement>> listDaily(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(dailyService.dailyList(token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JsonElement> findDaily(@PathVariable(name = "id") String id, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(dailyService.dailyFind(id, token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonElement> deleteDaily(@PathVariable(name = "id") String id, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(dailyService.dailyDelete(id, token));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JsonElement> updateDaily(@PathVariable(name = "id") String id, @RequestBody JsonElement jsonElement, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(dailyService.dailyUpdate(id, jsonElement, token));
    }
}
