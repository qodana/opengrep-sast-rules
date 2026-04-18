// License: LGPL-3.0 License (c) find-sec-bugs
package file;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import java.util.List;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileUploadFileName {

    public void handleFileCommon(javax.servlet.http.HttpServletRequest req) throws FileUploadException {
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        List<FileItem> fileItems = upload.parseRequest(req);

        for (FileItem item : fileItems) {
            // ruleid: java_file_rule-FileUploadFileName
            System.out.println("Saving " + item.getName() + "...");
        }
    }

}

@javax.servlet.annotation.WebServlet("/upload")
@javax.servlet.annotation.MultipartConfig
class SimpleFileUploadServlet extends javax.servlet.http.HttpServlet {

    protected void doPost(
            javax.servlet.http.HttpServletRequest request,
            javax.servlet.http.HttpServletResponse response)
            throws ServletException, IOException {

        javax.servlet.http.Part filePart = request.getPart("file");
        // ruleid: java_file_rule-FileUploadFileName
        String fileName = filePart.getSubmittedFileName();

        // Define the path where you want to save the file
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists())
            uploadDir.mkdir();

        // Write the file to the server
        try (InputStream fileContent = filePart.getInputStream();
                FileOutputStream fos = new FileOutputStream(uploadPath + File.separator + fileName)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }
}

@jakarta.servlet.annotation.WebServlet("/upload2")
@jakarta.servlet.annotation.MultipartConfig
class FileUploadServlet extends jakarta.servlet.http.HttpServlet {

    protected void doPost(
            jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response) throws IOException, jakarta.servlet.ServletException {
        jakarta.servlet.http.Part filePart = request.getPart("file");

        // ruleid: java_file_rule-FileUploadFileName
        String fileName = filePart.getSubmittedFileName(); // Retrieves the file name

        File file = new File("/path/to/uploads", fileName);
        try (InputStream fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
