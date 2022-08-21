//package net.javaguides.springboot.model;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "posts")
//public class Post {
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
//	@OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "userId", referencedColumnName = "id")
//	@Getter @Setter
//    private User user;
//
//	@Getter @Setter
//	private String status;
//
//	@Getter @Setter
//	private String createdDate;
//
//	@Getter @Setter
//	private String updatedDate;
//
//	@Lob
//	@Column(length = Integer.MAX_VALUE, nullable = true)
//	@Getter @Setter
//	private byte[] image;
//
//	public Post() {
//
//	}
//
//	public Post(User user, String status, String createdDate, String updatedDate, byte[] image) {
//		super();
//		this.user = user;
//		this.status = status;
//		this.createdDate = createdDate;
//		this.updatedDate = updatedDate;
//		this.image = image;
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
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public String getCreatedDate() {
//		return createdDate;
//	}
//
//	public void setCreatedDate(String createdDate) {
//		this.createdDate = createdDate;
//	}
//
//	public String getUpdatedDate() {
//		return updatedDate;
//	}
//
//	public void setUpdatedDate(String updatedDate) {
//		this.updatedDate = updatedDate;
//	}
//
//	public byte[] getImage() {
//		return image;
//	}
//
//	public void setImage(byte[] image) {
//		this.image = image;
//	}
//
//
//
//}
