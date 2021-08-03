package vn.hoapm.springboot.domain.documentstorage.repository;

import vn.hoapm.springboot.domain.documentstorage.presentation.DSRequest;

public interface IDSRepository {
    long createDS(DSRequest request);
}
