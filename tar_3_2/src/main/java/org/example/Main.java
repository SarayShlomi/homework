package org.example;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println(new JobPosition.Builder(LocalDate.now(), "title1", true));
        System.out.println(new JobPosition.Builder(LocalDate.now().plusMonths(14), "title2", true).yearsOfExperienceRequired(3).salaryCap(15.0));
    }

}