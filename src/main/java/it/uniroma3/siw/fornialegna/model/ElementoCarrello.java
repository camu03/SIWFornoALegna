package it.uniroma3.siw.fornialegna.model;

import jakarta.persistence.*;

@Entity
public class ElementoCarrello {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Carrello carrello;

    @ManyToOne
    private Pizza pizza;       // solo uno dei tre
    @ManyToOne
    private Bibita bibita;     // sarà non-null
    @ManyToOne
    private Fritto fritto;     //

    @Column(nullable = false)
    private Integer quantita;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Carrello getCarrello() { return carrello; }
    public void setCarrello(Carrello carrello) { this.carrello = carrello; }
    public Pizza getPizza() { return pizza; }
    public void setPizza(Pizza pizza) { this.pizza = pizza; }
    public Bibita getBibita() { return bibita; }
    public void setBibita(Bibita bibita) { this.bibita = bibita; }
    public Fritto getFritto() { return fritto; }
    public void setFritto(Fritto fritto) { this.fritto = fritto; }
    public Integer getQuantita() { return quantita; }
    public void setQuantita(Integer quantita) { this.quantita = quantita; }
}
