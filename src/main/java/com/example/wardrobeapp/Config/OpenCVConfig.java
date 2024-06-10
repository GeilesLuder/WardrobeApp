package com.example.wardrobeapp.Config;

import org.opencv.core.Core;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenCVConfig {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
}