package io.cupokki.webmvcboilerplate.controller;

import io.cupokki.webmvcboilerplate.dto.MemberCreateDto;
import io.cupokki.webmvcboilerplate.dto.MemberDto;
import io.cupokki.webmvcboilerplate.dto.MemberLoginDto;
import io.cupokki.webmvcboilerplate.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /** 로그인 뷰 */
    @GetMapping("/login")
    public String login() { return "login"; }

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
        return "join";
    }

    /** 가입 요청 폼 */
    @PostMapping("/join")
    public String loginFrom(@ModelAttribute MemberCreateDto memberCreateDto,
                            HttpSession httpSession,
                            Model model) {
        try {
            log.info(memberCreateDto.getPassword());
            log.info(memberCreateDto.getConfirmPassword());
            MemberDto memberDto = memberService.join(memberCreateDto);
            model.addAttribute("msg", "success"); // useless
            httpSession.setAttribute("member", memberDto);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "join";
        }
    }

}
