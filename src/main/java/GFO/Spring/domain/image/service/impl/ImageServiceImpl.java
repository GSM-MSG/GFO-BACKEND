package GFO.Spring.domain.image.service.impl;

import GFO.Spring.domain.image.entity.Image;
import GFO.Spring.domain.image.repository.AttachmentRepository;
import GFO.Spring.domain.image.service.ImageService;
import GFO.Spring.domain.post.entity.Post;
import GFO.Spring.domain.post.exception.PostNotFoundException;
import GFO.Spring.domain.post.repository.PostRepository;
import GFO.Spring.global.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final AttachmentRepository attachmentRepository;
    private final PostRepository postRepository;
    private final ImageUtil imageUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(Long postId, List<MultipartFile> images) throws Exception {
        List<Image> attachments = imageHandler(postId, images);
        attachmentRepository.saveAll(attachments);
    }

    private List<Image> imageHandler(Long postId, List<MultipartFile> images) throws Exception {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("게시물을 찾을 수 없습니다"));
        List<Image> attachments = new ArrayList<>();


        List<MultipartFile> filteredImages = images.stream()
                .filter(i -> Objects.requireNonNull(i.getContentType()).endsWith("image/jpeg") || i.getContentType().endsWith("image/png"))
                .collect(Collectors.toList());

        for (MultipartFile multipartFile : filteredImages) {
            String extensionName = null;
            String contentType = multipartFile.getContentType();

            if (!ObjectUtils.isEmpty(contentType)) {
                if (contentType.endsWith("image/jpeg")) {
                    extensionName = ".jpg";
                } else if (contentType.endsWith("image/png")) {
                    extensionName = ".png";
                }
            }

            Image image = Image.builder()
                    .originFileName(multipartFile.getOriginalFilename())
                    .filePath(imageUtil.designatePath() + File.separator + UUID.randomUUID() + extensionName)
                    .fileSize(multipartFile.getSize())
                    .post(post)
                    .build();

            attachments.add(image);

            imageUtil.saveFile(multipartFile, extensionName);

        }
        return attachments;
    }



}