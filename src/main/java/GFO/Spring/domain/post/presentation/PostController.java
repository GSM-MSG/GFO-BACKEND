package GFO.Spring.domain.post.presentation;

import GFO.Spring.domain.post.presentation.dto.request.CreatePostRequest;
import GFO.Spring.domain.post.presentation.dto.request.ModifyPostRequest;
import GFO.Spring.domain.post.presentation.dto.response.PostListResDto;
import GFO.Spring.domain.post.service.CreatePostService;
import GFO.Spring.domain.post.service.ModifyPostService;
import GFO.Spring.domain.post.service.PostListService;
import GFO.Spring.domain.post.service.RemovePostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final CreatePostService createPostService;
    private final ModifyPostService modifyPostService;
    private final RemovePostService removePostService;
    private final PostListService postListService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody @Valid CreatePostRequest createPostRequest) {
        createPostService.execute(createPostRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> modifyPost(@PathVariable Long id,  @RequestBody @Valid ModifyPostRequest modifyPostRequest) {
        modifyPostService.execute(id, modifyPostRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePost(@PathVariable Long id) {
        removePostService.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<PostListResDto>> findAllPost() {
        List<PostListResDto> results = postListService.execute();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
