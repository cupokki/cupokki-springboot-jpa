package io.cupokki.jpa.controller;

import io.cupokki.jpa.dto.PostCreateDto;
import io.cupokki.jpa.dto.PostDto;
import io.cupokki.jpa.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * 게시물 리스트 뷰
     */
    @GetMapping
    public String list(Model model) {
//    public String list(@RequestParam(required = false) int pages, Model model) {
        //TODO : Pageable 추후에 적용하기
        var dto = new PostCreateDto();
        dto.setTitle("test title");
        dto.setContent("test content");
        dto.setMemberSeq(1L);
        postService.createPost(dto);
        List<PostDto> posts = postService.findAll();
        model.addAttribute("posts", posts);
        log.info("test");
        return "list";
    }

//
//    /**
//     * 게시물 상세보기 뷰
//     */
//    @GetMapping("/{postSeq}")
//    public String detail (@PathVariable("postSeq") Long postSeq,
//                          Model model) {
//        PostDto postDto = postService.findById(postSeq).get();
//        model.addAttribute("post", postDto);
//        return "detail";
//    }
//
//    /**
//     * 게시물 생성 뷰
//     */
//    @GetMapping("/create")
//    public String create(Model model) {
//
//        return "create";
//    }
//
//
//    /**
//     * 게시물 생성 폼
//     */
//    @PostMapping("/create")
//    public String createForm(@ModelAttribute PostCreateDto postCreateDto) {
//        try {
//            postService.save(postCreateDto);
//        } catch (Exception e) {
//            // 저장 실패
//            return "create";
//        }
//        return "redirect:/post";
//
//    }
//
//
//    /**
//     * 게시물 삭제
//     */
//    @DeleteMapping("/{postSeq}/delete")
//    public String delete(@PathVariable Long postSeq, HttpSession httpSession) {
//        // TODO : 이상한 구조
//        Long memberSeq = (Long) httpSession.getAttribute("memberSeq");
//        try {
//            postService.delete(postSeq, memberSeq); // <- PostDeleteDto화?
//        } catch (Exception e) {
//            // 소유자가 아닐 수도
//            // DB 에러
//        }
//
//        return "redirect:/list";
//    }
}
