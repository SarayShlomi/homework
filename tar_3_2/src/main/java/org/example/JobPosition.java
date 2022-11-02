package org.example;

import java.time.LocalDate;

public class JobPosition {
    private final int publishDate;
    private final String title;
    private final String isRemot;
    private String description;
    private String location;
    private double salaryCap;
    private double yearsOfExperienceRequired;

    public static class Builder {

        @Override
        public String toString() {
            return "Builder{" +
                    "publishDate=" + publishDate +
                    ", title='" + title + '\'' +
                    ", isRemote=" + isRemote +
                    ", description='" + description + '\'' +
                    ", location='" + location + '\'' +
                    ", salaryCap=" + salaryCap +
                    ", yearsOfExperienceRequired=" + yearsOfExperienceRequired +
                    '}';
        }

        private final LocalDate publishDate;
        private final String title;
        private final boolean isRemote;

        private String description;
        private String location = "Tel Aviv";
        private double salaryCap;
        private int yearsOfExperienceRequired;

        public Builder(LocalDate publishDate, String title, boolean isRemote) {
            this.publishDate = publishDate;
            this.title = title;
            this.isRemote = isRemote;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder salaryCap(double salaryCap) {
            this.salaryCap = salaryCap;
            return this;
        }

        public Builder yearsOfExperienceRequired(int yearsOfExperienceRequired) {
            this.yearsOfExperienceRequired = yearsOfExperienceRequired;
            return this;
        }
//        public JobPosition build() {
//            return new JobPosition(this);
//        }
    }



    public JobPosition(int publishDate, String title, String isRemot, String description, String location, double salaryCap, double yearsOfExperienceRequired) {
        this.publishDate = publishDate;
        this.title = title;
        this.isRemot = isRemot;
        this.description = description;
        this.location = location;
        this.salaryCap = salaryCap;
        this.yearsOfExperienceRequired = yearsOfExperienceRequired;
    }




}