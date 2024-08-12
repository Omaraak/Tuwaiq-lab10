package com.example.lab10.Service;

import com.example.lab10.Model.JobPost;
import com.example.lab10.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;

    public List<JobPost> getJobPosts() {
        return jobPostRepository.findAll();
    }

    public void addJobPost(JobPost jobPost) {
        jobPostRepository.save(jobPost);
    }

    public boolean updateJobPost(int id, JobPost jobPost) {
        JobPost oldJobPost = jobPostRepository.getById(id);
        if (oldJobPost != null) {
            oldJobPost.setTitle(jobPost.getTitle());
            oldJobPost.setDescription(jobPost.getDescription());
            oldJobPost.setLocation(jobPost.getLocation());
            oldJobPost.setSalary(jobPost.getSalary());
            jobPostRepository.save(oldJobPost);
            return true;
        }
        return false;
    }

    public boolean deleteJobPost(int id) {
        JobPost oldJobPost = jobPostRepository.getById(id);
        if (oldJobPost != null) {
            jobPostRepository.delete(oldJobPost);
            return true;
        }
        return false;
    }
}
