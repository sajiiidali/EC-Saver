package com.myECapplication.sajiiidali.ecsaver.kotlin.GoogleDriveBackup.util;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface InputSource {
    InputStream open() throws FileNotFoundException;

    long length();
}
