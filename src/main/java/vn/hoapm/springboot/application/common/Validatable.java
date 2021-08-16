package vn.hoapm.springboot.application.common;

import vn.hoapm.springboot.application.exception.CommonException;

@FunctionalInterface
public interface Validatable {
    void validate() throws CommonException;
}
