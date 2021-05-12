package com.example.testdemo.jpaDomain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of="groupCode")
@ToString(exclude = "codeDetails")
@Entity
@NoArgsConstructor
public class CodeGroup {

	@Id
	private String groupCode;

	private String groupName;
	private String userYn = "Y";

	@CreationTimestamp
	private Date regDate;
	@UpdateTimestamp
	private Date updDate;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="groupCode")
	private List<CodeDetail> codeDetails;

	@Builder
	public CodeGroup(String groupCode, String groupName, List<CodeDetail> codeDetails) {
		this.groupCode = groupCode;
		this.groupName = groupName;
		this.codeDetails = codeDetails;
	}
}
