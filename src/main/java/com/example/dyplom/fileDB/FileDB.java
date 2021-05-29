package com.example.dyplom.fileDB;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class FileDB {

    @Id
            @GeneratedValue
    Long id;
    @Column (nullable = false)
    String name;

    @Lob
    String type;
    byte[] data;

 public FileDB(String name, String type, byte[] data){
     this.name=name;
     this.type = type;
     this.data = data;
 }



}
