package com.example.file.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.file.Model.CreateMemberForm;
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
}
