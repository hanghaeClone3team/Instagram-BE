package instagram.instagrambe.post.image.controller;

import instagram.instagrambe.post.image.common.S3Uploader;
import instagram.instagrambe.post.image.entity.ImageEntity;
import instagram.instagrambe.post.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final S3Uploader s3Uploader;
    private Object fileSize;

    @ResponseBody
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Long saveDiary(HttpServletRequest request, @RequestParam(value = "image") MultipartFile image, ImageEntity imageEntity) throws IOException {
        Long imageId = imageService.saveImage(image, imageEntity);
        return imageId;
    }

    public int solution(String s) {
        int answer = 0;
        answer = Integer.parseInt(s);
        return answer;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        s3Uploader.upload(multipartFile);
        System.out.println("ImageController.uploadFile");

        return "성공";

    }



}
