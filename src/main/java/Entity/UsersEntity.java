package Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users")
public class UsersEntity {
	private Integer id;
	private String password;
	private String fullName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "full_name", unique = true)
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UsersEntity that = (UsersEntity) o;
		return Objects.equals(id, that.id) && Objects.equals(password, that.password) && Objects.equals(fullName, that.fullName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, password, fullName);
	}

	@Override
	public String toString() {
		return "UsersEntity{" +
				"id=" + id +
				", password='" + password + '\'' +
				", fullName='" + fullName + '\'' +
				'}';
	}
}
