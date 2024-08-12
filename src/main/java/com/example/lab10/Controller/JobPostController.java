package com.example.lab10.Controller;

import com.example.lab10.ApiResponse;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jss/jobPost")
@RequiredArgsConstructor
public class JobPostController {
    private final JobPostService jobPostService;

    @GetMapping("/get")
    public ResponseEntity getJobPosts() {
        return ResponseEntity.status(200).body(jobPostService.getJobPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addJobPost(@Valid @RequestBody JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(200).body(new ApiResponse("jobPost added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateJobPost(@PathVariable int id, @Valid @RequestBody JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if (jobPostService.updateJobPost(id, jobPost))
            return ResponseEntity.status(200).body(new ApiResponse("jobPost updated successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("jobPost update failed"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobPost(@RequestParam int id) {
        if (jobPostService.deleteJobPost(id))
            return ResponseEntity.status(200).body(new ApiResponse("jobPost deleted successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("jobPost delete failed"));
    }
}
