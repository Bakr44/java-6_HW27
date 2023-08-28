package com.example.assignment27.Controller;

import com.example.assignment27.Api.ApiResponse;
import com.example.assignment27.Model.Blog;
import com.example.assignment27.Model.User;
import com.example.assignment27.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/get-all")
    public ResponseEntity getAllBlog() {
        return ResponseEntity.status(200).body(blogService.getAllBlog());
    }

    @PostMapping("/add")
    public ResponseEntity addBlogs(@AuthenticationPrincipal User user, @RequestBody @Valid Blog blog){

        blogService.addBlogs(user.getId(),blog);
        return ResponseEntity.status(200).body(new ApiResponse("Blog Added"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTodo(@AuthenticationPrincipal User user,@PathVariable Integer id,
                                     @RequestBody @Valid Blog blog) {
        blogService.updateBlog(user.getId(),id,blog);
        return ResponseEntity.status(200).body("Updated");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user,@PathVariable Integer id) {
        blogService.deleteBlog(user.getId(),id);
        return ResponseEntity.status(200).body("Deleted");
    }


    @GetMapping("/users/blogs")
    public ResponseEntity getAllBlogsForUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(blogService.getAllBlogsForUser(user));
    }

    @GetMapping("/blogs/{blogId}")
    public ResponseEntity getBlogById(@PathVariable Integer blogId) {
        return ResponseEntity.status(200).body(blogService.getBlogById(blogId));
    }

    @GetMapping("/users/blogs/byTitle")
    public ResponseEntity getBlogByTitle(@AuthenticationPrincipal User user, @RequestParam String title) {
        return ResponseEntity.status(200).body(blogService.getBlogByTitleAndUser(user,title));
    }
}
