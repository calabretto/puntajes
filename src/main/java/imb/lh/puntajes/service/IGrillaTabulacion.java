package imb.lh.puntajes.service;

import java.util.List;

import imb.lh.puntajes.entity.GrillaTabulacion;



public interface IGrillaTabulacion {
	public List<GrillaTabulacion> buscarTodos();
	public GrillaTabulacion buscarPorId(Integer id);
    public GrillaTabulacion guardar(GrillaTabulacion grillaTabulacion);
    public void eliminar(Integer id);
    public boolean existe (Integer id);
}
