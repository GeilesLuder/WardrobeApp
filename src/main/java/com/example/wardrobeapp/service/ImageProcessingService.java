package com.example.wardrobeapp.service;
// For image snipping and stuff
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageProcessingService {

    public File processImage(MultipartFile file) throws IOException {
        // Save the uploaded file to a temporary location
        File tempFile = File.createTempFile("uploaded-", ".jpg");
        file.transferTo(tempFile);

        // Read the image using OpenCV
        Mat src = Imgcodecs.imread(tempFile.getAbsolutePath());
        if (src.empty()) {
            throw new IOException("Failed to load image from " + tempFile.getAbsolutePath());
        }

        // Convert the image to gray scale
        Mat gray = new Mat();
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);

        // Apply Gaussian blur to the image
        Imgproc.GaussianBlur(gray, gray, new Size(5, 5), 0);

        // Apply edge detection
        Mat edges = new Mat();
        Imgproc.Canny(gray, edges, 75, 200);

        // Find contours
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Draw contours on a new image
        Mat result = Mat.zeros(src.size(), src.type());
        Imgproc.drawContours(result, contours, -1, new Scalar(0, 255, 0));

        // Save the result to a new file
        File resultFile = File.createTempFile("processed-", ".jpg");
        Imgcodecs.imwrite(resultFile.getAbsolutePath(), result);

        // Clean up temporary files
        Files.delete(tempFile.toPath());

        return resultFile;
    }
}