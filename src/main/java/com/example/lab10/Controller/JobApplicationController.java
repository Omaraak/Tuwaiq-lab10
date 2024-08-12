package com.example.lab10.Controller;

import com.example.lab10.ApiResponse;
import com.example.lab10.Model.JobApplication;
import com.example.lab10.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jss/jobApplication")
@RequiredArgsConstructor
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity getJobApplications() {
        return ResponseEntity.status(200).body(jobApplicationService.getJobApplications());
    }

    @PostMapping("/addJobApplication/{userId}/{jobPostId}")
    public ResponseEntity addJobApplication(@PathVariable Integer userId, @PathVariable Integer jobPostId, @Valid @RequestBody JobApplication user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        jobApplicationService.applyForJob(userId, jobPostId);
        return ResponseEntity.status(200).body(new ApiResponse("user added successfully"));
    }


    @DeleteMapping("/withdraw/{id}")
    public ResponseEntity withdraw(@RequestParam int id) {
        if (jobApplicationService.withdraw(id))
            return ResponseEntity.status(200).body(new ApiResponse("user deleted successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("user delete failed"));
    }
}
