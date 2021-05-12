package com.example.testdemo.jpaDomain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(of="codeDetailNo")
@ToString
@Entity
@NoArgsConstructor
public class CodeDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codeDetailNo;

	private String codeValue;
	private String codeName;
	private int sortSeq;
	private String groupCode;

	private String useYn = "Y";

	@CreationTimestamp
	private Date regDate;
	@UpdateTimestamp
	private Date updDate;

	@Builder
	public CodeDetail(Long codeDetailNo, String codeValue, String codeName, int sortSeq, String groupCode) {
		this.codeDetailNo = codeDetailNo;
		this.codeValue = codeValue;
		this.codeName = codeName;
		this.sortSeq = sortSeq;
		this.groupCode = groupCode;
	}
}

