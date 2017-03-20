package nagarciah.laliga.demo.dao;

import java.util.List;

import nagarciah.laliga.demo.model.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
	public void insert(User user);
	public void update(User user);
	public void delete(int id);
	public User getById(int id);
	public List<User> getAll();
}
