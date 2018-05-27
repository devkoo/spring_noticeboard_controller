package com.javalec.spring_pjt_board_command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.javalec.spring_pjt_board_dao.BDao;
import com.javalec.spring_pjt_board_dto.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {

		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list(); // 같은 변수명으로 맞춤
		
		model.addAttribute("list", dtos);
	}

}
