package com.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final String filename = "company.json";

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(2, "Bob Smith", 45, "Project Manager", 120000,
                new Contact("bob.smith@techcorp.com", "555-5678")));
        employees.add(new Employee(3, "Charlie Brown", 28, "UI/UX Designer", 85000,
                new Contact("charlie.brown@techcorp.com", "555-8765")));

        Company company = new Company("TechCorp", "New York", employees);

        try {
            writeJsonFile(company, filename);
            Company readCompany = readJsonFile(filename);

            System.out.println("Company Name: " + readCompany.getName());
            System.out.println("Location: " + readCompany.getLocation());
            System.out.println("\nEmployee Data:");
            System.out.println("----------------------");
            for (int i = 0; i < readCompany.getEmployees().size(); i++) {
                System.out.println("Emplyee #" + (i + 1) + ":");
                System.out.println("empID: " + readCompany.getEmployees().get(i).getId());
                System.out.println("Name: " + readCompany.getEmployees().get(i).getName());
                System.out.println("Age: " + readCompany.getEmployees().get(i).getAge());
                System.out.println("Position: " + readCompany.getEmployees().get(i).getPosition());
                System.out.println("Salary: " + readCompany.getEmployees().get(i).getSalary());
                System.out.println("Email: " + readCompany.getEmployees().get(i).getContact().getEmail());
                System.out.println("Phone: " + readCompany.getEmployees().get(i).getContact().getPhone());
                System.out.println("----------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeJsonFile(Company company, String filename) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(filename);
        writer.write(gson.toJson(company));
        writer.close();
        System.out.println("JSON file created successfully.");
    }

    public static Company readJsonFile(String filename) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        Company company = gson.fromJson(bufferedReader, Company.class);
        return company;
    }
}
