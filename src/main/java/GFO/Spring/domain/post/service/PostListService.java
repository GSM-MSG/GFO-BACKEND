package GFO.Spring.domain.post.service;

import GFO.Spring.domain.post.presentation.dto.response.PostListResDto;

import java.util.List;

public interface PostListService {
    List<PostListResDto> execute();
}
