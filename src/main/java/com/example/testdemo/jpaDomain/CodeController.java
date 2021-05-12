package com.example.testdemo.jpaDomain;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class CodeController {

	@Resource(name="CodeService")
	private CodeService codeService;

	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String codeDetailList(Model model, PageRequestVO pageRequestVO) throws Exception {
		model.addAttribute("selectCodeGroupList", codeService.selectCodeGroupList());
		return "home";
	}

	@RequestMapping(value="/codeList", method = RequestMethod.GET)
	public String pagingList(Model model, PageRequestVO pageRequestVO) throws Exception {

		Page<CodeDetail> page = codeService.selectListPaging(pageRequestVO);
		model.addAttribute("selectCodeGroupList", page);
		return "home";
	}
}
