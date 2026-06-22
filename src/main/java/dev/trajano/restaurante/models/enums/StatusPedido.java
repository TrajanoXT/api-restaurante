package dev.trajano.restaurante.models.enums;

public enum StatusPedido {
    ABERTO,
    EM_PREPARO,
    PRONTO,
    ENTREGUE,
    CANCELADO;

    public boolean podeTracionarPara(StatusPedido novo){
        return switch (this){
            case ABERTO     -> novo == EM_PREPARO   || novo == CANCELADO;
            case EM_PREPARO -> novo == PRONTO       || novo == CANCELADO;
            case PRONTO     -> novo == ENTREGUE;
            default         -> false;
        };
    }
}
