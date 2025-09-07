package fr.eni.caveavin.bo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder

@Entity
@Table(name = "CAV_OWNER")
public class Proprio extends Utilisateur {

    @Column(name = "CLIENT_NUMBER", length = 14)
    private String siret;

}
