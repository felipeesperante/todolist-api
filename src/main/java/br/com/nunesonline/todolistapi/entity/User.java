package br.com.nunesonline.todolistapi.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "uuid") //unusual field name, but time is money to refactor
    private String id;
    
    @Column(name = "login")
    private String login;
    
    @Column(name = "passwd")
    private String passwd;
    
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    
    @Column(name = "is_logged")
    private boolean isLogged; //was going to use JWT to control login, but changed due time avaliable to work on project
    
    @Column(name = "email")
    private String email;

}
