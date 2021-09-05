package vn.hoapm.springbootV2.dao;

/**
 * Interface defines methods to be implemented by any DaoFactory
 *
 * @author hoapham
 */
public interface DaoFactory {
    UserInfoDao getUserInfoDao();
}
