package _06.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

    private long id;

    public BaseEntity(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long getId(){
        return this.id;
    }

    private void setId(Long id){
        this.id = id;
    }
}
