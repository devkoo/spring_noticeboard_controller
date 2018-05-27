package com.javalec.spring_pjt_board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javalec.spring_pjt_board_command.BCommand;
import com.javalec.spring_pjt_board_command.BContentCommand;
import com.javalec.spring_pjt_board_command.BDeleteCommand;
import com.javalec.spring_pjt_board_command.BListCommand;
import com.javalec.spring_pjt_board_command.BModifyCommand;
import com.javalec.spring_pjt_board_command.BReplyCommand;
import com.javalec.spring_pjt_board_command.BReplyViewCommand;
import com.javalec.spring_pjt_board_command.BWriteCommand;

@Controller
public class BController {

	BCommand command; // 인터페이스 BCommand 객체를 선언만 하고 모든 메소드가 공유해서 쓸 수 있도록 하자!
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("리스트");
		
		command = new BListCommand(); // 생성은 여기서
		command.execute(model); // 메소드 execute면 하면 됨
		
		return "list"; // 뷰 페이지 찾아가라
	}
	
	@RequestMapping("/write_view") // 글 작성 화면
	public String write_view(Model model) {
		System.out.println("쓰기 보기");
		
		return "write_view";
	}
	
	@RequestMapping("/write") // 실제 DB 접근 등의 작업을 함
	public String write(HttpServletRequest request, Model model) {
		System.out.println("글쓰기");
		
		model.addAttribute("request", request);
		command = new BWriteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	@RequestMapping("content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.err.println("컨텐츠 보기");
		
		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(model);
		
		return "content_view";
	}
	@RequestMapping(method=RequestMethod.POST, value="/modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("수정");
		
		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("리플라이 뷰");
		
		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(model);
		
		return "replyl_view";
	}
	
	@RequestMapping("reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("리플라이");
		
		model.addAttribute("request", request);
		command = new BReplyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("삭제");
		
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
}
