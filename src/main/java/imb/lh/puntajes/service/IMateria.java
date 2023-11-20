package imb.lh.puntajes.service;

import java.util.List;

import imb.lh.puntajes.entity.Materia;



public interface IMateria {
	public List<Materia> buscarTodos();
	public Materia buscarPorId(Integer id);
    public Materia guardar(Materia materia);
    public void eliminar(Integer id);
    public boolean existe (Integer id);
    List<Materia> buscarPorCarreraId(Integer carreraId);
    
}
