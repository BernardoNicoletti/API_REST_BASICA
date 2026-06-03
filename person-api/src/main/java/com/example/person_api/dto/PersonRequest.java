package com.example.person_api.dto;
import com.example.person_api.model.Gender;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PersonRequest {
    @NotBlank private String nome;
    @NotBlank private String sobrenome;
    @NotNull @Min(0) private Integer idade;
    @NotNull private Gender genero;
    
    public String getNome() {
        return nome; 
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Gender getGenero() {
        return genero;
    }

    public void setGenero(Gender genero) {
        this.genero = genero;
    }
}