package io.cupokki.webmvcboilerplate.controller;

import io.cupokki.webmvcboilerplate.dto.PostCreateDto;
import io.cupokki.webmvcboilerplate.dto.PostDto;
import io.cupokki.webmvcboilerplate.service.PostService;
import jakarta.servlet.http.HttpSession;
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
        var dto = PostCreateDto.builder()
                .title("테스트 게시물 제목")
                .content("이런내용의 본문이 어쩌구 저쩌구 어쩌구 저쩌구 어쩌구 저쩌구 어쩌구 저쩌구 어쩌구 저쩌구 어쩌구 저쩌구")
                .member(null)
                .build();

        postService.createPost(dto);
        List<PostDto> posts = postService.getAll();
        model.addAttribute("posts", posts);
        log.info("test");
        return "post/list";
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
    /**
     * 게시물 생성 뷰
     */
    @GetMapping("/create")
    public String create(Model model, HttpSession httpSession) {
//        httpSession.getAttribute("memberSeq");
//        model.addAttribute("memberSeq", )
        return "post/create";
    }
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
