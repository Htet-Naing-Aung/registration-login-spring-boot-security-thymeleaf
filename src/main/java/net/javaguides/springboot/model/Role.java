//package net.javaguides.springboot.model;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//
////this is a JPA Entity class
//@Entity
//@Table(name = "roles")
//public class Role {
//
//	@Id
//	@SequenceGenerator(
//			name = "student_sequence",
//			sequenceName = "student_sequence",
//			allocationSize = 1
//	)
//	@GeneratedValue(
//			strategy = GenerationType.SEQUENCE,
//			generator = "student_sequence"
//	)
//	@Getter @Setter
//	private Long id;
//
//	@Getter @Setter
//	private String name;
//
//	public Role() {
//
//	}
//
//	public Role(String name) {
//		super();
//		this.name = name;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//}
