package vn.hoapm.springbootV2.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @param <E> Entity
 * @param <D> DTO
 */
public abstract class CommonEntityMapper<E, D, I extends Serializable> {

    protected abstract Class<E> getEntityClass();

    public abstract D mapEntityToDTO(E entity);

    public List<D> mapEntitesToListDTO(List<E> entities) {
        return entities.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
    }

}
