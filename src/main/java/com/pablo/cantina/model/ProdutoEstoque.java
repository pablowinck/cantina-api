package com.pablo.cantina.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(min = 1, max = 250, message = "Código de barras tem que ter de 1 a 250 caracteres.")
    @NotBlank(message = "Código de barras não pode estar vázio")
    private String cod_barras;

    @NotNull
    private int qnt;

    @ManyToOne
    private Produto produto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDateTime data_cadastro,
            data_venda,
            data_validade;

    public boolean vendido() {
        return data_venda != null;
    }

    public boolean isValid(){
        return !vendido() && data_validade.compareTo(LocalDateTime.now()) > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoEstoque that = (ProdutoEstoque) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
