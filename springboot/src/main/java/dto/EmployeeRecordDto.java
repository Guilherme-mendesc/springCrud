package dto;

import jakarta.validation.constraints.NotBlank;

public record EmployeeRecordDto(@NotBlank String birthDate,@NotBlank String firstName,
		@NotBlank String lastName,@NotBlank String gender,@NotBlank String hireDate) {
	
	

}
