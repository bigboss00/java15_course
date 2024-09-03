package model;

import java.time.LocalDate;

public class Course {
    private Long id;
    private String name;
    private String description;
    private LocalDate duration;
    private StudyFormat studyFormat;

    private static long idCounter = 0;
    public Course() {}

    public Course(String name, String description, LocalDate duration, StudyFormat studyFormat) {
        this.id = ++idCounter;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.studyFormat = studyFormat;
    }

    public enum StudyFormat {
        ONLINE,
        OFFLINE
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDuration() {
        return duration;
    }

    public void setDuration(LocalDate duration) {
        this.duration = duration;
    }

    public StudyFormat getStudyFormat() {
        return studyFormat;
    }

    public void setStudyFormat(StudyFormat studyFormat) {
        this.studyFormat = studyFormat;
    }
}

