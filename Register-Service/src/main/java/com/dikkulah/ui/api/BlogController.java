package com.dikkulah.ui.api;

import com.dikkulah.business.services.BlogService;
import com.dikkulah.business.services.DailyService;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gateway/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;


    @PostMapping
    public JsonElement saveDaily(@RequestBody JsonElement jsonElement, @RequestHeader("Authorization") String token) {
        return blogService.blogSave(jsonElement, token);
    }

    @GetMapping
    public ResponseEntity<List<JsonElement>> listDaily(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(blogService.blogList(token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JsonElement> findDaily(@PathVariable(name = "id") String id, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(blogService.blogFind(id, token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonElement> deleteDaily(@PathVariable(name = "id") String id, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(blogService.blogDelete(id, token));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JsonElement> updateDaily(@PathVariable(name = "id") String id, @RequestBody JsonElement jsonElement, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(blogService.blogUpdate(id, jsonElement, token));
    }

}
