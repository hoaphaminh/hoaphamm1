package vn.hoapm.springboot.domain.account.service.usecase;

import vn.hoapm.springboot.application.rest.user.UserJSONResponse;

import java.io.IOException;
import java.util.List;

public interface IUserImportUC {

    IUserImportUC getUploadDir(String uploadDir);

    IUserImportUC convertDataInFileToList() throws IOException;

    IUserImportUC insertBatchUsers();

    IUserImportUC insertBatchUserRoles();

    IUserImportUC fail();

    List<UserJSONResponse> result();
}
