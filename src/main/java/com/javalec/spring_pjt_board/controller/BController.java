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

	BCommand command; // �������̽� BCommand ��ü�� ���� �ϰ� ��� �޼ҵ尡 �����ؼ� �� �� �ֵ��� ����!
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("����Ʈ");
		
		command = new BListCommand(); // ������ ���⼭
		command.execute(model); // �޼ҵ� execute�� �ϸ� ��
		
		return "list"; // �� ������ ã�ư���
	}
	
	@RequestMapping("/write_view") // �� �ۼ� ȭ��
	public String write_view(Model model) {
		System.out.println("���� ����");
		
		return "write_view";
	}
	
	@RequestMapping("/write") // ���� DB ���� ���� �۾��� ��
	public String write(HttpServletRequest request, Model model) {
		System.out.println("�۾���");
		
		model.addAttribute("request", request);
		command = new BWriteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	@RequestMapping("content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.err.println("������ ����");
		
		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(model);
		
		return "content_view";
	}
	@RequestMapping(method=RequestMethod.POST, value="/modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("����");
		
		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("���ö��� ��");
		
		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(model);
		
		return "replyl_view";
	}
	
	@RequestMapping("reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("���ö���");
		
		model.addAttribute("request", request);
		command = new BReplyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("����");
		
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
}