package imb.lh.puntajes.service;

import java.util.List;

import imb.lh.puntajes.entity.Carrera;



public interface ICarrera {
	public List<Carrera> buscarTodos();
	public Carrera buscarPorId(Integer id);
    public Carrera guardar(Carrera carrera);
    public void eliminar(Integer id);
    public boolean existe (Integer id);
}
