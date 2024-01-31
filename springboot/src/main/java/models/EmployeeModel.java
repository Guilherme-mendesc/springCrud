package models;



import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name= "TB_EMPLOYEE")
public class EmployeeModel extends RepresentationModel<EmployeeModel> implements Serializable {
	
	    private static final long serialVersionUID =1L;

	    @Id
	    @GeneratedValue(strategy= GenerationType.AUTO)
	    private UUID idEmployee;
	    private String birthDate;
	    private String firstName;
	    private String lastName;
	    private String gender;
	    private String hireDate;


	    public void EmplopyeeModel( UUID idEmployee, String birthDate, String firstName, String lastName, String gender, String hireDate) {
	    	this.idEmployee= idEmployee;
	        this.birthDate = birthDate;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.gender = gender;
	        this.hireDate = hireDate;
	    } 
	    
	    public UUID getIdEmployee() {
			return idEmployee;
		}


		public void setIdEmployee(UUID idEmployee) {
			this.idEmployee = idEmployee;
		}



		public String getBirthDate() {
	        return birthDate;
	    }

	    public void setBirthDate(String birthDate) {
	        this.birthDate = birthDate;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    public String getGender() {
	        return gender;
	    }

	    public void setGender(String gender) {
	        this.gender = gender;
	    }

	    public String getHireDate() {
	        return hireDate;
	    }

	    public void setHireDate(String hireDate) {
	        this.hireDate = hireDate;
	    }

}
