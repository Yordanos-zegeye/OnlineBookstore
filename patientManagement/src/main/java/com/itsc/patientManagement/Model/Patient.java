package com.itsc.patientManagement.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Record> records;

    public int getRecordCount() {
        return records != null ? records.size() : 0;
    }
}
