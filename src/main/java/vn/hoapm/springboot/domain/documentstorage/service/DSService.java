package vn.hoapm.springboot.domain.documentstorage.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import vn.hoapm.springboot.application.exception.CommonException;
import vn.hoapm.springboot.domain.documentstorage.presentation.DSRequest;
import vn.hoapm.springboot.domain.documentstorage.repository.IDSRepository;
import vn.hoapm.springboot.domain.documentstorage.service.impl.CreateDSUCImpl;
import vn.hoapm.springboot.domain.documentstorage.service.usecase.ICreateDSUC;


@Service
public class DSService {

    private final IDSRepository idsRepository;

    @Value("${file.upload-dir}")
    String uploadDir;

    public DSService(IDSRepository idsRepository){
        this.idsRepository = idsRepository;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public String createDS(DSRequest request, MultipartFile file) throws CommonException {
        ICreateDSUC iCreateDSUC = new CreateDSUCImpl(idsRepository, DSRequest.builder().uploadDir(uploadDir).build());
        return iCreateDSUC
                .applyData(request)
                .getFile(file)
                .retrieveFileInfo()
                .createDS()
                .fail()
                .sendResult();
    }
}
