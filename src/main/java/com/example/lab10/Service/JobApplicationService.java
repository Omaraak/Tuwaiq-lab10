package com.example.lab10.Service;

import com.example.lab10.Model.JobApplication;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Model.User;
import com.example.lab10.Repository.JobApplicationRepository;
import com.example.lab10.Repository.JobPostRepository;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;

    public List<JobApplication> getJobApplications() {
        return jobApplicationRepository.findAll();
    }

    public void applyForJob(int userId, int jobId) {
        User user = userRepository.getById(userId);
        JobPost jobPost = jobPostRepository.getById(jobId);
        if (user == null || jobPost == null) {
            return;
        }
        JobApplication jobApplication = new JobApplication();
        jobApplication.setUserId(userId);
        jobApplication.setJobPostId(jobPost.getId());
        jobApplicationRepository.save(jobApplication);
    }

    public boolean withdraw(int id) {
        JobApplication jobApplication = jobApplicationRepository.getById(id);
        if (jobApplication == null) {
            return false;
        }
        jobApplicationRepository.deleteById(id);
        return true;
    }
}
