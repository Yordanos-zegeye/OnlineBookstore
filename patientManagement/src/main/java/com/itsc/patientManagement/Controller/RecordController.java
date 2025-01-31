package com.itsc.patientManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itsc.patientManagement.Model.Patient;
import com.itsc.patientManagement.Model.Record;
import com.itsc.patientManagement.Services.PatientService;
import com.itsc.patientManagement.Services.RecordServices;

@Controller
@RequestMapping("/records")
public class RecordController {
    @Autowired
    private RecordServices recordServices;
    @Autowired
    private PatientService patientService;

    @GetMapping("/{patientId}")
    public String listRecords(@PathVariable Long patientId, Model model) {
        model.addAttribute("records", recordServices.getRecordsByPatient(patientId));
        model.addAttribute("patient", patientService.getPatientById(patientId));
        return "patient-records";
    }

    @GetMapping("/view/{id}")
    public String viewRecord(@PathVariable Long id, Model model) {
        model.addAttribute("record", recordServices.getRecordById(id));
        return "record-details";
    }

    @PostMapping("/add/{patientId}")
    public String addRecord(@PathVariable Long patientId, @ModelAttribute Record record) {
        Patient patient = new Patient();
        patient.setId(patientId);
        record.setPatient(patient);
        recordServices.saveRecord(record);
        return "redirect:/records/" + patientId;
    }

    @GetMapping("/delete/{id}/{patientId}")
    public String deleteRecord(@PathVariable Long id, @PathVariable Long patientId) {
        recordServices.deleteRecord(id);
        return "redirect:/records/" + patientId;
    }

}
