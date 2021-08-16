package vn.hoapm.springboot.domain.documentstorage.service.usecase;

import org.springframework.web.multipart.MultipartFile;
import vn.hoapm.springboot.application.exception.CommonException;
import vn.hoapm.springboot.domain.documentstorage.presentation.DSRequest;

public interface ICreateDSUC {
    ICreateDSUC getFile(MultipartFile file);

    ICreateDSUC retrieveFileInfo();

    ICreateDSUC applyData(DSRequest dsRequest);

    ICreateDSUC createDS();

    ICreateDSUC fail() throws CommonException;

    String sendResult();
}
