package vn.hoapm.springbootV2.dao;

import vn.hoapm.springboot.application.exception.CommonException;

import java.io.Serializable;
import java.util.List;
/*
   GenericDao list the most common function which every Dao need to have
 */
public interface GenericDao<D, K extends Serializable> {
/*
       D: Object; K: parameter Type
 */

    List<D> getAll() throws CommonException;

    List<D> getAll(int offset, int limit) throws CommonException;

    D getById(K id) throws CommonException;


}
