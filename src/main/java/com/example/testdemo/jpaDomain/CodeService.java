package com.example.testdemo.jpaDomain;

import org.springframework.data.domain.Page;

import java.util.List;

public interface CodeService {

	List<CodeDetail> selectCodeGroupList() throws Exception;

	Page<CodeDetail> selectListPaging(PageRequestVO pageRequestVO) throws  Exception;
}
