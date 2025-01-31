package com.itsc.patientManagement.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsc.patientManagement.Model.*;
import com.itsc.patientManagement.Model.Record;

@Service
public class RecordServices {
    @Autowired
    private RecordRepository recordRepository;

    public List<Record> getRecordsByPatient(Long patientId) {
        return recordRepository.findByPatientId(patientId);
    }

    public Record getRecordById(Long id) {
        return recordRepository.findById(id).orElse(null);
    }

    public void saveRecord(Record record) {
        recordRepository.save(record);
    }

    public void deleteRecord(Long id) {
        recordRepository.deleteById(id);
    }
}
