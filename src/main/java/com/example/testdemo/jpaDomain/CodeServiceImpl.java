package com.example.testdemo.jpaDomain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CodeService")
public class CodeServiceImpl implements CodeService{

	@Autowired
	protected CodeDetailRepository codeDetailRepository;


	public List<CodeDetail> selectCodeGroupList() throws Exception {
		return codeDetailRepository.findAll();
	}

	public Page<CodeDetail> selectListPaging(PageRequestVO pageRequestVO) {
		int pageNumber = pageRequestVO.getPage() -1;
		int sizePerPage = pageRequestVO.getSizePerPage();

		Pageable pageRequest = PageRequest.of(pageNumber, sizePerPage, Sort.Direction.DESC, "codeDetailNo");

		return  codeDetailRepository.findAll(pageRequest);
	}
}
