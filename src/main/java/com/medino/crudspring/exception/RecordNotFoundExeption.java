package com.medino.crudspring.exception;

public class RecordNotFoundExeption  extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public RecordNotFoundExeption(Long id) {
        super("Registro não encontrado com o id: " + id);
    }   
}
