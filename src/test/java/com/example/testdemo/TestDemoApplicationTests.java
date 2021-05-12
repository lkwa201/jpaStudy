package com.example.testdemo;


import com.example.testdemo.domain.Board;
import com.example.testdemo.domain.BoardRepository;
import com.example.testdemo.jpaDomain.CodeDetail;
import com.example.testdemo.jpaDomain.CodeDetailRepository;
import com.example.testdemo.jpaDomain.CodeGroup;
import com.example.testdemo.jpaDomain.CodeGroupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class TestDemoApplicationTests {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private CodeGroupRepository codeGroupRepository;

	@Autowired
	private CodeDetailRepository codeDetailRepository;

	@Autowired  private MockMvc mockMvc;

//	@Test
//	void contextLoads() {
//	}

	@Test
	public void getCount() throws Exception {
		long count = boardRepository.count();
		System.out.println("조회된 갯수는: "+count);
	}

	@Test
	public void insertTest() {
		for(int i =0; i < 10; i++) {
			Board board = Board.builder().content("내용 "+i).title("제목 "+i).writer("홍길동"+i).build();
			boardRepository.save(board);
		}
	}

	@Test
	public void deleteTest() {
		//Board board = Board.BoardBuilder().id(3L).build();
		Board param = Board.builder().id(3L).build();
		boardRepository.delete(param);
	}

	@Test
	public void updateTest() {
		//Board board = Board.BoardBuilder().content("내용을바꾸자2222222").id(4L).build();
		String content = "내용을바꾸자";
		boardRepository.updateContent(1L, content);
	}

	@Test
	public void insertCodeGroup() {
		CodeGroup CodeGrp = CodeGroup.builder().groupCode("A01").groupName("JOB").build();
		codeGroupRepository.save(CodeGrp);
	}

	@Test
	public void insertCodeDetail() {
		CodeDetail detail = CodeDetail.builder().codeName("designer").codeValue("01").sortSeq(2).groupCode("A01").build();
		codeDetailRepository.save(detail);
	}

	@Test
	public void selectCodeList() {
		Iterable<CodeGroup> codeGroupList = codeGroupRepository.findAll();
		for(CodeGroup codeGroup: codeGroupList) {

			System.out.println(codeGroup);

			Iterable<CodeDetail> CodeDetailList = codeGroup.getCodeDetails();
			for (CodeDetail codeDetail : CodeDetailList) {
				System.out.println(codeDetail);
			}
		}
	}

	@Test
	public void selectDetailList() {
		List<CodeDetail> codeDetails = codeDetailRepository.findByGroupCode("A01");

		for (CodeDetail codeDetail : codeDetails) {
			System.out.println(codeDetail);
		}
	}

	@Test
	public void updateCodeDetail() {
		CodeDetail codeDetail = CodeDetail.builder().codeDetailNo(1L).codeName("developersss").build();
		codeDetailRepository.update(codeDetail);
	}

	// 대량 인설트
	@Test
	public void insertCodeDetails() {

		String jobs = "카피라이터/편집디자이너/만화콘티작가/관광여행사/컴퓨터그래픽디자이너/용기디자이너/건축가/감정평가사/관세사" +
				"/증권투자상담사/주택관리사/산업안전관리자/보험세일즈맨/자동차세일즈맨/구매대리인/선물거래중개업/통역사/동시통역사" +
				"/관광통역안내원/국제관광홍보업/포토그래퍼/시스템엔지니어/시스템컨설턴트/오퍼레이터/자동차고객관리대행업/애니매이터" +
				"/이벤트기획전문가/택스타일디자이너/외환딜러/스카우트대행업/마켓트리서치컨설턴트/부동산자료전문제공업/기상정보회사/" +
				"레크레이션지도자/방송스크립터/방송작가/데이터통신기능사/환지사/판매사/귀금속세공사/인테리어디자이너/그래픽디자이너/" +
				"패션코디네이터/특수교사/프로듀서/패션디자이너/디스플레이어/신문잡지취재기자/신문잡지편집기자/보석감정사/속기사/" +
				"열관리사/음향영상기기기능사/인쇄통신기능사/자동차정비기사/조경사/환경기사/환경영향평가사/피아노조율사/항공정비사/" +
				"방화관리자/카풀중개센타/결혼준비대행업/컴퓨터강사/보육사/간병인/전화교환원/기상관측요원/경영지도사/공인회계사/" +
				"공인중계사/세무사/손해사정인/비서/사서/사보기자/상담원/가구디자이너/제품디자이너/일러스트레이터/패키지다자이너/" +
				"광고디자이너/얼음조각가/아트플라워디자이너/스튜어디스/에어로빅강사/피부관리사/요리사/호텔/향조연구가/홍보대행업/" +
				"비디오촬영기사/치과기공사/증권분석가/보험계리인/변리사/사회복지사/카드맨/컴퓨터결혼중매소/공인노무사";

		String[] temp = jobs.split("/");

		for (int i = 0; i < temp.length; i++) {
			String codeValue = String.valueOf(i+1);
			CodeDetail detail = CodeDetail.builder().codeName(temp[i]).codeValue(codeValue).sortSeq(i).groupCode("A01").build();
			codeDetailRepository.save(detail);
		}

	}

	//페이징 테스트 코드
	@Test
	public void pagingTest() {
		Pageable pageable = PageRequest.of(2,10, Sort.Direction.ASC, "codeDetailNo");
		Page<CodeDetail> page = codeDetailRepository.findAll(pageable);


		for (CodeDetail codeDetail : page) {
			System.out.println(codeDetail);
		}
	}




}
