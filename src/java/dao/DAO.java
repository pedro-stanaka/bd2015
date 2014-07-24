package dao;

import java.util.List;

public interface DAO<T> {
    
    public void create(T t);
    public T read(Integer id);
    public void update(T t);
    public void delete(Integer id);

    public List<T> all();
}