package com.dikkulah.ui.api;

import com.dikkulah.business.dto.BlogDto;
import com.dikkulah.business.services.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/blog")
@CrossOrigin
public class BlogRestController {

    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<List<BlogDto>> getUserBlog(@RequestHeader("GatewayToken") String gatewayToken) {
        return ResponseEntity.ok().body(blogService.listUserBlog(gatewayToken));
    }

    @PostMapping
    public ResponseEntity<BlogDto> createBlog(
            @Valid @RequestBody BlogDto blogDto,
            @RequestHeader("GatewayToken") String gatewayToken) {

        return ResponseEntity.ok().body(blogService.createBlog(blogDto, gatewayToken));
    }

    @GetMapping("{id}")
    public ResponseEntity<BlogDto> findBlogById(
            @PathVariable(name = "id") String id,
            @RequestHeader("GatewayToken") String gatewayToken) {
        return ResponseEntity.ok(blogService.findBlog(id, gatewayToken));
    }

    @PutMapping("{id}")
    public ResponseEntity<BlogDto> updateBlogById(
            @PathVariable(name = "id") String id,
            @Valid @RequestBody BlogDto blogDto,
            @RequestHeader("GatewayToken") String gatewayToken) {


        return ResponseEntity.ok(blogService.updateBlog(id, blogDto, gatewayToken));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBlogById(
            @PathVariable(name = "id") String id,
            @RequestHeader("GatewayToken") String gatewayToken) {
        return ResponseEntity.ok().body(blogService.deleteBlog(id, gatewayToken));
    }

}
