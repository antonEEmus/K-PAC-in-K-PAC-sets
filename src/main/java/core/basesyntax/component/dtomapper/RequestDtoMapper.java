package core.basesyntax.component.dtomapper;

public interface RequestDtoMapper<D, M> {
    M toModel(D dto);
}
