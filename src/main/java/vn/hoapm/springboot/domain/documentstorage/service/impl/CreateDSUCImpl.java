package vn.hoapm.springboot.domain.documentstorage.service.impl;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.hoapm.springboot.application.exception.CommonException;
import vn.hoapm.springboot.domain.documentstorage.presentation.DSRequest;
import vn.hoapm.springboot.domain.documentstorage.repository.IDSRepository;
import vn.hoapm.springboot.domain.documentstorage.service.usecase.ICreateDSUC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CreateDSUCImpl implements ICreateDSUC {
    private final Path fileStorageLocation;
    private final IDSRepository dsRepository;
    private DSRequest request;
    private long result;
    private MultipartFile file;

    public CreateDSUCImpl(IDSRepository dsRepository, DSRequest documentRequest) throws CommonException {
        this.dsRepository = dsRepository;
        this.fileStorageLocation = Paths.get(documentRequest.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new CommonException(
                    "Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    @Override
    public ICreateDSUC applyData(DSRequest dsRequest) {
        this.request = dsRequest;
        return this;
    }

    @Override
    public ICreateDSUC getFile(MultipartFile file) {
        this.file = file;
        return this;
    }

    @Override
    public ICreateDSUC retrieveFileInfo() {
        //TODO covert file info to request
        // Normalize file name
        String originalFileName = StringUtils.cleanPath(this.file.getOriginalFilename());
        String fileName = "";
        try {
            // Check if the file's name contains invalid characters
            if (originalFileName.contains("..")) {
                throw new CommonException("Sorry! Filename contains invalid path sequence" + originalFileName);
            }
            String fileExtension = "";
            try {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            } catch (Exception e) {
                fileExtension = "";
            }
            fileName = String.valueOf(System.nanoTime() + "_" + originalFileName);
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName.replace("HEIC", "JPEG"));
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            //retrieve data to request
            request.setFileName(originalFileName.replace("HEIC", "JPEG"));
            request.setUploadDir(targetLocation.toString().replace("HEIC", "JPEG"));
        } catch (CommonException | IOException e) {
            e.printStackTrace();
        }
        return this;
    }


    @Override
    public ICreateDSUC createDS() {
        this.result = dsRepository.createDS(request);
        return this;
    }

    @Override
    public ICreateDSUC fail() throws CommonException {
        if (result <= 0) {
            throw new CommonException("Create document storage failed !");
        }
        return this;
    }

    @Override
    public long sendResult() {
        return result;
    }
}
