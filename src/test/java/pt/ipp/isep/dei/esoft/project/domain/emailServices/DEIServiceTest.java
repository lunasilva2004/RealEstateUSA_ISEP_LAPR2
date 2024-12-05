package pt.ipp.isep.dei.esoft.project.domain.emailServices;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class DEIServiceTest {

    @Test
    public void testSendEmail() {
        // Arrange
        String expectedEmail = "john.doe@example.com";
        String expectedSubject = "Test Subject";
        String expectedBody = "Test Body";
        DEIService deiService = new DEIService();

        // Act
        deiService.sendEmail(expectedEmail, expectedSubject, expectedBody);

        // Assert
        try (BufferedReader reader = new BufferedReader(new FileReader("DEINotification.txt"))) {
            String line;
            StringBuilder fileContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                fileContent.append(line);
            }
            String content = fileContent.toString();
            Assertions.assertTrue(content.contains("To: " + expectedEmail));
            Assertions.assertTrue(content.contains("Subject: " + expectedSubject));
            Assertions.assertTrue(content.contains("Body: " + expectedBody));
        } catch (IOException e) {
            Assertions.fail("An error occurred while reading the file: " + e.getMessage());
        }
    }
}