package com.media.controller;

public class PostController {
    package com.media.controller;

import com.media.entity.Post;
import com.media.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/posts")
    public class PostController {

        private final PostService postService;

        public PostController(PostService postService) {
            this.postService = postService;
        }
        @GetMapping("/author/{id}")
        public List<Post> getByAuthor(@PathVariable Long id) {
            return postService.getPostsByAuthor(id);
        }
        @DeleteMapping("/author/{id}")
        public void deleteByAuthor(@PathVariable Long id) {
            postService.deletePostsByAuthor(id);
        }
    }
}
