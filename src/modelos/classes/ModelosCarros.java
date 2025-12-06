/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package modelos.classes;

/**
 *
 * @author artur
 */
public enum ModelosCarros {
    SELECIONE("Selecione:"),
    GOL("Gol"),
    ONIX("Onix"),
    COROLLA("Corolla"),
    CIVIC("Civic"),
    UNO("Uno"),
    HILUX("Hilux");

    private String nome;

    private ModelosCarros(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

}
