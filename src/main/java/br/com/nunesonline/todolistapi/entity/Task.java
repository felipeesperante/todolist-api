package br.com.nunesonline.todolistapi.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "tasks")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uuid")
    private String id;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    
    @Column(name = "has_comments")
    private boolean hasComments;
    
    @Column(name = "time_spent")
    private Integer timeSpent;
    
    @Column(name = "is_completed")
    private boolean isCompleted;
    
    @Column(name = "schedule_to")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduledTo;
    
    @OneToMany
    @JoinColumn(name = "created_by")
    private User createdBy;

}
