package com.abhinav.config.DTO;

import com.abhinav.model.Project;
import com.abhinav.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueDTO {

    private Long id;
    private String title;
    private String description;
    private Long projectId;
    private String priority;
    private LocalDate dueDate;
    private String status;
    private List<String> tags = new ArrayList<>();
    private Project project;


    private User assignee;

}
