package imb.lh.puntajes.service;

import java.util.List;

import imb.lh.puntajes.entity.Item;



public interface IItem {
	public List<Item> buscarTodos();
	public Item buscarPorId(Integer id);
    public Item guardar(Item item);
    public void eliminar(Integer id);
    public boolean existe (Integer id);
}
