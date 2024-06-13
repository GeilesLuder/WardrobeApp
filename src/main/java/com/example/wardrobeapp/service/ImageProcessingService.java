package com.example.wardrobeapp.service;

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

    static {
        // Load OpenCV native library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

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

        // Apply adaptive thresholding
        Mat thresh = new Mat();
        Imgproc.adaptiveThreshold(gray, thresh, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY_INV, 11, 2);

        // Apply morphological operations to remove small noises and enhance the object
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
        Imgproc.morphologyEx(thresh, thresh, Imgproc.MORPH_CLOSE, kernel);
        Imgproc.morphologyEx(thresh, thresh, Imgproc.MORPH_OPEN, kernel);

        // Find contours
        Mat hierarchy = new Mat();
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(thresh, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Find the largest contour which is most likely the T-shirt
        double largestArea = 0;
        MatOfPoint largestContour = null;
        for (MatOfPoint contour : contours) {
            double area = Imgproc.contourArea(contour);
            if (area > largestArea) {
                largestArea = area;
                largestContour = contour;
            }
        }

        if (largestContour != null) {
            // Draw the largest contour on a mask
            Mat mask = Mat.zeros(src.size(), CvType.CV_8UC1);
            Imgproc.drawContours(mask, List.of(largestContour), -1, new Scalar(255), -1);

            // Apply GrabCut algorithm
            Mat bgdModel = new Mat();
            Mat fgdModel = new Mat();
            Rect rect = Imgproc.boundingRect(largestContour);
            Mat resultMask = new Mat();
            Imgproc.grabCut(src, resultMask, rect, bgdModel, fgdModel, 5, Imgproc.GC_INIT_WITH_RECT);

            // Prepare the mask for extracting the foreground
            Core.compare(resultMask, new Scalar(Imgproc.GC_PR_FGD), resultMask, Core.CMP_EQ);

            Mat foreground = new Mat(src.size(), CvType.CV_8UC3, new Scalar(255, 255, 255));
            src.copyTo(foreground, resultMask);

            // Save the result to a new file
            File resultFile = File.createTempFile("processed-", ".jpg");
            Imgcodecs.imwrite(resultFile.getAbsolutePath(), foreground);

            // Clean up temporary files
            Files.delete(tempFile.toPath());

            return resultFile;
        } else {
            throw new IOException("No contours found in the image");
        }
    }
}
