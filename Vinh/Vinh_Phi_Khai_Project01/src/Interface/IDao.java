/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.List;

/**
 *
 * @author Quang Phi
 * @param <Model>
 * @param <Object>
 */
public interface IDao<Model, Object> {

    List<Model> getAll();

    boolean insert(Model model);

    boolean update(Model model);

    boolean delete(Object object);

    List<Model> findModel(Object object);
}
