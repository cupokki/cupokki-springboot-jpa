package io.cupokki.jpa.controller;

import io.cupokki.jpa.dto.MemberCreateDto;
import io.cupokki.jpa.dto.MemberDto;
import io.cupokki.jpa.dto.MemberLoginDto;
import io.cupokki.jpa.dto.PostCreateDto;
import io.cupokki.jpa.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /** 로그인 뷰 */
    @GetMapping("/login")
    public String login() { return "member/login"; }

    /** 로그인 요청 폼 */
    @PostMapping("/login")
    //public ResponseEntity<?> loginFrom(@RequestBody MemberLoginDto MemberLoginDto) { // Rest
    public String loginForm(@ModelAttribute MemberLoginDto memberLoginDto,
                            HttpSession httpSession,
                            Model model) {
        try {
            MemberDto memberDto = memberService.login(memberLoginDto);
            httpSession.setAttribute("member", memberDto);
            // TODO : HttpServletRequest의 referer헤더를 이용하여 직전페이지를 기록해보자
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("err", e);
            return "login";
        }
    }

    /** 가입 뷰 */
    @GetMapping("/join")
    public String join(){
        return "login";
    }

    /** 가입 요청 폼 */
    @PostMapping("/join")
    public String loginFrom(@ModelAttribute MemberCreateDto memberCreateDto,
                            HttpSession httpSession,
                            Model model) {
        try {
            MemberDto memberDto = memberService.join(memberCreateDto);
            httpSession.setAttribute("member", memberDto);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("err", e);
            return "join";
        }
    }

}
