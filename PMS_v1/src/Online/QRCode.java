package Online;

import DatabaseConnection.Conection;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import DatabaseConnection.*;

import java.awt.Desktop;
public class QRCode {

    private String code;

    public void setCode(String code){ this.code = code; }
    public String getCode(){ return this.code; }

    public QRCode(String ParkingServiceID) {

        String fictitiousData = generateRandomString(10); // Generar una cadena aleatoria, puedes ajustar la longitud

        try {
            generateQRCode(fictitiousData, "FictitiousQRCode.png");
            abrirDocumento(fictitiousData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.code = fictitiousData;
        SaveTicketBooking(fictitiousData, ParkingServiceID);
       // printQRCodeToConsole(fictitiousData);

    }



    public static void SaveTicketBooking(String Code, String ParkingServiceID) {
        try (Connection connection = Conection.Openconextion();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO BookingTickek (TicketCode, DateCreate, ParkingServiceID) VALUES (?, ?, ?)")) {

            statement.setString(1, Code);

            // Obtener la fecha actual en el formato correcto
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String formattedDate = dateFormat.format(new Timestamp(System.currentTimeMillis()));

            statement.setString(2, formattedDate);
            statement.setString(3, ParkingServiceID);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones (puedes cambiar esto según tus necesidades)
        } finally {
            Conection.closeConnection();
        }
    }

    public static boolean isCodeExists(String code) {
        boolean codeExists = false;

        try (Connection connection = Conection.Openconextion();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT COUNT(*) AS count FROM BookingTickek WHERE TicketCode = ?")) {

            statement.setString(1, code);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    codeExists = count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones (puedes cambiar esto según tus necesidades)
        } finally {
            Conection.closeConnection();
        }

        return codeExists;
    }




    public static void createUsers(String username, String password, String name, String lastname, String email, String telephone, String address, String TipeWorker) throws SQLException {

    }


    private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomString = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }

    public static void abrirDocumento(String filePath) {
        try {
            File file = new File(filePath);

            if (file.exists()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            } else {
                System.out.println("El archivo no existe: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void generateQRCode(String data, String filePath) throws IOException {
        int width = 300;
        int height = 300;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        // Fondo blanco
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        // Texto negro
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial", Font.PLAIN, 12));

        // Dibujar el texto (código QR ficticio) en el centro
        int textX = (width - graphics.getFontMetrics().stringWidth(data)) / 2;
        int textY = height / 2;
        graphics.drawString(data, textX, textY);

        // Guardar la imagen como archivo PNG
        File qrCodeFile = new File(filePath);
        ImageIO.write(image, "png", qrCodeFile);

        System.out.println("Código QR ficticio generado con éxito en: " + qrCodeFile.getAbsolutePath());
    }

}
