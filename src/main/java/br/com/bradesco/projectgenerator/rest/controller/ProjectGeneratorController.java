package br.com.bradesco.projectgenerator.rest.controller;

import br.com.bradesco.projectgenerator.service.ProjectGeneratorService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@RestController
public class ProjectGeneratorController {

    private final ProjectGeneratorService projectGeneratorService;

    public ProjectGeneratorController(ProjectGeneratorService projectGeneratorService) {
        this.projectGeneratorService = projectGeneratorService;
    }

    @PostMapping("/api/project-generator")
    public ResponseEntity<Resource> generateProject(@RequestBody ProjectConfig config) {
        try {
            Resource resource = projectGeneratorService.generateProject(config);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate project", e);
        }
    }

//    @PostMapping("/api/project-generator")
//    public ResponseEntity<Resource> generateProject(@RequestBody ProjectConfig config) {
//        try {
//            Path templatePath = Paths.get("C:\\Projetos\\Java Template");
//            Path newProjectPath = Paths.get("C:\\Projetos\\" + config.getArtifactId());
//
//            // Copy template to new project directory
//            Files.walk(templatePath).forEach(source -> copy(source, newProjectPath.resolve(templatePath.relativize(source)), config, templatePath));
//
//            // Delete any empty directories in the new project
//            deleteEmptyDirectories(newProjectPath);
//
//            // Replace placeholders in new project
//            replacePlaceholders(newProjectPath, config);
//
//            // Zip the new project directory
//            Path zipPath = Paths.get("C:\\Projetos\\" + config.getArtifactId() + "\\project.zip");
//            zipDirectory(newProjectPath, zipPath);
//
//            // Return the zip file as response
//            Resource resource = new FileSystemResource(zipPath.toFile());
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                    .body(resource);
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to generate project", e);
//        }
//    }
//
//    private void copy(Path source, Path dest, ProjectConfig config, Path root) {
//        try {
//            // Get the relative path of the source
//            Path relativePath = root.relativize(source);
//
//            // Check if the relative path matches any of the exclude patterns
//            for (String pattern : config.getExcludes()) {
//                PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
//                if (matcher.matches(relativePath)) {
//                    return; // Skip this file/directory
//                }
//            }
//
//            // Replace the root package name in the destination path
//            String destPath = dest.toString().replace("javatemplate", config.getArtifactId());
//            dest = Paths.get(destPath);
//
//            // Copy the file/directory
//            Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
//
//            // If the source is a directory, check if it's empty after copying the files
//            if (Files.isDirectory(source)) {
//                try (Stream<Path> stream = Files.list(source)) {
//                    if (!stream.findAny().isPresent()) { // If the directory is empty
//                        Files.deleteIfExists(source); // Delete the directory
//                    }
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Error copying files", e);
//        }
//    }
//
//    private void replacePlaceholders(Path projectPath, ProjectConfig config) throws IOException {
//        try (Stream<Path> paths = Files.walk(projectPath)) {
//            paths
//                    .filter(Files::isRegularFile)
//                    .forEach(file -> replacePlaceholdersInFile(file, config));
//        }
//    }
//
//    private void deleteEmptyDirectories(Path path) throws IOException {
//        Files.walk(path)
//                .filter(Files::isDirectory)
//                .sorted(Comparator.reverseOrder())
//                .forEach(dir -> {
//                    try (Stream<Path> stream = Files.list(dir)) {
//                        if (!stream.findAny().isPresent()) { // If the directory is empty
//                            Files.deleteIfExists(dir); // Delete the directory
//                        }
//                    } catch (IOException e) {
//                        throw new UncheckedIOException(e);
//                    }
//                });
//    }
//
//    private void replacePlaceholdersInFile(Path file, ProjectConfig config) {
//        try {
//            String content = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
//
//            content = content.replace("${projectName}", config.getProjectName());
//            content = content.replace("${groupId}", config.getGroupId());
//            content = content.replace("${artifactId}", config.getArtifactId());
//            content = content.replace("${version}", config.getVersion());
//            content = content.replace("${costCenter}", config.getCostCenter());
//            content = content.replace("${valueStream}", config.getValueStream());
//            content = content.replace("${namespace}", config.getNamespace());
//            content = content.replace("${clustersName}", config.getClustersName());
//
//            // Replace the root package name in the file content
//            content = content.replace("br.com.bradesco.javatemplate", config.getGroupId() + "." + config.getArtifactId());
//
//
//            Files.write(file, content.getBytes(StandardCharsets.UTF_8));
//        } catch (IOException e) {
//            throw new UncheckedIOException(e);
//        }
//    }
//
//    private void zipDirectory(Path source, Path zip) throws IOException {
//        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(zip))) {
//            Files.walk(source)
//                    .filter(path -> !Files.isDirectory(path))
//                    .forEach(path -> {
//                        ZipEntry zipEntry = new ZipEntry(source.relativize(path).toString());
//                        try {
//                            zos.putNextEntry(zipEntry);
//                            Files.copy(path, zos);
//                            zos.closeEntry();
//                        } catch (IOException e) {
//                            System.err.println(e);
//                        }
//                    });
//        }
//    }
}