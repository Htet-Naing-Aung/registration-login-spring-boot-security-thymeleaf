package net.javaguides.springboot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

	@Id
	@SequenceGenerator(
			name = "student_sequence",
			sequenceName = "student_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "student_sequence"
	)
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private String firstName;
	
	@Getter @Setter
	private String lastName;

	@Getter @Setter
	private String email;

	@Getter @Setter
	private String password;
	
	@Getter @Setter
	private String createdDate;

	@Getter @Setter
	private String updatedDate;

	@Getter @Setter
	private String createdBy;

	@Getter @Setter
	@Column(length = 64)
	private String updatedBy;

	@Getter @Setter
	@Lob
	@Column(length = Integer.MAX_VALUE, nullable = true)
	private byte[] image;

	public User(String firstName, String lastName, String email, String password, String createdDate,
			String updatedDate, String createdBy, String updatedBy) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}

}
