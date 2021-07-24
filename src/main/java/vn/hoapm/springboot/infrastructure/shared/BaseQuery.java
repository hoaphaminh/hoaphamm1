package vn.hoapm.springboot.infrastructure.shared;

public interface BaseQuery {

    String buildSQL();

    void declareParameters();
}
