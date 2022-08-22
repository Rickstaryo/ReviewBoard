package com.example.file.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.file.Model.CreateMemberForm;
import com.example.file.Model.Member;
import com.example.file.Model.loginForm;
import com.example.file.Service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping(value="/member")
@Slf4j
@Controller
public class MemberController {
	private final MemberService memberService;
	
	// From Join Form 
	@GetMapping(path = "/join")
	public String join(Model model) {
		model.addAttribute("createMemberForm",new CreateMemberForm());
		return "member/joinForm";
	}
	
	@PostMapping("/join")
	public String joinForm(@ModelAttribute("createMemberForm") CreateMemberForm createMember) {
		 log.info("createMemberForm : {}", createMember);

		  memberService.saveMember(createMember.toMember());
		
		return "redirect:/";
	}
	
	// Login UP Form
	@GetMapping(value= "/login")
	public String login(Model model) {
		model.addAttribute("loginForm", new loginForm());
		return "member/loginForm";
	}
	
	@PostMapping(value="/login")
	public String login(@Validated @ModelAttribute("loginForm") loginForm loginForm,
			BindingResult bindingResult, HttpServletRequest request) {
	      log.info("loginForm : {}", loginForm);
		//에러가 있다면 다시 웹사이트로 보내줌
		if(bindingResult.hasErrors()) {
			return "member/loginForm";
		}
		
		// 로그인안에서 ID 체크처리 해줌  
		Member findMember = memberService.findMember(loginForm.toMember());
		if(findMember == null) {
			bindingResult.reject("loginError", "아이디 또는 패스워드가 맞지 않습니다.");
            return "member/loginForm";
        }
		// 세션안에 유지 
		 HttpSession session = request.getSession();
	        session.setAttribute("loginMember", findMember);
	        return "redirect:/board/list";
	}
	// logOut Form 
	@GetMapping(value= "/logout")
	public String logOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
	
}
