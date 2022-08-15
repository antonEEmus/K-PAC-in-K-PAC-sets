package core.basesyntax.mapper;

public interface ResponseDtoMapper<D, M> {
    D toDto(M model);
}
