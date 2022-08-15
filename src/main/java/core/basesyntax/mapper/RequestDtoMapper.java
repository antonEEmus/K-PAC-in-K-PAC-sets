package core.basesyntax.mapper;

public interface RequestDtoMapper<D, M> {
    M toModel(D dto);
}
