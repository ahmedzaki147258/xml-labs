package com.example;

import javax.json.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        final String filename = "company.json";
        try {
            writeJsonFile(filename);
            readJsonFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeJsonFile(String filename) throws IOException {
        JsonObject model = Json.createObjectBuilder()
                .add("name", "TechCorp")
                .add("location", "New York")
                .add("employees", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("id", 2)
                                .add("name", "Bob Smith")
                                .add("age", 45)
                                .add("position", "Project Manager")
                                .add("salary", 120000)
                                .add("contact", Json.createObjectBuilder()
                                        .add("email", "bob.smith@techcorp.com")
                                        .add("phone", "555-5678")))
                        .add(Json.createObjectBuilder()
                                .add("id", 3)
                                .add("name", "Charlie Brown")
                                .add("age", 28)
                                .add("position", "UI/UX Designer")
                                .add("salary", 85000)
                                .add("contact", Json.createObjectBuilder()
                                        .add("email", "charlie.brown@techcorp.com")
                                        .add("phone", "555-8765"))))
                .build();

        try (JsonWriter writer = Json.createWriter(new FileWriter(filename))) {
            writer.writeObject(model);
            System.out.println("JSON file created successfully.");
        }
    }

    public static void readJsonFile(String filename) throws FileNotFoundException {
        JsonReader jsonReader = Json.createReader(new FileReader(filename));
        JsonObject companyJsonObject = (JsonObject) jsonReader.read();
        System.out.println("Company Name: " + companyJsonObject.getString("name"));
        System.out.println("Location: " + companyJsonObject.getString("location"));

        JsonArray employees = companyJsonObject.getJsonArray("employees");
        System.out.println("\nEmployee Data:");
        System.out.println("----------------------");
        for (int i = 0; i < employees.size(); i++) {
            System.out.println("Emplyee #"+(i+1)+":");
            System.out.println("empID: " + employees.getJsonObject(i).getInt("id"));
            System.out.println("Name: " + employees.getJsonObject(i).getString("name"));
            System.out.println("Age: " + employees.getJsonObject(i).getInt("age"));
            System.out.println("Position: " + employees.getJsonObject(i).getString("position"));
            System.out.println("Salary: " + employees.getJsonObject(i).getInt("salary"));
            System.out.println("Email: " + employees.getJsonObject(i).getJsonObject("contact").getString("email"));
            System.out.println("Phone: " + employees.getJsonObject(i).getJsonObject("contact").getString("phone"));
            System.out.println("----------------------");
        }
    }
}
