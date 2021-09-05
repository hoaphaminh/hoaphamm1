package vn.hoapm.springbootV2.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import vn.hoapm.springboot.application.exception.CommonException;
import vn.hoapm.springbootV2.dao.DaoFactory;

public abstract class AbstractBasedService {
    protected Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    protected DaoFactory daoFactory;

    protected CommonException handleException(Exception e) {
        LOGGER.error("Handle exeption from service layser", e);
        return new CommonException(e);
    }
}
