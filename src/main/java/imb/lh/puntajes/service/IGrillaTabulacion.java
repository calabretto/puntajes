package imb.lh.puntajes.service;

import java.util.List;

import imb.lh.puntajes.entity.GrillaTabulacion;




public interface IGrillaTabulacion {
    List<GrillaTabulacion> buscarTodos();
    GrillaTabulacion buscarPorId(Integer id);
    GrillaTabulacion guardar(GrillaTabulacion grillaTabulacion);
    List<GrillaTabulacion> buscarPorIds(Integer aplicante, Integer materia, Integer periodo);
    List<GrillaTabulacion> buscarPorMateriaYPeriodo(Integer materia, Integer periodo);
    void eliminar(Integer id);
    boolean existe(Integer id);
}
