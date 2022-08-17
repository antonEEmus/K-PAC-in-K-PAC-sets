package core.basesyntax.component.dtomapper;

public interface ResponseDtoMapper<D, M> {
    D toDto(M model);
}
