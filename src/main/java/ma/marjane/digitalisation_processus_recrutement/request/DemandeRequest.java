package ma.marjane.digitalisation_processus_recrutement.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DemandeRequest {

    @NotEmpty(message = "first title required")
    @NotBlank(message = "first title required")
    private String title;

    @NotEmpty(message = "first description required")
    @NotBlank(message = "first description required")
    private String description;

    @NotEmpty(message = "first type required")
    @NotBlank(message = "first type required")
    private String type;

//    @Email(message = "Email is not valid")
//    @NotEmpty(message = "first name required")
//    @NotBlank(message = "first name required")
//    private String email;
//
//    @NotEmpty(message = "first name required")
//    @NotBlank(message = "first name required")
//    @Size(min = 8, message = "Password should be at least 8 characters")
//    private String password;
}
