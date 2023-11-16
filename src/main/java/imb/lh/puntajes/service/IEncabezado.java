package imb.lh.puntajes.service;

import java.util.List;

import imb.lh.puntajes.entity.Encabezado;



public interface IEncabezado {
	public List<Encabezado> buscarTodos();
	public Encabezado buscarPorId(Integer id);
    public Encabezado guardar(Encabezado encabezado);
    public void eliminar(Integer id);
    public boolean existe (Integer id);
}
