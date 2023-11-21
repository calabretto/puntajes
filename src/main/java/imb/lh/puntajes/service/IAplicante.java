package imb.lh.puntajes.service;

import java.util.List;

import imb.lh.puntajes.entity.Aplicante;



public interface IAplicante {
	public List<Aplicante> buscarTodos();
	public Aplicante buscarPorId(Integer id);
	public Aplicante buscarPorDNI(Integer dni);
    public Aplicante guardar(Aplicante aplicante);
    public void eliminar(Integer id);
    public boolean existe (Integer id);
}
