package vn.hoapm.springboot.domain.account.service.impl;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.hoapm.springboot.application.rest.user.UserJSONResponse;
import vn.hoapm.springboot.domain.account.factory.UserCUD;
import vn.hoapm.springboot.domain.account.repository.UserRepository;
import vn.hoapm.springboot.domain.account.service.usecase.IUserImportUC;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserImportImpl implements IUserImportUC {
    private static final String USER_ROLE = "USER";
    private final UserRepository repository;
    private String uploadDir;
    private List<UserCUD> importRequest;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserImportImpl(UserRepository repository) {
        this.repository = repository;
        this.importRequest = new ArrayList<>();
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public IUserImportUC getUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
        return this;
    }

    @Override
    public IUserImportUC convertDataInFileToList() throws IOException {
        File file = new File(this.uploadDir); //creating a new file instances
        FileInputStream fis = new FileInputStream(file); //obtaining bytes from the file
        XSSFWorkbook wb = new XSSFWorkbook(fis);//create a workbook instance that refer to .xlsx file
        XSSFSheet sheet = wb.getSheetAt(0); // create sheet object to retrieve object
        Iterator<Row> itr = sheet.iterator(); // iterating over excel file
        while (itr.hasNext()) {
            Row row = itr.next();
            String name = row.getCell(1).getStringCellValue();
            String phone = row.getCell(2).getStringCellValue();
            String email = row.getCell(3).getStringCellValue();
            String username = row.getCell(4).getStringCellValue();
            String password = row.getCell(5).getStringCellValue();
            String roleCode = USER_ROLE;
            UserCUD userCUD = UserCUD.builder()
                    .name(name)
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .roleCode(roleCode)
                    .phone(phone)
                    .email(email)
                    .build();
            importRequest.add(userCUD);
        }
        return this;
    }

    @Override
    public IUserImportUC insertBatchUsers() {
        repository.insertBatch(importRequest);
        return this;
    }

    @Override
    public IUserImportUC insertBatchUserRoles() {
        return this;
    }

    @Override
    public IUserImportUC fail() {
        return this;
    }

    @Override
    public List<UserJSONResponse> result() {
        return null;
    }
}
