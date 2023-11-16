package imb.lh.puntajes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
public class GrillaTabulacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="item_id")
	private Item item;
	@ManyToOne
	@JoinColumn(name="aplicante_id")
	private Aplicante aplicante;
	@ManyToOne
	@JoinColumn(name="materia_id")
	private Materia materia;
	@ManyToOne
	@JoinColumn(name="periodo_id")
	private Periodo periodo;
	
	
	private Integer puntaje;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}


	public Aplicante getAplicante() {
		return aplicante;
	}


	public void setAplicante(Aplicante aplicante) {
		this.aplicante = aplicante;
	}


	public Materia getMateria() {
		return materia;
	}


	public void setMateria(Materia materia) {
		this.materia = materia;
	}


	public Periodo getPeriodo() {
		return periodo;
	}


	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}


	public Integer getPuntaje() {
		return puntaje;
	}


	public void setPuntaje(Integer puntaje) {
		this.puntaje = puntaje;
	}
	
	

}
