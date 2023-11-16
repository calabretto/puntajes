package imb.lh.puntajes.service;

import java.util.List;

import imb.lh.puntajes.entity.Periodo;



public interface IPeriodo {
	public List<Periodo> buscarTodos();
	public Periodo buscarPorId(Integer id);
    public Periodo guardar(Periodo periodo);
    public void eliminar(Integer id);
    public boolean existe (Integer id);
}
